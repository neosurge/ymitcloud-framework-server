package com.ymit.framework.websocket.core.sender.kafka;

/**
 * Kafka 广播 WebSocket 的消息
 *
 * @author Y.S
 * @date 2024.05.26
 */
public class KafkaWebSocketMessage {
    /**
     * Session 编号
     */
    private String sessionId;
    /**
     * 用户类型
     */
    private Integer userType;
    /**
     * 用户编号
     */
    private Long userId;
    /**
     * 消息类型
     */
    private String messageType;
    /**
     * 消息内容
     */
    private String messageContent;

    public String getSessionId() {
        return this.sessionId;
    }

    public KafkaWebSocketMessage setSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public Integer getUserType() {
        return this.userType;
    }

    public KafkaWebSocketMessage setUserType(Integer userType) {
        this.userType = userType;
        return this;
    }

    public Long getUserId() {
        return this.userId;
    }

    public KafkaWebSocketMessage setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getMessageType() {
        return this.messageType;
    }

    public KafkaWebSocketMessage setMessageType(String messageType) {
        this.messageType = messageType;
        return this;
    }

    public String getMessageContent() {
        return this.messageContent;
    }

    public KafkaWebSocketMessage setMessageContent(String messageContent) {
        this.messageContent = messageContent;
        return this;
    }
}
