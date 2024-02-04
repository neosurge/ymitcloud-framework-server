package com.ymitcloud.module.trade.controller.app.delivery.vo.config;



import lombok.Data;

// TODO 云码：后续要实现下，配送配置；后续融合到 AppTradeConfigRespVO 中
/** 用户 App - 配送配置 */
@Data
public class AppDeliveryConfigRespVO {

    /** 腾讯地图 KEY*/
    private String tencentLbsKey;

    /** 是否开启自提*/

    private Boolean pickUpEnable;

}
