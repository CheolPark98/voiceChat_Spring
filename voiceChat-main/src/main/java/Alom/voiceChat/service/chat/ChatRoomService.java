package Alom.voiceChat.service.chat;

import Alom.voiceChat.entity.ChatRoom;
import Alom.voiceChat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    public ChatRoom create(ChatRoom chatRoom){
        return chatRoomRepository.save(chatRoom);
    }

    public List<ChatRoom> getAllRoom(){
        return chatRoomRepository.findAll();
    }

}
