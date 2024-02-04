package com.ymitcloud.module.crm.controller.admin.contract.vo;


import java.time.LocalDateTime;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - CRM 合同 Response VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ContractRespVO extends CrmContractBaseVO {

    /**
     * 合同编号
     */
    private Long id;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建人名字
     */
    private String creatorName;
    /**
     * 客户名字
     */
    private String customerName;
    /**
     * 负责人
     */
    private String ownerUserName;
    /**
     * 审批状态
     */

    private Integer auditStatus;

}
