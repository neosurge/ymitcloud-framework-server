package com.ymitcloud.module.product.controller.app.spu;


import static com.ymitcloud.framework.common.pojo.CommonResult.success;
import static com.ymitcloud.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ymitcloud.framework.common.exception.util.ServiceExceptionUtil;
import com.ymitcloud.framework.common.pojo.CommonResult;
import com.ymitcloud.framework.common.pojo.PageResult;
import com.ymitcloud.module.member.api.level.MemberLevelApi;
import com.ymitcloud.module.member.api.level.dto.MemberLevelRespDTO;
import com.ymitcloud.module.member.api.user.MemberUserApi;
import com.ymitcloud.module.member.api.user.dto.MemberUserRespDTO;
import com.ymitcloud.module.product.controller.app.spu.vo.AppProductSpuDetailRespVO;
import com.ymitcloud.module.product.controller.app.spu.vo.AppProductSpuPageReqVO;
import com.ymitcloud.module.product.controller.app.spu.vo.AppProductSpuPageRespVO;
import com.ymitcloud.module.product.convert.spu.ProductSpuConvert;
import com.ymitcloud.module.product.dal.dataobject.sku.ProductSkuDO;
import com.ymitcloud.module.product.dal.dataobject.spu.ProductSpuDO;

import com.ymitcloud.module.product.enums.ErrorCodeConstants;
import com.ymitcloud.module.product.enums.spu.ProductSpuStatusEnum;
import com.ymitcloud.module.product.service.sku.ProductSkuService;
import com.ymitcloud.module.product.service.spu.ProductSpuService;

import cn.hutool.core.collection.CollUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;

/**
 * 用户 APP - 商品 SPU
 */

@RestController
@RequestMapping("/product/spu")
@Validated
public class AppProductSpuController {

    @Resource
    private ProductSpuService productSpuService;
    @Resource
    private ProductSkuService productSkuService;

    @Resource
    private MemberLevelApi memberLevelApi;
    @Resource
    private MemberUserApi memberUserApi;


    /**
     * 获得商品 SPU 列表
     * 
     * @param recommendType 推荐类型
     * @param count         数量
     * @return
     */
    @GetMapping("/list")
    public CommonResult<List<AppProductSpuPageRespVO>> getSpuList(@RequestParam("recommendType") String recommendType,

            @RequestParam(value = "count", defaultValue = "10") Integer count) {
        List<ProductSpuDO> list = productSpuService.getSpuList(recommendType, count);
        if (CollUtil.isEmpty(list)) {
            return success(Collections.emptyList());
        }

        // 拼接返回
        List<AppProductSpuPageRespVO> voList = ProductSpuConvert.INSTANCE.convertListForGetSpuList(list);
        // 处理 vip 价格
        MemberLevelRespDTO memberLevel = getMemberLevel();
        voList.forEach(vo -> vo.setVipPrice(calculateVipPrice(vo.getPrice(), memberLevel)));
        return success(voList);
    }


    /**
     * 获得商品 SPU 列表
     * 
     * @param ids 编号列表
     * @return
     */
    @GetMapping("/list-by-ids")

    public CommonResult<List<AppProductSpuPageRespVO>> getSpuList(@RequestParam("ids") Set<Long> ids) {
        List<ProductSpuDO> list = productSpuService.getSpuList(ids);
        if (CollUtil.isEmpty(list)) {
            return success(Collections.emptyList());
        }

        // 拼接返回
        List<AppProductSpuPageRespVO> voList = ProductSpuConvert.INSTANCE.convertListForGetSpuList(list);
        // 处理 vip 价格
        MemberLevelRespDTO memberLevel = getMemberLevel();
        voList.forEach(vo -> vo.setVipPrice(calculateVipPrice(vo.getPrice(), memberLevel)));
        return success(voList);
    }


    /**
     * 获得商品 SPU 分页
     * 
     * @param pageVO
     * @return
     */
    @GetMapping("/page")

    public CommonResult<PageResult<AppProductSpuPageRespVO>> getSpuPage(@Valid AppProductSpuPageReqVO pageVO) {
        PageResult<ProductSpuDO> pageResult = productSpuService.getSpuPage(pageVO);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return success(PageResult.empty(pageResult.getTotal()));
        }

        // 拼接返回

        PageResult<AppProductSpuPageRespVO> voPageResult = ProductSpuConvert.INSTANCE
                .convertPageForGetSpuPage(pageResult);

        // 处理 vip 价格
        MemberLevelRespDTO memberLevel = getMemberLevel();
        voPageResult.getList().forEach(vo -> vo.setVipPrice(calculateVipPrice(vo.getPrice(), memberLevel)));
        return success(voPageResult);
    }


    /**
     * 获得商品 SPU 明细
     * 
     * @param id 编号
     * @return
     */
    @GetMapping("/get-detail")

    public CommonResult<AppProductSpuDetailRespVO> getSpuDetail(@RequestParam("id") Long id) {
        // 获得商品 SPU
        ProductSpuDO spu = productSpuService.getSpu(id);
        if (spu == null) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.SPU_NOT_EXISTS);
        }
        if (!ProductSpuStatusEnum.isEnable(spu.getStatus())) {
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.SPU_NOT_ENABLE);
        }

        // 拼接返回
        List<ProductSkuDO> skus = productSkuService.getSkuListBySpuId(spu.getId());
        AppProductSpuDetailRespVO detailVO = ProductSpuConvert.INSTANCE.convertForGetSpuDetail(spu, skus);
        // 处理 vip 价格
        MemberLevelRespDTO memberLevel = getMemberLevel();
        detailVO.setVipPrice(calculateVipPrice(detailVO.getPrice(), memberLevel));
        return success(detailVO);
    }

    private MemberLevelRespDTO getMemberLevel() {
        Long userId = getLoginUserId();
        if (userId == null) {
            return null;
        }
        MemberUserRespDTO user = memberUserApi.getUser(userId);
        if (user.getLevelId() == null || user.getLevelId() <= 0) {
            return null;
        }
        return memberLevelApi.getMemberLevel(user.getLevelId());
    }

    /**
     * 计算会员 VIP 优惠价格
     *

     * @param price       原价

     * @param memberLevel 会员等级
     * @return 优惠价格
     */
    public Integer calculateVipPrice(Integer price, MemberLevelRespDTO memberLevel) {
        if (memberLevel == null || memberLevel.getDiscountPercent() == null) {
            return 0;
        }
        Integer newPrice = price * memberLevel.getDiscountPercent() / 100;
        return price - newPrice;
    }


    // TODO 云码：商品的浏览记录；

}
