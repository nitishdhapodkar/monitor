package clientcore.staticobjects;

import common.util.queue.SqQueue;

public class Startup {
	
	public static void init() {
		
		GlobalQueues.commandInQueue = new SqQueue<String>();
		GlobalQueues.commandOutQueue = new SqQueue<String>();
	}

}
