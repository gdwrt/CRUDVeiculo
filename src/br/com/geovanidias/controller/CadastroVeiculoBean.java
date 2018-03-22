package br.com.geovanidias.controller;

import static br.com.geovanidias.util.jsf.FacesUtil.addErrorMessage;
import static br.com.geovanidias.util.jsf.FacesUtil.addSuccessMessage;
import static br.com.geovanidias.util.jsf.FacesUtil.recuperarObjetoFlash;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.geovanidias.model.Veiculo;
import br.com.geovanidias.service.BusinessException;
import br.com.geovanidias.service.VeiculoService;

@Named
@ViewScoped
public class CadastroVeiculoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private @Inject VeiculoService veiculoService;
	private Veiculo veiculo;

	public void salvar() {
		try {
			veiculoService.salvar(veiculo);
			if(veiculo.getId() == null) {
				addSuccessMessage("Cadastrado com sucesso!");
			} else {
				addSuccessMessage("Alterado com sucesso!");
			}	
			novo();
		} catch (BusinessException e) {
			addErrorMessage(e.getMessage());
		}
	}

	public void novo() {
		veiculo = new Veiculo();
	}
 
	public void alterar() {
		Veiculo veiculo = (Veiculo)recuperarObjetoFlash("veiculo");
		if(veiculo != null) {
			this.veiculo = veiculo;
		}
	}
	
	public Veiculo getVeiculo() {
		if (veiculo == null) { veiculo = new Veiculo(); }
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
}
