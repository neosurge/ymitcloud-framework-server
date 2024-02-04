package com.ymitcloud.module.promotion.controller.admin.diy.vo.template;


import java.util.List;

import com.ymitcloud.module.promotion.controller.admin.diy.vo.page.DiyPagePropertyRespVO;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 装修模板属性 Response VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DiyTemplatePropertyRespVO extends DiyTemplateBaseVO {


    /**
     * 装修模板编号
     */
    private Long id;

    /**
     * 模板属性，JSON 格式
     */
    private String property;

    /**
     * 模板页面
     */

    private List<DiyPagePropertyRespVO> pages;

}
