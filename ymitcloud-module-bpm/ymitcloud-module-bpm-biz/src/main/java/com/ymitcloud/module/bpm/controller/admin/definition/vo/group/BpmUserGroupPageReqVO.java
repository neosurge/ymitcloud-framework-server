package com.ymitcloud.module.bpm.controller.admin.definition.vo.group;


import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.ymitcloud.framework.common.pojo.PageParam;
import com.ymitcloud.framework.common.util.date.DateUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 管理后台 - 用户组分页 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BpmUserGroupPageReqVO extends PageParam {


    /**
     * 组名
     */
    private String name;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)

    private LocalDateTime[] createTime;

}
