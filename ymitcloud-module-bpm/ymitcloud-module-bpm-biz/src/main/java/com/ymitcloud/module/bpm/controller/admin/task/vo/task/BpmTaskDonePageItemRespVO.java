package com.ymitcloud.module.bpm.controller.admin.task.vo.task;


import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
/**
 * 管理后台 - 流程任务的 Done 已完成的分页项 Response VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BpmTaskDonePageItemRespVO extends BpmTaskTodoPageItemRespVO {

    /**
     * 结束时间
     */
    private LocalDateTime endTime;
    /**
     * 持续时间
     */
    private Long durationInMillis;
    /**
     * 任务结果-参见 bpm_process_instance_result
     */
    private Integer result;
    /**
     * 审批建议
     */

    private String reason;

}
