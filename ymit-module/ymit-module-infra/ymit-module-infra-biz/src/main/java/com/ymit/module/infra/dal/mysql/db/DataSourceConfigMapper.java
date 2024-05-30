package com.ymit.module.infra.dal.mysql.db;

import com.ymit.framework.mybatis.core.mapper.BaseMapperX;
import com.ymit.module.infra.dal.dataobject.db.DataSourceConfigDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据源配置 Mapper
 *
 * @author 云码源码
 */
@Mapper
public interface DataSourceConfigMapper extends BaseMapperX<DataSourceConfigDO> {
}
