package com.ymitcloud.module.promotion.controller.admin.bargain.vo.help;


import lombok.Data;


/**
 * 砍价助力 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class BargainHelpBaseVO {


    /** 
     * 用户编号
     */
    private Long userId;

    /** 
     * 砍价活动名称
     */
    private Long activityId;

    /** 
     * 砍价记录编号
     */
    private Long recordId;

    /** 
     * 减少砍价，单位：分
     */

    private Integer reducePrice;

}
