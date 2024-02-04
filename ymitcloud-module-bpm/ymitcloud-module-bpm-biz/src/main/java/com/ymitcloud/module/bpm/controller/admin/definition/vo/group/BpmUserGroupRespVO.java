package com.ymitcloud.module.bpm.controller.admin.definition.vo.group;


import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 管理后台 - 用户组 Response VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BpmUserGroupRespVO extends BpmUserGroupBaseVO {


    /**
     * 编号
     */
    private Long id;

    /**
     * 创建时间
     */

    private LocalDateTime createTime;

}
