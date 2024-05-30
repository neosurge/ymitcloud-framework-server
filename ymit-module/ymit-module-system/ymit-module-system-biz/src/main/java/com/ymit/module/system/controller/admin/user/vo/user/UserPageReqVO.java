package com.ymit.module.system.controller.admin.user.vo.user;

import com.ymit.framework.common.data.PageParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static com.ymit.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;


/**
 * 管理后台 - 用户分页 Request VO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserPageReqVO extends PageParam {
    /**
     * 用户账号，模糊匹配
     */
    private String username;
    /**
     * 手机号码，模糊匹配
     */
    private String mobile;
    /**
     * 展示状态，参见 CommonStatusEnum 枚举类
     */
    private Integer status;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;
    /**
     * 部门编号，同时筛选子部门
     */
    private Long deptId;

}
