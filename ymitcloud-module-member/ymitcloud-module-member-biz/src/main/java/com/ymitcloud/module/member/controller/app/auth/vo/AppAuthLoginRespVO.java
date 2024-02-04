package com.ymitcloud.module.member.controller.app.auth.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/** 用户 APP - 登录 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppAuthLoginRespVO {

    /** 用户编号 */
    private Long userId;

    /** 访问令牌 */
    private String accessToken;

    /** 刷新令牌 */
    private String refreshToken;

    /** 过期时间 */
    private LocalDateTime expiresTime;
    /**
     * 社交用户 openid,仅社交登录、社交绑定时会返回
     */
    private String openid;

}
