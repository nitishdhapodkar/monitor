package common.jobs;

import java.io.Serializable;

import common.database.io.DatabaseUtil;

public class CronJob implements Serializable {

	private static final long serialVersionUID = -2721663438729562138L;

	private String id;
	
	private String name;
	
	private String description;
	
	private String cronExpresstion;
	
	private String dataTemplate;
	
	private Command command;

	public CronJob(String name, String description, String cronExpresstion, String dataTemplate, Command command) {
		this.id = DatabaseUtil.getNewId();
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

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}
}
