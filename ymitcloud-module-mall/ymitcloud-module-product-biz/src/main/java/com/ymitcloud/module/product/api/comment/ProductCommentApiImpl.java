package com.ymitcloud.module.product.api.comment;

import com.ymitcloud.module.product.api.comment.dto.ProductCommentCreateReqDTO;
import com.ymitcloud.module.product.service.comment.ProductCommentService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;

/**
 * 商品评论 API 实现类
 *
 * @author HUIHUI
 */
@Service
@Validated
public class ProductCommentApiImpl implements ProductCommentApi {

    @Resource
    private ProductCommentService productCommentService;

    @Override
    public Long createComment(ProductCommentCreateReqDTO createReqDTO) {
        return productCommentService.createComment(createReqDTO);
    }

}
