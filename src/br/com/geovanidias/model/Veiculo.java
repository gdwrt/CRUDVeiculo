package br.com.geovanidias.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Veiculo extends AbstractEntity{
	
	private String placa;
	private String marca;
	private String descricao;
	@Enumerated(EnumType.STRING)
	private Categoria categoria;
	
	public Veiculo() {
	}
	
	public Veiculo(Long id, String placa, String marca, String descricao, Categoria categoria) {
		super(id);
		this.placa = placa;
		this.marca = marca;
		this.descricao = descricao;
		this.categoria = categoria;
	}

	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Categoria[] categorias() {
		return Categoria.values();
	}
}
