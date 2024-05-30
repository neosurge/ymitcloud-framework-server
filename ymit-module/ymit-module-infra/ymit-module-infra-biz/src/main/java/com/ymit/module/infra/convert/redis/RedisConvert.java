package com.ymit.module.infra.convert.redis;

import cn.hutool.core.util.StrUtil;
import com.ymit.module.infra.controller.admin.redis.vo.RedisMonitorRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Properties;

@Mapper
public interface RedisConvert {

    RedisConvert INSTANCE = Mappers.getMapper(RedisConvert.class);

    default RedisMonitorRespVO build(Properties info, Long dbSize, Properties commandStats) {
        RedisMonitorRespVO respVO = RedisMonitorRespVO.builder().info(info).dbSize(dbSize).commandStats(new ArrayList<>(commandStats.size())).build();
        commandStats.forEach((key, value) -> {
            if (StrUtil.contains(key.toString(), "cmdstat_")) {
                respVO.getCommandStats()
                        .add(RedisMonitorRespVO.CommandStat.builder()
                                .command(StrUtil.subAfter((String) key, "cmdstat_", false))
                                .calls(Long.valueOf(StrUtil.subBetween((String) value, "calls=", ",")))
                                .usec(Long.valueOf(StrUtil.subBetween((String) value, "usec=", ",")))
                                .build()
                        );
            }
        });
        return respVO;
    }

}