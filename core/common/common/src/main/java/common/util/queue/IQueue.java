package common.util.queue;

import java.util.List;

import common.exceptions.SqQueueException;

public interface IQueue<E> {
	
	public void sendMessage(String queueName, E message) throws SqQueueException;
	
	public E getMessage(String queueName) throws SqQueueException;
	
	public void sendMessage(String queueName, List<E> messages) throws SqQueueException;
	
	public List<E> getAllMessages(String queueName) throws SqQueueException;
	
	public boolean isEmpty(String queueName) throws SqQueueException;

}
