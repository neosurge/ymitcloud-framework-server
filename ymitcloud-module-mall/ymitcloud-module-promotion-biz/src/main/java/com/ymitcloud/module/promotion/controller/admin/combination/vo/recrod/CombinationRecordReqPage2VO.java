package com.ymitcloud.module.promotion.controller.admin.combination.vo.recrod;

import com.ymitcloud.framework.common.pojo.PageParam;


import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 拼团记录分页 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CombinationRecordReqPage2VO extends PageParam {


    /**
     * 团长编号
     */

    @NotNull(message = "团长编号不能为空")
    private Long headId;

}
