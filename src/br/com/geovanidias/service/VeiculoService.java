package br.com.geovanidias.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.geovanidias.dao.GenericDAO;
import br.com.geovanidias.model.Veiculo;
import br.com.geovanidias.util.jpa.Transactional;

public class VeiculoService  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private GenericDAO<Veiculo> dao;
	
	@Transactional
	public void salvar(Veiculo veiculo) throws BusinessException {
		//Regras de negócio
		if(veiculo.getMarca() == null || veiculo.getMarca().trim().equals("")) {
			throw new BusinessException("Marca obrigatório");
		}
		
		dao.save(veiculo);
	}
	
	@Transactional
	public void remover(Long id) {
		//Regras de negócio
		
		dao.remove(id);
	}
	
	
}
