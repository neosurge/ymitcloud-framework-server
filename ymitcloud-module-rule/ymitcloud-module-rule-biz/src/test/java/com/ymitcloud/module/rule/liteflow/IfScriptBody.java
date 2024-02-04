package com.ymitcloud.module.rule.liteflow;

import com.yomahub.liteflow.script.ScriptExecuteWrap;
import com.yomahub.liteflow.script.body.JaninoIfScriptBody;
import com.yomahub.liteflow.slot.DefaultContext;

public class IfScriptBody implements JaninoIfScriptBody {

    @Override
    public Boolean body(ScriptExecuteWrap wrap) {
        DefaultContext ctx = (DefaultContext) wrap.cmp.getContextBean(DefaultContext.class);
        //条件组件返回true/false
        return (Double) ctx.getData("waterLevel") > 100;
    }
}
