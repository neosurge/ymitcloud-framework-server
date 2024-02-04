package com.ymitcloud.module.trade.controller.admin.delivery.vo.expresstemplate;


import java.util.List;

import jakarta.validation.Valid;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 快递运费模板创建 Request VO=
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DeliveryExpressTemplateCreateReqVO extends DeliveryExpressTemplateBaseVO {


    /**
     * 区域运费列表=
     */
    @Valid
    private List<DeliveryExpressTemplateChargeBaseVO> charges;

    /**
     * 包邮区域列表
     */

    @Valid
    private List<DeliveryExpressTemplateFreeBaseVO> frees;

}
