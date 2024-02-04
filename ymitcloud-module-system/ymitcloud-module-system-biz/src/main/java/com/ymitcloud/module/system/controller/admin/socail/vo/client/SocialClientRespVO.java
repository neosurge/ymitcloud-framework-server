package com.ymitcloud.module.system.controller.admin.socail.vo.client;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * 管理后台 - 社交客户端 Response VO
 */
@Data
public class SocialClientRespVO {
    /**
     * 编号
     */
    private Long id;
    /**
     * 应用名
     */
    private String name;
    /**
     * 社交平台的类型
     */
    private Integer socialType;
    /**
     * 用户类型
     */
    private Integer userType;
    /**
     * 客户端编号
     */
    private String clientId;
    /**
     * 客户端密钥
     */
    private String clientSecret;
    /**
     * 授权方的网页应用编号
     */
    private String agentId;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
