package com.ymit.module.system.dal.mysql.dict;

import com.ymit.framework.common.data.PageResult;
import com.ymit.framework.mybatis.core.mapper.BaseMapperX;
import com.ymit.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ymit.module.system.controller.admin.dict.vo.type.DictTypePageReqVO;
import com.ymit.module.system.dal.dataobject.dict.DictTypeDO;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;

@Mapper
public interface DictTypeMapper extends BaseMapperX<DictTypeDO> {

    default PageResult<DictTypeDO> selectPage(DictTypePageReqVO reqVO) {
        LambdaQueryWrapperX<DictTypeDO> queryWrapper = new LambdaQueryWrapperX<DictTypeDO>()
                .likeIfPresent(DictTypeDO::getName, reqVO.getName())
                .eqIfPresent(DictTypeDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(DictTypeDO::getCreateTime, reqVO.getCreateTime());
        if (!StringUtils.isAnyBlank(reqVO.getName(), reqVO.getType())) {
            queryWrapper.and(x -> x.eq(DictTypeDO::getType, reqVO.getType()).or().like(DictTypeDO::getType, reqVO.getName()));
        } else {
            if (StringUtils.isNotBlank(reqVO.getName())) {
                queryWrapper.or(x -> x.like(DictTypeDO::getType, reqVO.getName()));
            }
            if (StringUtils.isNotBlank(reqVO.getType())) {
                queryWrapper.or(x -> x.eq(DictTypeDO::getType, reqVO.getType()));
            }
        }
        queryWrapper.orderByAsc(DictTypeDO::getType);
        return selectPage(reqVO, queryWrapper);
    }

    default DictTypeDO selectByType(String type) {
        return selectOne(DictTypeDO::getType, type);
    }

    default DictTypeDO selectByName(String name) {
        return selectOne(DictTypeDO::getName, name);
    }

    @Update("UPDATE system_dict_type SET deleted = 1, deleted_time = #{deletedTime} WHERE id = #{id}")
    void updateToDelete(@Param("id") Long id, @Param("deletedTime") LocalDateTime deletedTime);

}
