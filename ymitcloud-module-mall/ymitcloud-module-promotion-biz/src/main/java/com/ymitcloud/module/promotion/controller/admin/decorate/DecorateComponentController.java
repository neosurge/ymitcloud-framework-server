package com.ymitcloud.module.promotion.controller.admin.decorate;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.validation.InEnum;
import com.ymitcloud.module.promotion.controller.admin.decorate.vo.DecorateComponentRespVO;
import com.ymitcloud.module.promotion.controller.admin.decorate.vo.DecorateComponentSaveReqVO;
import com.ymitcloud.module.promotion.convert.decorate.DecorateComponentConvert;
import com.ymitcloud.module.promotion.enums.decorate.DecoratePageEnum;
import com.ymitcloud.module.promotion.service.decorate.DecorateComponentService;


import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 店铺页面装修
 */

@RestController
@RequestMapping("/promotion/decorate")
@Validated
public class DecorateComponentController {

    @Resource
    private DecorateComponentService decorateComponentService;


    /**
     * 保存页面装修组件
     * 
     * @param reqVO
     * @return
     */
    @PostMapping("/save")

    @PreAuthorize("@ss.hasPermission('promotion:decorate:save')")
    public CommonResult<Boolean> saveDecorateComponent(@Valid @RequestBody DecorateComponentSaveReqVO reqVO) {
        decorateComponentService.saveDecorateComponent(reqVO);
        return success(true);
    }


    /**
     * 获取指定页面的组件列表
     * 
     * @param page 页面 id
     * @return
     */
    @GetMapping("/list")
    @PreAuthorize("@ss.hasPermission('promotion:decorate:query')")
    public CommonResult<List<DecorateComponentRespVO>> getDecorateComponentListByPage(
            @RequestParam("page") @InEnum(DecoratePageEnum.class) Integer page) {
        return success(DecorateComponentConvert.INSTANCE
                .convertList02(decorateComponentService.getDecorateComponentListByPage(page, null)));

    }

}
