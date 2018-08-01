package common.util.schedule;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.CronExpression;

import common.constants.ErrorCode;
import common.exceptions.SqCronResolverException;

public class CornResolver {

	public Date getNextDate(String cornString) throws SqCronResolverException{
		
		Date nextDate;
		CronExpression cronExpression;
		
		try {
			cronExpression = new CronExpression(cornString);
			nextDate = cronExpression.getNextValidTimeAfter(new Date());
		} catch (Exception e) {
			throw new SqCronResolverException("Error occured while resolving expression", e, ErrorCode.CRON_RESOLVER);
		}
		
		return nextDate;
	}
	
	public Map<String, Date> getNextDate(List<CronJob> cronJobs) throws SqCronResolverException{
		
		Map<String, Date> nextJobTime = null; 
		
		try {
			if(cronJobs != null) {
				
				nextJobTime = new HashMap<>();
				
				for( CronJob cronJob : cronJobs ) {
					nextJobTime.put(cronJob.getId(), getNextDate(cronJob.getCronExpresstion()));
				}
			}
			
		} catch (Exception e) {
			throw new SqCronResolverException("Error occured to get next time for job", e, ErrorCode.CRON_RESOLVER);
		}
		
		return nextJobTime;
	}
	
	public Map<String, Date> getNextDate(CronJob cronJob) throws SqCronResolverException{
		
		Map<String, Date> nextJobTime = null; 
		
		try {
			
			nextJobTime = new HashMap<>();
			
			if(cronJob != null) {
				nextJobTime.put(cronJob.getId(), getNextDate(cronJob.getCronExpresstion()));
			}
			
		} catch (Exception e) {
			throw new SqCronResolverException("Error occured to get next time for job", e, ErrorCode.CRON_RESOLVER);
		}
		
		return nextJobTime;
	}
	
	public Map<String, Date> getNextDate(CronJob cronJob,  Map<String, Date> nextTimes) throws SqCronResolverException{
		
		Map<String, Date> nextJobTime = null; 
		
		try {
			
			nextJobTime = new HashMap<>(nextTimes);
			
			if(cronJob != null) {
				nextJobTime.put(cronJob.getId(), getNextDate(cronJob.getCronExpresstion()));
			}
			
		} catch (Exception e) {
			throw new SqCronResolverException("Error occured to get next time for job", e, ErrorCode.CRON_RESOLVER);
		}
		
		return nextJobTime;
	}
	public static void main(String[] args) {
		CornResolver cornResolver = new CornResolver();
		try {
			System.out.println(new Date().toString());
			Date nextDate = cornResolver.getNextDate("0 0/10 * * * ?");
			System.out.println(nextDate.toString());
		} catch (SqCronResolverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
