package Alom.voiceChat.controller;

import com.google.gson.JsonObject;
import org.kurento.client.IceCandidate;
import org.kurento.client.KurentoClient;
import org.kurento.client.MediaPipeline;
import org.kurento.client.WebRtcEndpoint;
import org.kurento.jsonrpc.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/kurento")
public class KurentoController {

    @Autowired
    private KurentoClient kurentoClient;

    private Map<String, UserSession> userSessions = new ConcurrentHashMap<>();

    @PostMapping("/start")
    public ResponseEntity<Map<String, String>> startSession(@RequestBody Map<String, String> offerSdp) {
        String sessionId = generateSessionId();
        MediaPipeline pipeline = kurentoClient.createMediaPipeline();
        WebRtcEndpoint webRtcEndpoint = new WebRtcEndpoint.Builder(pipeline).build();

        UserSession session = new UserSession(sessionId, pipeline, webRtcEndpoint);
        userSessions.put(sessionId, session);

        webRtcEndpoint.addOnIceCandidateListener(event -> {
            JsonObject candidate = JsonUtils.toJsonObject(event.getCandidate());
            // Send this candidate to the client
        });

        String sdpAnswer = webRtcEndpoint.processOffer(offerSdp.get("sdpOffer"));
        webRtcEndpoint.gatherCandidates();

        Map<String, String> response = new HashMap<>();
        response.put("sdpAnswer", sdpAnswer);
        response.put("sessionId", sessionId);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/onIceCandidate/{sessionId}")
    public ResponseEntity<Void> onIceCandidate(@PathVariable String sessionId, @RequestBody IceCandidateInfo candidateInfo) {
        UserSession session = userSessions.get(sessionId);
        if (session == null) {
            return ResponseEntity.notFound().build();
        }

        IceCandidate candidate = new IceCandidate(
                candidateInfo.getCandidate(),
                candidateInfo.getSdpMid(),
                candidateInfo.getSdpMLineIndex()
        );
        session.getWebRtcEndpoint().addIceCandidate(candidate);
        return ResponseEntity.ok().build();
    }

    // 추가: 세션 종료 메소드
    @PostMapping("/stop/{sessionId}")
    public ResponseEntity<Void> stopSession(@PathVariable String sessionId) {
        UserSession session = userSessions.remove(sessionId);
        if (session != null) {
            session.release();
        }
        return ResponseEntity.ok().build();
    }
}