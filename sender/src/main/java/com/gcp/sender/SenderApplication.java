package com.gcp.sender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.cloud.gcp.pubsub.integration.outbound.PubSubMessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;

@SpringBootApplication
public class SenderApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SenderApplication.class, args);
	}

	@Bean
	@ServiceActivator(inputChannel = "myOutputChannel")
	public MessageHandler messageSender(PubSubTemplate pubsubTemplate) {
		return new PubSubMessageHandler(pubsubTemplate, "myTopic");
	}

	@MessagingGateway(defaultRequestChannel = "myOutputChannel")
	public interface PubsubOutboundGateway {

		void sendToPubsub(String text);
	}
}
