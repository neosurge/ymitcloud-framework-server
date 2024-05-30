package com.ymit.module.system.controller.admin.dept.vo.dept;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 管理后台 - 部门列表 Request VO
 */
@Data
@Accessors(chain = true)
public class DeptListReqVO {
    /**
     * 部门名称，模糊匹配
     */
    private String name;
    /**
     * 展示状态，参见 CommonStatusEnum 枚举类
     */
    private Integer status;

}
