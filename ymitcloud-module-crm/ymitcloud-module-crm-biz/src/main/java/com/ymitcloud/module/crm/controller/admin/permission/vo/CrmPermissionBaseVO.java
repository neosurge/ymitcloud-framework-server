package com.ymitcloud.module.crm.controller.admin.permission.vo;

import com.ymitcloud.framework.common.validation.InEnum;
import com.ymitcloud.module.crm.enums.common.CrmBizTypeEnum;
import com.ymitcloud.module.crm.enums.permission.CrmPermissionLevelEnum;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 数据权限 Base VO，提供给添加、修改、详细的子 VO 使用 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成

 *
 * @author HUIHUI
 */
@Data
public class CrmPermissionBaseVO {

    /**
     * 用户编号
     */
    @NotNull(message = "用户编号不能为空")
    private Long userId;
    /**
     * CRM 类型
     */
    @InEnum(CrmBizTypeEnum.class)
    @NotNull(message = "CRM 类型不能为空")
    private Integer bizType;
    /**
     * CRM 类型数据编号
     */
    @NotNull(message = "CRM 类型数据编号不能为空")
    private Long bizId;
    /**
     * 权限级别
     */

    @InEnum(CrmPermissionLevelEnum.class)
    @NotNull(message = "权限级别不能为空")
    private Integer level;

}
