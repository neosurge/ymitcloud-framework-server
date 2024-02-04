package com.ymitcloud.module.bpm.controller.admin.task;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.bpm.controller.admin.task.vo.instance.BpmProcessInstanceCancelReqVO;
import com.ymitcloud.module.bpm.controller.admin.task.vo.instance.BpmProcessInstanceCreateReqVO;
import com.ymitcloud.module.bpm.controller.admin.task.vo.instance.BpmProcessInstanceMyPageReqVO;
import com.ymitcloud.module.bpm.controller.admin.task.vo.instance.BpmProcessInstancePageItemRespVO;
import com.ymitcloud.module.bpm.controller.admin.task.vo.instance.BpmProcessInstanceRespVO;
import com.ymitcloud.module.bpm.service.task.BpmProcessInstanceService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
/**
 * 管理后台 - 流程实例
 */

@RestController
@RequestMapping("/bpm/process-instance")
@Validated
public class BpmProcessInstanceController {

    @Resource
    private BpmProcessInstanceService processInstanceService;


    /**
     * 获得我的实例分页列表，在【我的流程】菜单中，进行调用
     * 
     * @param pageReqVO
     * @return
     */
    @GetMapping("/my-page")

    @PreAuthorize("@ss.hasPermission('bpm:process-instance:query')")
    public CommonResult<PageResult<BpmProcessInstancePageItemRespVO>> getMyProcessInstancePage(
            @Valid BpmProcessInstanceMyPageReqVO pageReqVO) {
        return success(processInstanceService.getMyProcessInstancePage(getLoginUserId(), pageReqVO));
    }

/**
 * 新建流程实例
 * @param createReqVO
 * @return
 */
    @PostMapping("/create")

    @PreAuthorize("@ss.hasPermission('bpm:process-instance:query')")
    public CommonResult<String> createProcessInstance(@Valid @RequestBody BpmProcessInstanceCreateReqVO createReqVO) {
        return success(processInstanceService.createProcessInstance(getLoginUserId(), createReqVO));
    }


    /**
     * 获得指定流程实例，在【流程详细】界面中，进行调用
     * @param id 流程实例的编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('bpm:process-instance:query')")
    public CommonResult<BpmProcessInstanceRespVO> getProcessInstance(@RequestParam("id") String id) {
        return success(processInstanceService.getProcessInstanceVO(id));
    }


    /**
     * 取消流程实例
     * @param cancelReqVO 撤回发起的流程
     * @return
     */
    @DeleteMapping("/cancel")

    @PreAuthorize("@ss.hasPermission('bpm:process-instance:cancel')")
    public CommonResult<Boolean> cancelProcessInstance(@Valid @RequestBody BpmProcessInstanceCancelReqVO cancelReqVO) {
        processInstanceService.cancelProcessInstance(getLoginUserId(), cancelReqVO);
        return success(true);
    }
}
