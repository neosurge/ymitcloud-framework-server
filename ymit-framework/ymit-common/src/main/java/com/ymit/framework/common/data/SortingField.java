package com.ymit.framework.common.data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 排序字段 DTO
 * <p>
 * 类名加了 ing 的原因是，避免和 ES SortField 重名。
 *
 * @author Y.S
 * @date 2024.05.15
 */
public class SortingField implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 顺序 - 升序
     */
    public static final String ORDER_ASC = "asc";
    /**
     * 顺序 - 降序
     */
    public static final String ORDER_DESC = "desc";
    /**
     * 字段
     */
    private String field;
    /**
     * 顺序: asc 或 desc
     */
    private String order;

    public SortingField() {
    }

    public SortingField(String field, String order) {
        this.field = field;
        this.order = order;
    }

    public String getField() {
        return this.field;
    }

    public SortingField setField(String field) {
        this.field = field;
        return this;
    }

    public String getOrder() {
        return this.order;
    }

    public SortingField setOrder(String order) {
        this.order = order;
        return this;
    }
}
