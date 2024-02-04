package com.ymitcloud.module.crm.controller.admin.customer.vo.limitconfig;


import java.time.LocalDateTime;
import java.util.List;

import com.ymitcloud.module.system.api.dept.dto.DeptRespDTO;
import com.ymitcloud.module.system.api.user.dto.AdminUserRespDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 管理后台 - 客户限制配置 Response VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CrmCustomerLimitConfigRespVO extends CrmCustomerLimitConfigBaseVO {

    /**
     * 编号
     */
    private Long id;
    /**
     * 规则适用人群名称
     */
    private List<AdminUserRespDTO> users;
    /**
     * 规则适用部门名称
     */
    private List<DeptRespDTO> depts;
    /**
     * 创建时间
     */

    private LocalDateTime createTime;

}
