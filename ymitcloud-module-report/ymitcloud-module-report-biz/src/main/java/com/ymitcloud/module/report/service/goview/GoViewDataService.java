package com.ymitcloud.module.report.service.goview;

import com.ymitcloud.module.report.controller.admin.goview.vo.data.GoViewDataRespVO;

/**
 * GoView 数据 Service 接口
 *

 * @author 

 */
public interface GoViewDataService {

    /**
     * 使用 SQL 查询数据
     *
     * @param sql SQL 语句
     * @return 数据
     */
    GoViewDataRespVO getDataBySQL(String sql);

}
