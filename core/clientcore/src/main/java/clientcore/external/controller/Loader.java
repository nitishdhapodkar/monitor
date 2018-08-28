package clientcore.external.controller;

import java.util.List;

import org.pmw.tinylog.Logger;

import clientcore.command.CommandOutputVo;
import clientcore.command.CommandPocesser;
import clientcore.command.CommandVO;
import clientcore.constants.GlobalConstants;
import clientcore.constants.QueueNames;
import clientcore.output.processor.JobProcesser;
import clientcore.output.processor.OutputConverter;
import clientcore.staticobjects.GlobalQueues;
import common.database.io.DBFileHandler;
import common.exceptions.SqQueueException;
import common.logger.config.LoggerConfiguration;
import common.util.queue.SqQueue;

public class Loader {

	private static JobProcesser jobProcesser = null;
	private static DBFileHandler dbFileHandler = null;
	private static CommandPocesser commandPocesser = null; 
	
	public static void main(String[] args) {
		
		initiateEnvironment();
		
		while( true ) {
			try {
				
				jobProcesser.startProcessingJobs();
				commandPocesser.processInputQueue();
				processOutput();
				
				Thread.sleep(1 * 60 * 1000);
			} catch (Exception e) {
				Logger.error(e);
			}
		}
	}
	
	private static void initiateEnvironment() {
		
		try {
			
			GlobalQueues.commandQueue = new SqQueue<CommandVO>();
			LoggerConfiguration.init();
			dbFileHandler = new DBFileHandler();
			commandPocesser = new CommandPocesser();
			
			List<Object> cronJobs =  dbFileHandler.readObjects(GlobalConstants.JOBS_PERSISTANCE_LOCATION);
			jobProcesser = new JobProcesser(cronJobs);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static List<CommandOutputVo> processOutput() {
		
		List<CommandVO> commandResult = null;
		OutputConverter outputConverter = null;
		List<CommandOutputVo> commandOutputs = null;
		try {
			outputConverter = new OutputConverter();
			
			commandResult = GlobalQueues.commandQueue.getAllMessages(QueueNames.COMMAND_OUT.toString());
			commandOutputs = outputConverter.convertCommandOutputs(commandResult);
			
			for(CommandOutputVo commandOutput : commandOutputs) {
				System.out.println(commandOutput.toJSON());
			}
			
		} catch (SqQueueException e) {
			Logger.info("Queue is not available");
		}
		
		return commandOutputs;
		
	}
	
}
