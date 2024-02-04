package com.ymitcloud.module.bpm.controller.admin.definition;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.module.bpm.controller.admin.definition.vo.rule.BpmTaskAssignRuleCreateReqVO;
import com.ymitcloud.module.bpm.controller.admin.definition.vo.rule.BpmTaskAssignRuleRespVO;
import com.ymitcloud.module.bpm.controller.admin.definition.vo.rule.BpmTaskAssignRuleUpdateReqVO;
import com.ymitcloud.module.bpm.service.definition.BpmTaskAssignRuleService;


import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 任务分配规则
 */

@RestController
@RequestMapping("/bpm/task-assign-rule")
@Validated
public class BpmTaskAssignRuleController {

    @Resource
    private BpmTaskAssignRuleService taskAssignRuleService;


    /**
     * 获得任务分配规则列表
     * 
     * @param modelId             模型编号
     * @param processDefinitionId 流程定义的编号
     * @return
     */
    @GetMapping("/list")

    @PreAuthorize("@ss.hasPermission('bpm:task-assign-rule:query')")
    public CommonResult<List<BpmTaskAssignRuleRespVO>> getTaskAssignRuleList(
            @RequestParam(value = "modelId", required = false) String modelId,
            @RequestParam(value = "processDefinitionId", required = false) String processDefinitionId) {
        return success(taskAssignRuleService.getTaskAssignRuleList(modelId, processDefinitionId));
    }


    /**
     * 创建任务分配规则
     */
    @PostMapping("/create")

    @PreAuthorize("@ss.hasPermission('bpm:task-assign-rule:create')")
    public CommonResult<Long> createTaskAssignRule(@Valid @RequestBody BpmTaskAssignRuleCreateReqVO reqVO) {
        return success(taskAssignRuleService.createTaskAssignRule(reqVO));
    }


    /**
     * 更新任务分配规则
     * 
     * @param reqVO
     * @return
     */
    @PutMapping("/update")

    @PreAuthorize("@ss.hasPermission('bpm:task-assign-rule:update')")
    public CommonResult<Boolean> updateTaskAssignRule(@Valid @RequestBody BpmTaskAssignRuleUpdateReqVO reqVO) {
        taskAssignRuleService.updateTaskAssignRule(reqVO);
        return success(true);
    }
}
