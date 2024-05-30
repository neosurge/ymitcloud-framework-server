package com.ymit.framework.websocket.core.sender;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.ymit.framework.common.util.json.JsonUtils;
import com.ymit.framework.websocket.core.message.JsonWebSocketMessage;
import com.ymit.framework.websocket.core.session.WebSocketSessionManager;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * WebSocketMessageSender 实现类
 *
 * @author Y.S
 * @date 2024.05.26
 */
public abstract class AbstractWebSocketMessageSender implements WebSocketMessageSender {
    private final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AbstractWebSocketMessageSender.class);

    private final WebSocketSessionManager sessionManager;

    protected AbstractWebSocketMessageSender(WebSocketSessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void send(Integer userType, Long userId, String messageType, String messageContent) {
        this.send(null, userType, userId, messageType, messageContent);
    }

    @Override
    public void send(Integer userType, String messageType, String messageContent) {
        this.send(null, userType, null, messageType, messageContent);
    }

    @Override
    public void send(String sessionId, String messageType, String messageContent) {
        this.send(sessionId, null, null, messageType, messageContent);
    }

    /**
     * 发送消息
     *
     * @param sessionId      Session 编号
     * @param userType       用户类型
     * @param userId         用户编号
     * @param messageType    消息类型
     * @param messageContent 消息内容
     */
    public void send(String sessionId, Integer userType, Long userId, String messageType, String messageContent) {
        // 1. 获得 Session 列表
        List<WebSocketSession> sessions = Collections.emptyList();
        if (StrUtil.isNotEmpty(sessionId)) {
            WebSocketSession session = this.sessionManager.getSession(sessionId);
            if (session != null) {
                sessions = Collections.singletonList(session);
            }
        } else if (userType != null && userId != null) {
            sessions = (List<WebSocketSession>) this.sessionManager.getSessionList(userType, userId);
        } else if (userType != null) {
            sessions = (List<WebSocketSession>) this.sessionManager.getSessionList(userType);
        }
        if (CollUtil.isEmpty(sessions)) {
            this.log.info("[send][sessionId({}) userType({}) userId({}) messageType({}) messageContent({}) 未匹配到会话]", sessionId, userType, userId, messageType, messageContent);
        }
        // 2. 执行发送
        this.doSend(sessions, messageType, messageContent);
    }

    /**
     * 发送消息的具体实现
     *
     * @param sessions       Session 列表
     * @param messageType    消息类型
     * @param messageContent 消息内容
     */
    public void doSend(Collection<WebSocketSession> sessions, String messageType, String messageContent) {
        JsonWebSocketMessage message = new JsonWebSocketMessage().setType(messageType).setContent(messageContent);
        String payload = JsonUtils.toJsonString(message); // 关键，使用 JSON 序列化
        sessions.forEach(session -> {
            // 1. 各种校验，保证 Session 可以被发送
            if (session == null) {
                this.log.error("[doSend][session 为空, message({})]", message);
                return;
            }
            if (!session.isOpen()) {
                this.log.error("[doSend][session({}) 已关闭, message({})]", session.getId(), message);
                return;
            }
            // 2. 执行发送
            try {
                session.sendMessage(new TextMessage(payload));
                this.log.info("[doSend][session({}) 发送消息成功，message({})]", session.getId(), message);
            } catch (IOException ex) {
                this.log.error("[doSend][session({}) 发送消息失败，message({})]", session.getId(), message, ex);
            }
        });
    }
}
