package com.ymitcloud.module.mp.controller.admin.account.vo;




import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;


/** 管理后台 - 公众号账号 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MpAccountRespVO extends MpAccountBaseVO {


    /** 编号*/
    private Long id;

    /** 二维码图片URL", example = "https://www.ymitcloud.com/1024.png")
    private String qrCodeUrl;

    /** 创建时间*/

    private LocalDateTime createTime;

}
