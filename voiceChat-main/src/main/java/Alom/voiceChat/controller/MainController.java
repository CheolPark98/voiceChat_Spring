package Alom.voiceChat.controller;


import Alom.voiceChat.service.chat.ChatServiceMain;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {

    private final ChatServiceMain chatServiceMain;


    @GetMapping("/room/{id}")
    public String goChatRoom(@PathVariable Long id, Model model){
        model.addAttribute("roomId", id);
        return "room";
    }
}
