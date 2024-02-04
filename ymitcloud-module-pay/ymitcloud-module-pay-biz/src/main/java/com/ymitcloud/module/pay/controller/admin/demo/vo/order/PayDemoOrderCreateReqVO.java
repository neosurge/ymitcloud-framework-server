package com.ymitcloud.module.pay.controller.admin.demo.vo.order;




import lombok.Data;

import jakarta.validation.constraints.NotNull;


/** 管理后台 - 示例订单创建 Request VO */
@Data
public class PayDemoOrderCreateReqVO {

    /** 商品编号*/

    @NotNull(message = "商品编号不能为空")
    private Long spuId;

}
