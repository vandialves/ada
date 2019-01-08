package br.com.ada.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.ada.exception.UsuarioExisteException;
import br.com.ada.model.Usuario;
import br.com.ada.model.repository.IUsuarioDAO;

@Controller
public class UsuarioBC {

	@Autowired
	private IUsuarioDAO dao;

	public void salvarUsuario(Usuario usuario) throws UsuarioExisteException {
		Usuario a = dao.findBylogin(usuario.getLogin());
		if (a != null && !a.equals(usuario)) {
			throw new UsuarioExisteException();
		} else {
			dao.save(usuario);
		}
	}

	public void excluirUsuario(Usuario usuario) {
		dao.delete(usuario);
	}

	public List<Usuario> listarUsuario() {
		return dao.findAll();
	}
	
	public Usuario getUsuarioBylogin(String login) {
		return dao.findBylogin(login);
	}

}
