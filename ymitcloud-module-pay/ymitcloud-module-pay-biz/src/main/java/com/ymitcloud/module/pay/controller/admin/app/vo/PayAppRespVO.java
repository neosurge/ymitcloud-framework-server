package com.ymitcloud.module.pay.controller.admin.app.vo;



import lombok.*;

import java.time.LocalDateTime;


/** 管理后台 - 支付应用信息 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PayAppRespVO extends PayAppBaseVO {


    /** 应用编号*/
    private Long id;

    /** 创建时间*/

    private LocalDateTime createTime;

}
