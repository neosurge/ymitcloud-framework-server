package com.ymitcloud.module.system.controller.admin.dept.vo.dept;

import com.ymitcloud.framework.common.enums.CommonStatusEnum;
import com.ymitcloud.framework.common.validation.InEnum;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

/**
 * 管理后台 - 部门创建/修改 Request VO
 */
@Data
public class DeptSaveReqVO {
    /**
     * 部门编号
     */
    private Long id;
    /**
     * 部门名称
     */
    @NotBlank(message = "部门名称不能为空")
    @Size(max = 30, message = "部门名称长度不能超过 30 个字符")
    private String name;
    /**
     * 父部门 ID
     */
    private Long parentId;
    /**
     * 显示顺序不能为空
     */
    @NotNull(message = "显示顺序不能为空")
    private Integer sort;
    /**
     * 负责人的用户编号
     */
    private Long leaderUserId;
    /**
     * 联系电话
     */
    @Size(max = 11, message = "联系电话长度不能超过11个字符")
    private String phone;
    /**
     * 邮箱
     */
    @Email(message = "邮箱格式不正确")
    @Size(max = 50, message = "邮箱长度不能超过 50 个字符")
    private String email;
    /**
     * 状态,见 CommonStatusEnum 枚举
     */
    @NotNull(message = "状态不能为空")
    @InEnum(value = CommonStatusEnum.class, message = "修改状态必须是 {value}")
    private Integer status;

}
