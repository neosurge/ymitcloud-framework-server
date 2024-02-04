package com.ymitcloud.module.promotion.controller.admin.combination.vo.recrod;


import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 拼团记录 Base VO，提供给添加、修改、详细的子 VO 使用 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成

 *
 * @author HUIHUI
 */
@Data
public class CombinationRecordBaseVO {


    /**
     * 拼团记录编号
     */
    private Long id;

    /**
     * 拼团活动编号
     */
    private Long activityId;

    /**
     * 团长编号
     */

    private Long headId;

    // ========== 用户相关 ==========


    /**
     * 用户编号
     */
    @NotNull(message = "用户编号不能为空")
    private Long userId;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户头像
     */

    private String avatar;

    // ========== 商品相关 ==========


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
     * 商品名字
     */
    private String spuName;

    /**
     * 商品图片
     */
    private String picUrl;

    /**
     * 过期时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime expireTime;

    /**
     * 可参团人数
     */
    private Integer userSize;

    /**
     * 已参团人数
     */
    private Integer userCount;

    /**
     * 拼团状态
     */
    private Integer status;

    /**
     * 是否虚拟成团
     */
    private Boolean virtualGroup;

    /**
     * 开始时间 (订单付款后开始的时间)
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime startTime;

    /**
     * 结束时间（成团时间/失败时间）
     */

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime endTime;

}
