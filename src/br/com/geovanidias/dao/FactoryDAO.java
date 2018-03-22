package br.com.geovanidias.dao;

import java.lang.reflect.ParameterizedType;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import javax.persistence.EntityManager;

public class FactoryDAO {
	
	
	@Inject
	private EntityManager em;

	
	@SuppressWarnings({"unchecked"})
	@Produces
	@Dependent
	public <T> GenericDAO<T> createGenericDao(InjectionPoint point) {
		
		ParameterizedType type = (ParameterizedType) point.getType();
		Class<T> classe = (Class<T>)type.getActualTypeArguments()[0];

		return new GenericDAO<T>(em, classe);
		
	}

}
