package com.ymit.module.infra.controller.admin.file.vo.filerel;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 管理后台 - 文件关系 Response VO
 */
@Data
@ExcelIgnoreUnannotated
@ToString(callSuper = true)
public class FileRelRespVO implements Serializable {

    @Serial
    private static final long serialVersionUID = -2215592982815660591L;
    /**
     * 文件编号
     */
    @ExcelProperty("文件编号")
    private Long id;

    /**
     * 文件id
     */
    @ExcelProperty("文件id")
    private Long fileId;

    /**
     * 配置编号
     */
    @ExcelProperty("配置编号")
    private Long configId;

    /**
     * 文件名
     */
    @ExcelProperty("文件名")
    private String name;

    /**
     * 文件路径
     */
    @ExcelProperty("文件路径")
    private String path;

    /**
     * 文件 URL
     */
    @ExcelProperty("文件 URL")
    private String url;

    /**
     * 文件类型
     */
    @ExcelProperty("文件类型")
    private String mime;

    /**
     * 文件大小(Byte)
     */
    @ExcelProperty("文件大小(Byte)")
    private Integer size;

    /**
     * 数据源值
     */
    @ExcelProperty("数据源值")
    private String dataKind;

    /**
     * 数据码值
     */
    @ExcelProperty("数据码值")
    private Long dataCode;

    /**
     * 使用场景
     */
    @ExcelProperty("使用场景")
    private String useScene;

    /**
     * 文件类型：image,file,video,audio等
     */
    @ExcelProperty("文件类型：image,file,video,audio等")
    private String type;

    /**
     * 文件格式：png jpg mp4 mp3 doc等
     */
    @ExcelProperty("文件格式：png jpg mp4 mp3 doc等")
    private String ext;

    /**
     * 排序，此处小靠前
     */
    @ExcelProperty("排序，此处小靠前")
    private Integer sort;

    /**
     * 备注
     */
    @ExcelProperty("备注")
    private String remark;

    /**
     * 创建时间
     */
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    /**
     * 最后更新时间
     */
    @ExcelProperty("最后更新时间")
    private LocalDateTime updateTime;

    /**
     * 创建者
     */
    @ExcelProperty("创建者")
    private String creator;

    /**
     * 更新者
     */
    @ExcelProperty("更新者")
    private String updater;
}