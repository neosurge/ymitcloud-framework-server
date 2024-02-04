package com.ymitcloud.module.member.controller.admin.level.vo.level;




import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;


/** 管理后台 - 会员等级 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MemberLevelRespVO extends MemberLevelBaseVO {


    /** 编号*/
    private Long id;

    /** 创建时间*/

    private LocalDateTime createTime;

}
