package clientcore.output.parser;

public class DeviceVo {

	private String id;
	
	private String name;
	
	private String deviceType;
	
	private String manufaturer;
	
	private String location;

	public DeviceVo(String id, String name, String deviceType, String manufaturer, String location) {
		this.id = id;
		this.name = name;
		this.deviceType = deviceType;
		this.manufaturer = manufaturer;
		this.location = location;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getManufaturer() {
		return manufaturer;
	}

	public void setManufaturer(String manufaturer) {
		this.manufaturer = manufaturer;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	

	
}
