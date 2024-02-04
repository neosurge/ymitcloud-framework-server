package com.ymitcloud.module.product.controller.admin.spu.vo;


import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.ymitcloud.framework.common.pojo.PageParam;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 管理后台 - 商品 SPU 分页 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductSpuPageReqVO extends PageParam {

    /**
     * 出售中商品
     */
    public static final Integer FOR_SALE = 0;

    /**
     * 仓库中商品
     */
    public static final Integer IN_WAREHOUSE = 1;

    /**
     * 已售空商品
     */
    public static final Integer SOLD_OUT = 2;

    /**
     * 警戒库存
     */
    public static final Integer ALERT_STOCK = 3;

    /**
     * 商品回收站
     */
    public static final Integer RECYCLE_BIN = 4;

    /**
     * 商品名称
     */
    private String name;
    /**
     * 前端请求的tab类型
     */
    private Integer tabType;
    /**
     * 商品分类编号
     */
    private Long categoryId;
    /**
     * 创建时间
     */

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
