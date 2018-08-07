package common.util.schedule;

import java.util.Date;

public class NextJobTime {
	
	private String jobId;
	
	private Date dateTime;

	public NextJobTime(String jobId, Date dateTime) {
		this.jobId = jobId;
		this.dateTime = dateTime;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
}
