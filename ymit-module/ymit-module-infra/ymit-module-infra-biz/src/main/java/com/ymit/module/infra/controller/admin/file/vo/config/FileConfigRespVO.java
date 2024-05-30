package com.ymit.module.infra.controller.admin.file.vo.config;

import com.ymit.framework.file.core.client.FileClientConfig;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 管理后台 - 文件配置 Response VO
 */
@Data
public class FileConfigRespVO {

    /**
     * 编号
     */
    private Long id;

    /**
     * 配置名
     */
    private String name;

    /**
     * 存储器，参见 FileStorageEnum 枚举类
     */
    private Integer storage;
    /**
     * 是否为主配置
     */
    private Boolean master;
    /**
     * 存储配置
     */
    private FileClientConfig config;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
