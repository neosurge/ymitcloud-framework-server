package com.ymitcloud.server.controller;

import com.ymitcloud.framework.common.pojo.CommonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ymitcloud.framework.common.exception.enums.GlobalErrorCodeConstants.NOT_IMPLEMENTED;

/**
 * 默认 Controller，解决部分 module 未开启时的 404 提示
 */
@RestController
public class DefaultController {

    /**
     * 防止bpm未开启时404
     *
     * @return
     */
    @RequestMapping("/admin-api/bpm/**")
    public CommonResult<Boolean> bpm404() {
        return CommonResult.error(NOT_IMPLEMENTED.getCode(),
                "[工作流模块 ymitcloud-module-bpm - 已禁用]");
    }

    /**
     * 防止mp未开启时404
     *
     * @return
     */
    @RequestMapping("/admin-api/mp/**")
    public CommonResult<Boolean> mp404() {
        return CommonResult.error(NOT_IMPLEMENTED.getCode(),
                "[微信公众号 ymitcloud-module-mp - 已禁用]");
    }

    /**
     * 防止mall未开启时404
     *
     * @return
     */
    @RequestMapping(value = {"/admin-api/product/**", // 商品中心
            "/admin-api/trade/**", // 交易中心
            "/admin-api/promotion/**"})  // 营销中心
    public CommonResult<Boolean> mall404() {
        return CommonResult.error(NOT_IMPLEMENTED.getCode(),
                "[商城系统 ymitcloud-module-mall - 已禁用]");
    }

    /**
     * 防止report未开启时404
     *
     * @return
     */
    @RequestMapping(value = {"/admin-api/report/**"})
    public CommonResult<Boolean> report404() {
        return CommonResult.error(NOT_IMPLEMENTED.getCode(),
                "[报表模块 ymitcloud-module-report - 已禁用]");
    }

    /**
     * 防止pay未开启时404
     *
     * @return
     */
    @RequestMapping(value = {"/admin-api/pay/**"})
    public CommonResult<Boolean> pay404() {
        return CommonResult.error(NOT_IMPLEMENTED.getCode(),
                "[支付模块 ymitcloud-module-pay - 已禁用][参考 https://doc.ymitcloud.com/pay/build/ 开启]");
    }

}
