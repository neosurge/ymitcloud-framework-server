package com.ymitcloud.module.bpm.controller.admin.task.vo.instance;


import static com.ymitcloud.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.ymitcloud.framework.common.pojo.PageParam;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 管理后台 - 流程实例的分页 Item Response VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BpmProcessInstanceMyPageReqVO extends PageParam {

    /**
     * 流程名称
     */
    private String name;
    /**
     * 流程定义的编号
     */
    private String processDefinitionId;
    /**
     * 流程实例的状态-参见 bpm_process_instance_status
     */
    private Integer status;
    /**
     * 流程实例的结果-参见 bpm_process_instance_result
     */
    private Integer result;

    /**
     * 流程分类-参见 bpm_model_category 数据字典
     */
    private String category;
    /**
     * 创建时间
     */

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
