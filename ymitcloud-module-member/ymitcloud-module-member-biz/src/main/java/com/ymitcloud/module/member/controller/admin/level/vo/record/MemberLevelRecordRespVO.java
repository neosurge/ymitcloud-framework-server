package com.ymitcloud.module.member.controller.admin.level.vo.record;




import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;


/** 管理后台 - 会员等级记录 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MemberLevelRecordRespVO extends MemberLevelRecordBaseVO {


    /** 编号*/
    private Long id;

    /** 创建时间*/

    private LocalDateTime createTime;

}
