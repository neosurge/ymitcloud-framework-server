package com.ymitcloud.module.trade.controller.app.delivery.vo.pickup;


import lombok.Data;

/** 用户 App - 自提门店 */
@Data
public class AppDeliveryPickUpStoreRespVO {

    /** 编号 */
    private Long id;

    /** 门店名称 */
    private String name;

    /** 门店 logo */
    private String logo;

    /** 门店手机 */
    private String phone;

    /** 区域编号 */
    private Integer areaId;

    /** 地区名字 */
    private String areaName;

    /** 门店详细地址 */
    private String detailAddress;

    /** 纬度 */
    private Double latitude;

    /** 经度 */
    private Double longitude;

    /**
     * 距离，单位：千米
     */

    private Double distance;

}
