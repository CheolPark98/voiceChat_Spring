package Alom.voiceChat.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRoomDto {
    @NotNull
    private String roomId;
    private String roomName;

    private String roomPassword;



    public ConcurrentMap<String ,?> userList= new ConcurrentHashMap<>();
}
