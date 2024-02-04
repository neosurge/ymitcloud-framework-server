package com.ymitcloud.module.system.controller.admin.socail.vo.user;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * 管理后台 - 社交用户 Response VO
 */
@Data
public class SocialUserRespVO {
    /**
     * 主键(自增策略)
     */
    private Long id;
    /**
     * 社交平台的类型
     */
    private Integer type;
    /**
     * 社交 openid
     */
    private String openid;
    /**
     * 社交 token
     */
    private String token;
    /**
     * 原始 Token 数据，一般是 JSON 格式
     */
    private String rawTokenInfo;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 原始用户数据，一般是 JSON 格式
     */
    private String rawUserInfo;
    /**
     * 最后一次的认证 code
     */
    private String code;
    /**
     * 最后一次的认证 state
     */
    private String state;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
