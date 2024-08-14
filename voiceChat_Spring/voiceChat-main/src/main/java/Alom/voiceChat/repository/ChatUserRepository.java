package Alom.voiceChat.repository;

import Alom.voiceChat.entity.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatUserRepository extends JpaRepository<ChatUser,Long> {

    Optional<ChatUser> findByUserId(String userId);
}
