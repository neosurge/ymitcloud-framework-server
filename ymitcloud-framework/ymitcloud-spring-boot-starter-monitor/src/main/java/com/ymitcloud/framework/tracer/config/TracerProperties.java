package com.ymitcloud.framework.tracer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * BizTracer配置类
 *
 */
@ConfigurationProperties("ymitcloud.tracer")
@Data
public class TracerProperties {
}
