package com.ymitcloud.module.mp.controller.admin.tag.vo;



import lombok.Data;

/** 管理后台 - 公众号标签精简信息 */
@Data
public class MpTagSimpleRespVO {

    /** 编号*/
    private Long id;

    /** 公众号的标签编号*/
    private Long tagId;

    /** 标签名称*/

    private String name;

}
