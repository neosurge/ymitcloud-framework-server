package com.ymit.framework.mybatis.core.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.ymit.framework.web.core.util.WebFrameworkUtils;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 通用参数填充实现类
 * <p>
 * 如果没有显式的对通用参数进行赋值，这里会对通用参数进行填充、赋值
 *
 * @author Y.S
 * @date 2024.05.16
 */
public class DefaultDBFieldHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());

        Long userId = WebFrameworkUtils.getLoginUserId();
        // 当前登录用户不为空
        if (userId != null) {
            this.strictInsertFill(metaObject, "creator", String.class, userId + "");
            this.strictInsertFill(metaObject, "updater", String.class, userId + "");
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 更新时间为空，则以当前时间为更新时间
        Object modifyTime = this.getFieldValByName("updateTime", metaObject);
        if (Objects.isNull(modifyTime)) {
            this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }
        // 当前登录用户不为空，更新人为空，则当前登录用户为更新人
        Object modifier = this.getFieldValByName("updater", metaObject);
        Long userId = WebFrameworkUtils.getLoginUserId();
        if (Objects.nonNull(userId) && Objects.isNull(modifier)) {
            this.setFieldValByName("updater", userId.toString(), metaObject);
        }
    }
}
