package com.ymit.module.infra.controller.admin.job.vo.job;

import com.ymit.framework.common.data.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serial;

/**
 * 管理后台 - 定时任务分页 Request VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class JobPageReqVO extends PageParam {
    @Serial
    private static final long serialVersionUID = -450688317850196451L;
    /**
     * 任务名称，模糊匹配
     */
    private String name;
    /**
     * 任务状态，参见 JobStatusEnum 枚举
     */
    private Integer status;
    /**
     * 处理器的名字，模糊匹配
     */
    private String handlerName;

}
