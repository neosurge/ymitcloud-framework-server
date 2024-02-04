package com.ymitcloud.module.member.controller.app.address.vo;



import lombok.Data;

import jakarta.validation.constraints.NotNull;


// TODO 云码：example 缺失

/**
* 用户收件地址 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class AppAddressBaseVO {


    /** 收件人名称*/
    @NotNull(message = "收件人名称不能为空")
    private String name;

    /** 手机号*/
    @NotNull(message = "手机号不能为空")
    private String mobile;

    /** 地区编号*/
    @NotNull(message = "地区编号不能为空")
    private Long areaId;

    /** 收件详细地址*/
    @NotNull(message = "收件详细地址不能为空")
    private String detailAddress;

    /** 是否默认地址*/

    @NotNull(message = "是否默认地址不能为空")
    private Boolean defaultStatus;

}
