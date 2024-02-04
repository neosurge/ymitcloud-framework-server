package com.ymitcloud.module.crm.controller.admin.customer.vo.poolconfig;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 客户公海配置 Base VO，提供给添加、修改、详细的子 VO 使用 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class CrmCustomerPoolConfigBaseVO {
    /**
     * 是否启用客户公海
     */
    @NotNull(message = "是否启用客户公海不能为空")
    private Boolean enabled;
    /**
     * 未跟进放入公海天数
     */
    private Integer contactExpireDays;
    /**
     * 未成交放入公海天数
     */
    private Integer dealExpireDays;
    /**
     * 是否开启提前提醒
     */
    private Boolean notifyEnabled;
    /**
     * 提前提醒天数
     */

    private Integer notifyDays;

}
