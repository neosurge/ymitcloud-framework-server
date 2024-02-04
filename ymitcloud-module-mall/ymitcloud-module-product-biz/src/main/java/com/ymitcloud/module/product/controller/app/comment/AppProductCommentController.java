package com.ymitcloud.module.product.controller.app.comment;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.convertSet;

import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.product.controller.app.comment.vo.AppCommentPageReqVO;
import com.ymitcloud.module.product.controller.app.comment.vo.AppCommentStatisticsRespVO;
import com.ymitcloud.module.product.controller.app.comment.vo.AppProductCommentRespVO;
import com.ymitcloud.module.product.convert.comment.ProductCommentConvert;
import com.ymitcloud.module.product.dal.dataobject.comment.ProductCommentDO;
import com.ymitcloud.module.product.service.comment.ProductCommentService;
import com.ymitcloud.module.product.service.sku.ProductSkuService;


import cn.hutool.core.collection.CollUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 用户 APP - 商品评价
 */

@RestController
@RequestMapping("/product/comment")
@Validated
public class AppProductCommentController {

    @Resource
    private ProductCommentService productCommentService;

    @Resource
    @Lazy
    private ProductSkuService productSkuService;


    /**
     * 获得最近的 n 条商品评价
     * 
     * @param spuId 商品 SPU 编号
     * @param count 数量
     * @return
     */
    @GetMapping("/list")
    public CommonResult<List<AppProductCommentRespVO>> getCommentList(@RequestParam("spuId") Long spuId,

            @RequestParam(value = "count", defaultValue = "10") Integer count) {
        return success(productCommentService.getCommentList(spuId, count));
    }


    /**
     * 获得商品评价分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")

    public CommonResult<PageResult<AppProductCommentRespVO>> getCommentPage(@Valid AppCommentPageReqVO pageVO) {
        // 查询评论分页
        PageResult<ProductCommentDO> commentPageResult = productCommentService.getCommentPage(pageVO, Boolean.TRUE);
        if (CollUtil.isEmpty(commentPageResult.getList())) {
            return success(PageResult.empty(commentPageResult.getTotal()));
        }

        // 拼接返回
        Set<Long> skuIds = convertSet(commentPageResult.getList(), ProductCommentDO::getSkuId);

        PageResult<AppProductCommentRespVO> commentVOPageResult = ProductCommentConvert.INSTANCE
                .convertPage02(commentPageResult, productSkuService.getSkuList(skuIds));
        return success(commentVOPageResult);
    }

    /**
     * 获得商品的评价统计
     * 
     * @param spuId
     * @return
     */
    // TODO 云码：需要搞下
    @GetMapping("/statistics")

    public CommonResult<AppCommentStatisticsRespVO> getCommentStatistics(@Valid @RequestParam("spuId") Long spuId) {
        return success(productCommentService.getCommentStatistics(spuId, Boolean.TRUE));
    }

}
