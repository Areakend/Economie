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

	public Environment() {
		this.price_s = new ArrayList<Double>();
		this.price_s.add(0.0);
        this.price_s.add(0.0);
		this.am = 1;
		this.tax = 0.0;
		this.F = 0;
	}

	public Environment(ArrayList<Double> price_s, double am, double tax, double f) {
		this.price_s = price_s;
		this.am = am;
		this.tax = tax;
		this.F = f;
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

	public void update(Agent[] agent, int numero_marche, int nb_agent, int indice) {
		Double somme = 0.0;
		for (int i = 0; i < nb_agent-1; i++) {
			if (agent[i].getMarche()-1 == numero_marche) {
				somme = somme + agent[i].getOrderI(indice-1) * this.am;
			}
		}
		this.addPrice_s(somme + this.price_s.get(indice - 1));
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
