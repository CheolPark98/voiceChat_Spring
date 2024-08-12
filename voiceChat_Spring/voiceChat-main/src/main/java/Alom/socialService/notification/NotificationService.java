package Alom.socialService.notification;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public interface NotificationService {
    public void sendFriendRequestNotification(String receiveId,String message);
}
