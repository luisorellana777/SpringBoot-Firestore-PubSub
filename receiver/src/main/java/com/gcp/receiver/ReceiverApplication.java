package com.gcp.receiver;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.cloud.gcp.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

import com.gcp.receiver.dto.PubSubMessage;
import com.gcp.receiver.feign.FirestoreServiceProxy;

@SpringBootApplication
@EnableFeignClients("com.gcp.receiver.feign")
public class ReceiverApplication {

	private static final Log LOGGER = LogFactory.getLog(ReceiverApplication.class);
	
	@Autowired
	private FirestoreServiceProxy firestoreServiceProxy;
	
	public static void main(String[] args) {
		SpringApplication.run(ReceiverApplication.class, args);
	}
	
	@Bean
	public PubSubInboundChannelAdapter messageChannelAdapter(
			@Qualifier("myInputChannel") MessageChannel inputChannel,
			PubSubTemplate pubSubTemplate) {
		PubSubInboundChannelAdapter adapter =
				new PubSubInboundChannelAdapter(pubSubTemplate, "mySubscription");
		adapter.setOutputChannel(inputChannel);

		return adapter;
	}

	@Bean
	public MessageChannel myInputChannel() {
		return new DirectChannel();
	}

	@ServiceActivator(inputChannel = "myInputChannel")
	public void messageReceiver(String payload) throws Exception {
		
		firestoreServiceProxy.saveMessage(new PubSubMessage((int)(Math.random()*100), payload));
		LOGGER.info("Mensaje Recibido: " + payload);	
	}
	
	
}