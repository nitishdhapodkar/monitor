package clientcore.external.controller;

import org.pmw.tinylog.Logger;

import clientcore.command.CommandVO;
import clientcore.staticobjects.GlobalQueues;
import common.logger.config.LoggerConfiguration;
import common.util.queue.SqQueue;

public class Loader {

	public static void main(String[] args) {
		
		initiateEnvironment();
		
		while( true ) {
			try {
				
				Thread.sleep(5 * 60 * 1000);
			} catch (Exception e) {
				Logger.error(e);
			}
		}
	}
	
	
	private static void initiateEnvironment() {
		GlobalQueues.commandQueue = new SqQueue<CommandVO>();
		LoggerConfiguration.init();
	}
	
}
