package com.ymitcloud.module.promotion.controller.admin.seckill.vo.activity;


import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


/**
 * 秒杀活动基地签证官
 * 秒杀活动 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 *
 * @author HUIHUI
 */
@Data
public class SeckillActivityBaseVO {


    /** 
     * 秒杀活动商品 id
     */
    @NotNull(message = "秒杀活动商品不能为空")
    private Long spuId;

    /** 
     * 秒杀活动名称
     */
    @NotNull(message = "秒杀活动名称不能为空")
    private String name;

    /** 
     * 备注
     */
    private String remark;

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
     * 排序
     */
    @NotNull(message = "排序不能为空")
    private Integer sort;

    /** 
     * 秒杀时段 id
     */
    @NotNull(message = "秒杀时段不能为空")
    private List<Long> configIds;

    /** 
     * 总限购数量
     */
    private Integer totalLimitCount;

    /** 
     * 单次限够数量
     */

    private Integer singleLimitCount;

}
