package br.com.ada.view;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

import br.com.ada.model.Avaliacao;
import br.com.ada.support.Fachada;

@Scope(value = WebApplicationContext.SCOPE_REQUEST)
@Named(value = "avaliacaoListMB")
public class AvaliacaoListMB {

	@Inject
	private Fachada fachada;

	private List<Avaliacao> listaAvaliacao;

	@PostConstruct
	private void init() {
		List<Avaliacao> listaAv = fachada.listarAvaliacao();
		Collections.reverse(listaAv);
		setListaAvaliacao(listaAv);
	}

	private List<Avaliacao> listarAvaliacao() {
		List<Avaliacao> listaAv = fachada.listarAvaliacao();
		Collections.reverse(listaAv);
		return listaAv;
	}

	public void excluir(Avaliacao avaliacao) {
		fachada.excluirAvaliacao(avaliacao);
		setListaAvaliacao(listarAvaliacao());
	}

	public List<Avaliacao> getListaAvaliacao() {
		return listaAvaliacao;
	}

	public void setListaAvaliacao(List<Avaliacao> listaAvaliacao) {
		this.listaAvaliacao = listaAvaliacao;
	}

}
