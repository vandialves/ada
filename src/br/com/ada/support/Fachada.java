package br.com.ada.support;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ada.controller.AvaliacaoBC;
import br.com.ada.controller.UsuarioBC;
import br.com.ada.exception.AvaliacaoExisteException;
import br.com.ada.exception.UsuarioExisteException;
import br.com.ada.model.Avaliacao;
import br.com.ada.model.Usuario;

@Component
public class Fachada {

	@Autowired
	private UsuarioBC usuarioBC;

	public void salvarUsuario(Usuario usuario) throws UsuarioExisteException {
		usuarioBC.salvarUsuario(usuario);
	}

	public void excluirUsuario(Usuario usuario) {
		usuarioBC.excluirUsuario(usuario);
	}

	public List<Usuario> listarUsuario() {
		return usuarioBC.listarUsuario();
	}
	
	public Usuario getBylogin(String login){
		return usuarioBC.getUsuarioBylogin(login);
	}
	
	@Autowired
	private AvaliacaoBC avaliacaoBC;
	
	public void salvarAvaliacao(Avaliacao avaliacao) throws AvaliacaoExisteException {
		avaliacaoBC.salvarAvaliacao(avaliacao);
	}
	
	public void excluirAvaliacao(Avaliacao avaliacao) {
		avaliacaoBC.excluirAvaliacao(avaliacao);
	}
	
	public List<Avaliacao> listarAvaliacao() {
		List<Avaliacao> lista = avaliacaoBC.listarAvaliacao();
		if(lista != null){
			if(lista.size() > 0){
				Collections.sort(lista, new Comparator<Avaliacao>() {
					@Override
					public int compare(Avaliacao o1, Avaliacao o2) {
						return o1.getData().compareTo(o2.getData());
					}
				});
			}
		}
		
		return lista;
	}

}
