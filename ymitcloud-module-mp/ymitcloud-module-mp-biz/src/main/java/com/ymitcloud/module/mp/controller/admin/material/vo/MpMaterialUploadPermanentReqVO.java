package com.ymitcloud.module.mp.controller.admin.material.vo;


import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.hutool.core.util.ObjectUtil;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import me.chanjar.weixin.common.api.WxConsts;

/** 管理后台 - 公众号素材上传永久 Request VO */
@Data
public class MpMaterialUploadPermanentReqVO {

    /** 公众号账号的编号*/
    @NotNull(message = "公众号账号的编号不能为空")
    private Long accountId;

    /** 文件类型 参见 WxConsts.MediaFileType 枚举*/
    @NotEmpty(message = "文件类型不能为空")
    private String type;

    /** 文件附件*/

    @NotNull(message = "文件不能为空")
    @JsonIgnore // 避免被操作日志，进行序列化，导致报错
    private MultipartFile file;


    /** 名字 如果 name 为空，则使用 file 文件名*/
    private String name;

    /** 视频素材的标题 文件类型为 video 时，必填*/
    private String title;
    /** 视频素材的描述 文件类型为 video 时，必填*/

    private String introduction;

    @AssertTrue(message = "标题不能为空")
    public boolean isTitleValid() {
        // 生成场景为管理后台时，必须设置上级菜单，不然生成的菜单 SQL 是无父级菜单的
        return ObjectUtil.notEqual(type, WxConsts.MediaFileType.VIDEO)
                || title != null;
    }

    @AssertTrue(message = "描述不能为空")
    public boolean isIntroductionValid() {
        // 生成场景为管理后台时，必须设置上级菜单，不然生成的菜单 SQL 是无父级菜单的
        return ObjectUtil.notEqual(type, WxConsts.MediaFileType.VIDEO)
                || introduction != null;
    }

}
