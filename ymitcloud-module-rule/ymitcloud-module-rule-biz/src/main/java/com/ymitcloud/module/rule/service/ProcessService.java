package com.ymitcloud.module.rule.service;

import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.rule.controller.admin.request.LogicFlowConvertElRequest;
import com.ymitcloud.module.rule.controller.admin.request.ProcessPageRequest;
import com.ymitcloud.module.rule.controller.admin.request.ProcessSaveRequest;
import com.ymitcloud.module.rule.controller.admin.response.LogicFlowConvertElResponse;
import com.ymitcloud.module.rule.controller.admin.response.ProcessExecuteResponse;
import com.ymitcloud.module.rule.controller.admin.response.ProcessResponse;

import java.util.List;

public interface ProcessService {
    PageResult<ProcessResponse> page(ProcessPageRequest request);

    ProcessResponse get(String id);

    void create(ProcessSaveRequest request);

    void update(String id, ProcessSaveRequest request);

    void remove(List<String> ids);

    LogicFlowConvertElResponse logicFlowConvertEl(LogicFlowConvertElRequest request);

    List<ProcessExecuteResponse> execute(String id);
}
