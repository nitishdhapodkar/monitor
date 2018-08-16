package clientcore.output.processor;

import java.util.Date;
import java.util.List;
import java.util.Map;

import common.exceptions.SqCronResolverException;
import common.jobs.CronJob;

public class JobProcesser {

	private List<CronJob> cronJobs;
	
	private Map<String, Date> nextJobTimes;

	public JobProcesser(List<CronJob> cronJobs) throws SqCronResolverException {
		this.cronJobs = cronJobs;
		CornResolver cornResolver = new CornResolver();
		this.nextJobTimes = cornResolver.getNextDate(cronJobs);
		this.init();
	}

	public List<CronJob> getCronJobs() {
		return cronJobs;
	}

	public Map<String, Date> getNextJobTimes() {
		return nextJobTimes;
	}
	
	private void init() {
		for(String jobId : nextJobTimes.keySet()) {
			
			Date currentDate = new Date();
			if(nextJobTimes.get(jobId).before(currentDate)) {
				
				executeJob(getCronJob(jobId));
			}
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
		
	}
}
