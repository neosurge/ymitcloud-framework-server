package com.ymit.module.infra.controller.admin.file.vo.file;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * 管理后台 - 上传文件 Request VO
 */
@Data
public class FileUploadReqVO {

    /**
     * 文件附件
     */
    @NotNull(message = "文件附件不能为空")
    private MultipartFile file;
    /**
     * 文件附件
     */
    private String path;

    /**
     * 数据源值
     */
    @NotNull(message = "数据源不能为空")
    private String dataKind;

    /**
     * 数据码值
     */
    @NotNull(message = "主记录不能为空")
    private Long dataCode;

    /**
     * 使用场景
     */
    @NotNull(message = "使用场景不能为空")
    private String useScene;

    /**
     * 排序，此处小靠前
     */
    private Integer sort;

    /**
     * 备注
     */
    private String remark;
}
