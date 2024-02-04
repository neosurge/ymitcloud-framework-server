package com.ymitcloud.module.mp.controller.admin.tag.vo;

import com.ymitcloud.framework.common.pojo.PageParam;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import jakarta.validation.constraints.NotEmpty;


/** 管理后台 - 公众号标签分页 Request VO */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MpTagPageReqVO extends PageParam {


    /** 公众号账号的编号 */
    @NotEmpty(message = "公众号账号的编号不能为空")
    private Long accountId;

    /**
     * 标签名，模糊匹配
     */

    private String name;

}
