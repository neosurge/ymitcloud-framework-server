package com.ymitcloud.module.product.controller.admin.spu.vo;


import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 商品 SPU Base VO，提供给添加、修改、详细的子 VO 使用 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成

 *
 * @author HUIHUI
 */
@Data
public class ProductSpuBaseVO {

    /**
     * 商品名称
     */
    @NotEmpty(message = "商品名称不能为空")
    private String name;
    /**
     * 关键字
     */
    @NotEmpty(message = "商品关键字不能为空")
    private String keyword;
    /**
     * 商品简介
     */
    @NotEmpty(message = "商品简介不能为空")
    private String introduction;
    /**
     * 商品详情
     */
    @NotEmpty(message = "商品详情不能为空")
    private String description;
    /**
     * 商品分类编号
     */
    @NotNull(message = "商品分类不能为空")
    private Long categoryId;
    /**
     * 商品品牌编号
     */
    @NotNull(message = "商品品牌不能为空")
    private Long brandId;
    /**
     * 商品封面图
     */
    @NotEmpty(message = "商品封面图不能为空")
    private String picUrl;
    /**
     * 商品轮播图
     */
    private List<String> sliderPicUrls;
    /**
     * 商品视频
     */
    private String videoUrl;
    /**
     * 单位
     */
    @NotNull(message = "商品单位不能为空")
    private Integer unit;
    /**
     * 排序字段
     */

    @NotNull(message = "商品排序字段不能为空")
    private Integer sort;

    // ========== SKU 相关字段 =========

    /**
     * 规格类型
     */

    @NotNull(message = "商品规格类型不能为空")
    private Boolean specType;

    // ========== 物流相关字段 =========

    /**
     * 物流配置模板编号
     */

    @NotNull(message = "物流配置模板编号不能为空")
    private Long deliveryTemplateId;

    // ========== 营销相关字段 =========
    /**
     * 是否热卖推荐
     */
    @NotNull(message = "商品推荐不能为空")
    private Boolean recommendHot;
    /**
     * 是否优惠推荐
     */
    @NotNull(message = "商品推荐不能为空")
    private Boolean recommendBenefit;
    /**
     * 是否精品推荐
     */
    @NotNull(message = "商品推荐不能为空")
    private Boolean recommendBest;
    /**
     * 是否新品推荐
     */
    @NotNull(message = "商品推荐不能为空")
    private Boolean recommendNew;
    /**
     * 是否优品推荐
     */
    @NotNull(message = "商品推荐不能为空")
    private Boolean recommendGood;
    /**
     * 赠送积分
     */
    @NotNull(message = "商品赠送积分不能为空")
    private Integer giveIntegral;
    /**
     * 分销类型
     */
    @NotNull(message = "商品分销类型不能为空")
    private Boolean subCommissionType;
    /**
     * 活动展示顺序
     */
    private List<Integer> activityOrders;

    // ========== 统计相关字段 =========
    /**
     * 虚拟销量
     */

    private Integer virtualSalesCount;

}
