package br.com.ada.view;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

import br.com.ada.model.Avaliacao;
import br.com.ada.support.Fachada;

@Scope(value = WebApplicationContext.SCOPE_REQUEST)
@Named(value = "avaliacaoReviewMB")
public class AvaliacaoReviewMB {

	@Autowired
	private Fachada fachada;

	private Avaliacao avaliacao;

	private String vazio = "";

	@PostConstruct
	private void init() {
		boolean novaAv = false;
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		Object test = ec.getRequestMap().get("novaAv");
		if(null != test){
			novaAv = (boolean) test;
		}

		if(novaAv){
			ec.getRequestMap().put("novaAv", false);
			this.setVazio("naovazio");
			this.avaliacao = (Avaliacao) ec.getRequestMap().get("Av");
		}
		else{
			List<Avaliacao> lista = fachada.listarAvaliacao();
			if(lista != null){
				if(lista.size() > 0){
					Collections.sort(lista, new Comparator<Avaliacao>() {
						@Override
						public int compare(Avaliacao o1, Avaliacao o2) {
							return o1.getData().compareTo(o2.getData());
						}
					});
					this.setVazio("naovazio");
					this.avaliacao = lista.get(lista.size()-1);
				}
			}
		}

	}

	public void preRevisar(Avaliacao avaliacao) {
		setAvaliacao(avaliacao);
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public String getVazio() {
		return vazio;
	}

	public void setVazio(String vazio) {
		this.vazio = vazio;
	}

}
