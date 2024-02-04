package com.ymitcloud.module.rule.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.rule.controller.admin.request.ComponentPageRequest;
import com.ymitcloud.module.rule.controller.admin.request.ComponentSaveRequest;
import com.ymitcloud.module.rule.controller.admin.response.ComponentParamResponse;
import com.ymitcloud.module.rule.controller.admin.response.ComponentResponse;
import com.ymitcloud.module.rule.dal.dataobject.Component;
import com.ymitcloud.module.rule.dal.dataobject.ComponentParam;
import com.ymitcloud.module.rule.mapper.ComponentMapper;
import com.ymitcloud.module.rule.mapper.ComponentParamMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComponentServiceImpl implements ComponentService {
    @Autowired
    private ComponentMapper componentMapper;
    @Autowired
    private ComponentParamMapper componentParamMapper;

    @Override
    public PageResult<ComponentResponse> page(ComponentPageRequest request) {
        QueryWrapper<Component> queryWrapper = new QueryWrapper<>();
        Page<Component> page = componentMapper.selectPage(new Page<>(request.getPage(), request.getPageSize()), queryWrapper);
        PageResult<ComponentResponse> pageResult = new PageResult<>();
        pageResult.setTotal(page.getTotal());
        List<ComponentResponse> list = new ArrayList<>(page.getRecords().size());
        page.getRecords().forEach(record -> {
            ComponentResponse response = new ComponentResponse();
            BeanUtils.copyProperties(record, response);
            List<ComponentParam> componentParams = componentParamMapper.selectList(new QueryWrapper<ComponentParam>().lambda().eq(ComponentParam::getComponentId, record.getId()));
            if (CollectionUtils.isNotEmpty(componentParams)) {
                List<ComponentParamResponse> resps = new ArrayList<>(componentParams.size());
                componentParams.forEach(param -> {
                    ComponentParamResponse resp = new ComponentParamResponse();
                    BeanUtils.copyProperties(param, resp);
                    resps.add(resp);
                });

                response.setParams(resps);
            }
            list.add(response);
        });
        pageResult.setList(list);
        return pageResult;
    }

    @Override
    public ComponentResponse get(String id) {
        Component component = componentMapper.selectById(id);
        if (component == null) {
            throw new RuntimeException("找不到对应数据");
        }
        ComponentResponse response = new ComponentResponse();
        BeanUtils.copyProperties(component, response);
        //查询参数信息
        List<ComponentParam> componentParams = componentParamMapper.selectList(new QueryWrapper<ComponentParam>().lambda().eq(ComponentParam::getComponentId, id));
        if (CollectionUtils.isNotEmpty(componentParams)) {
            List<ComponentParamResponse> resps = new ArrayList<>(componentParams.size());
            componentParams.forEach(param -> {
                ComponentParamResponse resp = new ComponentParamResponse();
                BeanUtils.copyProperties(param, resp);
                resps.add(resp);
            });

            response.setParams(resps);
        }
        return response;
    }

    @Transactional
    @Override
    public void create(ComponentSaveRequest request) {
        Component component = new Component();
        BeanUtils.copyProperties(request, component);
        componentMapper.insert(component);
        if (CollectionUtils.isNotEmpty(request.getParams())) {
            request.getParams().forEach(param -> {
                ComponentParam componentParam = new ComponentParam();
                BeanUtils.copyProperties(param, componentParam);
                componentParam.setComponentId(component.getId());
                componentParamMapper.insert(componentParam);
            });
        }
    }

    @Transactional
    @Override
    public void update(String id, ComponentSaveRequest request) {
        Component component = componentMapper.selectById(id);
        if (component == null) {
            throw new RuntimeException("找不到对应数据");
        }
        BeanUtils.copyProperties(request, component);
        componentMapper.updateById(component);
        //全部删除再重新插入
        componentParamMapper.delete(new QueryWrapper<ComponentParam>().lambda().eq(ComponentParam::getComponentId,id));
        if (CollectionUtils.isNotEmpty(request.getParams())) {
            request.getParams().forEach(param -> {
                ComponentParam componentParam = new ComponentParam();
                BeanUtils.copyProperties(param, componentParam);
                componentParam.setComponentId(id);
                componentParamMapper.insert(componentParam);
            });
        }
    }

    @Transactional
    @Override
    public void delete(List<String> ids) {
        if(CollectionUtils.isEmpty(ids)){
            throw new RuntimeException("数据id列表不能为空");
        }
        componentMapper.deleteBatchIds(ids);
        componentParamMapper.delete(new QueryWrapper<ComponentParam>().lambda().in(ComponentParam::getComponentId,ids));
    }
}
