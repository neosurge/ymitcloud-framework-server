package com.ymitcloud.module.mp.controller.admin.message.vo.autoreply;

import com.ymitcloud.framework.common.pojo.PageParam;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import jakarta.validation.constraints.NotNull;


/** 管理后台 - 公众号自动回复的分页 Request VO */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MpAutoReplyPageReqVO extends PageParam {


    /** 公众号账号的编号*/

    @NotNull(message = "公众号账号的编号不能为空")
    private Long accountId;

}
