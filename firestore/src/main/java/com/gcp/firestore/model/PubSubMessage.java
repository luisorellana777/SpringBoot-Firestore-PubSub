package com.gcp.firestore.model;

import org.springframework.stereotype.Component;

@Component
public class PubSubMessage {
	private int id;
	private String message;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public PubSubMessage() {
		super();
	}
}