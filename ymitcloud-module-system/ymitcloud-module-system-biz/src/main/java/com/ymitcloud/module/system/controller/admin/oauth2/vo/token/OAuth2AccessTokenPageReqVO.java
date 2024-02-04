package com.ymitcloud.module.system.controller.admin.oauth2.vo.token;

import com.ymitcloud.framework.common.pojo.PageParam;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 管理后台 - 访问令牌分页 Request VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OAuth2AccessTokenPageReqVO extends PageParam {
    /**
     * 用户编号
     */
    private Long userId;
    /**
     * 用户类型，参见 UserTypeEnum 枚举
     */
    private Integer userType;
    /**
     * 客户端编号
     */
    private String clientId;

}
