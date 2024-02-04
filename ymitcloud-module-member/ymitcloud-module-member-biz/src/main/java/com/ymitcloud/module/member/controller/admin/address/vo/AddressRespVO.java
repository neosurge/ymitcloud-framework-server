package com.ymitcloud.module.member.controller.admin.address.vo;



import lombok.*;
import java.time.LocalDateTime;

/** 管理后台 - 用户收件地址 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AddressRespVO extends AddressBaseVO {


    /** 收件地址编号*/
    private Long id;

    /** 创建时间*/

    private LocalDateTime createTime;

}
