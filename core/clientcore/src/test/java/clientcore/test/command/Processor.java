package clientcore.test.command;

import java.util.List;
import java.util.Map;

import clientcore.command.CommandPocesser;
import clientcore.command.CommandVO;
import clientcore.command.OutputProcessor;
import clientcore.constants.CommandType;
import clientcore.constants.QueueNames;
import clientcore.exceptions.SqCommandOutputProcessingException;
import clientcore.staticobjects.GlobalQueues;
import clientcore.staticobjects.Startup;
import common.exceptions.SqQueueException;

public class Processor {
	public static void main(String[] args) {
		try {
			Startup.init();
			CommandVO cmdToExecute = new CommandVO("Get-Process | Format-list", CommandType.OUT_LIST);
			GlobalQueues.commandQueue.sendMessage(QueueNames.COMMAND_IN.toString(), cmdToExecute);
			
			new CommandPocesser().processInputQueue();
			
			List<CommandVO> results = GlobalQueues.commandQueue.getAllMessages(QueueNames.COMMAND_OUT.toString());
			for(CommandVO result : results) {
				System.out.println(result.getOutput());
				
				OutputProcessor outputProcessor = new OutputProcessor();
				try {
					 List<Map<String, String>> lineSplit = outputProcessor.processFormatedList(result.getOutput());
					 System.out.println(lineSplit );
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
