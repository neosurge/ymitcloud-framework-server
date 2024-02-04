package com.ymitcloud.module.mp.controller.admin.message.vo.autoreply;




import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import jakarta.validation.constraints.NotNull;


/** 管理后台 - 公众号自动回复的更新 Request VO */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MpAutoReplyUpdateReqVO extends MpAutoReplyBaseVO {


    /** 主键*/

    @NotNull(message = "主键不能为空")
    private Long id;

}
