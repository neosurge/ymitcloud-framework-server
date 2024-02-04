package com.ymitcloud.module.crm.controller.admin.receivable.vo.plan;


import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 管理后台 - CRM 回款计划 Response VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CrmReceivablePlanRespVO extends CrmReceivablePlanBaseVO {


    /**
     * ID
     */
    private Long id;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 客户名字
     */
    private String customerName;

    /**
     * 合同编号
     */
    private String contractNo;
    /**
     * 负责人
     */
    private String ownerUserName;
    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建人名字
     */
    private String creatorName;

    /**
     * 完成状态
     */
    private Boolean finishStatus;

    /**
     * 回款方式
     */

    private Integer returnType;

}
