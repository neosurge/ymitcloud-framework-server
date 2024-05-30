package com.ymit.framework.datapermission.config;

import com.ymit.framework.common.data.LoginUser;
import com.ymit.framework.datapermission.core.rule.dept.DeptDataPermissionRule;
import com.ymit.framework.datapermission.core.rule.dept.DeptDataPermissionRuleCustomizer;
import com.ymit.module.system.api.permission.PermissionApi;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * 基于部门的数据权限 AutoConfiguration
 *
 * @author Y.S
 * @date 2024.05.23
 */
@AutoConfiguration
@ConditionalOnClass(LoginUser.class)
@ConditionalOnBean(value = {PermissionApi.class, DeptDataPermissionRuleCustomizer.class})
public class YmitDeptDataPermissionAutoConfiguration {
    @Bean
    public DeptDataPermissionRule deptDataPermissionRule(PermissionApi permissionApi, List<DeptDataPermissionRuleCustomizer> customizers) {
        // 创建 DeptDataPermissionRule 对象
        DeptDataPermissionRule rule = new DeptDataPermissionRule(permissionApi);
        // 补全表配置
        customizers.forEach(customizer -> customizer.customize(rule));
        return rule;
    }
}
