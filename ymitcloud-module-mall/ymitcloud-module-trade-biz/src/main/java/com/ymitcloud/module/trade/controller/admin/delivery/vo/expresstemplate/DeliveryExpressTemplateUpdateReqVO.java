package com.ymitcloud.module.trade.controller.admin.delivery.vo.expresstemplate;


import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 快递运费模板更新 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DeliveryExpressTemplateUpdateReqVO extends DeliveryExpressTemplateBaseVO {


    /** 编号 */
    @NotNull(message = "编号不能为空")
    private Long id;

    /**
     * 区域运费列表
     */
    @Valid
    private List<DeliveryExpressTemplateChargeBaseVO> charges;

    /**
     * 包邮区域列表
     */

    @Valid
    private List<DeliveryExpressTemplateFreeBaseVO> frees;

}
