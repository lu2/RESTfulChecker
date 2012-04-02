package tk.ludva.restfulchecker.model;

import java.util.ArrayList;
import java.util.List;

public class ViolationMessagesHolder {
	private String key;
	private List<String> messages = new ArrayList<String>();
	
	public ViolationMessagesHolder() {
		
	}
	
	public ViolationMessagesHolder(String key, String message) {
		this.key = key;
		messages.add(message);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
	
	
	public void addMessage(String message) {
		messages.add(message);
	}

	@Override
	public String toString() {
		return key + ": " + messages.toString();
	}
	
	
	
}
