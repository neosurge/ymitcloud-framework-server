package com.ymitcloud.module.system.service.logger;

import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.system.api.logger.dto.OperateLogCreateReqDTO;
import com.ymitcloud.module.system.controller.admin.logger.vo.operatelog.OperateLogPageReqVO;
import com.ymitcloud.module.system.dal.dataobject.logger.OperateLogDO;

/**
 * 操作日志 Service 接口
 *

 * @author 云码源码

 */
public interface OperateLogService {

    /**
     * 记录操作日志
     *
     * @param createReqDTO 操作日志请求
     */
    void createOperateLog(OperateLogCreateReqDTO createReqDTO);

    /**
     * 获得操作日志分页列表
     *
     * @param pageReqVO 分页条件
     * @return 操作日志分页列表
     */
    PageResult<OperateLogDO> getOperateLogPage(OperateLogPageReqVO pageReqVO);

}
