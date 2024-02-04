package com.ymitcloud.module.pay.controller.admin.notify.vo;



import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/** 管理后台 - 回调通知 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PayNotifyTaskRespVO extends PayNotifyTaskBaseVO {


    /** 任务编号*/
    private Long id;

    /** 创建时间*/
    private LocalDateTime createTime;

    /** 
     * 应用名称
     */

    private String  appName;

}
