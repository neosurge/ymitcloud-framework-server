package com.ymitcloud.module.crm.controller.admin.contact.vo;

import com.ymitcloud.framework.common.pojo.PageParam;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - CRM 联系人分页 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CrmContactPageReqVO extends PageParam {


    /**
     * 姓名
     */
    private String name;

    /**
     * 客户编号
     */
    private Long customerId;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 电话
     */
    private String telephone;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * QQ
     */
    private Long qq;
    /**
     * 微信
     */

    private String wechat;

}
