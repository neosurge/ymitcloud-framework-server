package com.ymitcloud.module.promotion.controller.admin.diy.vo.page;


import java.time.LocalDateTime;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 管理后台 - 装修页面 Response VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DiyPageRespVO extends DiyPageBaseVO {


    /**
     * 装修页面编号
     */
    private Long id;

    /**
     * 创建时间
     */

    private LocalDateTime createTime;

}
