package com.ymitcloud.module.trade.controller.admin.delivery.vo.pickup;


import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.ymitcloud.framework.common.enums.CommonStatusEnum;
import com.ymitcloud.framework.common.pojo.PageParam;
import com.ymitcloud.framework.common.validation.InEnum;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 管理后台 - 自提门店分页 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DeliveryPickUpStorePageReqVO extends PageParam {


    /** 门店名称 */
    private String name;

    /**
     * 门店手机
     */
    private String phone;

    /** 区域编号 */
    private Integer areaId;

    /** 门店状态 */
    @InEnum(CommonStatusEnum.class)
    private Integer status;

    /**
     * 创建时间
     */

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
