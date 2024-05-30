package com.ymit.module.infra.api.file.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 附件关联
 *
 * @author Y.S
 * @date 2024.05.17
 */
public class SaveWithFileRelations implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 附件关联数据
     */
    private List<FileRelation> fileRelations;

    public List<FileRelation> getFileRelations() {
        return this.fileRelations;
    }

    public void setFileRelations(List<FileRelation> fileRelations) {
        this.fileRelations = fileRelations;
    }
}
