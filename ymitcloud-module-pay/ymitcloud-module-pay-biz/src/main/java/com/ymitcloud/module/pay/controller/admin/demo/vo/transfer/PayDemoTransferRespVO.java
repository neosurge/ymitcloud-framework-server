package com.ymitcloud.module.pay.controller.admin.demo.vo.transfer;



import lombok.Data;

import java.time.LocalDateTime;

/**

 * 示例业务转账订单 Base VO，提供给添加、修改、详细的子 VO 使用 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成

 */
@Data
public class PayDemoTransferRespVO {


    /** 订单编号 */
    private Long id;

    /** 应用编号 */
    private Long appId;

    /** 转账金额，单位：分 */
    private Integer price;

    /** 转账类型 */
    private Integer type;

    /** 收款人姓名 */
    private String userName;

    /** 支付宝登录号 */
    private String alipayLogonId;

    /** 微信 openId */
    private String openid;

    /** 转账状态 */
    private Integer transferStatus;

    /** 转账订单编号 */
    private Long payTransferId;

    /**
     * 转账支付成功渠道
     */
    private String payChannelCode;

    /**
     * 转账支付时间
     */

    private LocalDateTime transferTime;
}
