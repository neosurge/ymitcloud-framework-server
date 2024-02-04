package com.ymitcloud.module.crm.controller.admin.permission.vo;


import java.time.LocalDateTime;
import java.util.Set;

import lombok.Data;

/**
 * 管理后台 - CRM 数据权限 Response VO
 */
@Data
public class CrmPermissionRespVO extends CrmPermissionBaseVO {
    /**
     * 数据权限编号
     */
    private Long id;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 岗位名称数组
     */
    private Set<String> postNames;
    /**
     * 创建时间
     */

    private LocalDateTime createTime;

}
