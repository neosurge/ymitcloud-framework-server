package com.ymitcloud.module.bpm.controller.admin.definition;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import java.util.List;

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
import com.ymitcloud.module.bpm.controller.admin.definition.vo.form.BpmFormCreateReqVO;
import com.ymitcloud.module.bpm.controller.admin.definition.vo.form.BpmFormPageReqVO;
import com.ymitcloud.module.bpm.controller.admin.definition.vo.form.BpmFormRespVO;
import com.ymitcloud.module.bpm.controller.admin.definition.vo.form.BpmFormSimpleRespVO;
import com.ymitcloud.module.bpm.controller.admin.definition.vo.form.BpmFormUpdateReqVO;
import com.ymitcloud.module.bpm.convert.definition.BpmFormConvert;
import com.ymitcloud.module.bpm.dal.dataobject.definition.BpmFormDO;
import com.ymitcloud.module.bpm.service.definition.BpmFormService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 动态表单
 */

@RestController
@RequestMapping("/bpm/form")
@Validated
public class BpmFormController {

    @Resource
    private BpmFormService formService;


    /**
     * 创建动态表单
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")

    @PreAuthorize("@ss.hasPermission('bpm:form:create')")
    public CommonResult<Long> createForm(@Valid @RequestBody BpmFormCreateReqVO createReqVO) {
        return success(formService.createForm(createReqVO));
    }


    /**
     * 更新动态表单
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")

    @PreAuthorize("@ss.hasPermission('bpm:form:update')")
    public CommonResult<Boolean> updateForm(@Valid @RequestBody BpmFormUpdateReqVO updateReqVO) {
        formService.updateForm(updateReqVO);
        return success(true);
    }


    /**
     * 删除动态表单
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthorize("@ss.hasPermission('bpm:form:delete')")
    public CommonResult<Boolean> deleteForm(@RequestParam("id") Long id) {
        formService.deleteForm(id);
        return success(true);
    }


    /**
     * 获得动态表单
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('bpm:form:query')")
    public CommonResult<BpmFormRespVO> getForm(@RequestParam("id") Long id) {
        BpmFormDO form = formService.getForm(id);
        return success(BpmFormConvert.INSTANCE.convert(form));
    }


    /**
     * 获得动态表单的精简列表，用于表单下拉框
     * 
     * @return
     */
    @GetMapping("/list-all-simple")

    public CommonResult<List<BpmFormSimpleRespVO>> getSimpleForms() {
        List<BpmFormDO> list = formService.getFormList();
        return success(BpmFormConvert.INSTANCE.convertList2(list));
    }


    /**
     * 获得动态表单分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('bpm:form:query')")
    public CommonResult<PageResult<BpmFormRespVO>> getFormPage(@Valid BpmFormPageReqVO pageVO) {
        PageResult<BpmFormDO> pageResult = formService.getFormPage(pageVO);
        return success(BpmFormConvert.INSTANCE.convertPage(pageResult));
    }

}
