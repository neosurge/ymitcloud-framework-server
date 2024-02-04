package com.ymitcloud.module.bpm.controller.admin.task;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import java.util.List;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.module.bpm.controller.admin.task.vo.activity.BpmActivityRespVO;
import com.ymitcloud.module.bpm.service.task.BpmActivityService;

import jakarta.annotation.Resource;

/**
 * 管理后台 - 流程活动实例
 */

@RestController
@RequestMapping("/bpm/activity")
@Validated
public class BpmActivityController {

    @Resource
    private BpmActivityService activityService;


    /**
     * 生成指定流程实例的高亮流程图
     * 
     * @param processInstanceId 流程实例的编号
     * @return
     */
    @GetMapping("/list")

    @PreAuthorize("@ss.hasPermission('bpm:task:query')")
    public CommonResult<List<BpmActivityRespVO>> getActivityList(
            @RequestParam("processInstanceId") String processInstanceId) {
        return success(activityService.getActivityListByProcessInstanceId(processInstanceId));
    }
}
