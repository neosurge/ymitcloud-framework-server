package com.ymitcloud.module.bpm.controller.admin.definition;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import java.io.IOException;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.framework.common.util.io.IoUtils;
import com.ymitcloud.module.bpm.controller.admin.definition.vo.model.BpmModeImportReqVO;
import com.ymitcloud.module.bpm.controller.admin.definition.vo.model.BpmModelCreateReqVO;
import com.ymitcloud.module.bpm.controller.admin.definition.vo.model.BpmModelPageItemRespVO;
import com.ymitcloud.module.bpm.controller.admin.definition.vo.model.BpmModelPageReqVO;
import com.ymitcloud.module.bpm.controller.admin.definition.vo.model.BpmModelRespVO;
import com.ymitcloud.module.bpm.controller.admin.definition.vo.model.BpmModelUpdateReqVO;
import com.ymitcloud.module.bpm.controller.admin.definition.vo.model.BpmModelUpdateStateReqVO;
import com.ymitcloud.module.bpm.convert.definition.BpmModelConvert;
import com.ymitcloud.module.bpm.service.definition.BpmModelService;


import jakarta.annotation.Resource;
import jakarta.validation.Valid;


/**
 * 管理后台 - 流程模型
 */

@RestController
@RequestMapping("/bpm/model")
@Validated
public class BpmModelController {

    @Resource
    private BpmModelService modelService;


    /**
     * 获得模型分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")

    public CommonResult<PageResult<BpmModelPageItemRespVO>> getModelPage(BpmModelPageReqVO pageVO) {
        return success(modelService.getModelPage(pageVO));
    }


    /**
     * 获得模型
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('bpm:model:query')")
    public CommonResult<BpmModelRespVO> getModel(@RequestParam("id") String id) {
        BpmModelRespVO model = modelService.getModel(id);
        return success(model);
    }


    /**
     * 新建模型
     * 
     * @param createRetVO
     * @return
     */
    @PostMapping("/create")

    @PreAuthorize("@ss.hasPermission('bpm:model:create')")
    public CommonResult<String> createModel(@Valid @RequestBody BpmModelCreateReqVO createRetVO) {
        return success(modelService.createModel(createRetVO, null));
    }


    /**
     * 修改模型
     * 
     * @param modelVO
     * @return
     */
    @PutMapping("/update")

    @PreAuthorize("@ss.hasPermission('bpm:model:update')")
    public CommonResult<Boolean> updateModel(@Valid @RequestBody BpmModelUpdateReqVO modelVO) {
        modelService.updateModel(modelVO);
        return success(true);
    }


    /**
     * 导入模型
     * 
     * @param importReqVO
     * @return
     * @throws IOException
     */
    @PostMapping("/import")

    @PreAuthorize("@ss.hasPermission('bpm:model:import')")
    public CommonResult<String> importModel(@Valid BpmModeImportReqVO importReqVO) throws IOException {
        BpmModelCreateReqVO createReqVO = BpmModelConvert.INSTANCE.convert(importReqVO);
        // 读取文件
        String bpmnXml = IoUtils.readUtf8(importReqVO.getBpmnFile().getInputStream(), false);
        return success(modelService.createModel(createReqVO, bpmnXml));
    }


    /**
     * 部署模型
     * 
     * @param id 编号
     * @return
     */
    @PostMapping("/deploy")

    @PreAuthorize("@ss.hasPermission('bpm:model:deploy')")
    public CommonResult<Boolean> deployModel(@RequestParam("id") String id) {
        modelService.deployModel(id);
        return success(true);
    }


    /**
     * 修改模型的状态 ，实际更新的部署的流程定义的状态
     * 
     * @param reqVO
     * @return
     */
    @PutMapping("/update-state")

    @PreAuthorize("@ss.hasPermission('bpm:model:update')")
    public CommonResult<Boolean> updateModelState(@Valid @RequestBody BpmModelUpdateStateReqVO reqVO) {
        modelService.updateModelState(reqVO.getId(), reqVO.getState());
        return success(true);
    }


    /**
     * 删除模型
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthorize("@ss.hasPermission('bpm:model:delete')")
    public CommonResult<Boolean> deleteModel(@RequestParam("id") String id) {
        modelService.deleteModel(id);
        return success(true);
    }
}
