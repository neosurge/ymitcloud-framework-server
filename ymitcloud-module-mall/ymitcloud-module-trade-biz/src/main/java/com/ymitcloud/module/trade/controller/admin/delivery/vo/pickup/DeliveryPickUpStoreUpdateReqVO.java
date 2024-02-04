package com.ymitcloud.module.trade.controller.admin.delivery.vo.pickup;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import jakarta.validation.constraints.NotNull;


/**
 * 管理后台 - 自提门店更新 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DeliveryPickUpStoreUpdateReqVO extends DeliveryPickUpStoreBaseVO {


    /** 编号 */

    @NotNull(message = "编号不能为空")
    private Long id;

}
