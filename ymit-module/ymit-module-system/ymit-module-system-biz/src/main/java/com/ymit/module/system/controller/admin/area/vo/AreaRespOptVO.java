package com.ymit.module.system.controller.admin.area.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class AreaRespOptVO extends AreaRespSimpleVO {
    @Serial
    private static final long serialVersionUID = 2755730872050131678L;
    /***
     * 父节点名称
     */
    private String parentName;
    /**
     * 父节点
     */
    @JsonIgnore
    private AreaRespSimpleVO parent;
    /**
     * 父节点名称集合
     */
    private String parentNames;
    /**
     * 父节点集合
     */
    @JsonIgnore
    private List<AreaRespSimpleVO> parents;
}
