package com.ymitcloud.module.mp.controller.admin.message.vo.autoreply;

import cn.hutool.core.util.ObjectUtil;
import com.ymitcloud.module.mp.dal.dataobject.message.MpMessageDO;
import com.ymitcloud.module.mp.enums.message.MpAutoReplyTypeEnum;
import com.ymitcloud.module.mp.framework.mp.core.util.MpUtils.*;



import lombok.Data;
import me.chanjar.weixin.common.api.WxConsts;
import org.hibernate.validator.constraints.URL;

import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/**

 * 公众号自动回复 Base VO，提供给添加、修改、详细的子 VO 使用 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成

 */
@Data
public class MpAutoReplyBaseVO {


    /** 回复类型 参见 MpAutoReplyTypeEnum 枚举 */

    @NotNull(message = "回复类型不能为空")
    private Integer type;

    // ==================== 请求消息 ====================


    /** 请求的关键字 当 type 为 MpAutoReplyTypeEnum#KEYWORD 时，必填 */
    private String requestKeyword;
    /** 请求的匹配方式 当 type 为 MpAutoReplyTypeEnum#KEYWORD 时，必填 */
    private Integer requestMatch;

    /** 请求的消息类型 当 type 为 MpAutoReplyTypeEnum#MESSAGE 时，必填 */

    private String requestMessageType;

    // ==================== 响应消息 ====================


    /** 回复的消息类型 枚举 TEXT、IMAGE、VOICE、VIDEO、NEWS、MUSIC */
    @NotEmpty(message = "回复的消息类型不能为空")
    private String responseMessageType;

    /** 回复的消息内容 */
    @NotEmpty(message = "回复的消息内容不能为空", groups = TextMessageGroup.class)
    private String responseContent;

    /** 回复的媒体 id */
    @NotEmpty(message = "回复的消息 mediaId 不能为空", groups = { ImageMessageGroup.class, VoiceMessageGroup.class,
            VideoMessageGroup.class })
    private String responseMediaId;
    /** 回复的媒体 URL */
    @NotEmpty(message = "回复的消息 mediaId 不能为空", groups = { ImageMessageGroup.class, VoiceMessageGroup.class,
            VideoMessageGroup.class })
    private String responseMediaUrl;

    /** 缩略图的媒体 id */
    @NotEmpty(message = "回复的消息 thumbMediaId 不能为空", groups = { MusicMessageGroup.class })
    private String responseThumbMediaId;
    /** 缩略图的媒体 URL */
    @NotEmpty(message = "回复的消息 thumbMedia 地址不能为空", groups = { MusicMessageGroup.class })
    private String responseThumbMediaUrl;

    /** 回复的标题 */
    @NotEmpty(message = "回复的消息标题不能为空", groups = VideoMessageGroup.class)
    private String responseTitle;
    /** 回复的描述 */

    @NotEmpty(message = "消息描述不能为空", groups = VideoMessageGroup.class)
    private String responseDescription;

    /**
     * 回复的图文消息
     *
     * 消息类型为 {@link WxConsts.XmlMsgType} 的 NEWS
     */

    @NotNull(message = "回复的图文消息不能为空", groups = { NewsMessageGroup.class, ViewLimitedButtonGroup.class })
    @Valid
    private List<MpMessageDO.Article> responseArticles;

    /** 回复的音乐链接 */
    @NotEmpty(message = "回复的音乐链接不能为空", groups = MusicMessageGroup.class)
    @URL(message = "回复的高质量音乐链接格式不正确", groups = MusicMessageGroup.class)
    private String responseMusicUrl;
    /** 高质量音乐链接 */

    @NotEmpty(message = "回复的高质量音乐链接不能为空", groups = MusicMessageGroup.class)
    @URL(message = "回复的高质量音乐链接格式不正确", groups = MusicMessageGroup.class)
    private String responseHqMusicUrl;

    @AssertTrue(message = "请求的关键字不能为空")
    public boolean isRequestKeywordValid() {

        return ObjectUtil.notEqual(type, MpAutoReplyTypeEnum.KEYWORD) || requestKeyword != null;

    }

    @AssertTrue(message = "请求的关键字的匹配不能为空")
    public boolean isRequestMatchValid() {

        return ObjectUtil.notEqual(type, MpAutoReplyTypeEnum.KEYWORD) || requestMatch != null;

    }

    @AssertTrue(message = "请求的消息类型不能为空")
    public boolean isRequestMessageTypeValid() {

        return ObjectUtil.notEqual(type, MpAutoReplyTypeEnum.MESSAGE) || requestMessageType != null;
    }


}
