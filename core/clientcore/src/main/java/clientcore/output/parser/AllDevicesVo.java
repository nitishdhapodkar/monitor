package clientcore.output.parser;

import java.util.Date;
import java.util.List;

public class AllDevicesVo {

	private String machineId;
	
	private Date time;
	
	private List<DeviceVo> devices;

	public AllDevicesVo(String machineId, Date time, List<DeviceVo> devices) {
		this.machineId = machineId;
		this.time = time;
		this.devices = devices;
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

	public List<DeviceVo> getDevices() {
		return devices;
	}

	public void setDevices(List<DeviceVo> devices) {
		this.devices = devices;
	}
	
	
}
