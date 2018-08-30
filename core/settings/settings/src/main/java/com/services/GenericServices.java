package com.services;

import java.util.List;

public interface GenericServices<E> {

	public void add(E e);
	
	public void update(E e);
	
	public void remove(String id);
	
	public E getById(String id);
	
	public E getByName(String name);
	
	public List<E> getAll();
}
