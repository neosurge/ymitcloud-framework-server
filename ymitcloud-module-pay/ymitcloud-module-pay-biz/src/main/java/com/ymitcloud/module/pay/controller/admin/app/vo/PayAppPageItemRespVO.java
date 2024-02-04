package com.ymitcloud.module.pay.controller.admin.app.vo;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;


/**
 * 管理后台 - 支付应用信息分页查询 Response VO,相比于支付信息，还会多出应用渠道的开关信息
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PayAppPageItemRespVO extends PayAppBaseVO {


    /** 应用编号 */
    private Long id;

    /** 创建时间 */
    private LocalDateTime createTime;

    /** 已配置的支付渠道编码 */

    private Set<String> channelCodes;

}
