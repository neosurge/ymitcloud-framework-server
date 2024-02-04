package com.ymitcloud.module.crm.controller.admin.customer.vo.limitconfig;


import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
/**
 * 管理后台 - 客户限制配置更新 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CrmCustomerLimitConfigUpdateReqVO extends CrmCustomerLimitConfigBaseVO {

    /**
     * 编号
     */

    @NotNull(message = "编号不能为空")
    private Long id;

}
