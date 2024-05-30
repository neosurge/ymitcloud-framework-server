package com.ymit.module.infra.dal.dataobject.db;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ymit.framework.mybatis.core.dataobject.BaseDO;
import com.ymit.framework.mybatis.core.type.EncryptTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serial;

/**
 * 数据源配置
 *
 * @author 云码源码
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "infra_data_source_config", autoResultMap = true)
@KeySequence("infra_data_source_config_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@Accessors(chain = true)
public class DataSourceConfigDO extends BaseDO {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 主键编号 - Master 数据源
     */
    public static final Long ID_MASTER = 0L;
    /**
     * 主键编号
     */
    private Long id;
    /**
     * 连接名
     */
    private String name;

    /**
     * 数据源连接
     */
    private String url;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    @TableField(typeHandler = EncryptTypeHandler.class)
    private String password;
}
