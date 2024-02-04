package com.ymitcloud.module.trade.controller.admin.delivery.vo.pickup;




import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/** 管理后台 - 自提门店精简信息 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryPickUpStoreSimpleRespVO {


    /** 编号*/
    private Long id;

    /** 门店名称*/
    private String name;

    /** 门店手机*/
    private String phone;

    /** 区域编号*/
    private Integer areaId;

    /** 区域名称*/
    private String areaName;

    /** 门店详细地址*/

    private String detailAddress;

}
