package com.ymitcloud.module.trade.controller.app.order.vo;

import cn.hutool.core.util.ObjUtil;
import com.ymitcloud.framework.common.validation.InEnum;
import com.ymitcloud.framework.common.validation.Mobile;
import com.ymitcloud.module.trade.enums.delivery.DeliveryTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * 用户 App - 交易订单结算 Request VO
 */

@Data
@Valid
public class AppTradeOrderSettlementReqVO {

    /** 商品项数组 */
    @NotEmpty(message = "商品不能为空")
    private List<Item> items;

    /**
     * 优惠劵编号
     */
    private Long couponId;

    /** 是否使用积分 */

    @NotNull(message = "是否使用积分不能为空")
    private Boolean pointStatus;

    // ========== 配送相关相关字段 ==========

    /** 配送方式 */
    @InEnum(value = DeliveryTypeEnum.class, message = "配送方式不正确")
    private Integer deliveryType;

    /** 收件地址编号 */
    private Long addressId;

    /** 自提门店编号 */
    private Long pickUpStoreId;
    /** 收件人名称 */ // 选择门店自提时，该字段为联系人名
    private String receiverName;
    /** 收件人手机 */ // 选择门店自提时，该字段为联系人手机

    @Mobile(message = "收件人手机格式不正确")
    private String receiverMobile;

    // ========== 秒杀活动相关字段 ==========
    /** 秒杀活动编号 */
    private Long seckillActivityId;

    // ========== 拼团活动相关字段 ==========
    /** 拼团活动编号 */
    private Long combinationActivityId;

    /** 拼团团长编号 */
    private Long combinationHeadId;

    // ========== 砍价活动相关字段 ==========
    /** 砍价记录编号 */

    private Long bargainRecordId;

    @AssertTrue(message = "活动商品每次只能购买一种规格")
    @JsonIgnore
    public boolean isValidActivityItems() {
        // 校验是否是活动订单
        if (ObjUtil.isAllEmpty(seckillActivityId, combinationActivityId, combinationHeadId, bargainRecordId)) {
            return true;
        }
        // 校验订单项是否超出
        return items.size() == 1;
    }

    /**
     * 用户 App - 商品项
     */
    @Data
    @Valid
    public static class Item {

        /** 商品 SKU 编号 */
        @NotNull(message = "商品 SKU 编号不能为空")
        private Long skuId;

        /** 购买数量 */
        @Min(value = 1, message = "购买数量最小值为 {value}")
        private Integer count;

        /** 购物车项的编号 */

        private Long cartId;

        @AssertTrue(message = "商品不正确")
        @JsonIgnore
        public boolean isValid() {
            // 组合一：skuId + count 使用商品 SKU
            if (skuId != null && count != null) {
                return true;
            }
            // 组合二：cartId 使用购物车项
            return cartId != null;
        }

    }

}
