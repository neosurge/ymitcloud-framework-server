package com.ymit.module.system.dal.dataobject.area;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ymit.framework.mybatis.core.dataobject.BaseDO;
import lombok.*;

import java.io.Serial;

/**
 * 行政区划 DO
 *
 * @author 超级管理员
 */
@TableName("system_area")
@KeySequence("system_area_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AreaDO extends BaseDO {

    public static final Long PARENT_ID_ROOT = 0L;
    public static final String LAYER_SEPARATOR_CHARS = ",";
    @Serial
    private static final long serialVersionUID = 5686929127150557734L;
    @TableField(exist = false)
    public final String selfKind = "area";
    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 区域编码
     */
    private String code;
    /**
     * 区域名称
     */
    private String name;
    /**
     * 区域等级：1 : 省/直辖市、2 : 市、3 : 区/县、4 : 乡镇/街道、5 : 社区/村委会
     */
    private Integer level;
    /**
     * 父级区域编码
     */
    private String parentCode;
    /**
     * 父级区域ID
     */
    private Long parentId;
    /**
     * 层级
     */
    private Integer layer;
    /**
     * 子节点数量
     */
    private Integer childrenCount;
    /**
     * 层级包含的id集合
     */
    private String layerList;

}