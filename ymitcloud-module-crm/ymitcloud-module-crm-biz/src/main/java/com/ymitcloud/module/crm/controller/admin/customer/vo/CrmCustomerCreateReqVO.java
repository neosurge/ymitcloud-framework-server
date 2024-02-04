package com.ymitcloud.module.crm.controller.admin.customer.vo;


import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
/**
 * 管理后台 - CRM 客户创建 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CrmCustomerCreateReqVO extends CrmCustomerBaseVO {


    /**
     * 负责人的用户编号
     */

    @NotNull(message = "负责人不能为空")
    private Long ownerUserId;

}
