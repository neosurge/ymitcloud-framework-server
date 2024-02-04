package com.ymitcloud.module.trade.controller.admin.delivery.vo.pickup;

import com.ymitcloud.framework.common.enums.CommonStatusEnum;
import com.ymitcloud.framework.common.validation.InEnum;
import com.ymitcloud.framework.common.validation.Mobile;
import com.fasterxml.jackson.annotation.JsonFormat;



import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;

/**
* 自提门店 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class DeliveryPickUpStoreBaseVO {


    /** 门店名称*/
    @NotBlank(message = "门店名称不能为空")
    private String name;

    /** 门店简介", example = "我是门店简介")
    private String introduction;

    /** 门店手机*/

    @NotBlank(message = "门店手机不能为空")
    @Mobile
    private String phone;


    /** 区域编号*/
    @NotNull(message = "区域编号不能为空")
    private Integer areaId;

    /** 门店详细地址*/
    @NotBlank(message = "门店详细地址不能为空")
    private String detailAddress;

    /** 门店 logo*/
    @NotBlank(message = "门店 logo 不能为空")
    private String logo;

    /** 营业开始时间*/

    @NotNull(message = "营业开始时间不能为空")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime openingTime;


    /** 营业结束时间*/

    @NotNull(message = "营业结束时间不能为空")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime closingTime;


    /** 纬度*/
    @NotNull(message = "纬度不能为空")
    private Double latitude;

    /** 经度*/
    @NotNull(message = "经度不能为空")
    private Double longitude;

    /** 门店状态*/

    @NotNull(message = "门店状态不能为空")
    @InEnum(CommonStatusEnum.class)
    private Integer status;

}
