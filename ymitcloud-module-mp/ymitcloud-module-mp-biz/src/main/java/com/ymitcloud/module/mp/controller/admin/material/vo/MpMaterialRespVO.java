package com.ymitcloud.module.mp.controller.admin.material.vo;




import lombok.Data;

import java.time.LocalDateTime;


/** 管理后台 - 公众号素材 */
@Data
public class MpMaterialRespVO {

    /** 主键*/
    private Long id;

    /** 公众号账号的编号*/
    private Long accountId;
    /** 公众号账号的 appId*/
    private String appId;

    /** 素材的 media_id*/
    private String mediaId;

    /** 文件类型 参见 WxConsts.MediaFileType 枚举*/
    private String type;

    /** 是否永久 true - 永久；false - 临时*/
    private Boolean permanent;

    /** 素材的 URL*/
    private String url;


    /** 名字", example = "yunai.png")
    private String name;

    /** 公众号文件 URL 只有【永久素材】使用", example = "https://mmbiz.qpic.cn/xxx.mp3")
    private String mpUrl;

    /** 视频素材的标题 只有【永久素材】使用", example = "我是标题")
    private String title;
    /** 视频素材的描述 只有【永久素材】使用", example = "我是介绍")
    private String introduction;

    /** 创建时间*/

    private LocalDateTime createTime;

}
