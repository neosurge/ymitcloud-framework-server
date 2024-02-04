package com.ymitcloud.module.crm.controller.admin.receivable.vo.receivable;


import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 管理后台 - CRM 回款 Response VO
 */
// TODO 云码：导出的 VO，可以考虑使用 @Excel 注解，实现导出功能

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CrmReceivableRespVO extends CrmReceivableBaseVO {


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
     * 审批状态
     */
    private Integer auditStatus;
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

}
