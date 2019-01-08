package br.com.ada.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "avaliacao")
public class Avaliacao extends EntidadeBase<Long> {

	private static final long serialVersionUID = 1L;

	@Column(name = "razaoSocial", length = 200, nullable = false)
	private String razaoSocial;
	
	@Column(name = "mesRef", length = 100, nullable = false)
	private String mesRef;
	
	@Column(name = "anoRef", length = 4, nullable = false)
	private String anoRef;
	
	@Column(name = "data", nullable = false)
	private Calendar data;

	@Column(name = "dataRef", nullable = false)
	private Date dataRef;

	@Column(name = "qtdPecas", nullable = false)
	private int qtdPecas;
	
	@Column(name = "qtdLenha", nullable = true)
	private double qtdLenha;
	
	@Column(name = "qtdAgua", nullable = true)
	private double qtdAgua;
	
	@Column(name = "qtdCorante", nullable = true)
	private double qtdCorante;
	
	@Column(name = "qtdLodo", nullable = true)
	private double qtdLodo;
	
	@Column(name = "qtdCinzas", nullable = true)
	private double qtdCinzas;
	
	@Column(name = "clenha", nullable = true)
	private double clenha;
	
	@Column(name = "cH2O", nullable = true)
	private double cH2O;
	
	@Column(name = "cc", nullable = true)
	private double cc;
	
	@Column(name = "cl", nullable = true)
	private double cl;
	
	@Column(name = "cci", nullable = true)
	private double cci;

	public Avaliacao() {

	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public Date getDataRef() {
		return dataRef;
	}

	public void setDataRef(Date dataRef) {
		this.dataRef = dataRef;
	}

	public int getQtdPecas() {
		return qtdPecas;
	}

	public void setQtdPecas(int qtdPecas) {
		this.qtdPecas = qtdPecas;
	}

	public double getQtdLenha() {
		return qtdLenha;
	}

	public void setQtdLenha(double qtdLenha) {
		this.qtdLenha = qtdLenha;
	}

	public double getQtdAgua() {
		return qtdAgua;
	}

	public void setQtdAgua(double qtdAgua) {
		this.qtdAgua = qtdAgua;
	}

	public double getQtdCorante() {
		return qtdCorante;
	}

	public void setQtdCorante(double qtdCorante) {
		this.qtdCorante = qtdCorante;
	}

	public double getQtdLodo() {
		return qtdLodo;
	}

	public void setQtdLodo(double qtdLodo) {
		this.qtdLodo = qtdLodo;
	}

	public double getQtdCinzas() {
		return qtdCinzas;
	}

	public void setQtdCinzas(double qtdCinzas) {
		this.qtdCinzas = qtdCinzas;
	}

	public double getClenha() {
		return clenha;
	}

	public void setClenha(double clenha) {
		this.clenha = clenha;
	}

	public double getcH2O() {
		return cH2O;
	}

	public void setcH2O(double cH2O) {
		this.cH2O = cH2O;
	}

	public double getCc() {
		return cc;
	}

	public void setCc(double cc) {
		this.cc = cc;
	}

	public double getCl() {
		return cl;
	}

	public void setCl(double cl) {
		this.cl = cl;
	}

	public double getCci() {
		return cci;
	}

	public void setCci(double cci) {
		this.cci = cci;
	}

	public String getMesRef() {
		return mesRef;
	}

	public void setMesRef(String mesRef) {
		this.mesRef = mesRef;
	}

	public String getAnoRef() {
		return anoRef;
	}

	public void setAnoRef(String anoRef) {
		this.anoRef = anoRef;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

}
