package com.ymit.module.system.service.area;

import com.ymit.framework.common.exception.util.ServiceExceptionUtil;
import com.ymit.framework.common.util.object.BeanUtils;
import com.ymit.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ymit.module.infra.api.file.FileApi;
import com.ymit.module.system.controller.admin.area.vo.AreaQueryReqVO;
import com.ymit.module.system.controller.admin.area.vo.AreaRespSimpleVO;
import com.ymit.module.system.controller.admin.area.vo.AreaRespTreeVO;
import com.ymit.module.system.controller.admin.area.vo.AreaSaveReqVO;
import com.ymit.module.system.dal.dataobject.area.AreaDO;
import com.ymit.module.system.dal.mysql.area.AreaMapper;
import com.ymit.module.system.enums.ErrorCodeConstants;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 行政区划 Service 实现类
 *
 * @author 超级管理员
 */
@Service
@Validated
public class AreaServiceImpl implements AreaService {

    @Resource
    private AreaMapper areaMapper;

    @Resource
    private FileApi fileApi;

    /**
     * 设置上级数据信息到数据对象中
     *
     * @param data 数据对象
     * @return 设置后的数据
     */
    @Override
    public AreaRespTreeVO setParents(AreaRespTreeVO data) {
        if (data == null) {
            return null;
        }
        List<AreaRespTreeVO> datas = new ArrayList<>();
        datas.add(data);
        this.setParents(datas);
        return data;
    }

