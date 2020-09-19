package com.gcp.receiver.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.gcp.receiver.dto.PubSubMessage;

@FeignClient(name = "firestore", url = "${FIRESTORE_URI:http://localhost}:8083")
public interface FirestoreServiceProxy {

	@PostMapping("/pubsub-message/")
	public int saveMessage(@RequestBody PubSubMessage mensajePubSub);
}