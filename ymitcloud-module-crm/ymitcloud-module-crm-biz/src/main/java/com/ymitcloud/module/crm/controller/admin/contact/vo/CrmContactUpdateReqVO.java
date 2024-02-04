package com.ymitcloud.module.crm.controller.admin.contact.vo;


import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - CRM 联系人更新 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CrmContactUpdateReqVO extends CrmContactBaseVO {


    /**
     * 主键
     */

    @NotNull(message = "主键不能为空")
    private Long id;

}
