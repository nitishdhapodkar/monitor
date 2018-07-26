package clientcore.output.processor;

import clientcore.command.CommandVO;

public interface IOutputProcessor {
	
	public CommandVO process(CommandVO command);

}
