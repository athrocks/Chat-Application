package com.atharva.__Spring_WebSocket_Chat_Application.config;

import com.atharva.__Spring_WebSocket_Chat_Application.chat.ChatMessage;
import com.atharva.__Spring_WebSocket_Chat_Application.chat.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messagingTemplate;

    // to inform user/users that a user has left chat application.
    @EventListener
    public void handleWebSockedDisconnectListener(SessionDisconnectEvent event){

        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = headerAccessor.getSessionAttributes().get("username").toString();

        if(username != null){
            log.info("Disconnected from user: {}", username);
            var chatMessage =
                    ChatMessage.builder()
                            .type(MessageType.LEAVE)
                            .sender(username)
                            .build();

            messagingTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }
}
