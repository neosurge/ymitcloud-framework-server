package com.ymit.framework.datapermission.core.rule;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import com.ymit.framework.datapermission.core.annotation.DataPermission;
import com.ymit.framework.datapermission.core.aop.DataPermissionContextHolder;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 默认的 DataPermissionRuleFactoryImpl 实现类
 * 支持通过 {@link DataPermissionContextHolder} 过滤数据权限
 *
 * @author Y.S
 * @date 2024.05.23
 */
public class DataPermissionRuleFactoryImpl implements DataPermissionRuleFactory {
    /**
     * 数据权限规则数组
     */
    private final List<DataPermissionRule> rules;

    public DataPermissionRuleFactoryImpl(List<DataPermissionRule> rules) {
        this.rules = rules;
    }

    @Override
    public List<DataPermissionRule> getDataPermissionRules() {
        return this.rules;
    }

    /**
     * 获取数据范围规则数组
     *
     * @param mappedStatementId 暂时没有用。以后，可以基于 mappedStatementId + DataPermission 进行缓存
     */
    @Override
    public List<DataPermissionRule> getDataPermissionRule(String mappedStatementId) {
        // 1. 无数据权限
        if (CollUtil.isEmpty(this.rules)) {
            return Collections.emptyList();
        }
        // 2. 未配置，则默认开启
        DataPermission dataPermission = DataPermissionContextHolder.get();
        if (dataPermission == null) {
            return this.rules;
        }
        // 3. 已配置，但禁用
        if (!dataPermission.enable()) {
            return Collections.emptyList();
        }
        // 4. 已配置，只选择部分规则
        if (ArrayUtil.isNotEmpty(dataPermission.includeRules())) {
            return this.rules.stream().filter(rule -> ArrayUtil.contains(dataPermission.includeRules(), rule.getClass())).collect(Collectors.toList()); // 一般规则不会太多，所以不采用 HashSet 查询
        }
        // 5. 已配置，只排除部分规则
        if (ArrayUtil.isNotEmpty(dataPermission.excludeRules())) {
            return this.rules.stream().filter(rule -> !ArrayUtil.contains(dataPermission.excludeRules(), rule.getClass())).collect(Collectors.toList()); // 一般规则不会太多，所以不采用 HashSet 查询
        }
        // 6. 已配置，全部规则
        return this.rules;
    }
}
