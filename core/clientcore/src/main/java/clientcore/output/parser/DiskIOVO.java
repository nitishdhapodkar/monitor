package clientcore.output.parser;

import java.util.Date;

public class DiskIOVO {

	private String machineId;
	
	private Date time;
	
	private float diskUsages;

	public DiskIOVO(String machineId, Date time, float diskUsages) {
		this.machineId = machineId;
		this.time = time;
		this.diskUsages = diskUsages;
	}

	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public float getDiskUsages() {
		return diskUsages;
	}

	public void setDiskUsages(float diskUsages) {
		this.diskUsages = diskUsages;
	}
}
