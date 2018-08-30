package com.vo;

import java.util.Date;

public class GenericVo {
	
private String id;
	
	private String name;
	
	private String displayName;
	
	private String description;
	
	private Date createdDate;
	
	private Date modifiedDate;

	public GenericVo(String id, String name, String displayName, String description, Date createdDate, Date modifiedDate) {
		this.id = id;
		this.name = name;
		this.displayName = displayName;
		this.description = description;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
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

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
}
