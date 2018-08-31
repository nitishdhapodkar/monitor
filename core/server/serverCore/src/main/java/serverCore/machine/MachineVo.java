package serverCore.machine;

import java.util.Date;

public class MachineVo {
	
	private String id;
	
	private String name;
	
	private String licenceId;
	
	private Date createdOn;
	
	private String token;

	public MachineVo(String id, String name, String licenceId, Date createdOn, String token) {
		this.id = id;
		this.name = name;
		this.licenceId = licenceId;
		this.createdOn = createdOn;
		this.token = token;
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

	public String getLicenceId() {
		return licenceId;
	}

	public void setLicenceId(String licenceId) {
		this.licenceId = licenceId;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
