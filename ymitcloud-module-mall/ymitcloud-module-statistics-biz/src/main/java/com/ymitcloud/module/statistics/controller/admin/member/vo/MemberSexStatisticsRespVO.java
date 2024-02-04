package com.ymitcloud.module.statistics.controller.admin.member.vo;



import lombok.Data;

/** 管理后台 - 会员性别统计 */
@Data
public class MemberSexStatisticsRespVO {

    /** 性别*/
    private Integer sex;

    // TODO @疯狂：要不还是其它字段，我们也补全，这样方便使用的用户，做定制化；就保持和 MemberAreaStatisticsRespVO 一致；
    /** 会员数量*/

    private Integer userCount;

}
