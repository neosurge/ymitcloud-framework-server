package com.ymit.module.system.controller.admin.auth.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 应用中台授权登录
 */
@Getter
@Setter
public class AppAuthLoginReqVO {
    /**
     * 应用中台租户id
     */
    @NotBlank(message = "应用中台租户id不能为空")
    private String tenantId;
    /**
     * 应用中台token
     */
    @NotBlank(message = "应用中台token不能为空")
    private String token;
}