    /**
     * 批量设置上级数据信息到数据对象中
     *
     * @param datas 批量数据对象
     * @return 设置后的数据
     */
    @Override
    public List<AreaRespTreeVO> setParents(List<AreaRespTreeVO> datas) {
        if (datas == null || datas.isEmpty()) {
            return datas;
        }
        HashMap<Long, long[]> map = new HashMap<>();
        HashSet<Long> parentIds = new HashSet<>();
        List<AreaDO> parents = null;
        List<AreaRespSimpleVO> tempSelfParents;
        for (AreaRespTreeVO data : datas) {
            if (data.getParentId() == null || data.getParentId().equals(0L)) {
                continue;
            }
            parentIds.add(data.getParentId());
            //判断是否需要设置parents
            if (StringUtils.isNotBlank(data.getLayerList())) {
                String id = data.getId().toString();
                String[] parts = StringUtils.split(data.getLayerList(), AreaDO.LAYER_SEPARATOR_CHARS);
                long[] ids = Arrays.stream(parts).filter(c -> StringUtils.isNotBlank(c) && !c.equals(id)).mapToLong(Long::valueOf).toArray();
                map.put(data.getId(), ids);
                for (long m : ids) {
                    parentIds.add(m);
                }
            }
        }
        if (!parentIds.isEmpty()) {
            AreaQueryReqVO query = new AreaQueryReqVO().setIds(parentIds);
            parents = this.areaMapper.selectList(query);
        }
        //开始组装parent相关
        for (AreaRespTreeVO data : datas) {
            for (int j = 0, k = parents != null ? parents.size() : 0; j < k; j++) {
                AreaDO parent = parents.get(j);
                if (parent.getId().equals(data.getParentId())) {
                    data.setParent(BeanUtils.toBean(parent, AreaRespSimpleVO.class));
                    data.setParentName(parent.getName());
                }
                //设置parents的数据
                if (map.containsKey(data.getId())) {
                    long[] pids = map.get(data.getId());
                    if (data.getParents() == null) {
                        //首次设置时，为null，进行初始化
                        data.setParents(new ArrayList<>());
                        //占位用途，显式输出某位置的节点是否正常存在
                        for (int x = 0, y = pids.length; x < y; x++) {
                            data.getParents().add(null);
                        }
                    }
                    //将占位按顺序填充
                    for (int idx = 0, n = pids.length; idx < n; idx++) {
                        long pid = pids[idx];
                        Long id = parent.getId();
                        if (!id.equals(pid)) {
                            continue;
                        }
                        AreaRespSimpleVO bean = BeanUtils.toBean(parent, AreaRespSimpleVO.class);
                        data.getParents().set(idx, bean);
                    }
                }
            }
            tempSelfParents = data.getParents();
            for (int j = 0, k = tempSelfParents != null ? tempSelfParents.size() : 0; j < k; j++) {
                AreaRespSimpleVO tempParent = tempSelfParents.get(j);
                //此处避免中间节点数据丢失，按位置填充时，中间数据为null的问题
                if (tempParent == null) {
                    tempSelfParents.remove(j);
                    j -= 1;
                    k -= 1;
                    continue;
                }
                String parentNames = data.getParentNames();
                if (StringUtils.isBlank(parentNames)) {
                    data.setParentNames(tempParent.getName());
                } else {
                    parentNames = parentNames + "," + tempParent.getName();
                    data.setParentNames(parentNames);
                }
            }
        }
        return datas;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createArea(AreaSaveReqVO createReqVO) {
        // 校验父级区域ID的有效性
        this.validateParent(null, createReqVO.getParentId());
        // 校验名称的唯一性
        this.validateNameUnique(null, createReqVO.getParentId(), createReqVO.getName());

        // 插入
        AreaDO entity = BeanUtils.toBean(createReqVO, AreaDO.class);
        entity.setChildrenCount(0);
        //更新节点本身
        AreaDO parent = this.areaMapper.selectById(createReqVO.getParentId());
        if (parent != null) {
            entity.setLayer(parent.getLayer() + 1);
            entity.setLayerList(parent.getLayerList() + entity.getId() + AreaDO.LAYER_SEPARATOR_CHARS);
            entity.setParentCode(parent.getCode());
            parent.setChildrenCount(parent.getChildrenCount() + 1);
            this.areaMapper.updateById(parent);
        } else {
            entity.setParentId(AreaDO.PARENT_ID_ROOT);
            entity.setParentCode("");
            entity.setLayer(0);
            entity.setLayerList(AreaDO.LAYER_SEPARATOR_CHARS + entity.getId() + AreaDO.LAYER_SEPARATOR_CHARS);
        }
        this.areaMapper.insert(entity);
        //附件相关
        this.fileApi.batchSaveFileRel(entity.getSelfKind(), entity.getId(), createReqVO.getFileRelations());
        // 返回
        return entity.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateArea(AreaSaveReqVO updateReqVO) {
        // 校验存在
        AreaDO oldEntity = this.validateExists(updateReqVO.getId());
        // 校验父级区域ID的有效性
        this.validateParent(updateReqVO.getId(), updateReqVO.getParentId());
        // 校验区域名称的唯一性
        this.validateNameUnique(updateReqVO.getId(), updateReqVO.getParentId(), updateReqVO.getName());
        //附件相关
        this.fileApi.batchSaveFileRel(oldEntity.getSelfKind(), updateReqVO.getId(), updateReqVO.getFileRelations());
        // 更新
        AreaDO entity = BeanUtils.toBean(updateReqVO, AreaDO.class);
        entity.setParentId(AreaDO.PARENT_ID_ROOT);
        entity.setLayer(0);
        Long count = this.areaMapper.selectCountByParentId(entity.getId());
        entity.setChildrenCount(Math.toIntExact(count));
        entity.setLayerList(AreaDO.LAYER_SEPARATOR_CHARS + entity.getId() + AreaDO.LAYER_SEPARATOR_CHARS);
        if (updateReqVO.getParentId() != null) {
            AreaDO parent = this.areaMapper.selectById(updateReqVO.getParentId());
            if (parent != null) {
                entity.setParentId(parent.getId());
                entity.setParentCode(parent.getCode());
                entity.setLayer(parent.getLayer() + 1);
                entity.setLayerList(parent.getLayerList() + entity.getId() + AreaDO.LAYER_SEPARATOR_CHARS);
            }
        }
        this.areaMapper.updateById(entity);
        boolean parentIdChanged = !oldEntity.getParentId().equals(entity.getParentId());
        if (parentIdChanged) {
            //更新oldParent的childrenCount
            AreaDO oldParent = this.areaMapper.selectById(oldEntity.getParentId());
            if (oldParent != null) {
                count = this.areaMapper.selectCountByParentId(oldParent.getId());
                oldParent.setChildrenCount(Math.toIntExact(count));
                this.areaMapper.updateById(oldParent);
            }
            //更新新parent的childrenCount
            AreaDO parent = this.areaMapper.selectById(entity.getParentId());
            if (parent != null) {
                count = this.areaMapper.selectCountByParentId(parent.getId());
                parent.setChildrenCount(Math.toIntExact(count));
                this.areaMapper.updateById(parent);
            }
            //更新子节点的layerList
            List<AreaDO> children = this.areaMapper.selectList(new LambdaQueryWrapperX<AreaDO>().like(AreaDO::getLayerList, AreaDO.LAYER_SEPARATOR_CHARS + entity.getId() + AreaDO.LAYER_SEPARATOR_CHARS));
            if (CollectionUtils.isEmpty(children)) {
                return;
            }
            this.deepUpdateChildren(entity, children);
            this.areaMapper.updateBatch(children);
        }
    }

    /**
     * 递归更新子节点
     *
     * @param parent   父节点
     * @param children 要更新的子节点集合
     */
    private void deepUpdateChildren(AreaDO parent, List<AreaDO> children) {
        if (parent == null) {
            return;
        }
        if (CollectionUtils.isEmpty(children)) {
            return;
        }
        List<AreaDO> datas = children.stream().filter(c -> parent.getId().equals(c.getParentId())).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(datas)) {
            return;
        }
        for (AreaDO child : datas) {
            child.setLayer(parent.getLayer());
            child.setLayerList(parent.getLayerList() + child.getId() + AreaDO.LAYER_SEPARATOR_CHARS);
            this.deepUpdateChildren(child, children);
        }
    }

    @Override
    public void deleteArea(Collection<Long> ids) {
        // 校验存在
        if (CollectionUtils.isEmpty(ids)) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.AREA_NOT_EXISTS);
        }
        // 校验是否有子集
        if (this.areaMapper.existsAnyChildren(ids) > 0) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.AREA_EXITS_CHILDREN);
        }
        //获取要删除的数据
        List<AreaDO> delDatas = this.areaMapper.selectList(new AreaQueryReqVO().setIds(ids));
        // 其他检查条件 比如不允许删除，存在关联等
        // 删除
        this.areaMapper.deleteBatchIds(ids);
        //跟新parent的childrenCount
        List<Long> pids = delDatas.stream().map(AreaDO::getParentId).toList();
        List<AreaDO> parents = this.areaMapper.selectList(new AreaQueryReqVO().setIds(pids));
        if (CollectionUtils.isNotEmpty(parents)) {
            for (var item : parents) {
                item.setChildrenCount(Math.toIntExact(this.areaMapper.selectCountByParentId(item.getId())));
            }
            this.areaMapper.updateBatch(parents);
        }
    }

    private AreaDO validateExists(Long id) {
        AreaDO entity = this.areaMapper.selectById(id);
        if (entity == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.AREA_NOT_EXISTS);
        }
        return entity;
    }

    private void validateParent(Long id, Long parentId) {
        if (parentId == null || AreaDO.PARENT_ID_ROOT.equals(parentId)) {
            return;
        }
        // 1. 不能设置自己为父节点
        if (Objects.equals(id, parentId)) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.AREA_PARENT_ERROR);
        }
        // 2. 父节点不存在
        AreaDO parent = this.areaMapper.selectById(parentId);
        if (parent == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.AREA_PARENT_NOT_EXITS);
        }
        // 3. 递归校验父节点，如果父节点是自己的子节点，则报错，避免形成环路
        if (id == null) { // id 为空，说明新增，不需要考虑环路
            return;
        }
        List<AreaDO> children = this.areaMapper.selectList(new LambdaQueryWrapperX<AreaDO>().like(AreaDO::getLayerList, AreaDO.LAYER_SEPARATOR_CHARS + id + AreaDO.LAYER_SEPARATOR_CHARS));
        List<Long> childrenIds = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(children)) {
            childrenIds = children.stream().map(AreaDO::getId).collect(Collectors.toList());
        }
        for (int i = 0; i < Short.MAX_VALUE; i++) {
            // 3.1 校验环路
            parentId = parent.getParentId();
            if (Objects.equals(id, parentId)) {
                throw ServiceExceptionUtil.exception(ErrorCodeConstants.AREA_PARENT_IS_CHILD);
            }
            if (childrenIds.contains(parentId)) {
                throw ServiceExceptionUtil.exception(ErrorCodeConstants.AREA_PARENT_IS_CHILD);
            }
            // 3.2 继续递归上一级
            if (parentId == null || AreaDO.PARENT_ID_ROOT.equals(parentId)) {
                break;
            }
            parent = this.areaMapper.selectById(parentId);
            if (parent == null) {
                break;
            }
        }
    }

    private void validateNameUnique(Long id, Long parentId, String name) {
        AreaDO area = this.areaMapper.selectByParentIdAndName(parentId, name);
        if (area == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的行政区划
        if (id == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.AREA_NAME_DUPLICATE);
        }
        if (!Objects.equals(area.getId(), id)) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.AREA_NAME_DUPLICATE);
        }
    }

    @Override
    public AreaDO getArea(Long id) {
        return this.areaMapper.selectById(id);
    }

    @Override
    public List<AreaRespTreeVO> getAreaBackfill(Long id) {
        AreaDO area = this.validateExists(id);
        String[] parts = StringUtils.split(area.getLayerList(), AreaDO.LAYER_SEPARATOR_CHARS);
        List<Long> parentIds = Arrays.stream(parts).filter(c -> StringUtils.isNotBlank(c) && !c.equals(id.toString())).map(Long::valueOf).collect(Collectors.toList());
        parentIds.add(AreaDO.PARENT_ID_ROOT);
        List<AreaDO> temp = this.getListBytParentIds(parentIds);
        List<AreaRespTreeVO> all = BeanUtils.toBean(temp, AreaRespTreeVO.class);
        return this.addChildrenToItem(AreaDO.PARENT_ID_ROOT, all);
    }

    private List<AreaRespTreeVO> addChildrenToItem(Long parentId, List<AreaRespTreeVO> childrenAll) {
        List<AreaRespTreeVO> children = new ArrayList<>();
        //将确定的item从all中移除，避免过多无效的循环
        for (int i = 0, s = childrenAll == null ? 0 : childrenAll.size(); i < s; i++) {
            AreaRespTreeVO item = childrenAll.get(i);
            if (!item.getParentId().equals(parentId)) {
                continue;
            }
            children.add(item);
            childrenAll.remove(i);
            i--;
            s--;
        }
        for (AreaRespTreeVO item : children) {
            item.setChildren(this.addChildrenToItem(item.getId(), childrenAll));
        }
        return children;
    }

    @Override
    public List<AreaDO> getAreaList(AreaQueryReqVO listReqVO) {
        return this.areaMapper.selectList(listReqVO);
    }

    @Override
    public List<AreaDO> getListBytParentIds(Collection<Long> parentIds) {
        return this.areaMapper.selectListBytParentIds(parentIds);
    }


}