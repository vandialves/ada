package br.com.ada.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ada.model.User;

public interface IUserDAO extends JpaRepository<User, Long> {

	public User findByLoginAndPassword(String username, String password);

}
