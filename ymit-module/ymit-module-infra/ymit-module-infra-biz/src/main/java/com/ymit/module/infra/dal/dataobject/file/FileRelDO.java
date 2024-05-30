package com.ymit.module.infra.dal.dataobject.file;


import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ymit.framework.mybatis.core.dataobject.BaseDO;
import lombok.*;

import java.io.Serial;

/**
 * 文件关系 DO
 */
@TableName("infra_file_rel")
@KeySequence("infra_file_rel_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileRelDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 5891657446569234383L;
    /**
     * 文件编号
     */
    @TableId
    private Long id;
    /**
     * 文件id
     */
    private Long fileId;
    /**
     * 配置编号
     */
    private Long configId;
    /**
     * 文件名
     */
    private String name;
    /**
     * 文件路径
     */
    private String path;
    /**
     * 文件 URL
     */
    private String url;
    /**
     * 文件类型
     */
    private String mime;
    /**
     * 文件大小(Byte)
     */
    private Integer size;
    /**
     * 数据源值
     */
    private String dataKind;
    /**
     * 数据码值
     */
    private Long dataCode;
    /**
     * 使用场景
     */
    private String useScene;
    /**
     * 文件类型：image,file,video,audio等
     */
    private String type;
    /**
     * 文件格式：png jpg mp4 mp3 doc等
     */
    private String ext;
    /**
     * 排序，此处小靠前
     */
    private Integer sort;
    /**
     * 备注
     */
    private String remark;

}