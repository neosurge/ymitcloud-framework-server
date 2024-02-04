package com.ymitcloud.module.mp.controller.admin.account.vo;




import lombok.Data;

import jakarta.validation.constraints.NotEmpty;

/**
 * 公众号账号 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 *
 * @author fengdan
 */
@Data
public class MpAccountBaseVO {


    /** 公众号名称*/
    @NotEmpty(message = "公众号名称不能为空")
    private String name;

    /** 公众号微信号*/
    @NotEmpty(message = "公众号微信号不能为空")
    private String account;

    /** 公众号 appId*/
    @NotEmpty(message = "公众号 appId 不能为空")
    private String appId;

    /** 公众号密钥*/
    @NotEmpty(message = "公众号密钥不能为空")
    private String appSecret;

    /** 公众号 token*/
    @NotEmpty(message = "公众号 token 不能为空")
    private String token;

    /** 加密密钥*/
    private String aesKey;

    /** 备注 */

    private String remark;

}
