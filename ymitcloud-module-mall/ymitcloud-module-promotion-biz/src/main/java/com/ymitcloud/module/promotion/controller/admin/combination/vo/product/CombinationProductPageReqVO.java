package com.ymitcloud.module.promotion.controller.admin.combination.vo.product;


import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.ymitcloud.framework.common.pojo.PageParam;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/** 
 * 管理后台 - 拼团商品分页 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CombinationProductPageReqVO extends PageParam {


    /** 
     * 拼团活动编号
     */
    private Long activityId;

    /** 
     * 商品 SPU 编号
     */
    private Long spuId;

    /** 
     * 商品 SKU 编号
     */
    private Long skuId;

    /** 
     * 拼团商品状态
     */
    private Integer activityStatus;

    /** 
     * 活动开始时间点
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] activityStartTime;

    /** 
     * 活动结束时间点
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] activityEndTime;

    /** 
     * 拼团价格，单位分
     */
    private Integer activePrice;

    /** 
     * 创建时间
     */

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
