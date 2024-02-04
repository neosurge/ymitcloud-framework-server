package com.ymitcloud.module.crm.controller.admin.business.vo.status;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 管理后台 - 商机状态新增/修改 Request VO
 */
@Data
public class CrmBusinessStatusSaveReqVO {

    /**
     * 主键
     */
    private Long id;
    /**
     * 状态类型编号
     */
    @NotNull(message = "状态类型编号不能为空")
    private Long typeId;
    /**
     * 状态名
     */
    @NotEmpty(message = "状态名不能为空")
    private String name;

    /**
     * 赢单率
     */
    // TODO @lilleo：percent 应该是 Integer；
    private String percent;
    /**
     * 排序
     */
    // TODO @lilleo：这个是不是不用前端新增和修改的时候传递，交给顺序计算出来，存储起来就好了；

    private Integer sort;

}
