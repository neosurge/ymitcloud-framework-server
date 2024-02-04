package com.ymitcloud.module.trade.controller.app.brokerage.vo.record;

import com.ymitcloud.framework.common.pojo.PageParam;
import com.ymitcloud.framework.common.validation.InEnum;
import com.ymitcloud.module.trade.enums.brokerage.BrokerageRecordBizTypeEnum;
import com.ymitcloud.module.trade.enums.brokerage.BrokerageRecordStatusEnum;


import lombok.Data;

/** 
 * 应用 App - 分销记录分页 Request VO
 */
@Data
public class AppBrokerageRecordPageReqVO extends PageParam {

    /** 业务类型*/
    @InEnum(value = BrokerageRecordBizTypeEnum.class, message = "业务类型必须是 {value}")
    private Integer bizType;

    /** 状态*/

    @InEnum(value = BrokerageRecordStatusEnum.class, message = "状态必须是 {value}")
    private Integer status;

}
