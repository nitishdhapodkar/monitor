package clientcore.output.parser;

import java.util.Date;
import java.util.List;

public class AllDiskUsages {
	
	private String machineId;
	
	private Date time;
	
	private List<DiskUsages> disksUsages;
	
	public AllDiskUsages(String machineId, Date time, List<DiskUsages> disksUsages) {
		this.machineId = machineId;
		this.time = time;
		this.disksUsages = disksUsages;
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

	public List<DiskUsages> getDisksUsages() {
		return disksUsages;
	}

	public void setDisksUsages(List<DiskUsages> disksUsages) {
		this.disksUsages = disksUsages;
	}

}
