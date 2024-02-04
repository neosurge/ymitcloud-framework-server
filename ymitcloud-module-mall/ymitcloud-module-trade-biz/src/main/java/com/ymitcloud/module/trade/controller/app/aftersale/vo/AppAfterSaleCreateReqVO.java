package com.ymitcloud.module.trade.controller.app.aftersale.vo;


import java.util.List;

import com.ymitcloud.framework.common.validation.InEnum;
import com.ymitcloud.module.trade.enums.aftersale.AfterSaleWayEnum;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/** 
 * 用户 App - 交易售后创建 Request VO
 */
@Data
public class AppAfterSaleCreateReqVO {

    /** 订单项编号*/
    @NotNull(message = "订单项编号不能为空")
    private Long orderItemId;

    /** 售后方式*/

    @NotNull(message = "售后方式不能为空")
    @InEnum(value = AfterSaleWayEnum.class, message = "售后方式必须是 {value}")
    private Integer way;


    /** 退款金额*/

    @NotNull(message = "退款金额不能为空")
    @Min(value = 1, message = "退款金额必须大于 0")
    private Integer refundPrice;


    /** 申请原因*/
    @NotNull(message = "申请原因不能为空")
    private String applyReason;

    /**
     *  补充描述
     */
    private String applyDescription;

    /** 
     * 补充凭证图片
     */

    private List<String> applyPicUrls;

}
