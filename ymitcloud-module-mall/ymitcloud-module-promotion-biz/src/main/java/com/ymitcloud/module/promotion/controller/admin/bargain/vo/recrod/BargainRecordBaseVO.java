package com.ymitcloud.module.promotion.controller.admin.bargain.vo.recrod;


import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


/**
 * 砍价记录 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class BargainRecordBaseVO {


    /** 
     * 砍价活动名称
     */
    @NotNull(message = "砍价活动名称不能为空")
    private Long activityId;

    /** 
     * 用户编号
     */
    @NotNull(message = "用户编号不能为空")
    private Long userId;

    /** 
     * 商品 SPU 编号
     */
    @NotNull(message = "商品 SPU 编号不能为空")
    private Long spuId;

    /** 
     * 商品 SKU 编号
     */
    @NotNull(message = "商品 SKU 编号不能为空")
    private Long skuId;

    /** 
     * 砍价起始价格，单位：分
     */
    @NotNull(message = "砍价起始价格，单位：分不能为空")
    private Integer bargainFirstPrice;

    /** 
     * 当前砍价，单位：分
     */
    @NotNull(message = "当前砍价，单位：分不能为空")
    private Integer bargainPrice;

    /** 
     * 砍价状态
     */
    @NotNull(message = "砍价状态不能为空")
    private Integer status;

    /** 
     * 订单编号
     */
    private Long orderId;

    /** 
     * 结束时间
     */

    @NotNull(message = "结束时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime endTime;

}
