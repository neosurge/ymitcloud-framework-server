package com.ymitcloud.module.crm.controller.admin.customer.vo.poolconfig;


import java.util.Objects;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.ObjectUtil;
import jakarta.validation.constraints.AssertTrue;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - CRM 客户公海配置的保存 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CrmCustomerPoolConfigSaveReqVO extends CrmCustomerPoolConfigBaseVO {

    // TODO @wanwan：AssertTrue 必须 is 开头哈；注意需要 json 忽略下，避免被序列化；
    @AssertTrue(message = "客户公海规则设置不正确")
    // TODO @wanwan：这个方法，是不是拆成 2 个，一个校验 contactExpireDays、一个校验 dealExpireDays；
    public boolean poolEnableValid() {
        if (!BooleanUtil.isTrue(getEnabled())) {
            return true;
        }
        return ObjectUtil.isAllNotEmpty(getContactExpireDays(), getDealExpireDays());
    }

    @AssertTrue(message = "客户公海规则设置不正确")
    // TODO @wanwan：这个方法，是不是改成 isNotifyDaysValid() 更好点？本质校验的是 notifyDays 是否为空
    public boolean notifyEnableValid() {
        if (!BooleanUtil.isTrue(getNotifyEnabled())) {
            return true;
        }
        return Objects.nonNull(getNotifyDays());
    }

}
