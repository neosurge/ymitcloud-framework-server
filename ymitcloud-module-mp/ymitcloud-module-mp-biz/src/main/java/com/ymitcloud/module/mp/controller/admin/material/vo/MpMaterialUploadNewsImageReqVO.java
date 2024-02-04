package com.ymitcloud.module.mp.controller.admin.material.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;



import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;


/** 管理后台 - 公众号素材上传图文内容中的图片 Request VO */
@Data
public class MpMaterialUploadNewsImageReqVO {

    /** 公众号账号的编号*/
    @NotNull(message = "公众号账号的编号不能为空")
    private Long accountId;

    /** 文件附件*/

    @NotNull(message = "文件不能为空")
    @JsonIgnore // 避免被操作日志，进行序列化，导致报错
    private MultipartFile file;

}
