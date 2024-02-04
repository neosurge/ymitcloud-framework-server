package com.ymitcloud.module.promotion.controller.admin.combination.vo.activity;


import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 拼团活动 Base VO，提供给添加、修改、详细的子 VO 使用 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成

 *
 * @author HUIHUI
 */
@Data
public class CombinationActivityBaseVO {


    /**
     * 拼团名称
     */
    @NotNull(message = "拼团名称不能为空")
    private String name;

    /**
     * 商品 SPU 编号
     */
    @NotNull(message = "拼团商品不能为空")
    private Long spuId;

    /**
     * 总限购数量
     */
    @NotNull(message = "总限购数量不能为空")
    private Integer totalLimitCount;

    /**
     * 单次限购数量
     */
    @NotNull(message = "单次限购数量不能为空")
    private Integer singleLimitCount;

    /**
     * 活动时间
     */

    @NotNull(message = "活动时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime startTime;


    /**
     * 活动时间
     */

    @NotNull(message = "活动时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime endTime;


    /**
     * 开团人数
     */
    @NotNull(message = "开团人数不能为空")
    private Integer userSize;

    /**
     * 虚拟成团
     */
    @NotNull(message = "虚拟成团不能为空")
    private Boolean virtualGroup;

    /**
     * 限制时长（小时）
     */

    @NotNull(message = "限制时长不能为空")
    private Integer limitDuration;

}
