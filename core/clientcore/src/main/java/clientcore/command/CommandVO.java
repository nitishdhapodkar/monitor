package clientcore.command;

import clientcore.constants.CommandType;

public class CommandVO {
	
	private String command;
	
	private CommandType commandType;
	
	private String output;

	public CommandVO(String command, CommandType commandType) {
		this.command = command;
		this.commandType = commandType;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public CommandType getCommandType() {
		return commandType;
	}

	public void setCommandType(CommandType commandType) {
		this.commandType = commandType;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}
}
