package com.ymitcloud.module.report.controller.admin.ureport.vo;

import com.ymitcloud.framework.common.enums.CommonStatusEnum;
import com.ymitcloud.framework.common.validation.InEnum;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/** 管理后台 - Ureport2 报表新增/修改 Request VO */
@Data
public class UReportDataSaveReqVO {

    /** ID */
    private Long id;

    /** 文件名称 */
    @NotEmpty(message = "文件名称不能为空")
    private String name;

    /** 状态 */

    @NotNull(message = "状态不能为空")
    @InEnum(CommonStatusEnum.class)
    private Integer status;


    /**
     * 文件内容
     */
    private String content;

    /**
     * 备注
     */

    private String remark;

}