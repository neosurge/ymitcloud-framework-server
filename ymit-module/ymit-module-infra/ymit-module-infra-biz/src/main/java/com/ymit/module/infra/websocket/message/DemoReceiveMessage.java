package com.ymit.module.infra.websocket.message;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 示例：server -> client 同步消息
 *
 * @author 云码源码
 */
@Data
@Accessors(chain = true)
public class DemoReceiveMessage {

    /**
     * 接收人的编号
     */
    private Long fromUserId;
    /**
     * 内容
     */
    private String text;

    /**
     * 是否单聊
     */
    private Boolean single;

}
