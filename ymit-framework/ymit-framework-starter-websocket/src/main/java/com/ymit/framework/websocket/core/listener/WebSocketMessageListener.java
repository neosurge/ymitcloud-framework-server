package com.ymit.framework.websocket.core.listener;

import com.ymit.framework.websocket.core.message.JsonWebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * WebSocket 消息监听器接口
 * <p>
 * 目的：前端发送消息给后端后，处理对应 {@link #getType()} 类型的消息
 *
 * @param <T> 泛型，消息类型
 * @author Y.S
 * @date 2024.05.26
 */
public interface WebSocketMessageListener<T> {

    /**
     * 处理消息
     *
     * @param session Session
     * @param message 消息
     */
    void onMessage(WebSocketSession session, T message);

    /**
     * 获得消息类型
     *
     * @return 消息类型
     * @see JsonWebSocketMessage#getType()
     */
    String getType();
}
