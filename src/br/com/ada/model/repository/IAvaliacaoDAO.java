package br.com.ada.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ada.model.Avaliacao;

public interface IAvaliacaoDAO extends JpaRepository<Avaliacao, Long> {

	public List<Avaliacao> findByanoRef(String ano);
	public List<Avaliacao> findByrazaoSocial(String razao);
}
