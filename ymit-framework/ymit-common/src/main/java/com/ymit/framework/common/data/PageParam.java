package com.ymit.framework.common.data;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;

/**
 * 分页参数
 *
 * @author Y.S
 * @date 2024.05.15
 */
public class PageParam implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 每页条数 - 不分页
     * <p>
     * 例如说，导出接口，可以设置 {@link #pageSize} 为 -1 不分页，查询所有数据。
     */
    public static final Integer PAGE_SIZE_NONE = -1;
    private static final Integer PAGE = 1;
    private static final Integer PAGE_SIZE = 10;

    /**
     * 页码，从 1 开始
     */
    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码最小值为 1")
    private Integer page = PAGE;
    /**
     * 每页条数，最大值为 1000
     */
    @NotNull(message = "每页条数不能为空")
    @Min(value = 1, message = "每页条数最小值为 1")
    @Max(value = 1000, message = "每页条数最大值为 1000")
    private Integer pageSize = PAGE_SIZE;
    /**
     * 搜索关键词
     */
    private String searchWord;

    public PageParam() {
    }

    public Integer getPage() {
        return this.page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSearchWord() {
        return this.searchWord;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }
}
