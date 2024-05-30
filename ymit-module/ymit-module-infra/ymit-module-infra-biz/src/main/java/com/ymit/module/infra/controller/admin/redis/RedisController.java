package com.ymit.module.infra.controller.admin.redis;

import com.ymit.framework.common.data.CommonResult;
import com.ymit.module.infra.controller.admin.redis.vo.RedisMonitorRespVO;
import com.ymit.module.infra.convert.redis.RedisConvert;
import jakarta.annotation.Resource;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

import static com.ymit.framework.common.data.CommonResult.success;

/**
 * 管理后台 - Redis 监控
 */
@RestController
@RequestMapping("/infra/redis")
public class RedisController {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 获得 Redis 监控信息
     *
     * @return
     */
    @GetMapping("/get-monitor-info")
    @PreAuthorize("@ss.hasPermission('infra:redis:get-monitor-info')")
    public CommonResult<RedisMonitorRespVO> getRedisMonitorInfo() {
        // 获得 Redis 统计信息
        Properties info = this.stringRedisTemplate.execute((RedisCallback<Properties>) RedisServerCommands::info);
        Long dbSize = this.stringRedisTemplate.execute(RedisServerCommands::dbSize);
        Properties commandStats = this.stringRedisTemplate.execute((
                RedisCallback<Properties>) connection -> connection.commands().info());
        assert commandStats != null; // 断言，避免警告
        // 拼接结果返回
        return success(RedisConvert.INSTANCE.build(info, dbSize, commandStats));
    }

}
