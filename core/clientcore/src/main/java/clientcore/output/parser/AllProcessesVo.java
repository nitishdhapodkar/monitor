package clientcore.output.parser;

import java.util.Date;
import java.util.List;

public class AllProcessesVo {

	private String machineId;
	
	private Date time;
	
	private List<ProcessVo> processes;

	public AllProcessesVo(String machineId, Date time, List<ProcessVo> processes) {
		this.machineId = machineId;
		this.time = time;
		this.processes = processes;
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

	public List<ProcessVo> getProcesses() {
		return processes;
	}

	public void setProcesses(List<ProcessVo> processes) {
		this.processes = processes;
	}
}
