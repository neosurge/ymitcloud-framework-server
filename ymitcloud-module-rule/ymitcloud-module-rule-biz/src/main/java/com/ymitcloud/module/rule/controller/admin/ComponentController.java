package com.ymitcloud.module.rule.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.rule.controller.admin.request.ComponentPageRequest;
import com.ymitcloud.module.rule.controller.admin.request.ComponentSaveRequest;
import com.ymitcloud.module.rule.controller.admin.response.ComponentResponse;
import com.ymitcloud.module.rule.service.ComponentService;

/**
 * 规则引擎 - 组件管理
 */
@RestController
@RequestMapping("/rule/component")
public class ComponentController {
    @Autowired
    private ComponentService componentService;

    /**
     * 分页查询
     * @param request
     * @return
     */
    @GetMapping("/page")
    public CommonResult<PageResult<ComponentResponse>> page(@Validated ComponentPageRequest request) {
        return CommonResult.success(componentService.page(request));
    }


    /**
     * 获取详情
     * @param id 数据id
     * @return
     */
    @GetMapping("/{id}")
    public CommonResult<ComponentResponse> get(@PathVariable("id") String id) {
        return CommonResult.success(componentService.get(id));
    }

    /**
     * 创建
     * @param request
     * @return
     */
    @PostMapping
    public CommonResult<Void> create(@Validated @RequestBody ComponentSaveRequest request) {
        componentService.create(request);
        return CommonResult.success(null);
    }

    /**
     * 修改
     * @param id 数据id
     * @param request
     * @return
     */
    @PutMapping("/{id}")
    public CommonResult<Void> update(@PathVariable("id") String id, @Validated @RequestBody ComponentSaveRequest request) {
        componentService.update(id,request);
        return CommonResult.success(null);
    }

    /**
     * 删除
     * @param ids 数据id列表
     * @return
     */
    @DeleteMapping("/{ids}")
    public CommonResult<Void> delete(@PathVariable("ids") List<String> ids) {
        componentService.delete(ids);
        return CommonResult.success(null);
    }
}
