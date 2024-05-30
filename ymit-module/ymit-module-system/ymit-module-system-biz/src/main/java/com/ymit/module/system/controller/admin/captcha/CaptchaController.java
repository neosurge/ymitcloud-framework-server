package com.ymit.module.system.controller.admin.captcha;

import cn.hutool.core.util.StrUtil;
import com.ymit.framework.common.util.servlet.ServletUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理后台 - 验证码
 */
@RestController("adminCaptchaController")
@RequestMapping("/system/captcha")
public class CaptchaController {
    //TODO:验证码
//
//    @Resource
//    private CaptchaService captchaService;

//    /**
//     * 获得验证码
//     *
//     * @param data
//     * @param request
//     * @return
//     */
//    @PostMapping({ "/get" })
//    @PermitAll
//    @OperateLog(enable = false) // 避免 Post 请求被记录操作日志
//    public ResponseModel get(@RequestBody CaptchaVO data, HttpServletRequest request) {
//        assert request.getRemoteHost() != null;
//        data.setBrowserInfo(getRemoteId(request));
//        return captchaService.get(data);
//    }
//
//    /**
//     * 校验验证码
//     *
//     * @param data
//     * @param request
//     * @return
//     */
//    @PostMapping("/check")
//    @PermitAll
//    @OperateLog(enable = false) // 避免 Post 请求被记录操作日志
//    public ResponseModel check(@RequestBody CaptchaVO data, HttpServletRequest request) {
//        data.setBrowserInfo(getRemoteId(request));
//        return captchaService.check(data);
//    }

    public static String getRemoteId(HttpServletRequest request) {
        String ip = ServletUtils.getClientIP(request);
        String ua = request.getHeader("user-agent");
        if (StrUtil.isNotBlank(ip)) {
            return ip + ua;
        }
        return request.getRemoteAddr() + ua;
    }

}
