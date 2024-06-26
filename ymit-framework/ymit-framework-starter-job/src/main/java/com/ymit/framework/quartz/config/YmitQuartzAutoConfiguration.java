package com.ymit.framework.quartz.config;

import com.ymit.framework.quartz.core.scheduler.SchedulerManager;
import org.quartz.Scheduler;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Optional;

/**
 * 定时任务 Configuration
 *
 * @author Y.S
 * @date 2024.05.16
 */
@AutoConfiguration
@EnableScheduling // 开启 Spring 自带的定时任务
public class YmitQuartzAutoConfiguration {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(YmitQuartzAutoConfiguration.class);

    @Bean
    public SchedulerManager schedulerManager(Optional<Scheduler> scheduler) {
        if (scheduler.isEmpty()) {
            log.info("[定时任务 - 已禁用]");
            return new SchedulerManager(null);
        }
        return new SchedulerManager(scheduler.get());
    }
}
