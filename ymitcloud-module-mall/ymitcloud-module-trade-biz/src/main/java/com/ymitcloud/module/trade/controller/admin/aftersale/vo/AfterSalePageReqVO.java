package com.ymitcloud.module.trade.controller.admin.aftersale.vo;

import com.ymitcloud.framework.common.pojo.PageParam;
import com.ymitcloud.framework.common.validation.InEnum;
import com.ymitcloud.module.trade.enums.aftersale.AfterSaleStatusEnum;
import com.ymitcloud.module.trade.enums.aftersale.AfterSaleTypeEnum;
import com.ymitcloud.module.trade.enums.aftersale.AfterSaleWayEnum;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;


/**
 * 管理后台 - 交易售后分页 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AfterSalePageReqVO extends PageParam {


    /** 售后流水号 */
    private String no;

    /** 售后状态 */
    @InEnum(value = AfterSaleStatusEnum.class, message = "售后状态必须是 {value}")
    private Integer status;

    /** 售后类型 */
    @InEnum(value = AfterSaleTypeEnum.class, message = "售后类型必须是 {value}")
    private Integer type;

    /** 售后方式 */
    @InEnum(value = AfterSaleWayEnum.class, message = "售后方式必须是 {value}")
    private Integer way;

    /** 订单编号 */
    private String orderNo;

    /** 商品 SPU 名称 */
    private String spuName;

    /**
     * 创建时间
     */

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
