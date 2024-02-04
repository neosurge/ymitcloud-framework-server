package com.ymitcloud.module.promotion.controller.admin.bargain.vo.activity;


import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


/**
 * 砍价活动 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 *
 * @author HUIHUI
 */
@Data
public class BargainActivityBaseVO {


    /** 
     * 砍价活动名称
     */
    @NotNull(message = "砍价名称不能为空")
    private String name;

    /** 
     * 商品 SPU 编号
     */
    @NotNull(message = "砍价商品不能为空")
    private Long spuId;

    /** 
     * 商品 skuId
     */
    @NotNull(message = "商品 skuId 不能为空")
    private Long skuId;

    /** 
     * 砍价起始价格
     */
    @NotNull(message = "砍价起始价格不能为空")
    private Integer bargainFirstPrice;

    /** 
     * 砍价底价
     */
    @NotNull(message = "砍价底价不能为空")
    private Integer bargainMinPrice;

    /** 
     * 活动库存
     */
    @NotNull(message = "活动库存不能为空")
    private Integer stock;

    /** 
     * 总限购数量
     */
    @NotNull(message = "总限购数量不能为空")
    private Integer totalLimitCount;

    /** 
     * 活动开始时间
     */

    @NotNull(message = "活动开始时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime startTime;


    /** 
     * 活动结束时间
     */

    @NotNull(message = "活动结束时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime endTime;


    /** 
     * 最大助力次数
     */
    @NotNull(message = "最大助力次数不能为空")
    private Integer helpMaxCount;

    /** 
     * 最大帮砍次数
     */
    @NotNull(message = "最大帮砍次数不能为空")
    private Integer bargainCount;

    /** 
     * 用户每次砍价的最小金额
     */
    @NotNull(message = "用户每次砍价的最小金额不能为空")
    private Integer randomMinPrice;

    /** 
     * 用户每次砍价的最大金额
     */

    @NotNull(message = "用户每次砍价的最大金额不能为空")
    private Integer randomMaxPrice;

}
