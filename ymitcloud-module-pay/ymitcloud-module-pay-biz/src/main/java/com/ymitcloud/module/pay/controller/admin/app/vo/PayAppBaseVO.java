package com.ymitcloud.module.pay.controller.admin.app.vo;
import com.ymitcloud.framework.common.enums.CommonStatusEnum;
import com.ymitcloud.framework.common.validation.InEnum;



import lombok.*;
import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.*;

/**
* 支付应用信息 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class PayAppBaseVO {


    /** 应用名*/
    @NotNull(message = "应用名不能为空")
    private String name;

    /** 开启状态*/

    @NotNull(message = "开启状态不能为空")
    @InEnum(CommonStatusEnum.class)
    private Integer status;


    /** 备注", example = "我是一个测试应用")
    private String remark;

    /** 支付结果的回调地址*/

    @NotNull(message = "支付结果的回调地址不能为空")
    @URL(message = "支付结果的回调地址必须为 URL 格式")
    private String orderNotifyUrl;


    /** 退款结果的回调地址*/

    @NotNull(message = "退款结果的回调地址不能为空")
    @URL(message = "退款结果的回调地址必须为 URL 格式")
    private String refundNotifyUrl;

}
