package com.ymitcloud.module.infra.dal.dataobject.demo.demo02;

import lombok.*;
import com.baomidou.mybatisplus.annotation.*;
import com.ymitcloud.framework.mybatis.core.dataobject.BaseDO;

/**
 * 示例分类 DO
 *

 * @author 

 */
@TableName("infra_demo02_category")
@KeySequence("infra_demo02_category_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Demo02CategoryDO extends BaseDO {

    public static final Long PARENT_ID_ROOT = 0L;

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 名字
     */
    private String name;
    /**
     * 父级编号
     */
    private Long parentId;

}