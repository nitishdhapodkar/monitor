package clientcore.output.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import clientcore.command.CommandOutputVo;
import clientcore.command.CommandVO;
import clientcore.command.ListOutputVo;
import clientcore.command.OutputProcessor;
import clientcore.exceptions.SqCommandOutputProcessingException;

public class OutputConverter {
	
	public List<CommandOutputVo> convertCommandOutputs( List<CommandVO> commandresults ) {
		
		List<CommandOutputVo> commandOutputs = null;
		
		if( commandresults != null) {
			commandOutputs = new ArrayList<>();
			for( CommandVO commandVO : commandresults) {
				CommandOutputVo commandOutput = convertCommandOutput(commandVO);
				commandOutputs.add(commandOutput);
			}
		}
		
		return commandOutputs;
	}
	
	public CommandOutputVo convertCommandOutput( CommandVO commandresult ) {
		
		CommandOutputVo commandOutput = null;
		OutputProcessor outputProcessor = new OutputProcessor();
		
		try {
			 List<Map<String, String>> outputList = outputProcessor.processFormatedList(commandresult.getOutput(), commandresult.getCommandType());
			 commandOutput = new CommandOutputVo(commandresult, new ListOutputVo(outputList));
		} catch (SqCommandOutputProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return commandOutput;
	}

}
