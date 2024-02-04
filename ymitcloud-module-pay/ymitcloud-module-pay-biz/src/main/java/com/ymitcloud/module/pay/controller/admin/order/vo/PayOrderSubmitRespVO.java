package com.ymitcloud.module.pay.controller.admin.order.vo;



import lombok.Data;

/** 管理后台 - 支付订单提交 */
@Data
public class PayOrderSubmitRespVO {

    /** 支付状态*/ // 参见 PayOrderStatusEnum 枚举
    private Integer status;

    /** 展示模式*/ // 参见 PayDisplayModeEnum 枚举
    private String displayMode;
    /** 展示内容*/

    private String displayContent;

}
