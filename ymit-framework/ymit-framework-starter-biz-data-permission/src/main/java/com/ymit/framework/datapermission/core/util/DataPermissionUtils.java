package com.ymit.framework.datapermission.core.util;

import com.ymit.framework.datapermission.core.annotation.DataPermission;
import com.ymit.framework.datapermission.core.aop.DataPermissionContextHolder;

/**
 * 数据权限 Util
 *
 * @author Y.S
 * @date 2024.05.24
 */
public class DataPermissionUtils {
    private static DataPermission DATA_PERMISSION_DISABLE;

    @DataPermission(enable = false)
    private static DataPermission getDisableDataPermissionDisable() {
        if (DATA_PERMISSION_DISABLE == null) {
            try {
                DATA_PERMISSION_DISABLE = DataPermissionUtils.class.getDeclaredMethod("getDisableDataPermissionDisable").getAnnotation(DataPermission.class);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        return DATA_PERMISSION_DISABLE;
    }

    /**
     * 忽略数据权限，执行对应的逻辑
     *
     * @param runnable 逻辑
     */
    public static void executeIgnore(Runnable runnable) {
        DataPermission dataPermission = getDisableDataPermissionDisable();
        DataPermissionContextHolder.add(dataPermission);
        try {
            // 执行 runnable
            runnable.run();
        } finally {
            DataPermissionContextHolder.remove();
        }
    }

}
