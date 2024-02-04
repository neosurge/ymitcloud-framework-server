package com.ymitcloud.module.member.controller.admin.point.vo.recrod;

import com.ymitcloud.framework.common.pojo.PageParam;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/** 管理后台 - 用户积分记录分页 Request VO */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MemberPointRecordPageReqVO extends PageParam {


    /** 用户昵称*/
    private String nickname;

    /** 用户编号*/
    private Long userId;

    /** 业务类型*/
    private Integer bizType;

    /** 积分标题 */

    private String title;

}
