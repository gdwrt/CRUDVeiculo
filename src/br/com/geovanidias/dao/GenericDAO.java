package br.com.geovanidias.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class GenericDAO<T>  implements InterfaceDAO<T>{
	
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager em;
	private Class<T> classe; 
	
	public GenericDAO(EntityManager em, Class<T> classe) {
		this.em = em;
		this.classe = classe;
	}

	@Override
	public T save(T entity) {
		em.merge(entity);
		em.flush();
		return entity;
	}

	@Override
	public void remove(Serializable ID) {
		T entity = findById(ID);
		em.remove(entity);
		em.flush();
	}

	@Override
	public T findById(Serializable ID) {
		return em.find(this.classe, ID); 
	}

	@Override
	public List<T> listAll() {
		
		String jpql = "select e from " + this.classe.getSimpleName() + " e" ;
		TypedQuery<T> query = em.createQuery(jpql, this.classe);
		
		return query.getResultList();
	}
	
	public int countAll(Map<String, Object> filters) {

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

		Root<T> root = criteriaQuery.from(classe);
		
		criteriaQuery.select(criteriaBuilder.count(root));

		
		// filtros
		if (filters != null && !filters.isEmpty()) {
			
			List<Predicate> condicoes = new ArrayList<Predicate>();

			for (Map.Entry<String, Object> filtro : filters.entrySet()) {

				Expression<String> atributo = root.get(filtro.getKey()).as(String.class);
				Predicate condicao = criteriaBuilder.like(atributo, "%" + filtro.getValue() + "%");
				condicoes.add(condicao);
			}
			
			Predicate[] condicoesComoArray = condicoes.toArray(new Predicate[condicoes.size()]);

			criteriaQuery.where(condicoesComoArray);
		}

		return em.createQuery(criteriaQuery).getSingleResult().intValue();

	}

	public List<T> buscaPorPaginacao(int posicaoPrimeiraLinha, int maximoPorPagina, String ordenarPeloCampo,
			String ordernarAscOuDesc, Map<String, Object> filtros) {

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(classe);

		Root<T> root = criteriaQuery.from(classe);

		// filtros
		if (filtros != null && !filtros.isEmpty()) {

			List<Predicate> condicoes = new ArrayList<Predicate>();

			for (Map.Entry<String, Object> filtro : filtros.entrySet()) {

				Expression<String> atributo = root.get(filtro.getKey()).as(String.class);
				Predicate condicao = criteriaBuilder.like(atributo, "%" + filtro.getValue() + "%");
				condicoes.add(condicao);
			}

			Predicate[] condicoesComoArray = condicoes.toArray(new Predicate[condicoes.size()]);

			criteriaQuery.where(condicoesComoArray);
		}

		// Ordenar
		if (ordenarPeloCampo != null && !ordenarPeloCampo.isEmpty() && ordernarAscOuDesc != null
				&& !ordernarAscOuDesc.isEmpty()) {

			if (ordernarAscOuDesc.toUpperCase().contains("DESC")) {
				criteriaQuery.orderBy(criteriaBuilder.desc(root.get(ordenarPeloCampo)));
			} else {
				criteriaQuery.orderBy(criteriaBuilder.asc(root.get(ordenarPeloCampo)));
			}
		}

		TypedQuery<T> query = em.createQuery(criteriaQuery);

		query.setFirstResult(posicaoPrimeiraLinha);
		query.setMaxResults(maximoPorPagina);

		return query.getResultList();
	}
	
}
