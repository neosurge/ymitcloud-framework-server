package com.ymitcloud.module.member.controller.app.level.vo.experience;




import lombok.Data;

import java.time.LocalDateTime;


/** 用户 App - 会员经验记录 */
@Data
public class AppMemberExperienceRecordRespVO {

    /** 标题*/
    private String title;

    /** 经验*/
    private Integer experience;

    /** 描述*/
    private String description;

    /** 创建时间*/

    private LocalDateTime createTime;

}
