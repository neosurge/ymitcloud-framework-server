package com.ymit.framework.common.util.object;

import com.ymit.framework.common.data.PageParam;

/**
 * {@link PageParam} 工具类
 *
 * @author Y.S
 * @date 2024.05.17
 */
public class PageUtils {
    public static int getStart(PageParam pageParam) {
        return (pageParam.getPage() - 1) * pageParam.getPageSize();
    }
}
