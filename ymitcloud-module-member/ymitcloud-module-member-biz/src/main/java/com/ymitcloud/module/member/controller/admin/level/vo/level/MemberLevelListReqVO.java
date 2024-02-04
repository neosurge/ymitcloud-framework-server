package com.ymitcloud.module.member.controller.admin.level.vo.level;



import lombok.Data;
import lombok.ToString;

/** 管理后台 - 会员等级列表筛选 Request VO */

@Data
@ToString(callSuper = true)
public class MemberLevelListReqVO {


    /** 等级名称*/
    private String name;

    /** 状态 */

    private Integer status;

}
