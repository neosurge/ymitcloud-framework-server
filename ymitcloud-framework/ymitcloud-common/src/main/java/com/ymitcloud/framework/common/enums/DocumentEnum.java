package com.ymitcloud.framework.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 文档地址
 *
 * @author 
 */
@Getter
@AllArgsConstructor
public enum DocumentEnum {

    REDIS_INSTALL("https://gitee.com/zhijiantianya/ymitcloud/issues/I4VCSJ", "Redis 安装文档"),
    TENANT("https://doc.ymitcloud.com", "SaaS 多租户文档");

    private final String url;
    private final String memo;

}
