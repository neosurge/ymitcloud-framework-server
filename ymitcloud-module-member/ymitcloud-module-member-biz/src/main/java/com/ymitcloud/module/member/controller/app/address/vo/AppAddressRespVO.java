package com.ymitcloud.module.member.controller.app.address.vo;




import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/** 用户 APP - 用户收件地址 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AppAddressRespVO extends AppAddressBaseVO {


    /** 编号*/
    private Long id;

    /** 地区名字*/

    private String areaName;

}
