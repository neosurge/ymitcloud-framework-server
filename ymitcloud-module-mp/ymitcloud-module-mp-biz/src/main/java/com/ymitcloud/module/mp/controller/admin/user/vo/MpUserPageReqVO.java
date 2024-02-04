package com.ymitcloud.module.mp.controller.admin.user.vo;

import com.ymitcloud.framework.common.pojo.PageParam;


import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/** 管理后台 - 公众号粉丝分页 Request VO */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MpUserPageReqVO extends PageParam {


    /** 公众号账号的编号*/
    @NotNull(message = "公众号账号的编号不能为空")
    private Long accountId;

    /** 
     * 公众号粉丝标识，模糊匹配
     */
    private String openid;

    /** 
     * 公众号粉丝昵称，模糊匹配
     */

    private String nickname;

}
