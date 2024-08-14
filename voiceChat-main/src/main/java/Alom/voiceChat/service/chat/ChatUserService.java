package Alom.voiceChat.service.chat;

import Alom.voiceChat.entity.ChatUser;
import Alom.voiceChat.repository.ChatUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ChatUserService {
    private final ChatUserRepository chatUserRepository;

    public ChatUser join(ChatUser chatUser){
        return chatUserRepository.save(chatUser);
    }

    public ChatUser login(ChatUser chatUser){
        Optional<ChatUser> findByUserId = chatUserRepository.findByUserId(chatUser.getUserId());
        if (findByUserId.isEmpty()){
            throw new RuntimeException();
        }else{
            findByUserId.get().setSuccess(true);
        }
        return findByUserId.get();
    }

}
