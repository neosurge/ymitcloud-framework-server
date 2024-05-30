package com.ymit.module.system.service.area;

import com.ymit.module.system.controller.admin.area.vo.AreaQueryReqVO;
import com.ymit.module.system.controller.admin.area.vo.AreaRespTreeVO;
import com.ymit.module.system.controller.admin.area.vo.AreaSaveReqVO;
import com.ymit.module.system.dal.dataobject.area.AreaDO;
import jakarta.validation.Valid;

import java.util.Collection;
import java.util.List;

/**
 * 行政区划 Service 接口
 *
 * @author 超级管理员
 */
public interface AreaService {
    /**
     * 设置上级数据信息到数据对象中
     *
     * @param data 数据对象
     * @return 设置后的数据
     */
    AreaRespTreeVO setParents(AreaRespTreeVO data);

    /**
     * 批量设置上级数据信息到数据对象中
     *
     * @param datas 批量数据对象
     * @return 设置后的数据
     */
    List<AreaRespTreeVO> setParents(List<AreaRespTreeVO> datas);

    /**
     * 创建行政区划
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createArea(@Valid AreaSaveReqVO createReqVO);

    /**
     * 更新行政区划
     *
     * @param updateReqVO 更新信息
     */
    void updateArea(@Valid AreaSaveReqVO updateReqVO);

    /**
     * 删除行政区划
     *
     * @param id 编号
     */
    void deleteArea(Collection<Long> id);

    /**
     * 获得行政区划
     *
     * @param id 编号
     * @return 行政区划
     */
    AreaDO getArea(Long id);

    /**
     * 树形结构数据回填
     *
     * @param id 选中的id
     */
    List<AreaRespTreeVO> getAreaBackfill(Long id);

    /**
     * 获得行政区划列表
     *
     * @param listReqVO 查询条件
     * @return 行政区划列表
     */
    List<AreaDO> getAreaList(AreaQueryReqVO listReqVO);

    /**
     * 根据上级id集合获取行政区划列表
     *
     * @param parentIds 上级id集合
     * @return 行政区划列表
     */
    List<AreaDO> getListBytParentIds(Collection<Long> parentIds);
}