package com.ymitcloud.module.promotion.controller.admin.banner.vo;

import com.ymitcloud.framework.common.enums.CommonStatusEnum;
import com.ymitcloud.framework.common.validation.InEnum;
import com.ymitcloud.module.promotion.enums.banner.BannerPositionEnum;


import jakarta.validation.constraints.NotNull;
import lombok.Data;


/**
 * Banner Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 * @author xia
 */
@Data
public class BannerBaseVO {


    /** 
     * 标题
     */
    @NotNull(message = "标题不能为空")
    private String title;

    /** 
     * 跳转链接
     */
    @NotNull(message = "跳转链接不能为空")
    private String url;

    /** 
     * 图片地址
     */
    @NotNull(message = "图片地址不能为空")
    private String picUrl;

    /** 
     * position
     */

    @NotNull(message = "position 不能为空")
    @InEnum(BannerPositionEnum.class)
    private Integer position;


    /** 
     * 排序
     */
    @NotNull(message = "排序不能为空")
    private Integer sort;

    /** 
     * 状态
     */

    @NotNull(message = "状态不能为空")
    @InEnum(CommonStatusEnum.class)
    private Integer status;


    /** 
     * 备注
     */

    private String memo;

}
