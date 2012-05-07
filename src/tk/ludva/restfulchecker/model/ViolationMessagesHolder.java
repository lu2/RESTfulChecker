package tk.ludva.restfulchecker.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for holding violation messages of same kind.
 * @author Lu2
 * 
 */
public class ViolationMessagesHolder
{
	/**
	 * Key (kind) of message.
	 */
	private String key;
	
	/**
	 * List of messages.
	 */
	private List<String> messages = new ArrayList<String>();

	public ViolationMessagesHolder()
	{

	}

	/**
	 * Initialize holder and store specified message into it.
	 * @param key Key (kind) of message.
	 * @param message text of message.
	 */
	public ViolationMessagesHolder(String key, String message)
	{
		this.key = key;
		messages.add(message);
	}

	/**
	 * Gets key (kind) of message.
	 * @return key.
	 */
	public String getKey()
	{
		return key;
	}

	/**
	 * Sets key (kind) of message.
	 * @param key as String.
	 */
	public void setKey(String key)
	{
		this.key = key;
	}

	/**
	 * Gets List of messages in this holder.
	 * @return List of messages.
	 */
	public List<String> getMessages()
	{
		return messages;
	}

	/**
	 * Sets List of messages for this holder.
	 * @param messages List of messages to be set.
	 */
	public void setMessages(List<String> messages)
	{
		this.messages = messages;
	}

	/**
	 * Adds message to this holder.
	 * @param message message to be added.
	 */
	public void addMessage(String message)
	{
		messages.add(message);
	}

	@Override
	public String toString()
	{
		return key + ": " + messages.toString();
	}

}
