package br.com.ada.view;

import java.io.Serializable;
import java.util.Calendar;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

@Scope(value = WebApplicationContext.SCOPE_REQUEST)
@Named(value = "filterMB")
public class FilterRelatorioMB implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String deMes;
	private String deAno;
	private Calendar deData;
	private String ateMes;
	private String ateAno;
	private Calendar ateData;
	
	public String gerarCompleto() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.getRequestMap().put("relatorioCompleto", true);
        return "relatorio.xhtml";
	}
	
	public String gerarRelatorio() {
		deData = Calendar.getInstance();
		deData.clear();
		deData.set(Integer.parseInt(deAno), getMes(deMes), Calendar.DATE);
		ateData = Calendar.getInstance();
		ateData.clear();
		ateData.set(Integer.parseInt(ateAno), getMes(ateMes), Calendar.DATE);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.getRequestMap().put("deData", deData);
        ec.getRequestMap().put("ateData", ateData);
        ec.getRequestMap().put("relatorioCompleto", false);
        return "relatorio.xhtml";
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
	
	public String getDeMes() {
		return deMes;
	}
	public void setDeMes(String deMes) {
		this.deMes = deMes;
	}
	public String getDeAno() {
		return deAno;
	}
	public void setDeAno(String deAno) {
		this.deAno = deAno;
	}
	public Calendar getDeData() {
		return deData;
	}
	public void setDeData(Calendar deData) {
		this.deData = deData;
	}
	public String getAteMes() {
		return ateMes;
	}
	public void setAteMes(String ateMes) {
		this.ateMes = ateMes;
	}
	public String getAteAno() {
		return ateAno;
	}
	public void setAteAno(String ateAno) {
		this.ateAno = ateAno;
	}
	public Calendar getAteData() {
		return ateData;
	}
	public void setAteData(Calendar ateData) {
		this.ateData = ateData;
	}

}
