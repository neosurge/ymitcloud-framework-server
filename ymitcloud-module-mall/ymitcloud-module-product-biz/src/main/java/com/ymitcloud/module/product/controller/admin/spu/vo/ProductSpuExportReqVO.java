package com.ymitcloud.module.product.controller.admin.spu.vo;


import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 管理后台 - 商品 SPU 导出 Request VO，参数和 ProductSpuPageReqVO 是一致的
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSpuExportReqVO {


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
