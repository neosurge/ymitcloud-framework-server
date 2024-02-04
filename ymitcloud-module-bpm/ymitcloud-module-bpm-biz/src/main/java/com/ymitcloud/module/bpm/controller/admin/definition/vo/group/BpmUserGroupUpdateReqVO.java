package com.ymitcloud.module.bpm.controller.admin.definition.vo.group;


import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 管理后台 - 用户组更新 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BpmUserGroupUpdateReqVO extends BpmUserGroupBaseVO {


    /**
     * 编号
     */

    @NotNull(message = "编号不能为空")
    private Long id;

}
