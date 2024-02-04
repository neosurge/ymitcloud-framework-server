package com.ymitcloud.module.trade.controller.admin.delivery.vo.express;




import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;


/** 管理后台 - 快递公司精简信息 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryExpressSimpleRespVO {


    /** 编号*/
    @NotNull(message = "编号不能为空")
    private Long id;

    /** 快递公司名称*/

    @NotNull(message = "快递公司名称不能为空")
    private String name;

}
