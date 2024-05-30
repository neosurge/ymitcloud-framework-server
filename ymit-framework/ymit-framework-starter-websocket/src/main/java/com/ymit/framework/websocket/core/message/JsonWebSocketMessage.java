package com.ymit.framework.websocket.core.message;

import com.ymit.framework.websocket.core.listener.WebSocketMessageListener;

import java.io.Serial;
import java.io.Serializable;

/**
 * JSON 格式的 WebSocket 消息帧
 *
 * @author Y.S
 * @date 2024.05.26
 */
public class JsonWebSocketMessage implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 消息类型
     * <p>
     * 目的：用于分发到对应的 {@link WebSocketMessageListener} 实现类
     */
    private String type;
    /**
     * 消息内容
     * <p>
     * 要求 JSON 对象
     */
    private String content;

    public String getType() {
        return this.type;
    }

    public JsonWebSocketMessage setType(String type) {
        this.type = type;
        return this;
    }

    public String getContent() {
        return this.content;
    }

    public JsonWebSocketMessage setContent(String content) {
        this.content = content;
        return this;
    }
}
