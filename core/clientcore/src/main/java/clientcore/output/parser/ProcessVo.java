package clientcore.output.parser;

import java.util.Map;

public class ProcessVo {

	private String processId;
	
	private String processName;
	
	private String Status;
	
	private float cpuUsages;
	
	private float memoryUsages;
	
	private float diskUsages;
	
	private float networkUsages;
	
	private Map<String, String> otherAttributes;

	public ProcessVo(String processId, String processName, String status, float cpuUsages, float memoryUsages, float diskUsages, float networkUsages, Map<String, String> otherAttributes) {
		this.processId = processId;
		this.processName = processName;
		Status = status;
		this.cpuUsages = cpuUsages;
		this.memoryUsages = memoryUsages;
		this.diskUsages = diskUsages;
		this.networkUsages = networkUsages;
		this.otherAttributes = otherAttributes;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public float getCpuUsages() {
		return cpuUsages;
	}

	public void setCpuUsages(float cpuUsages) {
		this.cpuUsages = cpuUsages;
	}

	public float getMemoryUsages() {
		return memoryUsages;
	}

	public void setMemoryUsages(float memoryUsages) {
		this.memoryUsages = memoryUsages;
	}

	public float getDiskUsages() {
		return diskUsages;
	}

	public void setDiskUsages(float diskUsages) {
		this.diskUsages = diskUsages;
	}

	public float getNetworkUsages() {
		return networkUsages;
	}

	public void setNetworkUsages(float networkUsages) {
		this.networkUsages = networkUsages;
	}

	public Map<String, String> getOtherAttributes() {
		return otherAttributes;
	}

	public void setOtherAttributes(Map<String, String> otherAttributes) {
		this.otherAttributes = otherAttributes;
	}
	
}
