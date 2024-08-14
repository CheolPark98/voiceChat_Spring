package Alom.voiceChat.controller;

import Alom.voiceChat.dto.ChatRoomDto;
import Alom.voiceChat.entity.ChatRoom;
import Alom.voiceChat.service.chat.ChatServiceMain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatRoomController {

    private final ChatServiceMain chatServiceMain;

    // 채팅방 생성
    // 채팅방 생성후 초기화면으로
    @PostMapping("/chat/createroom")
    public String createRoom(@RequestParam("roomName") String name,
                             @RequestParam("roomPwd") String roomPwd,
                             RedirectAttributes rttr){

        // 파라미터 : 방이름, 비밀번호, 프라이빗 여부
        ChatRoomDto room = chatServiceMain.createChatRoom(name, roomPwd);

        log.info("CREATE Chat Room [{}]",room);

        rttr.addAttribute("roomName", room);

        return "redirect:/";
    }

//    @GetMapping("/chat/room")
//    public String roomDetail(Model model, String roomId, @AuthenticationPrincipal P){
//        log.info("roomId {}",roomId);
//
//        if (principalDetails != null){
//            model.addAttribute("user", principalDetails.)
//        }
//    }
}
