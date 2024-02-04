package com.ymitcloud.module.member.controller.admin.signin.vo.record;

import com.ymitcloud.framework.common.pojo.PageParam;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/** 管理后台 - 签到记录分页 Request VO */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MemberSignInRecordPageReqVO extends PageParam {
    /** 签到用户 */
    private String nickname;

    /** 第几天签到 */
    private Integer day;

    /** 用户编号 */
    private Long userId;

    /**
     * 签到时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
