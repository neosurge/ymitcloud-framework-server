package com.ymitcloud.module.rule.service;

import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.rule.controller.admin.request.ComponentPageRequest;
import com.ymitcloud.module.rule.controller.admin.request.ComponentSaveRequest;
import com.ymitcloud.module.rule.controller.admin.response.ComponentResponse;

import java.util.List;

public interface ComponentService {
    PageResult<ComponentResponse> page(ComponentPageRequest request);

    ComponentResponse get(String id);

    void create(ComponentSaveRequest request);

    void update(String id, ComponentSaveRequest request);

    void delete(List<String> ids);
}
