package com.ymitcloud.module.mp.controller.admin.menu.vo;

import com.ymitcloud.module.mp.dal.dataobject.message.MpMessageDO;
import com.ymitcloud.module.mp.framework.mp.core.util.MpUtils;



import lombok.Data;
import me.chanjar.weixin.common.api.WxConsts;
import org.hibernate.validator.constraints.URL;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/**

 * 公众号菜单 Base VO，提供给添加、修改、详细的子 VO 使用 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成

 */
@Data
public class MpMenuBaseVO {

    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单标识
     *

     * 支持多 DB 类型时，无法直接使用 key + @TableField("menuKey") 来实现转换，原因是 "menuKey" AS key
     * 而存在报错

     */
    private String menuKey;
    /**
     * 父菜单编号
     */
    private Long parentId;

    // ========== 按钮操作 ==========

    /**
     * 按钮类型
     *
     * 枚举 {@link WxConsts.MenuButtonType}
     */
    private String type;


    /** 网页链接 */
    @NotEmpty(message = "网页链接不能为空", groups = { MpUtils.ViewButtonGroup.class, MpUtils.MiniProgramButtonGroup.class })
    @URL(message = "网页链接必须是 URL 格式")
    private String url;

    /** 小程序的 appId */
    @NotEmpty(message = "小程序的 appId 不能为空", groups = MpUtils.MiniProgramButtonGroup.class)
    private String miniProgramAppId;

    /** 小程序的页面路径 */
    @NotEmpty(message = "小程序的页面路径不能为空", groups = MpUtils.MiniProgramButtonGroup.class)
    private String miniProgramPagePath;

    /**
     * 跳转图文的媒体编号
     */

    @NotEmpty(message = "跳转图文的媒体编号不能为空", groups = MpUtils.ViewLimitedButtonGroup.class)
    private String articleId;

    // ========== 消息内容 ==========


    /** 回复的消息类型 枚举 TEXT、IMAGE、VOICE、VIDEO、NEWS、MUSIC */
    @NotEmpty(message = "回复的消息类型不能为空", groups = { MpUtils.ClickButtonGroup.class,
            MpUtils.ScanCodeWaitMsgButtonGroup.class })
    private String replyMessageType;

    /** 回复的消息内容 */
    @NotEmpty(message = "回复的消息内容不能为空", groups = MpUtils.TextMessageGroup.class)
    private String replyContent;

    /** 回复的媒体 id */
    @NotEmpty(message = "回复的消息 mediaId 不能为空", groups = { MpUtils.ImageMessageGroup.class,
            MpUtils.VoiceMessageGroup.class, MpUtils.VideoMessageGroup.class })
    private String replyMediaId;
    /** 回复的媒体 URL */
    @NotEmpty(message = "回复的消息 mediaId 不能为空", groups = { MpUtils.ImageMessageGroup.class,
            MpUtils.VoiceMessageGroup.class, MpUtils.VideoMessageGroup.class })
    private String replyMediaUrl;

    /** 缩略图的媒体 id */
    @NotEmpty(message = "回复的消息 thumbMediaId 不能为空", groups = { MpUtils.MusicMessageGroup.class })
    private String replyThumbMediaId;
    /** 缩略图的媒体 URL */
    @NotEmpty(message = "回复的消息 thumbMedia 地址不能为空", groups = { MpUtils.MusicMessageGroup.class })
    private String replyThumbMediaUrl;

    /** 回复的标题 */
    @NotEmpty(message = "回复的消息标题不能为空", groups = MpUtils.VideoMessageGroup.class)
    private String replyTitle;
    /** 回复的描述 */

    @NotEmpty(message = "消息描述不能为空", groups = MpUtils.VideoMessageGroup.class)
    private String replyDescription;

    /**
     * 回复的图文消息数组
     *
     * 消息类型为 {@link WxConsts.XmlMsgType} 的 NEWS
     */

    @NotNull(message = "回复的图文消息不能为空", groups = { MpUtils.NewsMessageGroup.class, MpUtils.ViewLimitedButtonGroup.class })
    @Valid
    private List<MpMessageDO.Article> replyArticles;

    /** 回复的音乐链接 */
    @NotEmpty(message = "回复的音乐链接不能为空", groups = MpUtils.MusicMessageGroup.class)
    @URL(message = "回复的高质量音乐链接格式不正确", groups = MpUtils.MusicMessageGroup.class)
    private String replyMusicUrl;
    /** 高质量音乐链接 */

    @NotEmpty(message = "回复的高质量音乐链接不能为空", groups = MpUtils.MusicMessageGroup.class)
    @URL(message = "回复的高质量音乐链接格式不正确", groups = MpUtils.MusicMessageGroup.class)
    private String replyHqMusicUrl;

}
