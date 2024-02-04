package com.ymitcloud.module.pay.controller.admin.notify.vo;




import lombok.Data;

import java.time.LocalDateTime;

/**
 * 回调通知 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class PayNotifyTaskBaseVO {


    /** 应用编号*/
    private Long appId;

    /** 通知类型*/
    private Byte type;

    /** 数据编号*/
    private Long dataId;

    /** 通知状态*/
    private Byte status;

    /** 商户订单编号*/
    private String merchantOrderId;

    /** 下一次通知时间*/
    private LocalDateTime nextNotifyTime;

    /** 最后一次执行时间*/
    private LocalDateTime lastExecuteTime;

    /** 当前通知次数*/
    private Byte notifyTimes;

    /** 最大可通知次数*/
    private Byte maxNotifyTimes;

    /** 异步通知地址*/

    private String notifyUrl;

}
