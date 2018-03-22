package br.com.geovanidias.util.primefaces;

import java.lang.reflect.ParameterizedType;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.geovanidias.dao.GenericDAO;


public class LazyDataModelFactory<T> {
	
	@Inject
	private EntityManager em;
	 
	@Produces
	@Dependent
	@SuppressWarnings({"rawtypes","unchecked"})
	public LazyDataModelGeneric<T> createLazyDataModelGeneric(InjectionPoint point) {
		
		ParameterizedType type = (ParameterizedType)point.getType();
		Class<T> entityClass = (Class)type.getActualTypeArguments()[0];
		GenericDAO<T> dao = new GenericDAO<T>(em, entityClass);
		
		return new LazyDataModelGeneric<T>(dao);
	}
}
