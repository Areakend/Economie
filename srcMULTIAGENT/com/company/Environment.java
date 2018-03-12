package com.company;

import javafx.scene.chart.XYChart;

import java.util.ArrayList;

/**
 * Created by alexa on 23/02/2018.
 */
public class Environment{
	private ArrayList<Double> price_s;

	private double am;
	private double tax;
	private double F;
	private ArrayList<Integer> nb_agent_f;
	private ArrayList<Integer> nb_agent_c;

	public Environment() {
		this.price_s = new ArrayList<Double>();
		this.price_s.add(0.0);
		this.price_s.add(0.0);
		this.am = 1;
		this.tax = 0.0;
		this.F = 0;
		this.nb_agent_c = new ArrayList<Integer>();
		this.nb_agent_c.add(0);
		this.nb_agent_f = new ArrayList<Integer>();
		this.nb_agent_f.add(0);
	}

	public Environment(ArrayList<Double> price_s, double am, double tax, double f) {
		this.price_s = price_s;
		this.am = am;
		this.tax = tax;
		this.F = f;
		this.nb_agent_c = new ArrayList<Integer>();
		this.nb_agent_f = new ArrayList<Integer>();
	}

	public ArrayList<Double> getPrice_s() {
		return price_s;
	}

	public Double getPrice_sI(int i) {
		return price_s.get(i);
	}

	public void setPrice_s(ArrayList<Double> price_s) {
		this.price_s = price_s;
	}

	public void setPrice_s(int indice, Double valeur) {
		this.price_s.set(indice, valeur);
	}

	public void addPrice_s(Double valeur) {
		this.price_s.add(valeur);
	}

	public double getAm() {
		return am;
	}

	public void setAm(double am) {
		this.am = am;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public double getF() {
		return F;
	}

	public void setF(double f) {
		F = f;
	}

	public ArrayList<Integer> getNb_agent_f() {
		return nb_agent_f;
	}

	public int getNb_agent_fI(int i){
		return nb_agent_f.get(i);
	}

	public void setNb_agent_f(ArrayList<Integer> nb_agent_f) {
		this.nb_agent_f = nb_agent_f;
	}

	public void setNb_agent_f(int indice, int valeur) {
		this.nb_agent_f.set(indice, valeur);
	}

	public void addNb_agent_f(int valeur) {
		this.nb_agent_f.add(valeur);
	}


	public ArrayList<Integer> getNb_agent_c() {
		return nb_agent_c;
	}

	public int getNb_agent_cI(int i){
		return nb_agent_c.get(i);
	}

	public void setNb_agent_c(ArrayList<Integer> nb_agent_c) {
		this.nb_agent_c = nb_agent_c;
	}

	public void setNb_agent_c(int indice, int valeur) {
		this.nb_agent_c.set(indice, valeur);
	}

	public void addNb_agent_c(int valeur) {
		this.nb_agent_c.add(valeur);
	}
	public void getFirstweight(Agent[] agent, int nb_agent){
		int nb_agentf = 0;
		int nb_agentc = 0;
		for(int i=0; i< nb_agent; i++){
				if(agent[i].getType()=="technical"){
					nb_agentf = nb_agentf +1;
				}else{
					nb_agentc = nb_agentc +1;
				}
		}
		this.addNb_agent_c(nb_agentc);
		this.addNb_agent_f(nb_agentf);
	}


	public void update(Agent[] agent, int numero_marche, int nb_agent, int indice) {
		Double somme = 0.0;
		int nb_agentf = 0;
		int nb_agentc = 0;
		for (int i = 0; i < nb_agent; i++) {
			if (agent[i].getMarche()-1 == numero_marche) {
				somme = somme + agent[i].getOrderI(indice-1) * this.am;
				if(agent[i].getType()=="technical"){
					nb_agentf = nb_agentf +1;
				}else{
					nb_agentc = nb_agentc +1;
				}
			}
		}
		this.addPrice_s(somme + this.price_s.get(indice - 1));
		this.addNb_agent_c(nb_agentc);
		this.addNb_agent_f(nb_agentf);
	}

	public XYChart.Series createData(){
		XYChart.Series serie = new XYChart.Series();
		return serie;
	}
	/*
	public void start(Stage s) {
		Stage stage = new Stage();
		NumberAxis xAxis = new NumberAxis();
		NumberAxis yAxis = new NumberAxis();
		LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
		lineChart.setTitle("coucou");

		XYChart.Series serie = new XYChart.Series();
		for (int i = 0; i < 2; i++) {
			serie.getData().add(new XYChart.Data(i, this.getPrice_sI(i)));
		}
		serie.setName("coucou");
		Scene sceneS1 = new Scene(lineChart, 800, 600);
		lineChart.getData().add(serie);
		lineChart.setCreateSymbols(false);
		stage.setScene(sceneS1);
		stage.show();
	}
	*/
}
