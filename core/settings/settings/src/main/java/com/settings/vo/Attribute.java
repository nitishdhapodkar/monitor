package com.settings.vo;

import java.util.Date;
import java.util.Map;

import com.vo.GenericVo;

public class Attribute extends GenericVo{

	private DataType dataType;
	
	private Map<Environment, String> values;

	public Attribute(String id, String name, String displayName, String description, Date createdDate, Date modifiedDate, DataType dataType, Map<Environment, String> values) {
		super(id, name, displayName, description, createdDate, modifiedDate);
		this.dataType = dataType;
		this.values = values;
	}

	public DataType getDataType() {
		return dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

	public Map<Environment, String> getValues() {
		return values;
	}

	public void setValues(Map<Environment, String> values) {
		this.values = values;
	}
	
	public void addValue(Environment environment, String value) {
		this.values.put(environment, value);
	}
	
	public void updateValue(Environment environment, String value) {
		this.values.put(environment, value);
	}
	
	public void removeValue(Environment environment) {
		this.values.remove(environment);
	}
	
	public String getValue(Environment environment) {
		return this.values.get(environment);
	}
	
}
