package clientcore.test.command;

import java.util.List;

import clientcore.command.CommandPocesser;
import clientcore.command.CommandVO;
import clientcore.constants.CommandType;
import clientcore.constants.QueueNames;
import clientcore.staticobjects.GlobalQueues;
import clientcore.staticobjects.Startup;
import common.exceptions.SqQueueException;

public class Processor {
	public static void main(String[] args) {
		try {
			Startup.init();
			CommandVO cmdToExecute = new CommandVO("dir d:\\temp", CommandType.DIR);
			GlobalQueues.commandQueue.sendMessage(QueueNames.COMMAND_IN.toString(), cmdToExecute);
			
			cmdToExecute = new CommandVO("dir d:", CommandType.DIR);
			GlobalQueues.commandQueue.sendMessage(QueueNames.COMMAND_IN.toString(), cmdToExecute);
			
			cmdToExecute = new CommandVO("dir", CommandType.DIR);
			GlobalQueues.commandQueue.sendMessage(QueueNames.COMMAND_IN.toString(), cmdToExecute);
			
			cmdToExecute = new CommandVO("dir d:\\temp", CommandType.DIR);
			GlobalQueues.commandQueue.sendMessage(QueueNames.COMMAND_IN.toString(), cmdToExecute);
			
			cmdToExecute = new CommandVO("dir d:", CommandType.DIR);
			GlobalQueues.commandQueue.sendMessage(QueueNames.COMMAND_IN.toString(), cmdToExecute);
			
			cmdToExecute = new CommandVO("dir", CommandType.DIR);
			GlobalQueues.commandQueue.sendMessage(QueueNames.COMMAND_IN.toString(), cmdToExecute);
			
			cmdToExecute = new CommandVO("dir d:\\temp", CommandType.DIR);
			GlobalQueues.commandQueue.sendMessage(QueueNames.COMMAND_IN.toString(), cmdToExecute);
			
			cmdToExecute = new CommandVO("dir d:", CommandType.DIR);
			GlobalQueues.commandQueue.sendMessage(QueueNames.COMMAND_IN.toString(), cmdToExecute);
			
			cmdToExecute = new CommandVO("dir", CommandType.DIR);
			GlobalQueues.commandQueue.sendMessage(QueueNames.COMMAND_IN.toString(), cmdToExecute);
			
			cmdToExecute = new CommandVO("dir d:\\temp", CommandType.DIR);
			GlobalQueues.commandQueue.sendMessage(QueueNames.COMMAND_IN.toString(), cmdToExecute);
			
			cmdToExecute = new CommandVO("dir d:", CommandType.DIR);
			GlobalQueues.commandQueue.sendMessage(QueueNames.COMMAND_IN.toString(), cmdToExecute);
			
			cmdToExecute = new CommandVO("dir", CommandType.DIR);
			GlobalQueues.commandQueue.sendMessage(QueueNames.COMMAND_IN.toString(), cmdToExecute);
			
			new CommandPocesser().processInputQueue();
			
			List<CommandVO> results = GlobalQueues.commandQueue.getAllMessages(QueueNames.COMMAND_OUT.toString());
			for(CommandVO result : results) {
				System.out.println(result.getOutput());
			}
			
		} catch (SqQueueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
