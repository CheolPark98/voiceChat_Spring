package Alom.socialService.notification.impl;

import Alom.socialService.notification.NotificationService;
import org.springframework.messaging.simp.SimpMessagingTemplate;

public class NotificationServiceImpl implements NotificationService {
    private final SimpMessagingTemplate messagingTemplate;

    public NotificationServiceImpl(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void sendFriendRequestNotification(String receiveId, String message) {
        messagingTemplate.convertAndSendToUser(receiveId,"/topic/friend-request",message);
    }
}
