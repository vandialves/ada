package br.com.ada.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

import br.com.ada.model.Avaliacao;
import br.com.ada.support.Fachada;

@Scope(value = WebApplicationContext.SCOPE_REQUEST)
@Named(value = "relatorioMB")
public class RelatorioMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Fachada fachada;

	private List<Avaliacao> listaAvaliacao;
	private LineChartModel lineModel1;
	private LineChartModel lineModel2;
	private LineChartModel lineModel3;
	private LineChartModel lineModel4;
	private LineChartModel lineModel5;
	private Calendar deDataRef;
	private Calendar ateDataRef; 
	private boolean relatorioCompleto;
	private String stat1;
	private String stat2;
	private String stat3;
	private String stat4;
	private String stat5;
	private String mmax1;
	private String mmax2;
	private String mmax3;
	private String mmax4;
	private String mmax5;
	private String mmin1;
	private String mmin2;
	private String mmin3;
	private String mmin4;
	private String mmin5;

	@PostConstruct
	private void init() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		relatorioCompleto = (boolean) ec.getRequestMap().get("relatorioCompleto");
		if(!isRelatorioCompleto()){
			deDataRef = (Calendar) ec.getRequestMap().get("deData");
			ateDataRef = (Calendar) ec.getRequestMap().get("ateData");				
		}
		listaAvaliacao = fachada.listarAvaliacao();
		createLineModels();
	}

	private void createLineModels() {
		ChartSeries series1 = new ChartSeries();
		ChartSeries series2 = new ChartSeries();
		ChartSeries series3 = new ChartSeries();
		ChartSeries series4 = new ChartSeries();
		ChartSeries series5 = new ChartSeries();
		double max1 = 0.0;
		double max2 = 0.0;
		double max3 = 0.0;
		double max4 = 0.0;
		double max5 = 0.0;
		double min1 = 0.0;
		double min2 = 0.0;
		double min3 = 0.0;
		double min4 = 0.0;
		double min5 = 0.0;
		double mean1 = 0.0;
		double mean2 = 0.0;
		double mean3 = 0.0;
		double mean4 = 0.0;
		double mean5 = 0.0;
		double cont1 = 0.0;
		double cont2 = 0.0;
		double cont3 = 0.0;
		double cont4 = 0.0;
		double cont5 = 0.0;

		List<Avaliacao> minhaLista;
		
		if(!isRelatorioCompleto()){
			List<Avaliacao> listaTemp = new ArrayList<Avaliacao>();
			for (Avaliacao avaliacao : listaAvaliacao) {
				if(avaliacao.getData().compareTo(ateDataRef) <= 0 && avaliacao.getData().compareTo(deDataRef) >= 0){
					listaTemp.add(avaliacao);
				}
			}
			minhaLista = listaTemp;
		}
		else{
			minhaLista = getListaAvaliacao();
		}
		
		Collections.sort(minhaLista, new Comparator<Avaliacao>() {
			@Override
			public int compare(Avaliacao o1, Avaliacao o2) {
				return o1.getData().compareTo(o2.getData());
			}
		});

		for (Avaliacao avaliacao : minhaLista) {
			//Consumo de lenha
			if(avaliacao.getClenha() != 0.0){
				mean1 = mean1 + avaliacao.getClenha();
				cont1 = cont1 + 1.0;
				series1.set(avaliacao.getMesRef()+"/"+avaliacao.getAnoRef(),avaliacao.getClenha());

				if(min1 == 0.0) { //primeira vez
					min1 = avaliacao.getClenha();
				}
				else{
					if(avaliacao.getClenha() < min1){
						min1 = avaliacao.getClenha();
					}
				}

				if(avaliacao.getClenha() > max1) {
					max1 = avaliacao.getClenha();
				}
			}
			
			//Consumo de agua
			if(avaliacao.getcH2O() != 0.0){
				mean2 = mean2 + avaliacao.getcH2O();
				cont2 = cont2 + 1.0;
				series2.set(avaliacao.getMesRef()+"/"+avaliacao.getAnoRef(),avaliacao.getcH2O());

				if(min2 == 0.0) { //primeira vez
					min2 = avaliacao.getcH2O();
				}
				else{
					if(avaliacao.getcH2O() < min2){
						min2 = avaliacao.getcH2O();
					}
				}

				if(avaliacao.getcH2O() > max2) {
					max2 = avaliacao.getcH2O();
				}
			}
			
			//Consumo de corante
			if(avaliacao.getCc() != 0.0){
				mean3 = mean3 + avaliacao.getCc();
				cont3 = cont3 + 1.0;
				series3.set(avaliacao.getMesRef()+"/"+avaliacao.getAnoRef(),avaliacao.getCc());

				if(min3 == 0.0) { //primeira vez
					min3 = avaliacao.getCc();
				}
				else{
					if(avaliacao.getCc() < min3){
						min3 = avaliacao.getCc();
					}
				}

				if(avaliacao.getCc() > max3) {
					max3 = avaliacao.getCc();
				}
			}
			
			//Quantidade de Lodo
			if(avaliacao.getCl() != 0.0){
				mean4 = mean4 + avaliacao.getCl();
				cont4 = cont4 + 1.0;
				series4.set(avaliacao.getMesRef()+"/"+avaliacao.getAnoRef(),avaliacao.getCl());

				if(min4 == 0.0) { //primeira vez
					min4 = avaliacao.getCl();
				}
				else{
					if(avaliacao.getCl() < min4){
						min4 = avaliacao.getCl();
					}
				}

				if(avaliacao.getCl() > max4) {
					max4 = avaliacao.getCl();
				}
			}
			
			//Quantidade de Cinzas
			if(avaliacao.getCci() != 0.0){
				mean5 = mean5 + avaliacao.getCci();
				cont5 = cont5 + 1.0;
				series5.set(avaliacao.getMesRef()+"/"+avaliacao.getAnoRef(),avaliacao.getCci());

				if(min5 == 0.0) { //primeira vez
					min5 = avaliacao.getCci();
				}
				else{
					if(avaliacao.getCci() < min5){
						min5 = avaliacao.getCci();
					}
				}

				if(avaliacao.getCci() > max5) {
					max5 = avaliacao.getCci();
				}
			}
		}

		if(cont1 > 0.0){
			mean1 = mean1/cont1;
		}
		if(cont2 > 0.0){
			mean2 = mean2/cont2;
		}
		if(cont3 > 0.0){
			mean3 = mean3/cont3;
		}
		if(cont4 > 0.0){
			mean4 = mean4/cont4;
		}
		if(cont5 > 0.0){
			mean5 = mean5/cont5;
		}

		lineModel1 = new LineChartModel();
		lineModel1.addSeries(series1);
		lineModel1.setTitle("Consumo de Lenha");
		lineModel1.setShowPointLabels(true);
		lineModel1.setZoom(false);
		lineModel1.getAxes().put(AxisType.X, new CategoryAxis(""));
		Axis yAxis1 = lineModel1.getAxis(AxisType.Y);
		yAxis1.setMin(min1-1.0);
		yAxis1.setMax(max1+1.0);

		lineModel2 = new LineChartModel();
		lineModel2.addSeries(series2);
		lineModel2.setTitle("Consumo de Água");
		lineModel2.setShowPointLabels(true);
		lineModel2.setZoom(false);
		lineModel2.getAxes().put(AxisType.X, new CategoryAxis(""));
		Axis yAxis2 = lineModel2.getAxis(AxisType.Y);
		yAxis2.setMin(min2-1.0);
		yAxis2.setMax(max2+1.0);

		lineModel3 = new LineChartModel();
		lineModel3.addSeries(series3);
		lineModel3.setTitle("Consumo de Corante");
		lineModel3.setShowPointLabels(true);
		lineModel3.setZoom(false);
		lineModel3.getAxes().put(AxisType.X, new CategoryAxis(""));
		Axis yAxis3 = lineModel3.getAxis(AxisType.Y);
		yAxis3.setMin(min3-1.0);
		yAxis3.setMax(max3+1.0);

		lineModel4 = new LineChartModel();
		lineModel4.addSeries(series4);
		lineModel4.setTitle("Quantidade de Lodo");
		lineModel4.setShowPointLabels(true);
		lineModel4.setZoom(false);
		lineModel4.getAxes().put(AxisType.X, new CategoryAxis(""));
		Axis yAxis4 = lineModel4.getAxis(AxisType.Y);
		yAxis4.setMin(min4-1.0);
		yAxis4.setMax(max4+1.0);

		lineModel5 = new LineChartModel();
		lineModel5.addSeries(series5);
		lineModel5.setTitle("Quantidade de Cinzas");
		lineModel5.setShowPointLabels(true);
		lineModel5.setZoom(false);
		lineModel5.getAxes().put(AxisType.X, new CategoryAxis(""));
		Axis yAxis5 = lineModel5.getAxis(AxisType.Y);
		yAxis5.setMin(min5-1.0);
		yAxis5.setMax(max5+1.0);
		
		stat1 = "Média: "+String.valueOf(mean1);
		mmin1 = "Valor Mínimo: "+String.valueOf(min1);
		mmax1 = " Valor Máximo: "+String.valueOf(max1);
		stat2 = "Média: "+String.valueOf(mean2);
		mmin2 = "Valor Mínimo: "+String.valueOf(min2);
		mmax2 = " Valor Máximo: "+String.valueOf(max2);
		stat3 = "Média: "+String.valueOf(mean3);
		mmin3 = "Valor Mínimo: "+String.valueOf(min3);
		mmax3 = " Valor Máximo: "+String.valueOf(max3);
		stat4 = "Média: "+String.valueOf(mean4);
		mmin4 = "Valor Mínimo: "+String.valueOf(min4);
		mmax4 = " Valor Máximo: "+String.valueOf(max4);
		stat5 = "Média: "+String.valueOf(max5);
		mmin5 = "Valor Mínimo: "+String.valueOf(min5);
		mmax5 = " Valor Máximo: "+String.valueOf(max5);
	}

	public List<Avaliacao> getListaAvaliacao() {
		return listaAvaliacao;
	}

	public void setListaAvaliacao(List<Avaliacao> listaAvaliacao) {
		this.listaAvaliacao = listaAvaliacao;
	}

	public LineChartModel getLineModel1() {
		return lineModel1;
	}

	public void setLineModel1(LineChartModel lineModel1) {
		this.lineModel1 = lineModel1;
	}

	public LineChartModel getLineModel2() {
		return lineModel2;
	}

	public void setLineModel2(LineChartModel lineModel2) {
		this.lineModel2 = lineModel2;
	}

	public Calendar getDeDataRef() {
		return deDataRef;
	}

	public void setDeDataRef(Calendar deDataRef) {
		this.deDataRef = deDataRef;
	}

	public Calendar getAteDataRef() {
		return ateDataRef;
	}

	public void setAteDataRef(Calendar ateDataRef) {
		this.ateDataRef = ateDataRef;
	}

	public LineChartModel getLineModel3() {
		return lineModel3;
	}

	public void setLineModel3(LineChartModel lineModel3) {
		this.lineModel3 = lineModel3;
	}

	public LineChartModel getLineModel4() {
		return lineModel4;
	}

	public void setLineModel4(LineChartModel lineModel4) {
		this.lineModel4 = lineModel4;
	}

	public LineChartModel getLineModel5() {
		return lineModel5;
	}

	public void setLineModel5(LineChartModel lineModel5) {
		this.lineModel5 = lineModel5;
	}

	public boolean isRelatorioCompleto() {
		return relatorioCompleto;
	}

	public void setRelatorioCompleto(boolean relatorioCompleto) {
		this.relatorioCompleto = relatorioCompleto;
	}

	public String getStat1() {
		return stat1;
	}

	public void setStat1(String stat1) {
		this.stat1 = stat1;
	}

	public String getStat2() {
		return stat2;
	}

	public void setStat2(String stat2) {
		this.stat2 = stat2;
	}

	public String getStat3() {
		return stat3;
	}

	public void setStat3(String stat3) {
		this.stat3 = stat3;
	}

	public String getStat4() {
		return stat4;
	}

	public void setStat4(String stat4) {
		this.stat4 = stat4;
	}

	public String getStat5() {
		return stat5;
	}

	public void setStat5(String stat5) {
		this.stat5 = stat5;
	}

	public String getMmax1() {
		return mmax1;
	}

	public void setMmax1(String mmax1) {
		this.mmax1 = mmax1;
	}

	public String getMmax2() {
		return mmax2;
	}

	public void setMmax2(String mmax2) {
		this.mmax2 = mmax2;
	}

	public String getMmax3() {
		return mmax3;
	}

	public void setMmax3(String mmax3) {
		this.mmax3 = mmax3;
	}

	public String getMmax4() {
		return mmax4;
	}

	public void setMmax4(String mmax4) {
		this.mmax4 = mmax4;
	}

	public String getMmax5() {
		return mmax5;
	}

	public void setMmax5(String mmax5) {
		this.mmax5 = mmax5;
	}

	public String getMmin1() {
		return mmin1;
	}

	public void setMmin1(String mmin1) {
		this.mmin1 = mmin1;
	}

	public String getMmin2() {
		return mmin2;
	}

	public void setMmin2(String mmin2) {
		this.mmin2 = mmin2;
	}

	public String getMmin3() {
		return mmin3;
	}

	public void setMmin3(String mmin3) {
		this.mmin3 = mmin3;
	}

	public String getMmin4() {
		return mmin4;
	}

	public void setMmin4(String mmin4) {
		this.mmin4 = mmin4;
	}

	public String getMmin5() {
		return mmin5;
	}

	public void setMmin5(String mmin5) {
		this.mmin5 = mmin5;
	}

}
