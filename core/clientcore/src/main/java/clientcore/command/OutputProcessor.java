package clientcore.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import clientcore.exceptions.SqCommandOutputProcessingException;
import common.constants.ErrorCode;
import common.jobs.CommandType;

public class OutputProcessor {
	
	public List<Map<String, String>> processFormatedList(String commandOutput, CommandType commandType) throws SqCommandOutputProcessingException {
		
		List<Map<String, String>> attributesAndValues = null;
		
		if( commandType == CommandType.HWDINFO) {
			attributesAndValues = formatCSVOutput(commandOutput);
		} else if(commandType == CommandType.PROCESSES ) {
			attributesAndValues = processFormatedList(commandOutput);
		}
		
		return attributesAndValues;
	}
	
	private List<Map<String, String>> processFormatedList(String commandOutput) throws SqCommandOutputProcessingException{
		
		List<Map<String, String>> attributesAndValues = null;
		
		try {
			String allLines[] = commandOutput.split("\r\n");
			Map<String, String> commandAttributList = null;
			
			for(String line : allLines) {
				
				if( line.length() > 0 ) {
					Map<String, String> lineSplit = getKeyValue(line);
					if(commandAttributList == null) {
						commandAttributList = new HashMap<>();
					}
					commandAttributList.putAll(lineSplit);
				}else {
					if(attributesAndValues == null) {
						attributesAndValues = new ArrayList<>();
					}
					if(commandAttributList != null) {
						attributesAndValues.add(commandAttributList);
					}
					commandAttributList = null;
				}
			}
			
		}catch (Exception e) {
			throw new SqCommandOutputProcessingException("Error in spliting output into lines", e, ErrorCode.COMMAND_OUTPUT_LIST_PROCESS);
		}
		
		return attributesAndValues;
	}
	
	private List<Map<String, String>> formatCSVOutput(String commandOutput) throws SqCommandOutputProcessingException {
		
		List<Map<String, String>> attributesAndValues = null;
		List<List<String>> formattedvalues = formatCSV(commandOutput);
		
		try {
			Map<String, String> headerValues = new HashMap<>();
			
			for(int rowNo = 0; rowNo < formattedvalues.get(0).size();  rowNo++ ) {
				headerValues.put(formattedvalues.get(0).get(rowNo), formattedvalues.get(1).get(rowNo));
			}
			
			attributesAndValues = new ArrayList<>();
			attributesAndValues.add(headerValues);
		} catch (Exception e) {
			throw new SqCommandOutputProcessingException("Error in mapping header and values", ErrorCode.COMMAND_OUTPUT_LIST_PROCESS);
		}
		
		
		return attributesAndValues;
	}
	
	private Map<String, String> getKeyValue(String outputLine)  throws SqCommandOutputProcessingException{
		
		Map<String, String> keyValue = null;
		String splitValues[] = outputLine.split(":");
		
		if(splitValues != null) {
			
			if(splitValues.length == 2) {
				keyValue = new HashMap<>();
				keyValue.put(splitValues[0].trim(), splitValues[1].trim());
			} else {
				throw new SqCommandOutputProcessingException("Error in spliting output line", ErrorCode.COMMAND_OUTPUT_LIST_PROCESS);
			}
			
		} else {
			throw new SqCommandOutputProcessingException("Error in spliting output line", ErrorCode.COMMAND_OUTPUT_LIST_PROCESS);
		}
		
		return keyValue;
	}
	
	private List<List<String>> formatCSV(String csvString) throws SqCommandOutputProcessingException {
		
		List<List<String>> formatedValues = null;
		try {
			String lines[] = csvString.split("\\r?\\n");
			
			for(String line : lines) {
				line = line.trim().substring(1, line.length() - 1 );
				List<String> lineValues = new ArrayList<>();
				String values[] = line.split("\",\"");
				
				for(String value : values) {
					lineValues.add(value);
				}
				if( formatedValues == null ) {
					formatedValues = new ArrayList<>();
				}
				formatedValues.add(lineValues);
			}
			
		} catch (Exception e) {
			throw new SqCommandOutputProcessingException("Error in spliting output line", e, ErrorCode.COMMAND_OUTPUT_LIST_PROCESS);
		}
		
		
		
		return formatedValues;
	}
	
	public static void main(String[] args) {
		OutputProcessor outputProcessor = new OutputProcessor();
		try {
			 Map<String, String> lineSplit = outputProcessor.getKeyValue("Id      : 1656");
			 System.out.println(lineSplit.get("Id") );
		} catch (SqCommandOutputProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
