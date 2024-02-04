package com.ymitcloud.module.trade.controller.admin.delivery.vo.expresstemplate;




import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * 快递运费模板运费设置 Base VO，提供给添加运费模板使用
 */
@Data
public class DeliveryExpressTemplateChargeBaseVO {


    /** 编号", example = "6592", hidden = true) // 由于想简单一点，复用这个 VO 在更新操作，所以 hidden 为 false
    private Long id;

    /** 区域编号列表*/
    @NotEmpty(message = "区域编号列表不能为空")
    private List<Integer> areaIds;

    /** 首件数量*/
    @NotNull(message = "首件数量不能为空")
    private Double startCount;

    /** 起步价*/
    @NotNull(message = "起步价不能为空")
    private Integer startPrice;

    /** 续件数量*/
    @NotNull(message = "续件数量不能为空")
    private Double extraCount;

    /** 额外价*/

    @NotNull(message = "额外价不能为空")
    private Integer extraPrice;
}
