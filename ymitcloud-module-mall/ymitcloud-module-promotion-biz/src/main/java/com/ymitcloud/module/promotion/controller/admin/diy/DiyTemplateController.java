package com.ymitcloud.module.promotion.controller.admin.diy;


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
import com.ymitcloud.module.promotion.controller.admin.diy.vo.template.DiyTemplateCreateReqVO;
import com.ymitcloud.module.promotion.controller.admin.diy.vo.template.DiyTemplatePageReqVO;
import com.ymitcloud.module.promotion.controller.admin.diy.vo.template.DiyTemplatePropertyRespVO;
import com.ymitcloud.module.promotion.controller.admin.diy.vo.template.DiyTemplatePropertyUpdateRequestVO;
import com.ymitcloud.module.promotion.controller.admin.diy.vo.template.DiyTemplateRespVO;
import com.ymitcloud.module.promotion.controller.admin.diy.vo.template.DiyTemplateUpdateReqVO;

import com.ymitcloud.module.promotion.convert.diy.DiyTemplateConvert;
import com.ymitcloud.module.promotion.dal.dataobject.diy.DiyPageDO;
import com.ymitcloud.module.promotion.dal.dataobject.diy.DiyTemplateDO;
import com.ymitcloud.module.promotion.service.diy.DiyPageService;
import com.ymitcloud.module.promotion.service.diy.DiyTemplateService;



import jakarta.annotation.Resource;
import jakarta.validation.Valid;


/**
 * 管理后台 - 装修模板
 */

@RestController
@RequestMapping("/promotion/diy-template")
@Validated
public class DiyTemplateController {

    @Resource
    private DiyTemplateService diyTemplateService;
    @Resource
    private DiyPageService diyPageService;


    /**
     * 创建装修模板
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")

    @PreAuthorize("@ss.hasPermission('promotion:diy-template:create')")
    public CommonResult<Long> createDiyTemplate(@Valid @RequestBody DiyTemplateCreateReqVO createReqVO) {
        return success(diyTemplateService.createDiyTemplate(createReqVO));
    }


    /**
     * 更新装修模板
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")

    @PreAuthorize("@ss.hasPermission('promotion:diy-template:update')")
    public CommonResult<Boolean> updateDiyTemplate(@Valid @RequestBody DiyTemplateUpdateReqVO updateReqVO) {
        diyTemplateService.updateDiyTemplate(updateReqVO);
        return success(true);
    }


    /**
     * 使用装修模板
     * 
     * @param id
     * @return
     */
    @PutMapping("/use")

    @PreAuthorize("@ss.hasPermission('promotion:diy-template:use')")
    public CommonResult<Boolean> useDiyTemplate(@RequestParam("id") Long id) {
        diyTemplateService.useDiyTemplate(id);
        return success(true);
    }


    /**
     * 删除装修模板
     * 
     * @param id 编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthorize("@ss.hasPermission('promotion:diy-template:delete')")
    public CommonResult<Boolean> deleteDiyTemplate(@RequestParam("id") Long id) {
        diyTemplateService.deleteDiyTemplate(id);
        return success(true);
    }


    /**
     * 获得装修模板
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    @PreAuthorize("@ss.hasPermission('promotion:diy-template:query')")
    public CommonResult<DiyTemplateRespVO> getDiyTemplate(@RequestParam("id") Long id) {
        DiyTemplateDO diyTemplate = diyTemplateService.getDiyTemplate(id);
        return success(DiyTemplateConvert.INSTANCE.convert(diyTemplate));
    }


    /**
     * 获得装修模板分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('promotion:diy-template:query')")
    public CommonResult<PageResult<DiyTemplateRespVO>> getDiyTemplatePage(@Valid DiyTemplatePageReqVO pageVO) {
        PageResult<DiyTemplateDO> pageResult = diyTemplateService.getDiyTemplatePage(pageVO);
        return success(DiyTemplateConvert.INSTANCE.convertPage(pageResult));
    }


    /**
     * 获得装修模板属性
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get-property")

    @PreAuthorize("@ss.hasPermission('promotion:diy-template:query')")
    public CommonResult<DiyTemplatePropertyRespVO> getDiyTemplateProperty(@RequestParam("id") Long id) {
        DiyTemplateDO diyTemplate = diyTemplateService.getDiyTemplate(id);
        List<DiyPageDO> pages = diyPageService.getDiyPageByTemplateId(id);
        return success(DiyTemplateConvert.INSTANCE.convertPropertyVo(diyTemplate, pages));
    }


    /**
     * 更新装修模板属性
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update-property")
    @PreAuthorize("@ss.hasPermission('promotion:diy-template:update')")
    public CommonResult<Boolean> updateDiyTemplateProperty(
            @Valid @RequestBody DiyTemplatePropertyUpdateRequestVO updateReqVO) {

        diyTemplateService.updateDiyTemplateProperty(updateReqVO);
        return success(true);
    }

}
