package com.ymit.framework.errorcode.core.generator;

/**
 * 错误码的自动生成器
 *
 * @author Y.S
 * @date 2024.05.23
 */
public interface ErrorCodeAutoGenerator {
    /**
     * 将配置类到错误码写入数据库
     */
    void execute();
}
