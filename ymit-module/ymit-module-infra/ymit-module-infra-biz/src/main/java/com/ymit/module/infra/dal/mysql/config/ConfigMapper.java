package com.ymit.module.infra.dal.mysql.config;

import com.ymit.framework.common.data.PageResult;
import com.ymit.framework.mybatis.core.mapper.BaseMapperX;
import com.ymit.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ymit.module.infra.controller.admin.config.vo.ConfigPageReqVO;
import com.ymit.module.infra.dal.dataobject.config.ConfigDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConfigMapper extends BaseMapperX<ConfigDO> {

    default ConfigDO selectByKey(String key) {
        return this.selectOne(ConfigDO::getConfigKey, key);
    }

    default PageResult<ConfigDO> selectPage(ConfigPageReqVO reqVO) {
        return this.selectPage(reqVO, new LambdaQueryWrapperX<ConfigDO>()
                .likeIfPresent(ConfigDO::getName, reqVO.getName())
                .likeIfPresent(ConfigDO::getConfigKey, reqVO.getKey())
                .eqIfPresent(ConfigDO::getType, reqVO.getType())
                .betweenIfPresent(ConfigDO::getCreateTime, reqVO.getCreateTime()));
    }

}
