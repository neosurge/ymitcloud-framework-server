package com.ymitcloud.module.system.controller.admin.errorcode.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 管理后台 - 错误码创建/修改 Request VO
 */
@Data
public class ErrorCodeSaveReqVO {
    /**
     * 错误码编号
     */
    private Long id;
    /**
     * 应用名
     */
    @NotNull(message = "应用名不能为空")
    private String applicationName;
    /**
     * 错误码编码
     */
    @NotNull(message = "错误码编码不能为空")
    private Integer code;
    /**
     * 错误码错误提示
     */
    @NotNull(message = "错误码错误提示不能为空")
    private String message;
    /**
     * 备注
     */
    private String memo;

}
