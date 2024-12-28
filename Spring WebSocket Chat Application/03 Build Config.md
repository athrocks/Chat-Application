
###  WebSocketConfig Class

-  `WebSocketConfig.class` in `config` Directory
```java 
@Configuration  
@EnableWebSocketMessageBroker  
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {  
  
    @Override  
    public void registerStompEndpoints(StompEndpointRegistry registry) {  
        registry.addEndpoint("/ws").withSockJS();  
    }  
  
    @Override  
    public void configureMessageBroker(MessageBrokerRegistry registry) {  
        registry.setApplicationDestinationPrefixes("/app")  
                .enableSimpleBroker("/topic");  
    }  
}
```