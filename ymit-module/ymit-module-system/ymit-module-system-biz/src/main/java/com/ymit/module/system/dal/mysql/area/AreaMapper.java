package com.ymit.module.system.dal.mysql.area;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ymit.framework.mybatis.core.mapper.BaseMapperX;
import com.ymit.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ymit.module.system.controller.admin.area.vo.AreaQueryReqVO;
import com.ymit.module.system.dal.dataobject.area.AreaDO;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

/**
 * 行政区划 Mapper
 *
 * @author 超级管理员
 */
@Mapper
public interface AreaMapper extends BaseMapperX<AreaDO> {
    private LambdaQueryWrapperX<AreaDO> buildQueryWrapper(AreaQueryReqVO reqVO) {
        LambdaQueryWrapperX<AreaDO> queryWrapper = new LambdaQueryWrapperX<AreaDO>()
                .inIfPresent(AreaDO::getId, reqVO.getIds())
                .eqIfPresent(AreaDO::getCode, reqVO.getCode())
                .inIfPresent(AreaDO::getCode, reqVO.getCodes())
                .likeIfPresent(AreaDO::getName, reqVO.getName())
                .eqIfPresent(AreaDO::getLevel, reqVO.getLevel())
                .eqIfPresent(AreaDO::getParentCode, reqVO.getParentCode())
                .inIfPresent(AreaDO::getParentCode, reqVO.getParentCodes())
                .eqIfPresent(AreaDO::getParentId, reqVO.getParentId())
                .eqIfPresent(AreaDO::getLayer, reqVO.getLayer())
                .likeIfPresent(AreaDO::getLayerList, reqVO.getLayerList())
                .eqIfPresent(AreaDO::getChildrenCount, reqVO.getChildrenCount())
                .betweenIfPresent(AreaDO::getCreateTime, reqVO.getCreateTime());
        //仅传入关键词时，才进行条件追加
        if (StringUtils.isNotBlank(reqVO.getSearchWord())) {
            Consumer<LambdaQueryWrapper<AreaDO>> subLikeQuery = q -> q
                    .or(false)
                    //.or().like(AreaDO::getCode, reqVO.getSearchWord())
                    .or().like(AreaDO::getName, reqVO.getSearchWord())
                    //.or().like(AreaDO::getParentCode, reqVO.getSearchWord())
                    //.or().like(AreaDO::getLayerList, reqVO.getSearchWord())
                    //.or().like(AreaDO::getCreator, reqVO.getSearchWord())
                    //.or().like(AreaDO::getUpdater, reqVO.getSearchWord())
                    ;
            queryWrapper.and(subLikeQuery);
        }
        return queryWrapper;
    }

    default List<AreaDO> selectList(AreaQueryReqVO reqVO) {
        LambdaQueryWrapperX<AreaDO> queryWrapperX = this.buildQueryWrapper(reqVO);
        queryWrapperX.orderByAsc(AreaDO::getId);
        return this.selectList(queryWrapperX);
    }

    default Long existsAnyChildren(Collection<Long> parentIds) {
        return this.selectCount(new LambdaQueryWrapperX<AreaDO>().in(AreaDO::getParentId, parentIds));
    }

    default AreaDO selectByParentIdAndName(Long parentId, String name) {
        return this.selectOne(AreaDO::getParentId, parentId, AreaDO::getName, name);
    }

    default Long selectCountByParentId(Long parentId) {
        return this.selectCount(AreaDO::getParentId, parentId);
    }

    default List<AreaDO> selectListBytParentId(Long parentId) {
        return this.selectList(AreaDO::getParentId, parentId);
    }

    default List<AreaDO> selectListBytParentIds(Collection<Long> parentIds) {
        LambdaQueryWrapperX<AreaDO> queryWrapperX = new LambdaQueryWrapperX<AreaDO>().in(AreaDO::getParentId, parentIds);
        queryWrapperX.orderByAsc(AreaDO::getId);
        return this.selectList(queryWrapperX);
    }
}