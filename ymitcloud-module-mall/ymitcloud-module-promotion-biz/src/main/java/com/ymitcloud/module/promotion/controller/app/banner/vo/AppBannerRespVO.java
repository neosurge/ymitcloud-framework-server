package com.ymitcloud.module.promotion.controller.app.banner.vo;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 用户 App - Banner Response VO
 */
@Data
public class AppBannerRespVO {

    /**
     * 编号
     */
    private Long id;

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

}
