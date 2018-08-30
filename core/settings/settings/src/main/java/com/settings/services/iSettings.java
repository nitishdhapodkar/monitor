package com.settings.services;

import com.settings.vo.Attribute;
import com.settings.vo.Environment;

public interface iSettings {
	
	public void updateEnvironmentValue(String id, Environment environment, String value);

	public void addEnvironmentValue(String id, Environment environment, String value);
	
	public void removeEnvironmentValue(String id, Environment environment);
	
	public String getEnvironmentValue(String id, Environment environment);
}
