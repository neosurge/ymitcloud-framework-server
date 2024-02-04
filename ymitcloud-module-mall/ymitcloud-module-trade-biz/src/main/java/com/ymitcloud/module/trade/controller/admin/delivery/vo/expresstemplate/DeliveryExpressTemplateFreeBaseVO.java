package com.ymitcloud.module.trade.controller.admin.delivery.vo.expresstemplate;




import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * 快递运费模板包邮 Base VO，提供给添加运费模板使用
 */
@Data
public class DeliveryExpressTemplateFreeBaseVO {


    /** 区域编号列表*/
    @NotEmpty(message = "区域编号列表不能为空")
    private List<Integer> areaIds;

    /** 包邮金额*/
    @NotNull(message = "包邮金额不能为空")
    private Integer freePrice;

    /** 包邮件数*/

    @NotNull(message = "包邮件数不能为空")
    private Integer freeCount;

}
