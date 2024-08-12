package Alom.socialService.service;

import Alom.login.domain.user.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FriendRequestService {
    @Transactional
    public void sendFriendRequest(String userId,String friendId);
    @Transactional
    public void acceptFriendRequest(String userId,String requesterId);
    @Transactional(readOnly = true)
    public List<User> getPendingFriendRequest(String userId);
}
