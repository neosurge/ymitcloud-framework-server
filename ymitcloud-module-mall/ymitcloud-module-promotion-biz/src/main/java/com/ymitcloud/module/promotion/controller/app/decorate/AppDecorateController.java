package com.ymitcloud.module.promotion.controller.app.decorate;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.enums.CommonStatusEnum;
import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.validation.InEnum;
import com.ymitcloud.module.promotion.controller.app.decorate.vo.AppDecorateComponentRespVO;
import com.ymitcloud.module.promotion.convert.decorate.DecorateComponentConvert;
import com.ymitcloud.module.promotion.enums.decorate.DecoratePageEnum;
import com.ymitcloud.module.promotion.service.decorate.DecorateComponentService;


import jakarta.annotation.Resource;

/**
 * 用户 APP - 店铺装修
 */

@RestController
@RequestMapping("/promotion/decorate")
@Validated
public class AppDecorateController {

    @Resource
    private DecorateComponentService decorateComponentService;


    /**
     * 获取指定页面的组件列表
     * 
     * @param page 页面编号
     * @return
     */
    @GetMapping("/list")

    public CommonResult<List<AppDecorateComponentRespVO>> getDecorateComponentListByPage(
            @RequestParam("page") @InEnum(DecoratePageEnum.class) Integer page) {
        return success(DecorateComponentConvert.INSTANCE.convertList(
                decorateComponentService.getDecorateComponentListByPage(page, CommonStatusEnum.ENABLE.getStatus())));
    }

}
