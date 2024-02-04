package com.ymitcloud.module.rule.liteflow;

import com.yomahub.liteflow.script.ScriptExecuteWrap;
import com.yomahub.liteflow.script.body.JaninoCommonScriptBody;
import com.yomahub.liteflow.slot.DefaultContext;

public class CommonScriptBody implements JaninoCommonScriptBody {
    @Override
    public Void body(ScriptExecuteWrap wrap) {
        //获取某地区水位信息
        double waterLevel = 200;
        //Java脚本不支持泛型，凡是调用的方法出现泛型返回，必须得强制转型
        DefaultContext ctx = (DefaultContext) wrap.cmp.getContextBean(DefaultContext.class);
        ctx.setData("waterLevel", waterLevel);
        //普通组件返回null
        return null;
    }
}

