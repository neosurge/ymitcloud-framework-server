package com.ymitcloud.module.infra.controller.admin.file.vo.file;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

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

}
