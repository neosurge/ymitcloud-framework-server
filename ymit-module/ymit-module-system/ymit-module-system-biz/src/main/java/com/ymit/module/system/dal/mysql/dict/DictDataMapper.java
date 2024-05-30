package com.ymit.module.system.dal.mysql.dict;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ymit.framework.common.data.PageResult;
import com.ymit.framework.mybatis.core.mapper.BaseMapperX;
import com.ymit.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ymit.module.system.controller.admin.dict.vo.data.DictDataPageReqVO;
import com.ymit.module.system.dal.dataobject.dict.DictDataDO;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface DictDataMapper extends BaseMapperX<DictDataDO> {

    default DictDataDO selectByDictTypeAndValue(String dictType, String value) {
        return selectOne(DictDataDO::getDictType, dictType, DictDataDO::getValue, value);
    }

    default DictDataDO selectByDictTypeAndLabel(String dictType, String label) {
        return selectOne(DictDataDO::getDictType, dictType, DictDataDO::getLabel, label);
    }

    default List<DictDataDO> selectByDictTypeAndValues(String dictType, Collection<String> values) {
        return selectList(new LambdaQueryWrapper<DictDataDO>().eq(DictDataDO::getDictType, dictType).in(DictDataDO::getValue, values));
    }

    default long selectCountByDictType(String dictType) {
        return selectCount(DictDataDO::getDictType, dictType);
    }

    default PageResult<DictDataDO> selectPage(DictDataPageReqVO reqVO) {
        LambdaQueryWrapperX<DictDataDO> queryWrapper = new LambdaQueryWrapperX<DictDataDO>()
                .likeIfPresent(DictDataDO::getLabel, reqVO.getLabel())
                .eqIfPresent(DictDataDO::getStatus, reqVO.getStatus());
        if (!StringUtils.isAnyBlank(reqVO.getLabel(), reqVO.getDictType())) {
            queryWrapper.and(x -> x.eq(DictDataDO::getDictType, reqVO.getDictType()).or().eq(DictDataDO::getDictType, reqVO.getLabel()));
        } else {
            if (StringUtils.isNotBlank(reqVO.getLabel())) {
                queryWrapper.or(x -> x.eq(DictDataDO::getDictType, reqVO.getLabel()));
            }
            if (StringUtils.isNotBlank(reqVO.getDictType())) {
                queryWrapper.or(x -> x.eq(DictDataDO::getDictType, reqVO.getDictType()));
            }
        }
        queryWrapper.orderByAsc(DictDataDO::getDictType);
        queryWrapper.orderByDesc(DictDataDO::getSort);
        return selectPage(reqVO, queryWrapper);
    }

    default List<DictDataDO> selectListByStatusAndDictType(Integer status, String dictType) {
        return selectList(new LambdaQueryWrapperX<DictDataDO>().eqIfPresent(DictDataDO::getStatus, status).eqIfPresent(DictDataDO::getDictType, dictType));
    }

}
