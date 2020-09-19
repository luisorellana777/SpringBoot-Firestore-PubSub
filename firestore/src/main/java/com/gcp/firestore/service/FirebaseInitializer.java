package com.gcp.firestore.service;

import java.io.FileInputStream;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

@Service
@ComponentScan
public class FirebaseInitializer {

	@PostConstruct
	private void initDB() throws IOException {

		FileInputStream serviceAccount = new FileInputStream("./private-key.json");

		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.setDatabaseUrl("https://nimble-net-289114.firebaseio.com").build();

		FirebaseApp.initializeApp(options);

	}

	public Firestore getFirebase() {
		return FirestoreClient.getFirestore();
	}
}
