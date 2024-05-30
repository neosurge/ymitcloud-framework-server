package com.ymit.module.system.api.dict.dto;

import com.ymit.framework.common.enums.CommonStatusEnum;

import java.io.Serial;
import java.io.Serializable;

/**
 * 字典数据 Response DTO
 *
 * @author Y.S
 * @date 2024.05.23
 */
public class DictDataRespDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 4414707374664525572L;
    /**
     * 字典标签
     */
    private String label;
    /**
     * 字典值
     */
    private String value;
    /**
     * 字典类型
     */
    private String dictType;
    /**
     * 状态
     * <p>
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDictType() {
        return this.dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
