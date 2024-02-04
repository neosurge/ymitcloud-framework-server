package com.ymitcloud.module.trade.controller.app.brokerage.vo.record;




import lombok.Data;

import java.time.LocalDateTime;


/** 用户 App - 分销记录 */
@Data
public class AppBrokerageRecordRespVO {

    /** 记录编号*/
    private Long id;

    /** 分销标题*/
    private String title;

    /** 分销金额，单位：分*/
    private Integer price;

    /** 创建时间*/
    private LocalDateTime createTime;

    /** 完成时间*/

    private LocalDateTime finishTime;

}
