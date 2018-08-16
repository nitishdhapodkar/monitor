package common.jobs;

public class CronJob {

	private String id;
	
	private String name;
	
	private String description;
	
	private String cronExpresstion;
	
	private String dataTemplate;
	
	private String command;

	public CronJob(String id, String name, String description, String cronExpresstion, String dataTemplate) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.cronExpresstion = cronExpresstion;
		this.dataTemplate = dataTemplate;
	}

	public CronJob(String id, String name, String description, String cronExpresstion, String dataTemplate, String command) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.cronExpresstion = cronExpresstion;
		this.dataTemplate = dataTemplate;
		this.command = command;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCronExpresstion() {
		return cronExpresstion;
	}

	public void setCronExpresstion(String cronExpresstion) {
		this.cronExpresstion = cronExpresstion;
	}

	public String getDataTemplate() {
		return dataTemplate;
	}

	public void setDataTemplate(String dataTemplate) {
		this.dataTemplate = dataTemplate;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}
}
