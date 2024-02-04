package com.ymitcloud.module.product.controller.app.spu.vo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ymitcloud.framework.common.pojo.PageParam;

import cn.hutool.core.util.StrUtil;
import jakarta.validation.constraints.AssertTrue;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 用户 App - 商品 SPU 分页 Request VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AppProductSpuPageReqVO extends PageParam {

    public static final String SORT_FIELD_PRICE = "price";
    public static final String SORT_FIELD_SALES_COUNT = "salesCount";

    public static final String RECOMMEND_TYPE_HOT = "hot";
    public static final String RECOMMEND_TYPE_BENEFIT = "benefit";
    public static final String RECOMMEND_TYPE_BEST = "best";
    public static final String RECOMMEND_TYPE_NEW = "new";
    public static final String RECOMMEND_TYPE_GOOD = "good";

    /**
     * 分类编号
     */
    private Long categoryId;
    /**
     * 关键字
     */
    private String keyword;
    /**
     * 排序字段
     */
    private String sortField;
    /**
     * 排序方式
     */
    private Boolean sortAsc;
    /**
     * 推荐类型
     */

    private String recommendType;

    @AssertTrue(message = "排序字段不合法")
    @JsonIgnore
    public boolean isSortFieldValid() {
        if (StrUtil.isEmpty(sortField)) {
            return true;
        }
        return StrUtil.equalsAny(sortField, SORT_FIELD_PRICE, SORT_FIELD_SALES_COUNT);
    }

}
