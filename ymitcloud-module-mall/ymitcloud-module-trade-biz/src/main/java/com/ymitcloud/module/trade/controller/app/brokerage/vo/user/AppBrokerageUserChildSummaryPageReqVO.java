package com.ymitcloud.module.trade.controller.app.brokerage.vo.user;


import org.hibernate.validator.constraints.Range;

import com.ymitcloud.framework.common.pojo.PageParam;
import com.ymitcloud.framework.common.pojo.SortingField;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/** 
 * 用户 App - 下级分销统计分页 Request VO
 */

@Data
public class AppBrokerageUserChildSummaryPageReqVO extends PageParam {

    public static final String SORT_FIELD_USER_COUNT = "userCount";
    public static final String SORT_FIELD_ORDER_COUNT = "orderCount";
    public static final String SORT_FIELD_PRICE = "price";


    /** 
     * 用户昵称
     */
    private String nickname;

    /** 
     * 排序字段
     */
    private SortingField sortingField;

    /** 下级的级别*/ // 1 - 直接下级；2 - 间接下级

    @NotNull(message = "下级的级别不能为空")
    @Range(min = 1, max = 2, message = "下级的级别只能是 {min} 或者 {max}")
    private Integer level;

}
