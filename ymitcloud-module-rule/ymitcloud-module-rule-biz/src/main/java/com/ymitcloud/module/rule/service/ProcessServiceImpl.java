package com.ymitcloud.module.rule.service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ymitcloud.framework.common.exception.ServiceException;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.rule.controller.admin.request.LogicFlowConvertElRequest;
import com.ymitcloud.module.rule.controller.admin.request.ProcessPageRequest;
import com.ymitcloud.module.rule.controller.admin.request.ProcessSaveRequest;
import com.ymitcloud.module.rule.controller.admin.response.LogicFlowConvertElResponse;
import com.ymitcloud.module.rule.controller.admin.response.ProcessExecuteResponse;
import com.ymitcloud.module.rule.controller.admin.response.ProcessResponse;
import com.ymitcloud.module.rule.dal.dataobject.Chain;
import com.ymitcloud.module.rule.dal.dataobject.Process;
import com.ymitcloud.module.rule.dal.dataobject.Script;
import com.ymitcloud.module.rule.mapper.ChainMapper;
import com.ymitcloud.module.rule.mapper.ProcessMapper;
import com.ymitcloud.module.rule.mapper.ScriptMapper;
import com.ymitcloud.module.rule.service.logicflow.LogicFlowElParser;
import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.flow.LiteflowResponse;
import com.yomahub.liteflow.flow.entity.CmpStep;
import com.yomahub.liteflow.slot.DefaultContext;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ProcessServiceImpl implements ProcessService {
    @Autowired
    private ProcessMapper processMapper;
    @Autowired
    private ChainMapper chainMapper;
    @Autowired
    private ScriptMapper scriptMapper;
    @Autowired
    private FlowExecutor flowExecutor;

    @Override
    public PageResult<ProcessResponse> page(ProcessPageRequest request) {
        QueryWrapper<Process> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(StringUtils.isNotBlank(request.getProcessName()), Process::getProcessName, request.getProcessName());
        Page<Process> page = processMapper.selectPage(new Page<>(request.getPage(), request.getPageSize()), queryWrapper);
        PageResult<ProcessResponse> pageResult = new PageResult<>();
        pageResult.setTotal(page.getTotal());
        List<ProcessResponse> list = new ArrayList<>(page.getRecords().size());
        page.getRecords().forEach(record -> {
            ProcessResponse resp = new ProcessResponse();
            BeanUtils.copyProperties(record, resp);
            list.add(resp);
        });
        pageResult.setList(list);
        return pageResult;
    }

    @Override
    public ProcessResponse get(String id) {
        Process process = processMapper.selectById(id);
        if (process == null) {
            throw new ServiceException(40004, "找不到对应数据");
        }
        ProcessResponse resp = new ProcessResponse();
        BeanUtils.copyProperties(process, resp);
        return resp;
    }

    /**
     * 创建实现
     * 1、解析前端的流程json，获取el表达式并且验证成功才往下走
     * 2、插入规则链信息
     * 3、插入流程信息
     * 4、插入脚本信息
     *
     * @param request
     */
    @Transactional
    @Override
    public void create(ProcessSaveRequest request) {
        QueryWrapper<Process> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Process::getProcessName, request.getProcessName());
        if (processMapper.exists(queryWrapper)) {
            throw new ServiceException(40006, "流程名称已存在");
        }
        Process process = new Process();
        BeanUtils.copyProperties(request, process);
        if (request.getProcessData() == null) {
            processMapper.insert(process);
            return;
        }
        //获取el表达式
        String elData = new LogicFlowElParser(JSON.parseObject(request.getProcessData().toJSONString(), LogicFlowConvertElRequest.class)).extractElNode().generateEl();
//        if (!LiteFlowChainELBuilder.validate(elData)) {
//            throw new ServiceException(99997, "流程规则无法被正确解析");
//        }
        //添加规则链
        Chain chain = new Chain();
        chain.setChainName(request.getProcessName());
        chain.setApplicationName("ymitcloud");
        chain.setElData(elData);
        chain.setEnable(true);
        chainMapper.insert(chain);
        //添加流程

        process.setChainId(chain.getId());
        processMapper.insert(process);
        insertScript(request.getProcessData(), process.getId());
    }

    @Transactional
    @Override
    public void update(String id, ProcessSaveRequest request) {
        Process process = processMapper.selectById(id);
        if (process == null) {
            throw new ServiceException(40004, "找不到对应数据");
        }

        QueryWrapper<Process> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().ne(Process::getId, id).eq(Process::getProcessName, request.getProcessName());
        if (processMapper.exists(queryWrapper)) {
            throw new ServiceException(40006, "流程名称已存在");
        }

        //获取el表达式
        String elData = new LogicFlowElParser(JSON.parseObject(request.getProcessData().toJSONString(), LogicFlowConvertElRequest.class)).extractElNode().generateEl();

        Chain chain = chainMapper.selectById(process.getChainId());
        if (chain == null) {
            //添加规则链
            chain = new Chain();
            chain.setChainName(request.getProcessName());
            chain.setApplicationName("ymitcloud");
            chain.setEnable(true);
            //设置EL表达式
            chain.setElData(elData);
            chainMapper.insert(chain);
        } else {
            //设置EL表达式
            chain.setElData(elData);
            chainMapper.updateById(chain);
        }

        BeanUtils.copyProperties(request, process);
        process.setChainId(chain.getId());
        processMapper.updateById(process);

        //将之前的脚本信息删除，再重新插入
        scriptMapper.delete(new QueryWrapper<>(Script.class).lambda().eq(Script::getProcessId, id));
        insertScript(request.getProcessData(), id);
    }

    @Transactional
    @Override
    public void remove(List<String> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            throw new ServiceException(40005, "数据id列表不能为空");
        }
        //获取流程列表，主要为了删除规则链
        List<Process> processes = processMapper.selectBatchIds(ids);
        if (CollectionUtils.isEmpty(processes)) {
            throw new ServiceException(40005, "查询不到要删除的相关数据");
        }
        //删除对应脚本
        scriptMapper.delete(new QueryWrapper<>(Script.class).lambda().in(Script::getProcessId, ids));
        //获取规则链id列表
        List<String> chainIds = processes.stream().map(Process::getChainId).filter(Objects::nonNull).toList();
        if (CollectionUtils.isNotEmpty(chainIds)) {
            //删除相关的规则链
            chainMapper.deleteBatchIds(chainIds);
        }
        //删除流程
        processMapper.deleteBatchIds(ids);
    }

    @Override
    public LogicFlowConvertElResponse logicFlowConvertEl(LogicFlowConvertElRequest request) {
        LogicFlowConvertElResponse resp = new LogicFlowConvertElResponse();
        resp.setElData(new LogicFlowElParser(request).extractElNode().generateEl());
        return resp;
    }

    @Override
    public List<ProcessExecuteResponse> execute(String id) {
        Process process = processMapper.selectById(id);
        if (process == null) {
            throw new ServiceException(40004, "找不到对应流程信息");
        }
        Chain chain = chainMapper.selectById(process.getChainId());

        if (chain == null) {
            throw new ServiceException(40004, "找不到对应规则链信息");
        }
        List<ProcessExecuteResponse> responses = new ArrayList<>();
        flowExecutor.reloadRule();
        LiteflowResponse liteflowResponse = flowExecutor.execute2Resp(chain.getChainName(), null, DefaultContext.class);
        //获取执行步骤队列
        Queue<CmpStep> executeStepQueue = liteflowResponse.getExecuteStepQueue();
        executeStepQueue.forEach(comStep -> {
            ProcessExecuteResponse response = new ProcessExecuteResponse();
            BeanUtils.copyProperties(comStep, response);
            responses.add(response);
        });
        return responses;
    }

    /**
     * 插入脚本信息
     *
     * @param processData 流程数据
     * @param processId   流程ID
     */
    private void insertScript(JSONObject processData, String processId) {
        //获取nodes节点列表
        List<JSONObject> nodes = processData.getList("nodes", JSONObject.class);
        //遍历节点
        for (JSONObject node : nodes) {
            //获取节点属性
            JSONObject properties = node.getJSONObject("properties");
            //如果不是脚本组件，继续下一个
            if (!StringUtils.equals("script", properties.getString("componentType"))) {
                continue;
            }

            //添加脚本信息
            Script script = new Script();
            script.setApplicationName("ymitcloud");
            //脚本类型
            String nodeType = properties.getString("nodeType");
            //获取脚本ID
            String scriptId = switch (nodeType) {
                case "COMMON" -> properties.getString("nodeId");
                case "IF", "SWITCH" -> properties.getString("conditionNodeId");
                default -> throw new ServiceException(99997, "节点类型错误");
            };
            script.setScriptId(scriptId);
            script.setScriptType(switch (nodeType) {
                case "COMMON" -> "script";
                case "IF" -> "if_script";
                case "SWITCH" -> "switch_script";
                default -> throw new ServiceException(99997, "节点类型错误");
            });
            //获取脚本组件属性对象
            JSONObject componentScript = properties.getJSONObject("componentScript");
            script.setScriptLanguage(componentScript.getString("language"));
            //获取脚本内容
            String scriptData = componentScript.getString("content");
            if (StringUtils.isBlank(scriptData)) {
                //设置默认脚本内容
                scriptData = """
                        import com.yomahub.liteflow.script.ScriptExecuteWrap;
                        import com.yomahub.liteflow.script.body.JaninoCommonScriptBody;
                        import com.yomahub.liteflow.slot.DefaultContext;
                                                
                        public class CommonScriptBody implements JaninoCommonScriptBody {
                            @Override
                            public Void body(ScriptExecuteWrap wrap) {
                                //普通组件返回null
                                return null;
                            }
                        }
                        """;
            }
            script.setScriptData(scriptData);
            //脚本名称
            JSONObject text = node.getJSONObject("text");
            script.setScriptName(Optional.ofNullable(text)
                    .map(json -> json.getString("value"))
                    .filter(StringUtils::isNotBlank)
                    .orElse(scriptId));
            script.setEnable(true);
            script.setProcessId(processId);
            scriptMapper.insert(script);
        }
    }
}
