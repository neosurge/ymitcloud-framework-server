package com.ymitcloud.module.member.controller.admin.level.vo.experience;

import com.ymitcloud.framework.common.pojo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/** 管理后台 - 会员经验记录分页 Request VO */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MemberExperienceRecordPageReqVO extends PageParam {
    /** 用户编号 */
    private Long userId;

    /** 业务编号 */
    private String bizId;

    /** 业务类型 */
    private Integer bizType;

    /** 标题 */
    private String title;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
