package com.ymit.framework.dict.core.util;

import cn.hutool.core.util.ObjectUtil;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.ymit.framework.common.core.KeyValue;
import com.ymit.framework.common.util.cache.CacheUtils;
import com.ymit.module.system.api.dict.DictDataApi;
import com.ymit.module.system.api.dict.dto.DictDataRespDTO;
import jakarta.annotation.Nonnull;

import java.time.Duration;
import java.util.concurrent.ExecutionException;

/**
 * 字典工具类
 *
 * @author Y.S
 * @date 2024.05.23
 */
public class DictFrameworkUtils {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DictFrameworkUtils.class);
    private static final DictDataRespDTO DICT_DATA_NULL = new DictDataRespDTO();
    private static DictDataApi dictDataApi;
    /**
     * 针对 {@link #getDictDataLabel(String, String)} 的缓存
     */
    private static final LoadingCache<KeyValue<String, String>, DictDataRespDTO> GET_DICT_DATA_CACHE = CacheUtils.buildAsyncReloadingCache(Duration.ofMinutes(1L), // 过期时间 1 分钟
            new CacheLoader<KeyValue<String, String>, DictDataRespDTO>() {
                @Override
                @Nonnull
                public DictDataRespDTO load(@Nonnull KeyValue<String, String> key) {
                    return ObjectUtil.defaultIfNull(dictDataApi.getDictData(key.getKey(), key.getValue()), DICT_DATA_NULL);
                }
            });
    /**
     * 针对 {@link #parseDictDataValue(String, String)} 的缓存
     */
    private static final LoadingCache<KeyValue<String, String>, DictDataRespDTO> PARSE_DICT_DATA_CACHE = CacheUtils.buildAsyncReloadingCache(Duration.ofMinutes(1L), // 过期时间 1 分钟
            new CacheLoader<KeyValue<String, String>, DictDataRespDTO>() {
                @Override
                @Nonnull
                public DictDataRespDTO load(@Nonnull KeyValue<String, String> key) {
                    return ObjectUtil.defaultIfNull(dictDataApi.parseDictData(key.getKey(), key.getValue()), DICT_DATA_NULL);
                }
            });

    public static void init(DictDataApi dictDataApi) {
        DictFrameworkUtils.dictDataApi = dictDataApi;
        log.info("[init][初始化 DictFrameworkUtils 成功]");
    }

    public static String getDictDataLabel(String dictType, Integer value) {
        try {
            return GET_DICT_DATA_CACHE.get(new KeyValue<>(dictType, String.valueOf(value))).getLabel();
        } catch (ExecutionException e) {
            return null;
        }
    }

    public static String getDictDataLabel(String dictType, String value) {
        try {
            return GET_DICT_DATA_CACHE.get(new KeyValue<>(dictType, value)).getLabel();
        } catch (ExecutionException e) {
            return null;
        }
    }

    public static String parseDictDataValue(String dictType, String label) {
        try {
            return PARSE_DICT_DATA_CACHE.get(new KeyValue<>(dictType, label)).getValue();
        } catch (ExecutionException e) {
            return null;
        }
    }
}
