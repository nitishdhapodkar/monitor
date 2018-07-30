package clientcore.output.parser;

import java.util.Map;

public class ServiceVO {
	
	private String name;
	
	private String description;
	
	private String status;
	
	private String group;
	
	private String logOnAs;
	
	private int processId;
	
	private Map<String, String> otherAttributes;
	
	public ServiceVO(String name, String description, String status, String group, String logOnAs, int processId, Map<String, String> otherAttributes) {
		this.name = name;
		this.description = description;
		this.status = status;
		this.group = group;
		this.logOnAs = logOnAs;
		this.processId = processId;
		this.otherAttributes = otherAttributes;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getLogOnAs() {
		return logOnAs;
	}

	public void setLogOnAs(String logOnAs) {
		this.logOnAs = logOnAs;
	}

	public int getProcessId() {
		return processId;
	}

	public void setProcessId(int processId) {
		this.processId = processId;
	}

	public Map<String, String> getOtherAttributes() {
		return otherAttributes;
	}

	public void setOtherAttributes(Map<String, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
	}
	
	

}
