package clientcore.output.parser;

import java.util.Date;
import java.util.List;

public class TotalCPUVO {
	
	private String machineId;
	
	private Date time;

	private float totalCpuUsages;
	
	private List<Float> cpuUsage;

	public TotalCPUVO(float totalCpuUsages, Date time, List<Float> cpuUsage) {
		this.totalCpuUsages = totalCpuUsages;
		this.time = time;
		this.cpuUsage = cpuUsage;
	}

	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	public float getTotalCpuUsages() {
		return totalCpuUsages;
	}

	public void setTotalCpuUsages(float totalCpuUsages) {
		this.totalCpuUsages = totalCpuUsages;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public List<Float> getCpuUsage() {
		return cpuUsage;
	}

	public void setCpuUsage(List<Float> cpuUsage) {
		this.cpuUsage = cpuUsage;
	}
}
