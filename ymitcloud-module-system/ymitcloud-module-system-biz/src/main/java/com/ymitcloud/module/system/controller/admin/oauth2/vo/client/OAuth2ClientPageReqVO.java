package com.ymitcloud.module.system.controller.admin.oauth2.vo.client;

import com.ymitcloud.framework.common.pojo.PageParam;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 管理后台 - OAuth2 客户端分页 Request VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OAuth2ClientPageReqVO extends PageParam {
    /**
     * 应用名，模糊匹配
     */
    private String name;
    /**
     * 状态，参见 CommonStatusEnum 枚举
     */
    private Integer status;

}
