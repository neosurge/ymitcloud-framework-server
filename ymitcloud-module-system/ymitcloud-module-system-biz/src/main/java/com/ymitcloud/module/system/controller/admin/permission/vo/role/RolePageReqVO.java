package com.ymitcloud.module.system.controller.admin.permission.vo.role;

import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.ymitcloud.framework.common.pojo.PageParam;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 管理后台 - 角色分页 Request VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RolePageReqVO extends PageParam {
    /**
     * 角色名称，模糊匹配
     */
    private String name;
    /**
     * 角色标识，模糊匹配
     */
    private String code;
    /**
     * 展示状态，参见 CommonStatusEnum 枚举类
     */
    private Integer status;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
