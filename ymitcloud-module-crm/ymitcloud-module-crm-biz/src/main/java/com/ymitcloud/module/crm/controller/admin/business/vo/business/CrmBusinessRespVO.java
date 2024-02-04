package com.ymitcloud.module.crm.controller.admin.business.vo.business;


import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 管理后台 - 商机 Response VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CrmBusinessRespVO extends CrmBusinessBaseVO {

    /**
     * 主键
     */
    private Long id;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 客户名称
     */
    private String customerName;
    /**
     * 状态类型名称
     */
    private String statusTypeName;
    /**
     * 状态名称
     */

    private String statusName;

}
