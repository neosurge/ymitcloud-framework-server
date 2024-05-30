package com.ymit.framework.websocket.core.sender.local;

import com.ymit.framework.websocket.core.sender.AbstractWebSocketMessageSender;
import com.ymit.framework.websocket.core.sender.WebSocketMessageSender;
import com.ymit.framework.websocket.core.session.WebSocketSessionManager;

/**
 * 本地的 {@link WebSocketMessageSender} 实现类
 * <p>
 * 注意：仅仅适合单机场景！！！
 *
 * @author Y.S
 * @date 2024.05.26
 */
public class LocalWebSocketMessageSender extends AbstractWebSocketMessageSender {
    public LocalWebSocketMessageSender(WebSocketSessionManager sessionManager) {
        super(sessionManager);
    }
}
