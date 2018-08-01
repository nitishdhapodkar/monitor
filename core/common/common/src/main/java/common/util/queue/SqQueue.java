package common.util.queue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import common.constants.ErrorCode;
import common.exceptions.SqQueueException;

public class SqQueue<E> implements IQueue<E>{

	private Map<String, Queue<E>> queue;

	public SqQueue() {
		queue = new HashMap<>();
	}

	@Override
	public synchronized void sendMessage(String queueName, E message) throws SqQueueException{
		
		try {
			
			if(queue.get(queueName) == null) {
				queue.put(queueName, new LinkedList<E>());
			} 
			
			queue.get(queueName).add(message);
			
		} catch (Exception e) {
			throw new SqQueueException("Error in adding new message to queue (name) : " + queueName, ErrorCode.QUEUE_ADD);
		}
	}

	@Override
	public synchronized E getMessage(String queueName) throws SqQueueException{
		try {
			
			if(queue.get(queueName) == null) {
				throw new SqQueueException("Queue name is not valid (name) : " + queueName, ErrorCode.QUEUE_REMOVE);
			}  else {
				return queue.get(queueName).remove();
			}
			
		} catch (Exception e) {
			throw new SqQueueException("Error in removing message from qurue (name) :" + queueName, ErrorCode.QUEUE_REMOVE);
		}
	}

	@Override
	public synchronized void sendMessage(String queueName, List<E> messages) throws SqQueueException{
		try {
			
			if(queue.get(queueName) == null) {
				queue.put(queueName, new LinkedList<E>());
			} 
			for(E message : messages) {
				queue.get(queueName).add(message);
			}
			
		} catch (Exception e) {
			throw new SqQueueException("Error in adding new messages to queue (name) : " + queueName, ErrorCode.QUEUE_ADD_MULTIPLE);
		}
		
	}

	@Override
	public synchronized List<E> getAllMessages(String queueName) throws SqQueueException{
		
		List<E> messages = null;
		try {
			
			if(queue.get(queueName) == null) {
				throw new SqQueueException("Queue name is not valid (name) : " + queueName, ErrorCode.QUEUE_REMOVE_MULTIPLE);
			}  else {
				if(!queue.get(queueName).isEmpty()) {
					messages = new ArrayList<>();
				}
				while(!queue.get(queueName).isEmpty()) {
					messages.add(queue.get(queueName).remove());
				}
				return messages;
			}
			
		} catch (Exception e) {
			throw new SqQueueException("Error in removing messages from qurue (name) :" + queueName  , ErrorCode.QUEUE_ADD);
		}
	}

	@Override
	public boolean isEmpty(String queueName) throws SqQueueException {
		try {
			
			if(queue.get(queueName) == null) {
				throw new SqQueueException("Queue name is not valid (name) : " + queueName, ErrorCode.QUEUE_REMOVE);
			}  else {
				return queue.get(queueName).isEmpty();
			}
			
		} catch (Exception e) {
			throw new SqQueueException("Error in removing message from qurue (name) :" + queueName, ErrorCode.QUEUE_REMOVE);
		}
	}
	
}
