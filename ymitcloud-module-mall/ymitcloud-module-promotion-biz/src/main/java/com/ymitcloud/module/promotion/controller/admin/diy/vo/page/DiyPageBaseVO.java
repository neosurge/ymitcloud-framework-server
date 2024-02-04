package com.ymitcloud.module.promotion.controller.admin.diy.vo.page;


import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


/**
 * 装修页面 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class DiyPageBaseVO {


    /** 
     * 装修模板编号
     */
    private Long templateId;

    /** 
     * 页面名称
     */
    @NotNull(message = "页面名称不能为空")
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
