package com.ymitcloud.module.system.controller.admin.sms;

import static com.ymitcloud.framework.common.pojo.CommonResult.success;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.util.servlet.ServletUtils;
import com.ymitcloud.framework.operatelog.core.annotations.OperateLog;
import com.ymitcloud.framework.sms.core.enums.SmsChannelEnum;
import com.ymitcloud.module.system.service.sms.SmsSendService;

import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletRequest;

/**
 * 管理后台 - 短信回调
 */
@RestController
@RequestMapping("/system/sms/callback")
public class SmsCallbackController {

    @Resource
    private SmsSendService smsSendService;

    /**
     * 阿里云短信的回调
     * 
     * @param request
     * @return
     * @throws Throwable
     */
    @PostMapping("/aliyun")
    @PermitAll
    @OperateLog(enable = false)
    public CommonResult<Boolean> receiveAliyunSmsStatus(HttpServletRequest request) throws Throwable {
        String text = ServletUtils.getBody(request);
        smsSendService.receiveSmsStatus(SmsChannelEnum.ALIYUN.getCode(), text);
        return success(true);
    }

    /**
     * 腾讯云短信的回调
     * 
     * @param request
     * @return
     * @throws Throwable
     */
    @PostMapping("/tencent")
    @PermitAll
    @OperateLog(enable = false)
    public CommonResult<Boolean> receiveTencentSmsStatus(HttpServletRequest request) throws Throwable {
        String text = ServletUtils.getBody(request);
        smsSendService.receiveSmsStatus(SmsChannelEnum.TENCENT.getCode(), text);
        return success(true);
    }

}
