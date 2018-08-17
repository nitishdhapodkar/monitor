package clientcore.output.processor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import clientcore.command.CommandVO;
import clientcore.constants.QueueNames;
import clientcore.staticobjects.GlobalQueues;
import common.exceptions.SqCronResolverException;
import common.exceptions.SqQueueException;
import common.jobs.CronJob;

public class JobProcesser {

	private List<CronJob> cronJobs;
	
	private Map<String, Date> nextJobTimes;

	public JobProcesser(List<Object> cronJobs) throws SqCronResolverException {
		this.cronJobs =  convertToJobs(cronJobs);
		CronResolver cronResolver = new CronResolver();
		this.nextJobTimes = cronResolver.getNextDate(this.cronJobs);
	}

	public List<CronJob> getCronJobs() {
		return cronJobs;
	}

	public Map<String, Date> getNextJobTimes() {
		return nextJobTimes;
	}
	
	public void startProcessingJobs()  {
		
		try {
			
			for(String jobId : nextJobTimes.keySet()) {
				
				Date currentDate = new Date();
				CronResolver cronResolver = new CronResolver();
				
				if(nextJobTimes.get(jobId).before(currentDate)) {
					executeJob(getCronJob(jobId));
					nextJobTimes.put(jobId, cronResolver.getNextDate(getCronJob(jobId).getCronExpresstion()));
				}
			}
		} catch (SqCronResolverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private CronJob getCronJob(String jobId) {
		
		for(CronJob cronJob : cronJobs) {
			if(cronJob.getId() == jobId) {
				return cronJob;
			}
		}
		
		return null;
	}
	
	private void executeJob(CronJob cronJob) {
		
		try {
			CommandVO cmdToExecute = new CommandVO(cronJob.getCommand().getInstruction(), cronJob.getCommand().getCommandType());
			GlobalQueues.commandQueue.sendMessage(QueueNames.COMMAND_IN.toString(), cmdToExecute);
		} catch (SqQueueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private List<CronJob> convertToJobs(List<Object> objects) {
		
		List<CronJob> cronJobs = null;
		
		if( objects != null ) {
			cronJobs = new ArrayList<>();
			for(Object object : objects) {
				cronJobs.add( (CronJob) object);
			}
		}
		
		return cronJobs;
	}
	
}
