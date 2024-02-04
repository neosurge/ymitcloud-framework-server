package com.ymitcloud.module.trade.controller.app.cart;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.security.core.annotations.PreAuthenticated;
import com.ymitcloud.module.trade.controller.app.cart.vo.AppCartAddReqVO;
import com.ymitcloud.module.trade.controller.app.cart.vo.AppCartListRespVO;
import com.ymitcloud.module.trade.controller.app.cart.vo.AppCartResetReqVO;
import com.ymitcloud.module.trade.controller.app.cart.vo.AppCartUpdateCountReqVO;
import com.ymitcloud.module.trade.controller.app.cart.vo.AppCartUpdateSelectedReqVO;
import com.ymitcloud.module.trade.service.cart.CartService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户 App - 购物车
 */

@RestController
@RequestMapping("/trade/cart")
@RequiredArgsConstructor
@Validated
@Slf4j
public class AppCartController {

    @Resource
    private CartService cartService;


    /**
     * 添加购物车商品
     * 
     * @param addCountReqVO
     * @return
     */
    @PostMapping("/add")

    @PreAuthenticated
    public CommonResult<Long> addCart(@Valid @RequestBody AppCartAddReqVO addCountReqVO) {
        return success(cartService.addCart(getLoginUserId(), addCountReqVO));
    }


    /**
     * 更新购物车商品数量
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update-count")

    @PreAuthenticated
    public CommonResult<Boolean> updateCartCount(@Valid @RequestBody AppCartUpdateCountReqVO updateReqVO) {
        cartService.updateCartCount(getLoginUserId(), updateReqVO);
        return success(true);
    }


    /**
     * 更新购物车商品选中
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update-selected")

    @PreAuthenticated
    public CommonResult<Boolean> updateCartSelected(@Valid @RequestBody AppCartUpdateSelectedReqVO updateReqVO) {
        cartService.updateCartSelected(getLoginUserId(), updateReqVO);
        return success(true);
    }


    /**
     * 重置购物车商品
     * 
     * @param updateReqVO
     * @return
     */
    @PutMapping("/reset")

    @PreAuthenticated
    public CommonResult<Boolean> resetCart(@Valid @RequestBody AppCartResetReqVO updateReqVO) {
        cartService.resetCart(getLoginUserId(), updateReqVO);
        return success(true);
    }


    /**
     * 删除购物车商品
     * 
     * @param ids 购物车商品编号
     * @return
     */
    @DeleteMapping("/delete")

    @PreAuthenticated
    public CommonResult<Boolean> deleteCart(@RequestParam("ids") List<Long> ids) {
        cartService.deleteCart(getLoginUserId(), ids);
        return success(true);
    }


    /**
     * 查询用户在购物车中的商品数量
     * 
     * @return
     */
    @GetMapping("get-count")

    @PreAuthenticated
    public CommonResult<Integer> getCartCount() {
        return success(cartService.getCartCount(getLoginUserId()));
    }


    /**
     * 查询用户的购物车列表
     * 
     * @return
     */
    @GetMapping("/list")

    @PreAuthenticated
    public CommonResult<AppCartListRespVO> getCartList() {
        return success(cartService.getCartList(getLoginUserId()));
    }

}
