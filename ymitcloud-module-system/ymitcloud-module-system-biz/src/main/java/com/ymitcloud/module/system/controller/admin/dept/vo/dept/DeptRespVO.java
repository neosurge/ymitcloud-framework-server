package com.ymitcloud.module.system.controller.admin.dept.vo.dept;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * 管理后台 - 部门信息 Response VO
 */
@Data
public class DeptRespVO {
    /**
     * 部门编号
     */
    private Long id;
    /**
     * 部门名称
     */
    private String name;
    /**
     * 父部门 ID
     */
    private Long parentId;
    /**
     * 显示顺序不能为空
     */
    private Integer sort;
    /**
     * 负责人的用户编号
     */
    private Long leaderUserId;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 状态,见 CommonStatusEnum 枚举
     */
    private Integer status;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
