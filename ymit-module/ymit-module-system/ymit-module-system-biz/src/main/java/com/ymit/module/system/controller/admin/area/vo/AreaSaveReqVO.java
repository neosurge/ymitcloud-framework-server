package com.ymit.module.system.controller.admin.area.vo;


import com.ymit.module.infra.api.file.dto.SaveWithFileRelations;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 管理后台 - 行政区划新增/修改 Request VO
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class AreaSaveReqVO extends SaveWithFileRelations {


    @Serial
    private static final long serialVersionUID = 9131479817367590543L;
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 区域编码
     */
    @NotEmpty(message = "区域编码不能为空")
    private String code;

    /**
     * 区域名称
     */
    @NotEmpty(message = "区域名称不能为空")
    private String name;

    /**
     * 区域等级：1 : 省/直辖市、2 : 市、3 : 区/县、4 : 乡镇/街道、5 : 社区/村委会
     */
    @NotNull(message = "区域等级：1 : 省/直辖市、2 : 市、3 : 区/县、4 : 乡镇/街道、5 : 社区/村委会不能为空")
    private Integer level;

    /**
     * 父级ID
     */
    private Long parentId;

}