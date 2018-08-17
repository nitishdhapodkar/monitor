package clientcore.external.controller;

import java.util.ArrayList;
import java.util.List;

import clientcore.command.CommandVO;
import clientcore.constants.GlobalConstants;
import clientcore.constants.QueueNames;
import clientcore.staticobjects.GlobalQueues;
import common.database.io.DBFileHandler;
import common.exceptions.SqInmenoryDBException;
import common.exceptions.SqQueueException;
import common.jobs.CommandType;
import common.jobs.CronJob;

public class Controller {
	
	public void addJob(CronJob cronJob) {
		
		DBFileHandler dbFileHandler = new DBFileHandler();
		List<Object> cronJobs = null;
		
		try {
			cronJobs = dbFileHandler.readObjects(GlobalConstants.JOBS_PERSISTANCE_LOCATION);
			
		} catch (SqInmenoryDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if( cronJobs == null ) {
			cronJobs = new ArrayList<>();
		}
		cronJobs.add((Object) cronJob);
		
		try {
			dbFileHandler.writeObject(cronJobs, GlobalConstants.JOBS_PERSISTANCE_LOCATION);
		} catch (SqInmenoryDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void executeCommand(String command, CommandType commandType) {
		
		try {
			CommandVO cmdToExecute = new CommandVO(command, commandType);
			GlobalQueues.commandQueue.sendMessage(QueueNames.COMMAND_IN.toString(), cmdToExecute);
		} catch (SqQueueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<String> getCommandOutput() {
		
		List<String> allOutput = null;
		
		
		return allOutput;
	}

}
