package com.ymitcloud.module.crm.controller.admin.clue.vo;


import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * 管理后台 - 线索 Excel 导出 Request VO，参数和 CrmCluePageReqVO 是一致的
 */
@Data
public class CrmClueExportReqVO {
    /**
     * 转化状态
     */
    private Boolean transformStatus;
    /**
     * 跟进状态
     */
    private Boolean followUpStatus;
    /**
     * 线索名称
     */
    private String name;
    /**
     * 客户id
     */
    private Long customerId;
    /**
     * 下次联系时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] contactNextTime;
    /**
     * 电话
     */
    private String telephone;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 地址
     */
    private String address;
    /**
     * 负责人的用户编号
     */
    private Long ownerUserId;
    /**
     * 最后跟进时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] contactLastTime;
    /**
     * 创建时间
     */

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
