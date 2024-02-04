package com.ymitcloud.module.crm.controller.admin.receivable.vo.plan;


import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 管理后台 - CRM 回款计划更新 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CrmReceivablePlanUpdateReqVO extends CrmReceivablePlanBaseVO {


    /**
     * ID
     */

    @NotNull(message = "ID不能为空")
    private Long id;

}
