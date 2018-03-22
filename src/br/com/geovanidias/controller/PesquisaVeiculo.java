package br.com.geovanidias.controller;

import static br.com.geovanidias.util.jsf.FacesUtil.addSuccessMessage;
import static br.com.geovanidias.util.jsf.FacesUtil.redirecionarObjetoFlash;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.geovanidias.model.Veiculo;
import br.com.geovanidias.service.VeiculoService;
import br.com.geovanidias.util.primefaces.LazyDataModelGeneric;


@Named
@ViewScoped
public class PesquisaVeiculo implements Serializable {

	private static final long serialVersionUID = 1L;

	private @Inject VeiculoService veiculoService;
	private Veiculo veiculoSelecionado;
	private @Inject LazyDataModelGeneric<Veiculo> veiculosLazy;

	public void remover(Veiculo veiculo) {
		veiculoService.remover(veiculo.getId()); 
		addSuccessMessage("Removido com sucesso");
	}
	public Veiculo getVeiculoSelecionado() {
		return veiculoSelecionado;
	}

	public void setVeiculoSelecionado(Veiculo veiculoSelecionado) {
		this.veiculoSelecionado = veiculoSelecionado;
	}
	public LazyDataModelGeneric<Veiculo> getVeiculosLazy() {
		return veiculosLazy;
	}

	public void setVeiculosLazy(LazyDataModelGeneric<Veiculo> veiculosLazy) {
		this.veiculosLazy = veiculosLazy;
	}
	public String redirecionarEditarVeiculo(Veiculo veiculo) {
		redirecionarObjetoFlash("veiculo", veiculo);
		return "cadastroVeiculo?faces-redirect=true";
	}
	
}
