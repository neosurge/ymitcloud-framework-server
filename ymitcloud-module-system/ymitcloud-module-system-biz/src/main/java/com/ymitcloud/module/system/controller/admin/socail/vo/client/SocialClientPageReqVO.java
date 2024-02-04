package com.ymitcloud.module.system.controller.admin.socail.vo.client;

import com.ymitcloud.framework.common.pojo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 管理后台 - 社交客户端分页 Request VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SocialClientPageReqVO extends PageParam {
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
     * 状态
     */
    private Integer status;

}
