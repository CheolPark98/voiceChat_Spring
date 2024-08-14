package Alom.voiceChat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VoiceChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoiceChatApplication.class, args);
	}

}
