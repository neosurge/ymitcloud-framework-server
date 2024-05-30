package com.ymit.module.system.controller.admin.user.vo.user;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 管理后台 - 用户导入 Response VO
 */
@Data
@Builder
public class UserImportRespVO {
    /**
     * 创建成功的用户名数组
     */
    private List<String> createUsernames;

    /**
     * 更新成功的用户名数组
     */
    private List<String> updateUsernames;
    /**
     * 导入失败的用户集合，key 为用户名，value 为失败原因
     */
    private Map<String, String> failureUsernames;

}
