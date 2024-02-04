package com.ymitcloud.module.system.controller.admin.mail.vo.account;

import com.ymitcloud.framework.common.pojo.PageParam;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 管理后台 - 邮箱账号分页 Request VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MailAccountPageReqVO extends PageParam {

    /**
     * 邮箱
     */
    private String mail;
    /**
     * 用户名
     */
    private String username;

}
