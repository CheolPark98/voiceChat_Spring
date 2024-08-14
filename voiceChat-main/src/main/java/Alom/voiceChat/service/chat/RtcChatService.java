package Alom.voiceChat.service.chat;

import Alom.voiceChat.dto.ChatRoomDto;
import Alom.voiceChat.dto.ChatRoomMap;
import Alom.voiceChat.dto.KurentoRoomDto;
import Alom.voiceChat.utils.WebSocketMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kurento.client.KurentoClient;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RequiredArgsConstructor
@Service
public class RtcChatService {

    private final KurentoClient kurento;

    public KurentoRoomDto createChatRoom(String roomName, String roomPwd){
        KurentoRoomDto room = new KurentoRoomDto();
        String roomId = UUID.randomUUID().toString();

        room.setRoomInfo(roomId, roomName, roomPwd,  kurento);

        room.setUserList(new ConcurrentHashMap<String, WebSocketSession>());

        ChatRoomMap.getInstance().getChatRooms().put(room.getRoomId(), room);
        return room;
    }
    public Map<String,WebSocketSession> getClients(ChatRoomDto room){
        Optional <ChatRoomDto> roomDto = Optional.ofNullable(room);

        return (Map<String, WebSocketSession>) roomDto.get().getUserList();
    }

    public Map<String,WebSocketSession> addClient(ChatRoomDto room,String name, WebSocketSession session){
        Map<String, WebSocketSession> userList = (Map<String, WebSocketSession>) room.getUserList();
        userList.put(name, session);
        return userList;
    }
    public void removeClientByName(ChatRoomDto room, String userUUID){
        room.getUserList().remove(userUUID);
    }

    public boolean findUserCount (WebSocketMessage webSocketMessage){
        ChatRoomDto room = ChatRoomMap.getInstance().getChatRooms().get(webSocketMessage.getData());

        return room.getUserList().size() > 1;
    }

}
