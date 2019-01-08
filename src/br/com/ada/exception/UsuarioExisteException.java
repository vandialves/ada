package br.com.ada.exception;

public class UsuarioExisteException extends Exception {

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Usuario ja cadastrado. Informe outro nome de usuario.";
	}
}
