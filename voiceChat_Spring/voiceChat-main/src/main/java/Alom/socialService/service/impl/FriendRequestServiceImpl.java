package Alom.socialService.service.impl;

import Alom.login.domain.user.User;
import Alom.login.repository.user.UserRepository;
import Alom.socialService.domain.FriendRequest;
import Alom.socialService.repository.FriendRequestRepository;
import Alom.socialService.service.FriendRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FriendRequestServiceImpl implements FriendRequestService {
    private final UserRepository userRepository;
    private final FriendRequestRepository friendRequestRepository;
    @Override
    public void sendFriendRequest(String userId, String friendId) {
        User requester = findByUserId(userId);
        User receiver = findByUserId(friendId);
        if (friendRequestRepository.findByRequestFromUserAndRequestToUser(requester,receiver).isPresent()){
            throw new IllegalArgumentException("Friend request already sent");
        }
        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setRequestFromUser(requester);
        friendRequest.setRequestToUser(receiver);
        friendRequest.setAcceptedByRequester(true);
        friendRequest.setAcceptedByReceiver(false);
        friendRequestRepository.save(friendRequest);

    }

    @Override
    public void acceptFriendRequest(String userId, String requesterId) {
        User receiver = findByUserId(userId);
        User requester = findByUserId(requesterId);
        FriendRequest friendRequest = friendRequestRepository.findByRequestFromUserAndRequestToUser(requester,receiver)
                .orElseThrow(()-> new IllegalArgumentException("Friend request not found"));
        friendRequest.setAcceptedByReceiver(true);
        if (friendRequest.isAcceptedByReceiver() && friendRequest.isAcceptedByRequester()){
            friendRequestRepository.save(friendRequest);
        }
    }

    @Override
    public List<User> getPendingFriendRequest(String userId) {
        User receiver = findByUserId(userId);
        return friendRequestRepository.findAllByRequestToUserAndAcceptedByReceiver(receiver,false)
                .stream()
                .map(FriendRequest::getRequestFromUser)
                .collect(Collectors.toList());
    }

    private User findByUserId(String userId){
        User user = userRepository.findByProviderId(userId);
        if (user == null){
            throw new IllegalArgumentException("User ID not found : " +userId);
        }
        return user;
    }
}
