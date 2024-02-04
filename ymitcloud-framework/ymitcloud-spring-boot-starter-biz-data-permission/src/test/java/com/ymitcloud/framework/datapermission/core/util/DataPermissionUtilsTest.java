package com.ymitcloud.framework.datapermission.core.util;

import com.ymitcloud.framework.datapermission.core.aop.DataPermissionContextHolder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DataPermissionUtilsTest {

    @Test
    public void testExecuteIgnore() {
        DataPermissionUtils.executeIgnore(() -> Assertions.assertFalse(DataPermissionContextHolder.get().enable()));
    }

}
