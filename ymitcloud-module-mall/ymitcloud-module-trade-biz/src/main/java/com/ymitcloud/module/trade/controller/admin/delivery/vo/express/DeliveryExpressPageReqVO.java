package com.ymitcloud.module.trade.controller.admin.delivery.vo.express;


import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.ymitcloud.framework.common.pojo.PageParam;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 管理后台 - 快递公司分页 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DeliveryExpressPageReqVO extends PageParam {

    
    /**
     * 快递公司编码
     */
    private String code;
    /**
     * 快递公司名称
     */
    private String name;

    /** 状态（0正常 1停用） */
    private Integer status;

    /**
     * 创建时间
     */

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
