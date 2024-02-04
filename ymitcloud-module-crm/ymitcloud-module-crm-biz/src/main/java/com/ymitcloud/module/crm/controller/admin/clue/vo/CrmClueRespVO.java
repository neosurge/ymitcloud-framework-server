package com.ymitcloud.module.crm.controller.admin.clue.vo;


import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 管理后台 - 线索 Response VO
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CrmClueRespVO extends CrmClueBaseVO {

    /**
     * 编号，主键自增
     */
    private Long id;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 转化状态
     */
    private Boolean transformStatus;
    /**
     * 跟进状态
     */

    private Boolean followUpStatus;

}
