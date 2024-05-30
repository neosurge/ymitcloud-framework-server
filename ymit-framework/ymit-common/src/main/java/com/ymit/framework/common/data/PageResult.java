package com.ymit.framework.common.data;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页结果
 *
 * @author Y.S
 * @date 2024.05.15
 */
public final class PageResult<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 数据
     */
    private List<T> records;
    /**
     * 总量
     */
    private Long total;

    public PageResult() {
    }

    public PageResult(List<T> records, Long total) {
        this.records = records;
        this.total = total;
    }

    public PageResult(Long total) {
        this.records = new ArrayList<>();
        this.total = total;
    }

    public static <T> PageResult<T> empty() {
        return new PageResult<>(0L);
    }

    public static <T> PageResult<T> empty(Long total) {
        return new PageResult<>(total);
    }

    public List<T> getRecords() {
        return this.records;
    }

    public PageResult<T> setRecords(List<T> records) {
        this.records = records;
        return this;
    }

    public Long getTotal() {
        return this.total;
    }

    public PageResult<T> setTotal(Long total) {
        this.total = total;
        return this;
    }
}
