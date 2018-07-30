package clientcore.output.parser;

import java.util.Date;

public class NetworkVo {

	private String machineId;
	
	private Date time;
	
	private float inputTraffic;
	
	private float outputTraffic;

	public NetworkVo(String machineId, Date time, float inputTraffic, float outputTraffic) {
		this.machineId = machineId;
		this.time = time;
		this.inputTraffic = inputTraffic;
		this.outputTraffic = outputTraffic;
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

	public float getInputTraffic() {
		return inputTraffic;
	}

	public void setInputTraffic(float inputTraffic) {
		this.inputTraffic = inputTraffic;
	}

	public float getOutputTraffic() {
		return outputTraffic;
	}

	public void setOutputTraffic(float outputTraffic) {
		this.outputTraffic = outputTraffic;
	}
}
