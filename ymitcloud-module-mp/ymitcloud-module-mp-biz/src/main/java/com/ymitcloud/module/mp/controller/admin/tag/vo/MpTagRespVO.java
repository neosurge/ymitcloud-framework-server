package com.ymitcloud.module.mp.controller.admin.tag.vo;




import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;


/** 管理后台 - 公众号标签 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MpTagRespVO extends MpTagBaseVO {


    /** 编号*/
    private Long id;

    /** 此标签下粉丝数量*/
    private Integer count;

    /** 创建时间*/

    private LocalDateTime createTime;

}
