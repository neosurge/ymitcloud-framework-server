package com.ymitcloud.module.mp.controller.admin.account.vo;

import com.ymitcloud.framework.common.pojo.PageParam;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/** 管理后台 - 公众号账号分页 Request VO */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MpAccountPageReqVO extends PageParam {

    /**
     * 公众号名称
     */
    private String name;
    /**
     * 公众号账号
     */
    private String account;
    /**
     * 公众号 appid
     */

    private String appId;

}
