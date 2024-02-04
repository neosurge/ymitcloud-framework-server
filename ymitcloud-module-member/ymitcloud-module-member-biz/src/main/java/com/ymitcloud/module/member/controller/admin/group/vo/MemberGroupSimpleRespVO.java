package com.ymitcloud.module.member.controller.admin.group.vo;



import lombok.Data;
import lombok.ToString;

/** 管理后台 - 用户分组 */

@Data
@ToString(callSuper = true)
public class MemberGroupSimpleRespVO {


    /** 编号*/
    private Long id;

    /** 等级名称 */

    private String name;

}
