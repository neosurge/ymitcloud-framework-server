package com.ymit.module.infra.controller.admin.redis.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Properties;

/**
 * 管理后台 - Redis 监控信息 Response VO
 */
@Data
@Builder
@AllArgsConstructor
public class RedisMonitorRespVO {
    /**
     * Redis info 指令结果,具体字段，查看 Redis 文档
     */
    private Properties info;
    /**
     * Redis key 数量
     */
    private Long dbSize;
    /**
     * CommandStat 数组
     */
    private List<CommandStat> commandStats;

    /**
     * Redis 命令统计结果
     */
    @Data
    @Builder
    @AllArgsConstructor
    public static class CommandStat {
        /**
         * Redis 命令
         */
        private String command;

        /**
         * 调用次数
         */
        private Long calls;

        /**
         * 消耗 CPU 秒数
         */
        private Long usec;

    }

}
