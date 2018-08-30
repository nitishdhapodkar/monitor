package serverCore.licence;

import java.util.Date;
import java.util.Map;

public class LicenceVo {
	
	private String id;
	
	private String name;
	
	private int noOfEndpoints;
	
	private Map<String, String> attributes;
	
	private Date createdDate;
	
	private Date modifiedDate;
	
	private Date expiryDate;

	public LicenceVo(String id, String name, int noOfEndpoints, Map<String, String> attributes, Date createdDate, Date modifiedDate, Date expiryDate) {
		this.id = id;
		this.name = name;
		this.noOfEndpoints = noOfEndpoints;
		this.attributes = attributes;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.expiryDate = expiryDate;
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

	public int getNoOfEndpoints() {
		return noOfEndpoints;
	}

	public void setNoOfEndpoints(int noOfEndpoints) {
		this.noOfEndpoints = noOfEndpoints;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	public void addAttribute(String name, String value) {
		this.attributes.put(name, value);
	}
	
	public void removeAttribute(String name) {
		this.attributes.remove(name);
	}
	
	public void updateAttribute(String name, String value) {
		this.attributes.put(name, value);
	}
	
	public String getAttribute(String name) {
		return this.attributes.get(name);
	}
}
