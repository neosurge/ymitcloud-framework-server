package com.ymitcloud.module.pay.controller.admin.channel.vo;
import com.ymitcloud.framework.common.enums.CommonStatusEnum;
import com.ymitcloud.framework.common.validation.InEnum;



import lombok.*;
import jakarta.validation.constraints.*;

/**
* 支付渠道 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class PayChannelBaseVO {


    /** 开启状态*/

    @NotNull(message = "开启状态不能为空")
    @InEnum(CommonStatusEnum.class)
    private Integer status;


    /** 备注", example = "我是小备注")
    private String remark;

    /** 渠道费率，单位：百分比*/
    @NotNull(message = "渠道费率，单位：百分比不能为空")
    private Double feeRate;

    /** 应用编号*/

    @NotNull(message = "应用编号不能为空")
    private Long appId;

}
