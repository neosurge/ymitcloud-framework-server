package com.ymitcloud.module.member.controller.admin.level.vo.record;

import com.ymitcloud.framework.common.pojo.PageParam;



import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;


/** 管理后台 - 会员等级记录分页 Request VO */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MemberLevelRecordPageReqVO extends PageParam {


    /** 用户编号 */
    private Long userId;

    /** 等级编号 */
    private Long levelId;

    /**
     * 创建时间
     */

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
