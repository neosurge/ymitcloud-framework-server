package com.ymit.module.infra.api.file.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Y.S
 * @date 2024.05.17
 */
public class FileRelation implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 文件id
     */
    @NotNull(message = "文件不能为空")
    private Long fileId;
    /**
     * 数据源值
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String dataKind;
    /**
     * 数据码值
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long dataCode;
    /**
     * 使用场景
     */
    @NotNull(message = "使用场景不能为空")
    private String useScene;
    /**
     * 排序，此处小靠前
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer sort;
    /**
     * 备注
     */
    private String remark;

    public Long getFileId() {
        return this.fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
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
