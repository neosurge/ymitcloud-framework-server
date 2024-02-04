package com.ymitcloud.module.crm.controller.admin.customer.vo;


import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 管理后台 - CRM 客户 Response VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CrmCustomerRespVO extends CrmCustomerBaseVO {

    /**
     * 编号
     */
    private Long id;
    /**
     * 跟进状态
     */
    private Boolean followUpStatus;
    /**
     * 锁定状态
     */
    private Boolean lockStatus;
    /**
     * 成交状态
     */
    private Boolean dealStatus;
    /**
     * 负责人的用户编号
     */
    private Long ownerUserId;
    /**
     * 负责人名字
     */
    private String ownerUserName;
    /**
     * 负责人部门
     */
    private String ownerUserDeptName;
    /**
     * 地区名称
     */
    private String areaName;
    /**
     * 最后跟进时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime contactLastTime;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 创建人名字
     */

    private String creatorName;

}
