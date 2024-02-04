package com.ymitcloud.module.crm.controller.admin.contract.vo;


import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 合同 Base VO，提供给添加、修改、详细的子 VO 使用 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成

 */
@Data
public class CrmContractBaseVO {

    // TODO @dhb52：类似 no 字段的 example 要写xia 哈；

    /**
     * 合同名称
     */

    @NotNull(message = "合同名称不能为空")
    private String name;

    // TODO @dhb52：这个必须传递

    /**
     * 客户编号
     */
    private Long customerId;
    /**
     * 商机编号
     */
    private Long businessId;
    /**
     * 工作流编号
     */
    private Long processInstanceId;

    // TODO @dhb52：这个必须传递
    /**
     * 下单日期
     */

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime orderDate;

    // TODO @dhb52：这个必须传递

    /**
     * 负责人的用户编号
     */
    private Long ownerUserId;

    // TODO @云码：未来应该支持自动生成；
    // TODO @dhb52：这个必须传递；
    /**
     * 合同编号
     */
    private String no;
    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime startTime;
    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime endTime;
    /**
     * 合同金额
     */
    private Integer price;
    /**
     * 整单折扣
     */
    private Integer discountPercent;
    /**
     * 产品总金额
     */
    private Integer productPrice;
    /**
     * 联系人编号
     */
    private Long contactId;
    /**
     * 公司签约人
     */
    private Long signUserId;
    /**
     * 最后跟进时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime contactLastTime;
    /**
     * 备注
     */
    private String remark;

    // TODO @dhb52：增加一个 status
    // 字段：具体有哪些值，你来枚举下；主要页面上有个【草稿】【提交审核】的流程，可以看看。然后要对接工作流，这块也可以看看，不确定的地方问我。


}
