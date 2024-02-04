package com.ymitcloud.module.trade.service.price.calculator;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import com.ymitcloud.module.member.api.config.MemberConfigApi;
import com.ymitcloud.module.member.api.config.dto.MemberConfigRespDTO;
import com.ymitcloud.module.member.api.user.MemberUserApi;
import com.ymitcloud.module.member.api.user.dto.MemberUserRespDTO;
import com.ymitcloud.module.promotion.enums.common.PromotionTypeEnum;
import com.ymitcloud.module.trade.service.price.bo.TradePriceCalculateReqBO;
import com.ymitcloud.module.trade.service.price.bo.TradePriceCalculateRespBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import java.util.List;

import static com.ymitcloud.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ymitcloud.framework.common.util.collection.CollectionUtils.filterList;
import static com.ymitcloud.module.trade.enums.ErrorCodeConstants.PRICE_CALCULATE_PAY_PRICE_ILLEGAL;

/**
 * 使用积分的 {@link TradePriceCalculator} 实现类
 *
 * @author owen
 */
@Component
@Order(TradePriceCalculator.ORDER_POINT_USE)
@Slf4j
public class TradePointUsePriceCalculator implements TradePriceCalculator {

    @Resource
    private MemberConfigApi memberConfigApi;
    @Resource
    private MemberUserApi memberUserApi;

    @Override
    public void calculate(TradePriceCalculateReqBO param, TradePriceCalculateRespBO result) {
        // 默认使用积分为 0
        result.setUsePoint(0);
        // 1.1 校验是否使用积分
        if (!BooleanUtil.isTrue(param.getPointStatus())) {
            result.setUsePoint(0);
            return;
        }
        // 1.2 校验积分抵扣是否开启
        MemberConfigRespDTO config = memberConfigApi.getConfig();
        if (!isDeductPointEnable(config)) {
            return;
        }
        // 1.3 校验用户积分余额
        MemberUserRespDTO user = memberUserApi.getUser(param.getUserId());
        if (user.getPoint() == null || user.getPoint() <= 0) {
            return;
        }

        // 2.1 计算积分优惠金额
        int pointPrice = calculatePointPrice(config, user.getPoint(), result);
        // 2.2 计算分摊的积分、抵扣金额
        List<TradePriceCalculateRespBO.OrderItem> orderItems = filterList(result.getItems(), TradePriceCalculateRespBO.OrderItem::getSelected);
        List<Integer> dividePointPrices = TradePriceCalculatorHelper.dividePrice(orderItems, pointPrice);
        List<Integer> divideUsePoints = TradePriceCalculatorHelper.dividePrice(orderItems, result.getUsePoint());

        // 3.1 记录优惠明细
        TradePriceCalculatorHelper.addPromotion(result, orderItems,
                param.getUserId(), "积分抵扣", PromotionTypeEnum.POINT.getType(),
                StrUtil.format("积分抵扣：省 {} 元", TradePriceCalculatorHelper.formatPrice(pointPrice)),
                dividePointPrices);
        // 3.2 更新 SKU 优惠金额
        for (int i = 0; i < orderItems.size(); i++) {
            TradePriceCalculateRespBO.OrderItem orderItem = orderItems.get(i);
            orderItem.setPointPrice(dividePointPrices.get(i));
            orderItem.setUsePoint(divideUsePoints.get(i));
            TradePriceCalculatorHelper.recountPayPrice(orderItem);
        }
        TradePriceCalculatorHelper.recountAllPrice(result);
    }

    private boolean isDeductPointEnable(MemberConfigRespDTO config) {
        return config != null &&
                BooleanUtil.isTrue(config.getPointTradeDeductEnable()) &&  // 积分功能是否启用
                config.getPointTradeDeductUnitPrice() != null && config.getPointTradeDeductUnitPrice() > 0; // 有没有配置：1 积分抵扣多少分
    }

    private Integer calculatePointPrice(MemberConfigRespDTO config, Integer usePoint, TradePriceCalculateRespBO result) {
        // 每个订单最多可以使用的积分数量
        if (config.getPointTradeDeductMaxPrice() != null && config.getPointTradeDeductMaxPrice() > 0) {
            usePoint = Math.min(usePoint, config.getPointTradeDeductMaxPrice());
        }
        // TODO @疯狂：这里应该是，抵扣到只剩下 0.01；
        // 积分优惠金额（分）
        int pointPrice = usePoint * config.getPointTradeDeductUnitPrice();
        if (result.getPrice().getPayPrice() <= pointPrice) {
            // 禁止 0 元购
            throw exception(PRICE_CALCULATE_PAY_PRICE_ILLEGAL);
        }
//        // 允许0 元购!!!：用户积分比较多时，积分可以抵扣的金额要大于支付金额，这时需要根据支付金额反推使用多少积分
//        if (result.getPrice().getPayPrice() < pointPrice) {
//            pointPrice = result.getPrice().getPayPrice();
//            // 反推需要扣除的积分
//            usePoint = NumberUtil.toBigDecimal(pointPrice)
//                    .divide(NumberUtil.toBigDecimal(config.getPointTradeDeductUnitPrice()), 0, RoundingMode.HALF_UP)
//                    .intValue();
//        }
        // 记录使用的积分
        result.setUsePoint(usePoint);
        return pointPrice;
    }

}
