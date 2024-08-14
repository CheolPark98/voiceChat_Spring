package Alom.voiceChat.controller;

import Alom.voiceChat.entity.ChatUser;
import Alom.voiceChat.service.chat.ChatUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class ChatUserController {
    private final ChatUserService chatUserService;

    @PostMapping("/signup")
    public ResponseEntity<ChatUser> joinUser(@RequestBody ChatUser chatUser){
        System.out.println(1);
        return ResponseEntity.ok(chatUserService.join(chatUser));
    }

    @PostMapping("/login")
    public ResponseEntity<ChatUser> loginUser(@RequestBody ChatUser chatUser){
        ChatUser login = chatUserService.login(chatUser);
        System.out.println(login.getNickName());
        return ResponseEntity.ok(chatUserService.login(chatUser));
    }
}
