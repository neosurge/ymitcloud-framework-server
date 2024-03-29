package com.ymitcloud.module.mp.controller.admin.tag.vo;




import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import jakarta.validation.constraints.NotNull;


/** 管理后台 - 公众号标签创建 Request VO */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MpTagCreateReqVO extends MpTagBaseVO {


    /** 公众号账号的编号*/

    @NotNull(message = "公众号账号的编号不能为空")
    private Long accountId;

}
