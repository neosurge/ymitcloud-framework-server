package com.ymitcloud.module.bpm.controller.admin.task;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.web.core.util.WebFrameworkUtils.getLoginUserId;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.bpm.controller.admin.task.vo.task.BpmTaskAddSignReqVO;
import com.ymitcloud.module.bpm.controller.admin.task.vo.task.BpmTaskApproveReqVO;
import com.ymitcloud.module.bpm.controller.admin.task.vo.task.BpmTaskDelegateReqVO;
import com.ymitcloud.module.bpm.controller.admin.task.vo.task.BpmTaskDonePageItemRespVO;
import com.ymitcloud.module.bpm.controller.admin.task.vo.task.BpmTaskDonePageReqVO;
import com.ymitcloud.module.bpm.controller.admin.task.vo.task.BpmTaskRejectReqVO;
import com.ymitcloud.module.bpm.controller.admin.task.vo.task.BpmTaskRespVO;
import com.ymitcloud.module.bpm.controller.admin.task.vo.task.BpmTaskReturnReqVO;
import com.ymitcloud.module.bpm.controller.admin.task.vo.task.BpmTaskSimpleRespVO;
import com.ymitcloud.module.bpm.controller.admin.task.vo.task.BpmTaskSubSignReqVO;
import com.ymitcloud.module.bpm.controller.admin.task.vo.task.BpmTaskSubSignRespVO;
import com.ymitcloud.module.bpm.controller.admin.task.vo.task.BpmTaskTodoPageItemRespVO;
import com.ymitcloud.module.bpm.controller.admin.task.vo.task.BpmTaskTodoPageReqVO;
import com.ymitcloud.module.bpm.controller.admin.task.vo.task.BpmTaskUpdateAssigneeReqVO;
import com.ymitcloud.module.bpm.service.task.BpmTaskService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 流程任务实例
 */

@RestController
@RequestMapping("/bpm/task")
@Validated
public class BpmTaskController {

    @Resource
    private BpmTaskService taskService;


    /**
     * 获取 Todo 待办任务分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("todo-page")

    @PreAuthorize("@ss.hasPermission('bpm:task:query')")
    public CommonResult<PageResult<BpmTaskTodoPageItemRespVO>> getTodoTaskPage(@Valid BpmTaskTodoPageReqVO pageVO) {
        return success(taskService.getTodoTaskPage(getLoginUserId(), pageVO));
    }


    /**
     * 获取 Done 已办任务分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("done-page")

    @PreAuthorize("@ss.hasPermission('bpm:task:query')")
    public CommonResult<PageResult<BpmTaskDonePageItemRespVO>> getDoneTaskPage(@Valid BpmTaskDonePageReqVO pageVO) {
        return success(taskService.getDoneTaskPage(getLoginUserId(), pageVO));
    }


    /**
     * 获得指定流程实例的任务列表,包括完成的、未完成的
     * 
     * @param processInstanceId 流程实例的编号
     * @return
     */
    @GetMapping("/list-by-process-instance-id")

    @PreAuthorize("@ss.hasPermission('bpm:task:query')")
    public CommonResult<List<BpmTaskRespVO>> getTaskListByProcessInstanceId(
            @RequestParam("processInstanceId") String processInstanceId) {
        return success(taskService.getTaskListByProcessInstanceId(processInstanceId));
    }


    /**
     * 通过任务
     * 
     * @param reqVO
     * @return
     */
    @PutMapping("/approve")

    @PreAuthorize("@ss.hasPermission('bpm:task:update')")
    public CommonResult<Boolean> approveTask(@Valid @RequestBody BpmTaskApproveReqVO reqVO) {
        taskService.approveTask(getLoginUserId(), reqVO);
        return success(true);
    }


    /**
     * 不通过任务
     * 
     * @param reqVO
     * @return
     */
    @PutMapping("/reject")

    @PreAuthorize("@ss.hasPermission('bpm:task:update')")
    public CommonResult<Boolean> rejectTask(@Valid @RequestBody BpmTaskRejectReqVO reqVO) {
        taskService.rejectTask(getLoginUserId(), reqVO);
        return success(true);
    }


    /**
     * 更新任务的负责人,用于【流程详情】的【转派】按钮
     * 
     * @param reqVO
     * @return
     */
    @PutMapping("/update-assignee")

    @PreAuthorize("@ss.hasPermission('bpm:task:update')")
    public CommonResult<Boolean> updateTaskAssignee(@Valid @RequestBody BpmTaskUpdateAssigneeReqVO reqVO) {
        taskService.updateTaskAssignee(getLoginUserId(), reqVO);
        return success(true);
    }


    /**
     * 获取所有可回退的节点,用于【流程详情】的【回退】按钮
     * 
     * @param taskId 当前任务ID
     * @return
     */
    @GetMapping("/return-list")

    @PreAuthorize("@ss.hasPermission('bpm:task:update')")
    public CommonResult<List<BpmTaskSimpleRespVO>> getReturnList(@RequestParam("taskId") String taskId) {
        return success(taskService.getReturnTaskList(taskId));
    }


    /**
     * 回退任务，用于【流程详情】的【回退】按钮
     * 
     * @param reqVO
     * @return
     */
    @PutMapping("/return")

    @PreAuthorize("@ss.hasPermission('bpm:task:update')")
    public CommonResult<Boolean> returnTask(@Valid @RequestBody BpmTaskReturnReqVO reqVO) {
        taskService.returnTask(getLoginUserId(), reqVO);
        return success(true);
    }


    /**
     * 委派任务，用于【流程详情】的【委派】按钮。和向前【加签】有点像，唯一区别是【委托】没有单独创立任务
     * 
     * @param reqVO
     * @return
     */
    @PutMapping("/delegate")

    @PreAuthorize("@ss.hasPermission('bpm:task:update')")
    public CommonResult<Boolean> delegateTask(@Valid @RequestBody BpmTaskDelegateReqVO reqVO) {
        taskService.delegateTask(getLoginUserId(), reqVO);
        return success(true);
    }


    /**
     * 加签，before 前加签，after 后加签
     * @param reqVO
     * @return
     */
    @PutMapping("/create-sign")

    @PreAuthorize("@ss.hasPermission('bpm:task:update')")
    public CommonResult<Boolean> createSignTask(@Valid @RequestBody BpmTaskAddSignReqVO reqVO) {
        taskService.createSignTask(getLoginUserId(), reqVO);
        return success(true);
    }

/**
 * 减签
 * @param reqVO
 * @return
 */
    @DeleteMapping("/delete-sign")

    @PreAuthorize("@ss.hasPermission('bpm:task:update')")
    public CommonResult<Boolean> deleteSignTask(@Valid @RequestBody BpmTaskSubSignReqVO reqVO) {
        taskService.deleteSignTask(getLoginUserId(), reqVO);
        return success(true);
    }


    /**
     * 获取能被减签的任务
     * @param parentId 父级任务 ID
     * @return
     */
    @GetMapping("children-list")

    @PreAuthorize("@ss.hasPermission('bpm:task:update')")
    public CommonResult<List<BpmTaskSubSignRespVO>> getChildrenTaskList(@RequestParam("parentId") String parentId) {
        return success(taskService.getChildrenTaskList(parentId));
    }

}
