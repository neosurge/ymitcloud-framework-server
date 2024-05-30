package com.ymit.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目启动类
 *
 * @author Y.S
 * @date 2024.05.15
 */
@SuppressWarnings("SpringComponentScan")
@SpringBootApplication(scanBasePackages = {"${ymit.info.base-package}"})
public class YmitServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(YmitServerApplication.class, args);
    }
}
