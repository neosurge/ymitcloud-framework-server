package com.ymitcloud.module.trade.controller.admin.config.vo;




import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/** 管理后台 - 交易中心配置 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TradeConfigRespVO extends TradeConfigBaseVO {


    /** 自增主键*/
    private Long id;

    /** 腾讯地图 KEY*/

    private String tencentLbsKey;

}
