package br.com.geovanidias.util.primefaces;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.geovanidias.dao.GenericDAO;
import br.com.geovanidias.model.AbstractEntity;

public class LazyDataModelGeneric<T> extends LazyDataModel<T> {

	private static final long serialVersionUID = 1L;

	private List<T> entities;

	private GenericDAO<T> dao;

	public LazyDataModelGeneric(GenericDAO<T> dao) {
		this.dao = dao;
	}

	@Override
	public List<T> load(int posicaoPrimeiraLinha, int maximoPorPagina, String ordernarPeloCampo,
			SortOrder ordernarAscOuDesc, Map<String, Object> filtros) {
		
		String ordernacao = ordernarAscOuDesc.toString();

		if (SortOrder.UNSORTED.equals(ordernarAscOuDesc)) {
			ordernacao = SortOrder.ASCENDING.toString();
		}

		entities = dao.buscaPorPaginacao(posicaoPrimeiraLinha, maximoPorPagina, ordernarPeloCampo, ordernacao,
				filtros);

		setRowCount(dao.countAll(filtros));

		setPageSize(maximoPorPagina);

		return entities;
	}

	@Override
	public T getRowData(String rowKey) {
		for (T entity : (List<T>)entities) {
			if (rowKey.equals(String.valueOf( ((AbstractEntity)entity).getId() ) ) )
				return (T) entity;
		}

		return null;
	}

	@Override
	public Object getRowKey(T entity) {
		return ((AbstractEntity)entity).getId();
	}

	@Override
	public void setRowIndex(int rowIndex) {
		// solu��o para evitar ArithmeticException
		if (rowIndex == -1 || getPageSize() == 0) {
			super.setRowIndex(-1);
		} else
			super.setRowIndex(rowIndex % getPageSize());
	}

}
