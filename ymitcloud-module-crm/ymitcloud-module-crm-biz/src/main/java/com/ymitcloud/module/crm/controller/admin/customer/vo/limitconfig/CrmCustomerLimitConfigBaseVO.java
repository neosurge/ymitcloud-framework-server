package com.ymitcloud.module.crm.controller.admin.customer.vo.limitconfig;


import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 客户限制配置 Base VO，提供给添加、修改、详细的子 VO 使用 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class CrmCustomerLimitConfigBaseVO {
    /**
     * 规则类型
     */
    @NotNull(message = "规则类型不能为空")
    private Integer type;
    /**
     * 规则适用人群
     */
    private List<Long> userIds;
    /**
     * 规则适用部门
     */
    private List<Long> deptIds;
    /**
     * 数量上限
     */
    @NotNull(message = "数量上限不能为空")
    private Integer maxCount;
    /**
     * 成交客户是否占有拥有客户数(当 type = 1 时)
     */

    private Boolean dealCountEnabled;

}
