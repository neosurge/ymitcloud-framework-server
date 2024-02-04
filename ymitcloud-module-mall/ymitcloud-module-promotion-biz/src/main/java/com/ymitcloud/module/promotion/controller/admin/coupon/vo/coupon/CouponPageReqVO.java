package com.ymitcloud.module.promotion.controller.admin.coupon.vo.coupon;


import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;


import java.time.LocalDateTime;
import java.util.Collection;


import org.springframework.format.annotation.DateTimeFormat;

import com.ymitcloud.framework.common.pojo.PageParam;
import com.ymitcloud.framework.common.validation.InEnum;
import com.ymitcloud.module.promotion.enums.coupon.CouponStatusEnum;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 管理后台 - 优惠劵分页 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CouponPageReqVO extends PageParam {


    /**
     * 优惠劵模板编号
     */
    private Long templateId;

    /**
     * 优惠码状态
     */
    @InEnum(value = CouponStatusEnum.class, message = "优惠劵状态，必须是 {value}")
    private Integer status;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户编号
     */

    private Collection<Long> userIds;

}
