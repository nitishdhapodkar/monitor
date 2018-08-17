package common.jobs;

public class Command {

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
