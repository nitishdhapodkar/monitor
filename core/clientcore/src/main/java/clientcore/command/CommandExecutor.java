package clientcore.command;

import clientcore.exceptions.SqCommandException;

public interface CommandExecutor {
	
	public String executeCommand(String command) throws SqCommandException;

}
