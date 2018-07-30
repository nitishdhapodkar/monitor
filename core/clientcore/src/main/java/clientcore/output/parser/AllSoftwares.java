package clientcore.output.parser;

import java.util.Date;
import java.util.List;

public class AllSoftwares {

	private String machineId;
	
	private Date time;
	
	private List<SoftwareVo> softwares;

	public AllSoftwares(String machineId, Date time, List<SoftwareVo> softwares) {
		this.machineId = machineId;
		this.time = time;
		this.softwares = softwares;
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

	public List<SoftwareVo> getSoftwares() {
		return softwares;
	}

	public void setSoftwares(List<SoftwareVo> softwares) {
		this.softwares = softwares;
	}
}
