package com.ymitcloud.module.promotion.dal.mysql.decorate;

import com.ymitcloud.framework.mybatis.core.mapper.BaseMapperX;
import com.ymitcloud.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ymitcloud.module.promotion.dal.dataobject.decorate.DecorateComponentDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DecorateComponentMapper extends BaseMapperX<DecorateComponentDO> {

    default List<DecorateComponentDO> selectListByPageAndStatus(Integer page, Integer status) {
        return selectList(new LambdaQueryWrapperX<DecorateComponentDO>()
                        .eq(DecorateComponentDO::getPage, page)
                        .eqIfPresent(DecorateComponentDO::getStatus, status));
    }

    default DecorateComponentDO selectByPageAndCode(Integer page, String code) {
        return selectOne(DecorateComponentDO::getPage, page,
                DecorateComponentDO::getCode, code);
    }

}




