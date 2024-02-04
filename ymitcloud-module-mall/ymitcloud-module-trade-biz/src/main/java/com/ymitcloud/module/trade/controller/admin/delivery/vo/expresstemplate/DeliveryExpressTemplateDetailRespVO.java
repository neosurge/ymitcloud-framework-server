package com.ymitcloud.module.trade.controller.admin.delivery.vo.expresstemplate;




import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;


/** 管理后台 - 快递运费模板的详细 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DeliveryExpressTemplateDetailRespVO extends DeliveryExpressTemplateBaseVO {


    /** 编号*/
    private Long id;

    /** 运费模板运费设置*/
    private List<DeliveryExpressTemplateChargeBaseVO> charges;

    /** 运费模板包邮区域*/

    private List<DeliveryExpressTemplateFreeBaseVO> frees;

}
