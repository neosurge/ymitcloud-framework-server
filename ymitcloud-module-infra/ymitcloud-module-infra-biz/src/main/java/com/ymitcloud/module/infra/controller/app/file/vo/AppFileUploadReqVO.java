package com.ymitcloud.module.infra.controller.app.file.vo;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 用户 App - 上传文件 Request VO
 */
@Data
public class AppFileUploadReqVO {

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
