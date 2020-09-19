package com.gcp.receiver.dto;

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

	public PubSubMessage(int id, String message) {
		super();
		this.id = id;
		this.message = message;
	}
	
}