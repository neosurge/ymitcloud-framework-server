package com.ymitcloud.module.system.controller.admin.dept.vo.post;

import com.ymitcloud.framework.common.enums.CommonStatusEnum;
import com.ymitcloud.framework.common.validation.InEnum;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 管理后台 - 岗位创建/修改 Request VO
 */
@Data
public class PostSaveReqVO {
    /**
     * 岗位编号
     */
    private Long id;
    /**
     * 岗位名称
     */
    @NotBlank(message = "岗位名称不能为空")
    @Size(max = 50, message = "岗位名称长度不能超过 50 个字符")
    private String name;
    /**
     * 岗位编码
     */
    @NotBlank(message = "岗位编码不能为空")
    @Size(max = 64, message = "岗位编码长度不能超过64个字符")
    private String code;
    /**
     * 显示顺序不能为空
     */
    @NotNull(message = "显示顺序不能为空")
    private Integer sort;
    /**
     * 状态
     */
    @InEnum(CommonStatusEnum.class)
    private Integer status;
    /**
     * 备注
     */
    private String remark;

}