package br.com.ada.view;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

import br.com.ada.exception.UsuarioExisteException;
import br.com.ada.model.Usuario;
import br.com.ada.support.Fachada;
import br.com.ada.support.Utils;

@Scope(value = WebApplicationContext.SCOPE_REQUEST)
@Named(value = "usuarioEditMB")
public class UsuarioEditMB {

	@Autowired
	private Fachada fachada;

	private Usuario usuario;

	@PostConstruct
	private void init() {
		Usuario tempUser = fachada.getBylogin(Utils.getCurrentUser());
		if(tempUser != null){
			setUsuario(tempUser);
		}
		else{
			usuario = new Usuario();
		}
	}

	public void preAlterar(Usuario usuario) {
		setUsuario(usuario);
	}

	public String salvar() {
		try {
			usuario.setLogin(Utils.getCurrentUser());
			fachada.salvarUsuario(usuario);
			return "success";
		} catch (UsuarioExisteException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
		return null;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
