package com.ymitcloud.module.crm.controller.admin.business.vo.business;


import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 商机 Base VO，提供给添加、修改、详细的子 VO 使用 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class CrmBusinessBaseVO {
    /**
     * 商机名称
     */
    @NotNull(message = "商机名称不能为空")
    private String name;
    /**
     * 商机状态类型编号
     */
    @NotNull(message = "商机状态类型不能为空")
    private Long statusTypeId;

    /**
     * 商机状态编号
     */
    @NotNull(message = "商机状态不能为空")
    private Long statusId;
    /**
     * 下次联系时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime contactNextTime;
    /**
     * 客户编号
     */
    @NotNull(message = "客户不能为空")
    private Long customerId;
    /**
     * 预计成交日期
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime dealTime;
    /**
     * 商机金额
     */
    private Integer price;

    /**
     * 整单折扣 折扣使用 Integer 类型，存储时，默认 * 100；展示的时候，前端需要 / 100；避免精度丢失问题
     */
    private Integer discountPercent;
    /**
     * 产品总金额
     */
    private BigDecimal productPrice;
    /**
     * 备注
     */

    private String remark;

}
