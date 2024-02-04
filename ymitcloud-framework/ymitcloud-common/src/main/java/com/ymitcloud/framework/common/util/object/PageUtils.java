package com.ymitcloud.framework.common.util.object;

import com.ymitcloud.framework.common.pojo.PageParam;

/**
 * {@link PageParam} 工具类
 *

 * @author 

 */
public class PageUtils {

    public static int getStart(PageParam pageParam) {
        return (pageParam.getPageNo() - 1) * pageParam.getPageSize();
    }

}
