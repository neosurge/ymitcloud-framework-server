package com.ymitcloud.module.pay.dal.mysql.notify;

import com.ymitcloud.module.pay.dal.dataobject.notify.PayNotifyLogDO;
import com.ymitcloud.framework.mybatis.core.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PayNotifyLogMapper extends BaseMapperX<PayNotifyLogDO> {

    default List<PayNotifyLogDO> selectListByTaskId(Long taskId) {
        return selectList(PayNotifyLogDO::getTaskId, taskId);
    }

}
