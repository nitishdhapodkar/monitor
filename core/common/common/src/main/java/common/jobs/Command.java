package common.jobs;

import java.io.Serializable;

public class Command implements Serializable {

	private static final long serialVersionUID = 4125423074126653540L;

	private CommandType commandType;
	
	private String instruction;

	public Command(CommandType commandType, String instruction) {
		this.commandType = commandType;
		this.instruction = instruction;
	}

	public CommandType getCommandType() {
		return commandType;
	}

	public void setCommandType(CommandType commandType) {
		this.commandType = commandType;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
}
