package com.ymitcloud.module.promotion.api.discount;

import com.ymitcloud.module.promotion.api.discount.dto.DiscountProductRespDTO;
import com.ymitcloud.module.promotion.convert.discount.DiscountActivityConvert;
import com.ymitcloud.module.promotion.service.discount.DiscountActivityService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * 限时折扣 API 实现类
 *

 * @author 

 */
@Service
@Validated
public class DiscountActivityApiImpl implements DiscountActivityApi {

    @Resource
    private DiscountActivityService discountActivityService;

    @Override
    public List<DiscountProductRespDTO> getMatchDiscountProductList(Collection<Long> skuIds) {
        return DiscountActivityConvert.INSTANCE.convertList02(discountActivityService.getMatchDiscountProductList(skuIds));
    }

}
