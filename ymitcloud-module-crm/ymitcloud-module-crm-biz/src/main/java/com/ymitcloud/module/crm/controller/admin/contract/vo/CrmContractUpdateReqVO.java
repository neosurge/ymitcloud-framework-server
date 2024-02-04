package com.ymitcloud.module.crm.controller.admin.contract.vo;


import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - CRM 合同更新 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CrmContractUpdateReqVO extends CrmContractBaseVO {

    /**
     * 合同编号
     */

    @NotNull(message = "合同编号不能为空")
    private Long id;

}
