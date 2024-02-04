package com.ymitcloud.module.mp.controller.admin.menu.vo;




import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;


/** 管理后台 - 公众号菜单 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MpMenuRespVO extends MpMenuBaseVO {


    /** 主键*/
    private Long id;

    /** 公众号账号的编号*/
    private Long accountId;

    /** 公众号 appId*/
    private String appId;

    /** 创建时间*/

    private LocalDateTime createTime;

}
