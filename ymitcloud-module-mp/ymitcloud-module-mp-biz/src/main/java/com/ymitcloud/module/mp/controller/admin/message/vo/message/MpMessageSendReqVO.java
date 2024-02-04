package com.ymitcloud.module.mp.controller.admin.message.vo.message;

import com.ymitcloud.module.mp.dal.dataobject.message.MpMessageDO;
import com.ymitcloud.module.mp.framework.mp.core.util.MpUtils.*;



import lombok.Data;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;


/** 管理后台 - 公众号消息发送 Request VO */
@Data
public class MpMessageSendReqVO {

    /** 公众号粉丝的编号*/

    @NotNull(message = "公众号粉丝的编号不能为空")
    private Long userId;

    // ========== 消息内容 ==========


    /** 消息类型 TEXT/IMAGE/VOICE/VIDEO/NEWS*/
    @NotEmpty(message = "消息类型不能为空")
    public String type;

    /** 消息内容*/
    @NotEmpty(message = "消息内容不能为空", groups = TextMessageGroup.class)
    private String content;

    /** 媒体 ID*/
    @NotEmpty(message = "消息内容不能为空", groups = {ImageMessageGroup.class, VoiceMessageGroup.class, VideoMessageGroup.class})
    private String mediaId;

    /** 标题*/
    @NotEmpty(message = "消息内容不能为空", groups = VideoMessageGroup.class)
    private String title;

    /** 描述*/
    @NotEmpty(message = "消息描述不能为空", groups = VideoMessageGroup.class)
    private String description;

    /** 缩略图的媒体 id*/
    @NotEmpty(message = "缩略图的媒体 id 不能为空", groups = MusicMessageGroup.class)
    private String thumbMediaId;

    /** 图文消息*/

    @Valid
    @NotNull(message = "图文消息不能为空", groups = NewsMessageGroup.class)
    private List<MpMessageDO.Article> articles;


    /** 音乐链接 消息类型为 MUSIC 时*/
    private String musicUrl;

    /** 高质量音乐链接 消息类型为 MUSIC 时 */

    private String hqMusicUrl;

}
