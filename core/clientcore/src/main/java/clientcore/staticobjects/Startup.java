package clientcore.staticobjects;

import clientcore.command.CommandVO;
import common.logger.config.LoggerConfiguration;
import common.util.queue.SqQueue;

public class Startup {
	
	public static void init() {
		
		GlobalQueues.commandQueue = new SqQueue<CommandVO>();
		LoggerConfiguration.init();
	}

}
