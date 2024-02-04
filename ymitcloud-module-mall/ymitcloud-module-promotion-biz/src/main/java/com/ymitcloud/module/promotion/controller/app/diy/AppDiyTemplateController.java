package com.ymitcloud.module.promotion.controller.app.diy;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.findFirst;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.module.promotion.controller.app.diy.vo.AppDiyTemplatePropertyRespVO;
import com.ymitcloud.module.promotion.convert.diy.DiyTemplateConvert;
import com.ymitcloud.module.promotion.dal.dataobject.diy.DiyPageDO;
import com.ymitcloud.module.promotion.dal.dataobject.diy.DiyTemplateDO;
import com.ymitcloud.module.promotion.service.diy.DiyPageService;
import com.ymitcloud.module.promotion.service.diy.DiyTemplateService;


import jakarta.annotation.Resource;

/**
 * 用户 APP - 装修模板
 */

@RestController
@RequestMapping("/promotion/diy-template")
@Validated
public class AppDiyTemplateController {

    @Resource
    private DiyTemplateService diyTemplateService;
    @Resource
    private DiyPageService diyPageService;


    /**
     * 使用中的装修模板
     * 
     * @return
     */
    @GetMapping("/used")

    public CommonResult<AppDiyTemplatePropertyRespVO> getUsedDiyTemplate() {
        DiyTemplateDO diyTemplate = diyTemplateService.getUsedDiyTemplate();
        return success(buildVo(diyTemplate));
    }


    /**
     * 获得装修模板
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get")

    public CommonResult<AppDiyTemplatePropertyRespVO> getDiyTemplate(@RequestParam("id") Long id) {
        DiyTemplateDO diyTemplate = diyTemplateService.getDiyTemplate(id);
        return success(buildVo(diyTemplate));
    }

    private AppDiyTemplatePropertyRespVO buildVo(DiyTemplateDO diyTemplate) {
        if (diyTemplate == null) {
            return null;
        }
        // 查询模板下的页面
        List<DiyPageDO> pages = diyPageService.getDiyPageByTemplateId(diyTemplate.getId());
        String home = findFirst(pages, page -> "首页".equals(page.getName()), DiyPageDO::getProperty);
        String user = findFirst(pages, page -> "我的".equals(page.getName()), DiyPageDO::getProperty);
        // 拼接返回
        return DiyTemplateConvert.INSTANCE.convertPropertyVo2(diyTemplate, home, user);
    }

}
