package com.ymitcloud.module.mp.controller.admin.account.vo;




import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import jakarta.validation.constraints.NotNull;


/** 管理后台 - 公众号账号更新 Request VO */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MpAccountUpdateReqVO extends MpAccountBaseVO {


    /** 编号*/

    @NotNull(message = "编号不能为空")
    private Long id;

}
