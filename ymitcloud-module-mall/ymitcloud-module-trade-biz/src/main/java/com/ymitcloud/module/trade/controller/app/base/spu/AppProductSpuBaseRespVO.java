package com.ymitcloud.module.trade.controller.app.base.spu;



import lombok.Data;


/**
 * 商品 SPU 基础 Response VO
 *

 * @author 

 */
@Data
public class AppProductSpuBaseRespVO {


    /** 主键*/
    private Long id;

    /** 商品 SPU 名字*/
    private String name;

    /** 
     * 商品主图地址
     */

    private String picUrl;

}
