package clientcore.test.command;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import clientcore.command.CommandPocesser;
import clientcore.command.CommandVO;
import clientcore.command.OutputProcessor;
import clientcore.constants.QueueNames;
import clientcore.exceptions.SqCommandOutputProcessingException;
import clientcore.staticobjects.GlobalQueues;
import clientcore.staticobjects.Startup;
import common.exceptions.SqQueueException;
import common.jobs.CommandType;

public class HardwareInfo {
	
	public static void main(String[] args) {
		try {
			Startup.init();
			CommandVO cmdToExecute = new CommandVO("systeminfo /FO CSV", CommandType.HWDINFO);
			GlobalQueues.commandQueue.sendMessage(QueueNames.COMMAND_IN.toString(), cmdToExecute);
			
			new CommandPocesser().processInputQueue();
			
			List<CommandVO> results = GlobalQueues.commandQueue.getAllMessages(QueueNames.COMMAND_OUT.toString());
			for(CommandVO result : results) {
				System.out.println(result.getOutput());
				
				OutputProcessor outputProcessor = new OutputProcessor();
				try {
					 List<Map<String, String>> lineSplit = outputProcessor.processFormatedList(result.getOutput(), CommandType.HWDINFO);
					 System.out.println( new Gson().toJson( lineSplit ));
				} catch (SqCommandOutputProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		} catch (SqQueueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
