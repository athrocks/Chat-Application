package com.atharva.__Spring_WebSocket_Chat_Application.chat;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {

    private String content; // message
    private String sender;
    private MessageType type;

}
