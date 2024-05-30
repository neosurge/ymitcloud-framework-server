package com.ymit.module.infra.controller.admin.file.vo.config;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Map;

/**
 * 管理后台 - 文件配置创建/修改 Request VO
 */
@Data
public class FileConfigSaveReqVO {
    /**
     * 编号
     */
    private Long id;
    /**
     * 配置名
     */
    @NotNull(message = "配置名不能为空")
    private String name;

    /**
     * 存储器，参见 FileStorageEnum 枚举类
     */
    @NotNull(message = "存储器不能为空")
    private Integer storage;
    /**
     * 存储配置,配置是动态参数，所以使用 Map 接收
     */
    @NotNull(message = "存储配置不能为空")
    private Map<String, Object> config;
    /**
     * 备注
     */
    private String remark;

}
