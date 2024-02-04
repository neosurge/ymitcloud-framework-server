package com.ymitcloud.module.trade.controller.app.base.sku;

import com.ymitcloud.module.trade.controller.app.base.property.AppProductPropertyValueDetailRespVO;



import lombok.Data;

import java.util.List;

/**
 * 商品 SKU 基础 Response VO
 *

 * @author 

 */
@Data
public class AppProductSkuBaseRespVO {


    /** 主键*/
    private Long id;

    /** 图片地址", example = "https://www.ymitcloud.com/xx.png")
    private String picUrl;

    /** 销售价格，单位：分*/
    private Integer price;

    /** 库存*/

    private Integer stock;

    /**
     * 属性数组
     */
    private List<AppProductPropertyValueDetailRespVO> properties;

}
