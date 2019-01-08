package br.com.ada.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ada.model.Usuario;

public interface IUsuarioDAO extends JpaRepository<Usuario, Long> {

	public Usuario findBylogin(String login);

}
