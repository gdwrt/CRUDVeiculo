package br.com.geovanidias.test;

import java.util.List;

import javax.inject.Inject;

import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.geovanidias.dao.GenericDAO;
import br.com.geovanidias.model.Categoria;
import br.com.geovanidias.model.Veiculo;
import br.com.geovanidias.util.jpa.Transactional;

@RunWith(CdiRunner.class)
public class CRUDTest { 
	
	@Inject
	private GenericDAO<Veiculo> dao;
	
	@Test
	@Transactional
	public void salvar() {
		
		Veiculo veiculo = new Veiculo();
		veiculo.setMarca("volkswagen");
		veiculo.setPlaca("AAA-1111"); 
		veiculo.setDescricao("GOL BRANCO");
		veiculo.setCategoria(Categoria.B);
		
		dao.save(veiculo);
		
		
		System.out.println(dao.listAll());
	}
	
	//@Test
	@Transactional
	public void atualizar() {
		
		Veiculo veiculo = new Veiculo();
		veiculo.setId(1L);
		veiculo.setMarca("volkswagen");
		veiculo.setPlaca("EEE-4444"); 
		veiculo.setDescricao("GOL AZUL");
		veiculo.setCategoria(Categoria.B);
		
		dao.save(veiculo);
		
		System.out.println(dao.listAll());
		
	}
	//@Test
	@Transactional
	public void remover() {
		
		dao.remove(1L);
		
		System.out.println(dao.listAll());
				
	}
	//@Test
	public void buscarPorId() {
		
		Veiculo veiculo = dao.findById(1L);
		
		System.out.println(veiculo.getDescricao()); 
		
	}
	
	//@Test
	public void listarTodos() {
		 
		List<Veiculo> veiculos = dao.listAll();
		
		System.out.println(veiculos); 
		
	}
	
}
