package Alom.socialService.controller;

import Alom.login.auth.info.GoogleUserInfo;
import Alom.login.auth.info.KakaoUserInfo;
import Alom.login.auth.info.NaverUserInfo;
import Alom.login.auth.info.OAuth2UserInfo;
import Alom.login.domain.user.User;
import Alom.login.repository.user.UserRepository;
import Alom.socialService.notification.NotificationService;
import Alom.socialService.service.FriendRequestService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/friend")
public class FriendRequestController {
    private final FriendRequestService friendRequestService;
    private final NotificationService notificationService;
    private final UserRepository userRepository;

    public FriendRequestController(FriendRequestService friendRequestService,NotificationService notificationService,UserRepository userRepository) {
        this.friendRequestService = friendRequestService;
        this.notificationService = notificationService;
        this.userRepository = userRepository;
    }


    @PostMapping("/request")
    public String sendFriendRequest(@RequestParam String friendId){
        String currentUserId = getCurrentUserId();
        String userNickname = getUserNickname(currentUserId);
        friendRequestService.sendFriendRequest(currentUserId,friendId);
        notificationService.sendFriendRequestNotification(friendId,"You have new friend request from "+userNickname);
        return "Friend request send succesfully";
    }

    @PostMapping("/accept")
    public String acceptFriendRequest(@RequestParam String requesterId){
        String currentUserId = getCurrentUserId();
        friendRequestService.acceptFriendRequest(currentUserId,requesterId);
        return "Friend request accepted successfully";
    }

    @GetMapping("/requestList")
    public List<String> getPendingFriendRequest(){
        String currentUserId = getCurrentUserId();
        return friendRequestService.getPendingFriendRequest(currentUserId)
                .stream()
                .map(User::getUserProviderId)
                .collect(Collectors.toList());
    }

    private String getCurrentUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof OAuth2User){
            OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
            String registrationId = oAuth2User.getAttribute("registrationId");
            OAuth2UserInfo userInfo;
            if(registrationId.equals("google")){
                userInfo = new GoogleUserInfo(oAuth2User.getAttributes());
            } else if (registrationId.equals("kakao")) {
                userInfo = new KakaoUserInfo(oAuth2User.getAttributes());
            } else if (registrationId.equals("naver")) {
                userInfo = new NaverUserInfo(oAuth2User.getAttributes());
            }else {
                throw new IllegalArgumentException("Unsupported provider: "+registrationId);
            }
            return userInfo.getProviderId();
        }
        throw new IllegalArgumentException("Failed to get user information");
    }
    private String getUserNickname(String userId){
        User user = userRepository.findByProviderId(userId);
        return user.getUserNickname();
    }


}
