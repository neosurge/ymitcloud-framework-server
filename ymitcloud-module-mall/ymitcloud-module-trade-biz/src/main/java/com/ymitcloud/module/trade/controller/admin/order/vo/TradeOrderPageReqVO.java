package com.ymitcloud.module.trade.controller.admin.order.vo;


import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;


import com.ymitcloud.framework.common.enums.TerminalEnum;
import com.ymitcloud.framework.common.pojo.PageParam;
import com.ymitcloud.framework.common.validation.InEnum;
import com.ymitcloud.framework.common.validation.Mobile;
import com.ymitcloud.module.trade.enums.order.TradeOrderStatusEnum;


import lombok.Data;

/** 
 * 管理后台 - 交易订单的分页 Request VO
 */
@Data
public class TradeOrderPageReqVO extends PageParam {

    /**
     * 订单号
     */
    private String no;

    /** 用户编号*/
    private Long userId;

    /** 用户昵称*/
    private String userNickname;

    /** 用户手机号*/
    @Mobile
    private String userMobile;

    /** 配送方式*/
    private Integer deliveryType;

    /** 发货物流公司编号*/
    private Long logisticsId;

    /** 自提门店编号*/
    private List<Long> pickUpStoreIds;

    /** 自提核销码*/
    private String pickUpVerifyCode;

    /** 订单类型*/
    private Integer type;

    /** 订单状态*/
    @InEnum(value = TradeOrderStatusEnum.class, message = "订单状态必须是 {value}")
    private Integer status;

    /** 支付渠道*/
    private String payChannelCode;

    /** 
     * 创建时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    /** 订单来源*/

    @InEnum(value = TerminalEnum.class, message = "订单来源 {value}")
    private Integer terminal;

}
