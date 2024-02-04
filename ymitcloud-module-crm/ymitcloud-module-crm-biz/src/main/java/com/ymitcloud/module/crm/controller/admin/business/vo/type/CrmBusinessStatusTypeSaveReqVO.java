package com.ymitcloud.module.crm.controller.admin.business.vo.type;


import java.util.List;

import com.google.common.collect.Lists;
import com.ymitcloud.module.crm.controller.admin.business.vo.status.CrmBusinessStatusSaveReqVO;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 管理后台 - 商机状态类型新增/修改 Request VO
 */
@Data
public class CrmBusinessStatusTypeSaveReqVO {
    /**
     * 主键
     */
    private Long id;
    /**
     * 状态类型名
     */
    @NotEmpty(message = "状态类型名不能为空")
    private String name;
    /**
     * 使用的部门编号
     */
    private List<Long> deptIds = Lists.newArrayList();

    /**
     * 商机状态集合
     */
    // TODO @ljlleo VO 里面，我们不使用默认值哈。这里 Lists.newArrayList() 看看怎么去掉。上面 deptIds 也是类似噢

    private List<CrmBusinessStatusSaveReqVO> statusList = Lists.newArrayList();

}
