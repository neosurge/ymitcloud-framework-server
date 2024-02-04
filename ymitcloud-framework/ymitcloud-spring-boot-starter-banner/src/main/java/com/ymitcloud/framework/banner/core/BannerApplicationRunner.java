package com.ymitcloud.framework.banner.core;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.util.ClassUtils;
import java.util.concurrent.TimeUnit;

/**
 * 项目启动成功后，提供文档相关的地址
 */
@Slf4j
public class BannerApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        ThreadUtil.execute(() -> {
            ThreadUtil.sleep(1, TimeUnit.SECONDS); // 延迟 1 秒，保证输出到结尾

            // 数据报表
            if (isNotPresent("com.ymitcloud.module.report.framework.security.config.SecurityConfiguration")) {
                System.out.println("[报表模块 ymitcloud-module-report - 已禁用]");
            }
            // 工作流
            if (isNotPresent("com.ymitcloud.framework.flowable.config.YmitcloudFlowableConfiguration")) {
                System.out.println("[工作流模块 ymitcloud-module-bpm - 已禁用]");
            }
            // 微信公众号
            if (isNotPresent("com.ymitcloud.module.mp.framework.mp.config.MpConfiguration")) {
                System.out.println("[微信公众号 ymitcloud-module-mp - 已禁用]");
            }
            // 商城系统
            if (isNotPresent("com.ymitcloud.module.trade.framework.web.config.TradeWebConfiguration")) {
                System.out.println("[商城系统 ymitcloud-module-mall - 已禁用]");
            }
            // 支付平台
            if (isNotPresent("com.ymitcloud.module.pay.framework.pay.config.PayConfiguration")) {
                System.out.println("[支付系统 ymitcloud-module-pay - 已禁用]");
            }
        });
    }

    private static boolean isNotPresent(String className) {
        return !ClassUtils.isPresent(className, ClassUtils.getDefaultClassLoader());
    }

}
