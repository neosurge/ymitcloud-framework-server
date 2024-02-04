package com.ymitcloud.module.crm.controller.admin.customer.vo;


import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - CRM 客户更新 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CrmCustomerUpdateReqVO extends CrmCustomerBaseVO {

    /**
     * 编号
     */

    @NotNull(message = "编号不能为空")
    private Long id;

}
