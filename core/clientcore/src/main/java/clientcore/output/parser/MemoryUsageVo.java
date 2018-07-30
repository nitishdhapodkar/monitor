package clientcore.output.parser;

import java.util.Date;

public class MemoryUsageVo {

	private String machineId;
	
	private Date time;
	
	private float totalMemoryUsages;

	public MemoryUsageVo(Date time, float totalMemoryUsages) {
		super();
		this.time = time;
		this.totalMemoryUsages = totalMemoryUsages;
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

	public float getTotalMemoryUsages() {
		return totalMemoryUsages;
	}

	public void setTotalMemoryUsages(float totalMemoryUsages) {
		this.totalMemoryUsages = totalMemoryUsages;
	}
	
}
