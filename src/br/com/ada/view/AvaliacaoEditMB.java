package br.com.ada.view;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

import br.com.ada.exception.AvaliacaoExisteException;
import br.com.ada.model.Avaliacao;
import br.com.ada.support.Fachada;

@Scope(value = WebApplicationContext.SCOPE_REQUEST)
@Named(value = "avaliacaoEditMB")
public class AvaliacaoEditMB {

	@Autowired
	private Fachada fachada;

	private Avaliacao avaliacao;

	@PostConstruct
	private void init() {
		avaliacao = new Avaliacao();
	}

	public void preAlterar(Avaliacao avaliacao) {
		setAvaliacao(avaliacao);
	}

	public String salvar() {
		try {
			fachada.salvarAvaliacao(avaliacao);
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	        ec.getRequestMap().put("novaAv", true);
	        ec.getRequestMap().put("Av", avaliacao);
			return "successavaliacao";
		} catch (AvaliacaoExisteException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
		return null;
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

}
