package com.ymitcloud.module.promotion.controller.admin.seckill.vo.config;

import com.ymitcloud.framework.common.enums.CommonStatusEnum;
import com.ymitcloud.framework.common.validation.InEnum;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

/** 
 * 管理后台 - 修改时段配置状态 Request VO
 */
@Data
public class SeckillConfigUpdateStatusReqVo {

    /** 
     * 时段配置编号*/
    @NotNull(message = "时段配置编号不能为空")
    private Long id;

    /** 
     * 状态，见 CommonStatusEnum 枚举*/

    @NotNull(message = "状态不能为空")
    @InEnum(value = CommonStatusEnum.class, message = "修改状态必须是 {value}")
    private Integer status;

}
