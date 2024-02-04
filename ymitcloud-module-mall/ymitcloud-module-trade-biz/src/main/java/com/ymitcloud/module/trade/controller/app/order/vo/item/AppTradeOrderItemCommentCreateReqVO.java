package com.ymitcloud.module.trade.controller.app.order.vo.item;



import lombok.Data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;


/**
 * 用户 App - 商品评价创建 Request VO
 */
@Data
public class AppTradeOrderItemCommentCreateReqVO {

    /** 是否匿名 */
    @NotNull(message = "是否匿名不能为空")
    private Boolean anonymous;

    /** 交易订单项编号 */
    @NotNull(message = "交易订单项编号不能为空")
    private Long orderItemId;

    /** 描述星级 1-5 分 */
    @NotNull(message = "描述星级 1-5 分不能为空")
    private Integer descriptionScores;

    /** 服务星级 1-5 分 */
    @NotNull(message = "服务星级 1-5 分不能为空")
    private Integer benefitScores;

    /** 评论内容 */
    @NotNull(message = "评论内容不能为空")
    private String content;

    /** 评论图片地址数组，以逗号分隔最多上传 9 张 */

    @Size(max = 9, message = "评论图片地址数组长度不能超过 9 张")
    private List<String> picUrls;

}
