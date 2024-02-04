package com.ymitcloud.module.rule.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.rule.controller.admin.request.LogicFlowConvertElRequest;
import com.ymitcloud.module.rule.controller.admin.request.ProcessPageRequest;
import com.ymitcloud.module.rule.controller.admin.request.ProcessSaveRequest;
import com.ymitcloud.module.rule.controller.admin.response.LogicFlowConvertElResponse;
import com.ymitcloud.module.rule.controller.admin.response.ProcessExecuteResponse;
import com.ymitcloud.module.rule.controller.admin.response.ProcessResponse;
import com.ymitcloud.module.rule.service.ProcessService;


/**
 * 规则引擎 - 流程管理
 */
@RestController
@RequestMapping("/rule/process")
public class ProcessController {
    @Autowired
    private ProcessService processService;

    /**
     * 分页查询
     * @param request
     * @return
     */
    @GetMapping("/page")
    public CommonResult<PageResult<ProcessResponse>> page(@Validated ProcessPageRequest request) {
        return CommonResult.success(processService.page(request));
    }


    /**
     * 获取详情
     * @param id 数据id
     * @return
     */
    @GetMapping("/{id}")
    public CommonResult<ProcessResponse> get(@PathVariable("id") String id) {
        return CommonResult.success( processService.get(id));
    }

    /**
     * "创建
     * @param request
     * @return
     */
    @PostMapping
    public CommonResult create(@Validated @RequestBody ProcessSaveRequest request) {
        processService.create(request);
        return CommonResult.success(null);
    }

    /**
     * 修改
     * @param id 数据id
     * @param request
     * @return
     */
    @PutMapping("/{id}")
    public CommonResult update(@PathVariable("id") String id, @Validated @RequestBody ProcessSaveRequest request) {
        processService.update(id,request);
        return CommonResult.success(null);
    }

    /**
     * 删除
     * @param ids 数据id列表
     * @return
     */
    @DeleteMapping("/{ids}")
    public CommonResult remove(@PathVariable("ids") List<String> ids) {
        processService.remove(ids);
        return CommonResult.success(null);
    }

    /**
     * LogicFlow转化el表达式
     * @param request
     * @return
     */
    @PostMapping("/logic-flow-convert-el")
    public CommonResult<LogicFlowConvertElResponse> logicFlowConvertEl(@Validated @RequestBody LogicFlowConvertElRequest request) {
        return CommonResult.success(processService.logicFlowConvertEl(request));
    }
    /**
     * 执行流程(执行一次)
     * @param id 要执行的流程数据id
     * @return
     */
    @PostMapping("/execute/{id}")
    public CommonResult<List<ProcessExecuteResponse>> execute(@PathVariable("id") String id) {
        return CommonResult.success(processService.execute(id));
    }
}
