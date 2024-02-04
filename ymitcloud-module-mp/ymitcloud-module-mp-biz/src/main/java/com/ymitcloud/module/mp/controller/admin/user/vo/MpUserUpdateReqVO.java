package com.ymitcloud.module.mp.controller.admin.user.vo;




import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.util.List;


/** 管理后台 - 公众号粉丝更新 Request VO */
@Data
public class MpUserUpdateReqVO {

    /** 编号*/
    @NotNull(message = "编号不能为空")
    private Long id;

    /** 昵称*/
    private String nickname;

    /** 备注*/
    private String remark;

    /** 标签编号数组 */

    private List<Long> tagIds;

}
