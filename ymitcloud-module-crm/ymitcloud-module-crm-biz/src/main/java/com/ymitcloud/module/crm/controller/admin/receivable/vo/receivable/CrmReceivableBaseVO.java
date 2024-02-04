package com.ymitcloud.module.crm.controller.admin.receivable.vo.receivable;


import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.ymitcloud.framework.common.validation.InEnum;
import com.ymitcloud.module.crm.enums.common.CrmAuditStatusEnum;

import lombok.Data;

/**
 * 回款 Base VO，提供给添加、修改、详细的子 VO 使用 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成

 */
@Data
public class CrmReceivableBaseVO {


    /**
     * 回款编号
     */
    private String no;

    /**
     * 回款计划
     */
    // TODO @liuhongfeng：回款计划编号
    private Long planId;

    /**
     * 客户名称
     */
    // TODO @liuhongfeng：客户编号
    private Long customerId;
    /**
     * 合同名称
     */
    // TODO @liuhongfeng：客户编号
    private Long contractId;

    /**
     * 审批状态
     */
    // TODO @liuhongfeng：这个字段，应该不是前端传递的噢，而是后端自己生成的
    @InEnum(CrmAuditStatusEnum.class)
    private Integer checkStatus;

    /**
     * 回款日期
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime returnTime;
    /**
     * 回款方式
     */
    private Integer returnType;

    /**
     * 回款金额，单位：分
     */
    private Integer price;

    /**
     * 负责人
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
