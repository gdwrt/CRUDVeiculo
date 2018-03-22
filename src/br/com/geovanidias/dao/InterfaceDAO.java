package br.com.geovanidias.dao;

import java.io.Serializable;
import java.util.List;

public interface InterfaceDAO <T> extends Serializable{
	
	T save(T entity);
	void remove(Serializable ID);
	T findById(Serializable ID);
	List<T> listAll();
	
}
