package clientcore.command;

import java.util.Date;

import clientcore.constants.CommandType;
import clientcore.exceptions.SqCommandOutputProcessingException;

public class CommandOutputVo {
	
	private String command;
	
	private Date date;
	
	private CommandType commandType;
	
	private ListOutputVo formatedOutput;

	public CommandOutputVo(String command, Date date, CommandType commandType, ListOutputVo formatedOutput) {
		this.command = command;
		this.date = date;
		this.commandType = commandType;
		this.formatedOutput = formatedOutput;
	}
	
	public CommandOutputVo(String command, Date date, CommandType commandType) {
		this.command = command;
		this.date = date;
		this.commandType = commandType;
		this.formatedOutput = new ListOutputVo();
	}
	
	public CommandOutputVo(CommandVO commandVO, ListOutputVo formatedOutput ) {
		this.command = commandVO.getCommand();
		this.date = commandVO.getDate();
		this.commandType = commandVO.getCommandType();
		this.formatedOutput = formatedOutput;
	}

	public CommandOutputVo(CommandVO commandVO) {
		this.command = commandVO.getCommand();
		this.date = commandVO.getDate();
		this.commandType = commandVO.getCommandType();
		this.formatedOutput = new ListOutputVo();
	}
	
	public CommandOutputVo(CommandVO commandVO, String commandOutput) {
		this.command = commandVO.getCommand();
		this.date = commandVO.getDate();
		this.commandType = commandVO.getCommandType();
		
		OutputProcessor outputProcessor = new OutputProcessor();
		try {
			this.formatedOutput = new ListOutputVo(outputProcessor.processFormatedList(commandOutput));
		} catch (SqCommandOutputProcessingException e) {
			e.printStackTrace();
		}
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public CommandType getCommandType() {
		return commandType;
	}

	public void setCommandType(CommandType commandType) {
		this.commandType = commandType;
	}

	public ListOutputVo getFormatedOutput() {
		return formatedOutput;
	}

	public void setFormatedOutput(ListOutputVo formatedOutput) {
		this.formatedOutput = formatedOutput;
	}
}
