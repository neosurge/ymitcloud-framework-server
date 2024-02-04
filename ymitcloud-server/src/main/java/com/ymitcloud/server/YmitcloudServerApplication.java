package com.ymitcloud.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目的启动类
 * @author 
 */
@SuppressWarnings("SpringComponentScan")
@SpringBootApplication(scanBasePackages = {"${ymitcloud.info.base-package}"})
public class YmitcloudServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(YmitcloudServerApplication.class, args);
    }
}
