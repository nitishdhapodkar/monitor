package clientcore.queue.vo;

import java.util.List;

import clientcore.exceptions.SqQueueException;

public interface IQueue<E> {
	
	public void sendMessage(String queueName, E Message) throws SqQueueException;
	
	public E getMessage(String queueName) throws SqQueueException;
	
	public void sendMessage(String queueName, List<E> messages) throws SqQueueException;
	
	public List<E> getAllMessages(String queueName) throws SqQueueException;

}
