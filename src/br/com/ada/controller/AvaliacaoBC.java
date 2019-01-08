package br.com.ada.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.ada.exception.AvaliacaoExisteException;
import br.com.ada.model.Avaliacao;
import br.com.ada.model.repository.IAvaliacaoDAO;
import br.com.ada.support.Utils;

@Controller
public class AvaliacaoBC {

	@Autowired
	private IAvaliacaoDAO dao;

	public void salvarAvaliacao(Avaliacao avaliacao) throws AvaliacaoExisteException {
		boolean avaliacaoValida = true;
		String currentUserName = Utils.getCurrentUser();
		avaliacao.setRazaoSocial(currentUserName);
		Date data = avaliacao.getDataRef();
		if(data == null){
			data = new Date();
			avaliacao.setDataRef(data);
		}

		List<Avaliacao> lista = dao.findByanoRef(avaliacao.getAnoRef());
		for (Avaliacao a : lista) {
			if (a != null && !a.equals(avaliacao)) {
				if(a.getMesRef().equals(avaliacao.getMesRef()) && a.getRazaoSocial().equals(avaliacao.getRazaoSocial())){
					avaliacaoValida = false;
					throw new AvaliacaoExisteException();
				}
			}
		}

		if(avaliacaoValida){
			avaliacao = setaCalendario(avaliacao);
			avaliacao = calcularIndicadores(avaliacao);
			dao.save(avaliacao);
		}

	}

	public void excluirAvaliacao(Avaliacao avaliacao) {
		dao.delete(avaliacao);
	}

	public List<Avaliacao> listarAvaliacao() {
		String user = Utils.getCurrentUser();
		return dao.findByrazaoSocial(user);
	}

	private Avaliacao setaCalendario(Avaliacao ava){
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(Integer.parseInt(ava.getAnoRef()), getMes(ava.getMesRef()), Calendar.DATE);
		ava.setData(cal);
		return ava;
	}

	private int getMes(String mes){
		if(mes.equals("Janeiro")){
			return 0;
		}
		else if(mes.equals("Fevereiro")){
			return 1;
		}
		else if(mes.equals("Março")){
			return 2;
		}
		else if(mes.equals("Abril")){
			return 3;
		}
		else if(mes.equals("Maio")){
			return 4;
		}
		else if(mes.equals("Junho")){
			return 5;
		}
		else if(mes.equals("Julho")){
			return 6;
		}
		else if(mes.equals("Agosto")){
			return 7;
		}
		else if(mes.equals("Setembro")){
			return 8;
		}
		else if(mes.equals("Outubro")){
			return 9;
		}
		else if(mes.equals("Novembro")){
			return 10;
		}
		else{
			return 11;
		}
	}

	private Avaliacao calcularIndicadores(Avaliacao ava){
		if(ava.getQtdLenha() != 0.0){
			ava.setClenha(ava.getQtdLenha()/ava.getQtdPecas());
		}

		if(ava.getQtdAgua() != 0.0){
			ava.setcH2O(ava.getQtdAgua()/ava.getQtdPecas());
		}

		if(ava.getQtdCorante() != 0.0 && ava.getQtdAgua() != 0.0){
			ava.setCc(ava.getQtdCorante()/ava.getQtdAgua());
		}

		if(ava.getQtdLodo() != 0.0){
			ava.setCl(ava.getQtdLodo()/ava.getQtdPecas());
		}

		if(ava.getQtdCinzas() != 0.0){
			ava.setCci(ava.getQtdCinzas()/ava.getQtdPecas());
		}
		return ava;
	}

}
