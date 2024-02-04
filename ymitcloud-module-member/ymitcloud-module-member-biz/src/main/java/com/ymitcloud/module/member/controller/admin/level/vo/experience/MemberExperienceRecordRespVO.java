package com.ymitcloud.module.member.controller.admin.level.vo.experience;




import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;


/** 管理后台 - 会员经验记录 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MemberExperienceRecordRespVO extends MemberExperienceRecordBaseVO {


    /** 编号*/
    private Long id;

    /** 创建时间*/

    private LocalDateTime createTime;

}
