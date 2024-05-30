package com.ymit.framework.dict.config;

import com.ymit.framework.dict.core.util.DictFrameworkUtils;
import com.ymit.module.system.api.dict.DictDataApi;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 字典相关自动配置类
 *
 * @author Y.S
 * @date 2024.05.23
 */
@AutoConfiguration
public class YmitDictAutoConfiguration {
    @Bean
    @SuppressWarnings("InstantiationOfUtilityClass")
    public DictFrameworkUtils dictUtils(DictDataApi dictDataApi) {
        DictFrameworkUtils.init(dictDataApi);
        return new DictFrameworkUtils();
    }
}
