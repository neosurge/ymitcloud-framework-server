package com.ymitcloud.module.promotion.controller.admin.diy.vo.page;


import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.ymitcloud.framework.common.pojo.PageParam;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/** 
 * 管理后台 - 装修页面分页 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DiyPagePageReqVO extends PageParam {


    /** 
     * 页面名称
     */
    private String name;

    /** 
     * 创建时间
     */

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
