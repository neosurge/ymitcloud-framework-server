package com.ymitcloud.module.pay.controller.admin.transfer.vo;



import lombok.Data;

/** 管理后台 - 发起转账 */
@Data
public class PayTransferCreateRespVO {

    /** 转账单编号*/
    private Long id;

    /** 转账状态*/ // 参见 PayTransferStatusEnum 枚举

    private Integer status;

}
