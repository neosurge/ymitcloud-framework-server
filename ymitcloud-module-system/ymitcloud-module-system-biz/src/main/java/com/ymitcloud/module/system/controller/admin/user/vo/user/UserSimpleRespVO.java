package com.ymitcloud.module.system.controller.admin.user.vo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 管理后台 - 用户精简信息 Response VO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSimpleRespVO {
    /**
     * 用户编号
     */
    private Long id;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 部门ID
     */
    private Long deptId;
    /**
     * 部门名称
     */
    private String deptName;

}
