package br.com.geovanidias.util.jpa;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Interceptor
@Transactional
public class TransactionInterceptor {

	@Inject
	EntityManager em;

	@AroundInvoke
	public Object aroundInvoke(InvocationContext context) throws Exception { 
		
		EntityTransaction transaction = em.getTransaction();
		boolean owner = false;
		
		try {
			if(!transaction.isActive()) {
				transaction.begin();
				transaction.rollback();
				
				transaction.begin();
				owner = true;
			}
			
			return context.proceed();
		} catch (Exception e) {
			if(transaction != null && owner) {
				transaction.rollback();
			}
			throw e;
		} finally {
			if(transaction != null && transaction.isActive() && owner) {
				transaction.commit();
			}
		}
	}

}
