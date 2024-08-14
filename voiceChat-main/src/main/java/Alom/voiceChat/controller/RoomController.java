package Alom.voiceChat.controller;
import Alom.voiceChat.entity.ChatRoom;
import Alom.voiceChat.repository.ChatRoomRepository;
import Alom.voiceChat.service.chat.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequiredArgsConstructor
public class RoomController {

    private Map<String, String> rooms = new HashMap<>(); // 방 이름과 비밀번호를 저장

    private final ChatRoomService chatRoomService;

    @GetMapping
    public String listRooms(Model model) {
        model.addAttribute("rooms", rooms.keySet());
        return "rooms";
    }

    //    @PostMapping("rooms/create")
//    @ResponseBody
//    public Map<String, Object> createRoom(@RequestBody ChatRoom chatRoom) {
//        String roomName = roomDetails.get("roomName");
//        String password = roomDetails.get("password");
//        Map<String, Object> response = new HashMap<>();
//        if (!rooms.containsKey(roomName)) {
//            rooms.put(roomName, password);
//            response.put("success", true);
//        } else {
//            response.put("success", false);
//        }
//
//
//        return response;
//    }
    @PostMapping("rooms/create")
    @ResponseBody
    public ResponseEntity<ChatRoom> createRoom(@RequestBody ChatRoom chatRoom) {
        ChatRoom createdChatRoom = chatRoomService.create(chatRoom);
        createdChatRoom.setSuccess(true);
        System.out.println(createdChatRoom.isSuccess());
        return ResponseEntity.ok(createdChatRoom);
    }



    @GetMapping("rooms/{roomName}")
    public String joinRoom(@PathVariable String roomName, @RequestParam(required = false) String password, Model model) {
//        if (!rooms.containsKey(roomName) || !rooms.get(roomName).equals(password)) {
//            return "redirect:/rooms";
//        }
        model.addAttribute("roomName", roomName);
        return "room";
    }

    @GetMapping("/roomList")
    @ResponseBody
    public List<String> fetchRooms() {
        return chatRoomService.getAllRoom().stream()
                .map(ChatRoom::getName)
                .collect(Collectors.toList());
    }
}
