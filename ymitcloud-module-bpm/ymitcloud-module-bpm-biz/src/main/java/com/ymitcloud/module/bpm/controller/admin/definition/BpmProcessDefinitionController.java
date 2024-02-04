package com.ymitcloud.module.bpm.controller.admin.definition;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.bpm.controller.admin.definition.vo.process.BpmProcessDefinitionListReqVO;
import com.ymitcloud.module.bpm.controller.admin.definition.vo.process.BpmProcessDefinitionPageItemRespVO;
import com.ymitcloud.module.bpm.controller.admin.definition.vo.process.BpmProcessDefinitionPageReqVO;
import com.ymitcloud.module.bpm.controller.admin.definition.vo.process.BpmProcessDefinitionRespVO;
import com.ymitcloud.module.bpm.service.definition.BpmProcessDefinitionService;


import jakarta.annotation.Resource;

/**
 * 管理后台 - 流程定义
 */

@RestController
@RequestMapping("/bpm/process-definition")
@Validated
public class BpmProcessDefinitionController {

    @Resource
    private BpmProcessDefinitionService bpmDefinitionService;


    /**
     * 获得流程定义分页
     * 
     * @param pageReqVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('bpm:process-definition:query')")
    public CommonResult<PageResult<BpmProcessDefinitionPageItemRespVO>> getProcessDefinitionPage(
            BpmProcessDefinitionPageReqVO pageReqVO) {
        return success(bpmDefinitionService.getProcessDefinitionPage(pageReqVO));
    }


    /**
     * 获得流程定义列表
     * 
     * @param listReqVO
     * @return
     */
    @GetMapping("/list")

    @PreAuthorize("@ss.hasPermission('bpm:process-definition:query')")
    public CommonResult<List<BpmProcessDefinitionRespVO>> getProcessDefinitionList(
            BpmProcessDefinitionListReqVO listReqVO) {
        return success(bpmDefinitionService.getProcessDefinitionList(listReqVO));
    }


    /**
     * 获得流程定义的 BPMN XML
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get-bpmn-xml")

    @PreAuthorize("@ss.hasPermission('bpm:process-definition:query')")
    public CommonResult<String> getProcessDefinitionBpmnXML(@RequestParam("id") String id) {
        String bpmnXML = bpmDefinitionService.getProcessDefinitionBpmnXML(id);
        return success(bpmnXML);
    }
}
