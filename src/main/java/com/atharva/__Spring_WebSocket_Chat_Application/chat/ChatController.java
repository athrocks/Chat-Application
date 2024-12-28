package com.atharva.__Spring_WebSocket_Chat_Application.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
public class ChatController {
    // we need to create two methods:
    // 1] to add user (when new user connect to ur chat application)
    // 2] send message method (to send message by user)

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public") // topic comes from WebSocketConfig
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage){

        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(
            @Payload ChatMessage chatMessage,
            SimpMessageHeaderAccessor headerAccessor
    ){
        // adds username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}
