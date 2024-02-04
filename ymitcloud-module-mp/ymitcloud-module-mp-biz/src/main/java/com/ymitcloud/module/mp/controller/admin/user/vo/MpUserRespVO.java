package com.ymitcloud.module.mp.controller.admin.user.vo;




import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


/** 管理后台 - 公众号粉丝 */
@Data
public class MpUserRespVO  {

    /** 编号*/
    private Long id;

    /** 公众号粉丝标识*/
    private String openid;

    /** 关注状态 参见 CommonStatusEnum 枚举*/
    private Integer subscribeStatus;
    /** 关注时间*/
    private LocalDateTime subscribeTime;
    /** 取消关注时间")
    private LocalDateTime unsubscribeTime;

    /** 昵称", example = "云码")
    private String nickname;
    /** 头像地址", example = "https://www.ymitcloud.com/1.png")
    private String headImageUrl;
    /** 语言", example = "zh_CN")
    private String language;
    /** 国家", example = "中国")
    private String country;
    /** 省份", example = "广东省")
    private String province;
    /** 城市", example = "广州市")
    private String city;
    /** 备注", example = "你是一个芋头嘛")
    private String remark;

    /** 标签编号数组", example = "1,2,3")
    private List<Long> tagIds;

    /** 公众号账号的编号*/
    private Long accountId;
    /** 公众号账号的 appId*/
    private String appId;

    /** 创建时间*/

    private LocalDateTime createTime;

}
