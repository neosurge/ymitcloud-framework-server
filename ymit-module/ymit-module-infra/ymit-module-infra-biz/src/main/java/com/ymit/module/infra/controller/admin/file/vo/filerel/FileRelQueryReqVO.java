package com.ymit.module.infra.controller.admin.file.vo.filerel;

import com.ymit.framework.common.data.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.Collection;

import static com.ymit.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 管理后台 - 文件关系 Request VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FileRelQueryReqVO extends PageParam {

    @Serial
    private static final long serialVersionUID = -7042826863966444680L;
    /**
     * id集合
     */
    private Collection<Long> ids;

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
     * 文件类型：image,file,video,audio等 集合
     */
    private Collection<String> types;

    /**
     * 文件格式：png jpg mp4 mp3 doc等
     */
    private String ext;

    /**
     * 文件格式：png jpg mp4 mp3 doc等 集合
     */
    private Collection<String> exts;

    /**
     * 排序，此处小靠前
     */
    private Integer sort;

    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;
}
