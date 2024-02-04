package com.ymitcloud.module.product.controller.admin.comment;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.product.controller.admin.comment.vo.ProductCommentCreateReqVO;
import com.ymitcloud.module.product.controller.admin.comment.vo.ProductCommentPageReqVO;
import com.ymitcloud.module.product.controller.admin.comment.vo.ProductCommentReplyReqVO;
import com.ymitcloud.module.product.controller.admin.comment.vo.ProductCommentRespVO;
import com.ymitcloud.module.product.controller.admin.comment.vo.ProductCommentUpdateVisibleReqVO;
import com.ymitcloud.module.product.convert.comment.ProductCommentConvert;
import com.ymitcloud.module.product.dal.dataobject.comment.ProductCommentDO;
import com.ymitcloud.module.product.service.comment.ProductCommentService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 管理后台 - 商品评价
 */

@RestController
@RequestMapping("/product/comment")
@Validated
public class ProductCommentController {

    @Resource
    private ProductCommentService productCommentService;


    /**
     * 获得商品评价分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")

    @PreAuthorize("@ss.hasPermission('product:comment:query')")
    public CommonResult<PageResult<ProductCommentRespVO>> getCommentPage(@Valid ProductCommentPageReqVO pageVO) {
        PageResult<ProductCommentDO> pageResult = productCommentService.getCommentPage(pageVO);
        return success(ProductCommentConvert.INSTANCE.convertPage(pageResult));
    }


    /**
     * 显示 / 隐藏评论
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update-visible")
    @PreAuthorize("@ss.hasPermission('product:comment:update')")
    public CommonResult<Boolean> updateCommentVisible(
            @Valid @RequestBody ProductCommentUpdateVisibleReqVO updateReqVO) {

        productCommentService.updateCommentVisible(updateReqVO);
        return success(true);
    }


    /**
     * 商家回复
     * 
     * @param replyVO
     * @return
     */
    @PutMapping("/reply")

    @PreAuthorize("@ss.hasPermission('product:comment:update')")
    public CommonResult<Boolean> commentReply(@Valid @RequestBody ProductCommentReplyReqVO replyVO) {
        productCommentService.replyComment(replyVO, getLoginUserId());
        return success(true);
    }


    /**
     * 添加自评
     * 
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")

    @PreAuthorize("@ss.hasPermission('product:comment:update')")
    public CommonResult<Boolean> createComment(@Valid @RequestBody ProductCommentCreateReqVO createReqVO) {
        productCommentService.createComment(createReqVO);
        return success(true);
    }

}
