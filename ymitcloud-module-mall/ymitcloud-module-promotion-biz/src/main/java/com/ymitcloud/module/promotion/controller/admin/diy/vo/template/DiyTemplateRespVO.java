package com.ymitcloud.module.promotion.controller.admin.diy.vo.template;


import java.time.LocalDateTime;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 装修模板
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DiyTemplateRespVO extends DiyTemplateBaseVO {


    /**
     * 装修模板编号
     */
    private Long id;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 是否使用
     */
    private Boolean used;

    /**
     * 使用时间
     */

    private LocalDateTime usedTime;

}
