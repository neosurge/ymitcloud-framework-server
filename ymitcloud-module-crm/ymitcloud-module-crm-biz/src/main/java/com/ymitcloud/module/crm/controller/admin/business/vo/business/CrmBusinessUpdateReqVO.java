package com.ymitcloud.module.crm.controller.admin.business.vo.business;


import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 商机更新 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CrmBusinessUpdateReqVO extends CrmBusinessBaseVO {

    /**
     * 主键
     */

    @NotNull(message = "主键不能为空")
    private Long id;

    // TODO @ljileo：修改的时候，应该可以传递添加的产品；

}
