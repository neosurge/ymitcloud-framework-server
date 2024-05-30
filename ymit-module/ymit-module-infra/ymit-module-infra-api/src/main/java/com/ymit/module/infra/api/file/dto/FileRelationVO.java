package com.ymit.module.infra.api.file.dto;

import java.io.Serial;
import java.io.Serializable;

/**
 * 文件输出vo
 *
 * @author Y.S
 * @date 2024.05.17
 */
public class FileRelationVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 文件编号
     */
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

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFileId() {
        return this.fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Long getConfigId() {
        return this.configId;
    }

    public void setConfigId(Long configId) {
        this.configId = configId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMime() {
        return this.mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public Integer getSize() {
        return this.size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getDataKind() {
        return this.dataKind;
    }

    public void setDataKind(String dataKind) {
        this.dataKind = dataKind;
    }

    public Long getDataCode() {
        return this.dataCode;
    }

    public void setDataCode(Long dataCode) {
        this.dataCode = dataCode;
    }

    public String getUseScene() {
        return this.useScene;
    }

    public void setUseScene(String useScene) {
        this.useScene = useScene;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExt() {
        return this.ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public Integer getSort() {
        return this.sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}