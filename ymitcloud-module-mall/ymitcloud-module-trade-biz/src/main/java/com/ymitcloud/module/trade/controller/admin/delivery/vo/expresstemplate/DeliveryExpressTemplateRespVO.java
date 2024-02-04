package com.ymitcloud.module.trade.controller.admin.delivery.vo.expresstemplate;




import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;


/** 管理后台 - 快递运费模板 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DeliveryExpressTemplateRespVO extends DeliveryExpressTemplateBaseVO {


    /** 编号，自增*/
    private Long id;

    /** 创建时间*/

    private LocalDateTime createTime;

}
