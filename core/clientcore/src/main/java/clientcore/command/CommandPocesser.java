package clientcore.command;

import java.util.List;

import org.pmw.tinylog.Logger;

import clientcore.constants.CommandType;
import clientcore.constants.QueueNames;
import clientcore.exceptions.SqCommandException;
import clientcore.staticobjects.GlobalQueues;
import clientcore.staticobjects.Startup;
import common.exceptions.SqQueueException;

public class CommandPocesser {

	public void processInputQueue() {
		
		CommandExecutor commandExecutor = null;
		try {
			while( !GlobalQueues.commandQueue.isEmpty(QueueNames.COMMAND_IN.toString()) ) {
				CommandVO cmdToExecute = GlobalQueues.commandQueue.getMessage(QueueNames.COMMAND_IN.toString());
				commandExecutor = new WindowsCommandExecutor();
				String output = commandExecutor.executeCommand(cmdToExecute.getCommand());
				cmdToExecute.setOutput(output);
				GlobalQueues.commandQueue.sendMessage(QueueNames.COMMAND_OUT.toString(), cmdToExecute);
			}
		} catch (SqQueueException e) {
			Logger.error(e.getMessage());
		} catch (SqCommandException e) {
			Logger.error(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		try {
			Startup.init();
			CommandVO cmdToExecute = new CommandVO("dir d:\\temp", CommandType.DIR);
			GlobalQueues.commandQueue.sendMessage(QueueNames.COMMAND_IN.toString(), cmdToExecute);
			
			CommandVO cmdToExecute2 = new CommandVO("dir d:\\temp", CommandType.DIR);
			GlobalQueues.commandQueue.sendMessage(QueueNames.COMMAND_IN.toString(), cmdToExecute2);
			
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
