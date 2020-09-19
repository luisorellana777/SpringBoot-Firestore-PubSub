package com.gcp.firestore.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gcp.firestore.model.PubSubMessage;
import com.gcp.firestore.service.FirebaseInitializer;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

@RestController
public class FirestoreController {
	@Autowired
	FirebaseInitializer db;
	
	@GetMapping("/pubsub-message")
	public List<PubSubMessage> getMessages() throws InterruptedException, ExecutionException {
		List<PubSubMessage> msgList = new ArrayList<PubSubMessage>();
		CollectionReference message = db.getFirebase().collection("PubSubMessage");
		ApiFuture<QuerySnapshot> querySnapshot= message.get();
		for(DocumentSnapshot doc:querySnapshot.get().getDocuments()) {
			PubSubMessage msg = doc.toObject(PubSubMessage.class);
			msgList.add(msg);
		}
		return msgList;
	}
	
	@PostMapping("/pubsub-message")
	public int saveMessage(@RequestBody PubSubMessage mensajePubSub) {
		CollectionReference messageCR= db.getFirebase().collection("PubSubMessage");
		messageCR.document(String.valueOf(mensajePubSub.getId())).set(mensajePubSub);
		return mensajePubSub.getId();
	}
}
