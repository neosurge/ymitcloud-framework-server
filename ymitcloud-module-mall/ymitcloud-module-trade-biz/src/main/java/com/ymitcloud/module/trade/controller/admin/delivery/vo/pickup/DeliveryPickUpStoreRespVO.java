package com.ymitcloud.module.trade.controller.admin.delivery.vo.pickup;




import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;


/** 管理后台 - 自提门店 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DeliveryPickUpStoreRespVO extends DeliveryPickUpStoreBaseVO {


    /** 编号*/
    private Long id;

    /** 创建时间*/

    private LocalDateTime createTime;

}
