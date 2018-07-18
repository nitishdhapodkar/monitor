package clientcore.queue;

import java.util.List;

public interface IQueueTransaction {

	public void sendMessage(String queueName, String Message);
	
	public String getMessage(String queueName);
	
	public void sendMessage(String queueName, List<String> messages);
	
	public List<String> getAllMessages(String queueName);

}
