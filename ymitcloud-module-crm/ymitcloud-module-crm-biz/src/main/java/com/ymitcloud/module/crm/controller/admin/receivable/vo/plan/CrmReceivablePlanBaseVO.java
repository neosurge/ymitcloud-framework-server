package com.ymitcloud.module.crm.controller.admin.receivable.vo.plan;


import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * 回款计划 Base VO，提供给添加、修改、详细的子 VO 使用 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class CrmReceivablePlanBaseVO {
    /**
     * 期数
     */
    private Integer period;

    /**
     * 回款计划编号
     */
    private Long receivableId;

    /**
     * 计划回款金额
     */
    private Integer price;
    /**
     * 计划回款日期
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime returnTime;
    /**
     * 提前几天提醒
     */
    private Integer remindDays;
    /**
     * 提醒日期
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime remindTime;

    /**
     * 客户名称
     */
    private Long customerId;

    /**
     * 合同编号
     */
    private Long contractId;
    /**
     * 负责人编号
     */
    // TODO @liuhongfeng：负责人编号
    private Long ownerUserId;
    /**
     * 显示顺序
     */
    private Integer sort;
    /**
     * 备注
     */

    private String remark;

}
