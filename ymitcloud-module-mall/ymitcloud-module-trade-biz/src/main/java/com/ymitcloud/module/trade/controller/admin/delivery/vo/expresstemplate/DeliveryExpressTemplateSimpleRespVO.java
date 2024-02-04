package com.ymitcloud.module.trade.controller.admin.delivery.vo.expresstemplate;




import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



/** 管理后台 - 模版精简信息 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryExpressTemplateSimpleRespVO {


    /** 模版编号*/
    private Long id;

    /** 模板名称*/

    private String name;

}
