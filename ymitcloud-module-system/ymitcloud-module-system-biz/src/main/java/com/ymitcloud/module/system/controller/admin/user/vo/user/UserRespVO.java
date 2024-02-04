package com.ymitcloud.module.system.controller.admin.user.vo.user;

import java.time.LocalDateTime;
import java.util.Set;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ymitcloud.framework.excel.core.annotations.DictFormat;
import com.ymitcloud.framework.excel.core.convert.DictConvert;
import com.ymitcloud.module.system.enums.DictTypeConstants;

import lombok.Data;

/**
 * 管理后台 - 用户信息 Response VO
 */
@Data
@ExcelIgnoreUnannotated
public class UserRespVO {
    /**
     * 用户编号
     */
    @ExcelProperty("用户编号")
    private Long id;
    /**
     * 用户账号
     */
    @ExcelProperty("用户名称")
    private String username;
    /**
     * 用户昵称
     */
    @ExcelProperty("用户昵称")
    private String nickname;
    /**
     * 备注
     */
    private String remark;
    /**
     * 部门ID
     */
    private Long deptId;
    /**
     * 部门名称
     */
    @ExcelProperty("部门名称")
    private String deptName;
    /**
     * 岗位编号数组
     */
    private Set<Long> postIds;
    /**
     * 用户邮箱
     */
    @ExcelProperty("用户邮箱")
    private String email;
    /**
     * 手机号码
     */
    @ExcelProperty("手机号码")
    private String mobile;
    /**
     * 用户性别，参见 SexEnum 枚举类
     */
    @ExcelProperty(value = "用户性别", converter = DictConvert.class)
    @DictFormat(DictTypeConstants.USER_SEX)
    private Integer sex;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 状态，参见 CommonStatusEnum 枚举类
     */
    @ExcelProperty(value = "帐号状态", converter = DictConvert.class)
    @DictFormat(DictTypeConstants.COMMON_STATUS)
    private Integer status;
    /**
     * 最后登录 IP
     */
    @ExcelProperty("最后登录IP")
    private String loginIp;
    /**
     * 最后登录时间
     */
    @ExcelProperty("最后登录时间")
    private LocalDateTime loginDate;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
