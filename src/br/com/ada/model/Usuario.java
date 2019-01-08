package br.com.ada.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario extends EntidadeBase<Long> {

	private static final long serialVersionUID = 1L;

	@Column(name = "nomeFantasia", length = 200 ,nullable = true)
	private String nomeFantasia;

	@Column(name = "razaoSocial", length = 200, nullable = true)
	private String razaoSocial;
	
	@Column(name = "cnpj", length = 200, nullable = true)
	private String cnpj;

	@Column(name = "endereco", length = 200, nullable = true)
	private String endereco;
	
	@Column(name = "telefone", length = 200, nullable = true)
	private String telefone;
	
	@Column(name = "login", length = 200, nullable = false)
	private String login;

	public Usuario() {

	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

}
