package com.ymitcloud.module.promotion.controller.admin.diy.vo.template;


import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 装修模板 Base VO，提供给添加、修改、详细的子 VO 使用 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成

 */
@Data
public class DiyTemplateBaseVO {


    /**
     * 模板名称
     */
    @NotEmpty(message = "模板名称不能为空")
    private String name;

    /**
     * 备注
     */
    private String remark;

    /**
     * 预览图
     */

    private List<String> previewImageUrls;

}
