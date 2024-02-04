package com.ymitcloud.module.mp.controller.admin.message.vo.message;

import com.ymitcloud.module.mp.dal.dataobject.message.MpMessageDO;
import com.baomidou.mybatisplus.annotation.TableField;



import lombok.Data;
import me.chanjar.weixin.common.api.WxConsts;

import java.time.LocalDateTime;
import java.util.List;


/** 管理后台 - 公众号消息 */
@Data
public class MpMessageRespVO {

    /** 主键*/
    private Integer id;

    /** 微信公众号消息 id*/
    private Long msgId;

    /** 公众号账号的编号*/
    private Long accountId;
    /** 公众号账号的 appid*/
    private String appId;

    /** 公众号粉丝编号*/
    private Long userId;
    /** 公众号粉丝标志*/
    private String openid;

    /** 消息类型 参见 WxConsts.XmlMsgType 枚举*/
    private String type;
    /** 消息来源 参见 MpMessageSendFromEnum 枚举*/

    private Integer sendFrom;

    // ========= 普通消息内容 https://developers.weixin.qq.com/doc/offiaccount/Message_Management/Receiving_standard_messages.html


    /** 消息内容 消息类型为 text 时，才有值", example = "你好呀")
    private String content;

    /** 媒体素材的编号 消息类型为 image、voice、video 时，才有值", example = "1234567890")
    private String mediaId;
    /** 媒体文件的 URL 消息类型为 image、voice、video 时，才有值", example = "https://www.ymitcloud.com/xxx.png")
    private String mediaUrl;

    /** 语音识别后文本 消息类型为 voice 时，才有值", example = "语音识别后文本")
    private String recognition;
    /** 语音格式 消息类型为 voice 时，才有值", example = "amr")
    private String format;

    /** 标题 消息类型为 video、music、link 时，才有值", example = "我是标题")
    private String title;

    /** 描述 消息类型为 video、music 时，才有值", example = "我是描述")
    private String description;

    /** 缩略图的媒体 id 消息类型为 video、music 时，才有值", example = "1234567890")
    private String thumbMediaId;
    /** 缩略图的媒体 URL 消息类型为 video、music 时，才有值", example = "https://www.ymitcloud.com/xxx.png")
    private String thumbMediaUrl;

    /** 点击图文消息跳转链接 消息类型为 link 时，才有值", example = "https://www.ymitcloud.com")
    private String url;

    /** 地理位置维度 消息类型为 location 时，才有值", example = "23.137466")
    private Double locationX;

    /** 地理位置经度 消息类型为 location 时，才有值", example = "113.352425")
    private Double locationY;

    /** 地图缩放大小 消息类型为 location 时，才有值", example = "13")
    private Double scale;

    /** 详细地址 消息类型为 location 时，才有值", example = "杨浦区黄兴路 221-4 号临")

    private String label;

    /**
     * 图文消息数组
     *
     * 消息类型为 {@link WxConsts.XmlMsgType} 的 NEWS
     */
    @TableField(typeHandler = MpMessageDO.ArticleTypeHandler.class)
    private List<MpMessageDO.Article> articles;


    /** 音乐链接 消息类型为 music 时，才有值", example = "https://www.ymitcloud.com/xxx.mp3")
    private String musicUrl;
    /** 高质量音乐链接 消息类型为 music 时，才有值", example = "https://www.ymitcloud.com/xxx.mp3")

    private String hqMusicUrl;

    // ========= 事件推送 https://developers.weixin.qq.com/doc/offiaccount/Message_Management/Receiving_event_pushes.html


    /** 事件类型 参见 WxConsts.EventType 枚举", example = "subscribe")
    private String event;
    /** 事件 Key 参见 WxConsts.EventType 枚举", example = "qrscene_123456")
    private String eventKey;

    /** 创建时间*/

    private LocalDateTime createTime;

}
