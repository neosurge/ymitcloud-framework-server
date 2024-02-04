package com.ymitcloud.module.bpm.controller.admin.oa;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;

import com.ymitcloud.module.bpm.controller.admin.oa.vo.BpmOALeaveCreateReqVO;
import com.ymitcloud.module.bpm.controller.admin.oa.vo.BpmOALeavePageReqVO;
import com.ymitcloud.module.bpm.controller.admin.oa.vo.BpmOALeaveRespVO;
import com.ymitcloud.module.bpm.convert.oa.BpmOALeaveConvert;
import com.ymitcloud.module.bpm.dal.dataobject.oa.BpmOALeaveDO;

import com.ymitcloud.module.bpm.service.oa.BpmOALeaveService;


import jakarta.annotation.Resource;
import jakarta.validation.Valid;


/**
 * 管理后台 - OA 请假申请
 */

@RestController
@RequestMapping("/bpm/oa/leave")
@Validated
public class BpmOALeaveController {

    @Resource
    private BpmOALeaveService leaveService;


    /**
     * 创建请求申请
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('bpm:oa-leave:create')")

    public CommonResult<Long> createLeave(@Valid @RequestBody BpmOALeaveCreateReqVO createReqVO) {
        return success(leaveService.createLeave(getLoginUserId(), createReqVO));
    }


    /**
     * 获得请假申请
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('bpm:oa-leave:query')")

    public CommonResult<BpmOALeaveRespVO> getLeave(@RequestParam("id") Long id) {
        BpmOALeaveDO leave = leaveService.getLeave(id);
        return success(BpmOALeaveConvert.INSTANCE.convert(leave));
    }


    /**
     * 获得请假申请分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('bpm:oa-leave:query')")

    public CommonResult<PageResult<BpmOALeaveRespVO>> getLeavePage(@Valid BpmOALeavePageReqVO pageVO) {
        PageResult<BpmOALeaveDO> pageResult = leaveService.getLeavePage(getLoginUserId(), pageVO);
        return success(BpmOALeaveConvert.INSTANCE.convertPage(pageResult));
    }

}
