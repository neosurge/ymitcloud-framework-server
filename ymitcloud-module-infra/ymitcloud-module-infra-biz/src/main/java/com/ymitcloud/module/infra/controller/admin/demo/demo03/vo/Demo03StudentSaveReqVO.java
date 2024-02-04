package com.ymitcloud.module.infra.controller.admin.demo.demo03.vo;

import java.time.LocalDateTime;
import java.util.List;

import com.ymitcloud.module.infra.dal.dataobject.demo.demo03.Demo03CourseDO;
import com.ymitcloud.module.infra.dal.dataobject.demo.demo03.Demo03GradeDO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 管理后台 - 学生新增/修改 Request VO
 */
@Data
public class Demo03StudentSaveReqVO {
    /**
     * 编号
     */
    private Long id;
    /**
     * 名字
     */
    @NotEmpty(message = "名字不能为空")
    private String name;
    /**
     * 性别
     */
    @NotNull(message = "性别不能为空")
    private Integer sex;
    /**
     * 出生日期
     */
    @NotNull(message = "出生日期不能为空")
    private LocalDateTime birthday;
    /**
     * 简介
     */
    @NotEmpty(message = "简介不能为空")
    private String description;
    private List<Demo03CourseDO> demo03Courses;

    private Demo03GradeDO demo03Grade;

}