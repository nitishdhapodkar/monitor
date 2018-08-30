package com.settings.services;

import java.util.List;

import com.services.GenericServices;
import com.settings.vo.Attribute;
import com.settings.vo.Environment;

public class SettingsService implements GenericServices<Attribute>, iSettings{

	@Override
	public void updateEnvironmentValue(String id, Environment environment, String value) {
		
		
	}

	@Override
	public void addEnvironmentValue(String id, Environment environment, String value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeEnvironmentValue(String id, Environment environment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getEnvironmentValue(String id, Environment environment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(Attribute e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Attribute e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Attribute getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Attribute getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Attribute> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
