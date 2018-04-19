package com.company;

import javafx.scene.chart.XYChart;

import java.util.ArrayList;

/**
 * Created by alexa on 23/02/2018.
 */
public class Environment{
	private ArrayList<Double> price_s;
	private ArrayList<Double> order_c;
	private ArrayList<Double> order_f;
	private ArrayList<Double> a_c;
	private ArrayList<Double> a_f;




	private double am;
	private double tax;
	private double F;
	private double sigma_c;
	private double sigma_f;
	private ArrayList<Integer> nb_agent_f;
	private ArrayList<Integer> nb_agent_c;

	public Environment() {
		this.price_s = new ArrayList<Double>();
		this.price_s.add(0.0);
		this.price_s.add(0.0);
		this.am = 1;
		this.tax = 0.0;
		this.F = 0;
		this.sigma_c = 0.05;
		this.sigma_f = 0.01;
		this.nb_agent_c = new ArrayList<Integer>();
		this.nb_agent_f = new ArrayList<Integer>();
	}


	public Environment(ArrayList<Double> price_s, double am, double tax, double f, double sigma_c, double sigma_f) {
		this.price_s = price_s;
		this.am = am;
		this.tax = tax;
		this.F = f;
		this.sigma_c = sigma_c;
		this.sigma_f = sigma_f;
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


	public ArrayList<Double> getOrder_c() {
		return order_c;
	}

	public void setOrder_c(ArrayList<Double> order_c) {
		this.order_c = order_c;
	}

	public ArrayList<Double> getOrder_f() {
		return order_f;
	}

	public void setOrder_f(ArrayList<Double> order_f) {
		this.order_f = order_f;
	}

	public ArrayList<Double> getA_c() {
		return a_c;
	}

	public void setA_c(ArrayList<Double> a_c) {
		this.a_c = a_c;
	}

	public ArrayList<Double> getA_f() {
		return a_f;
	}

	public void setA_f(ArrayList<Double> a_f) {
		this.a_f = a_f;
	}

	public double getSigma_c() {
		return sigma_c;
	}

	public void setSigma_c(double sigma_c) {
		this.sigma_c = sigma_c;
	}

	public double getSigma_f() {
		return sigma_f;
	}

	public void setSigma_f(double sigma_f) {
		this.sigma_f = sigma_f;
	}

	public void addNb_agent_c(int valeur) {
		this.nb_agent_c.add(valeur);
	}

	public void getFirstweight(Agent[] agent, int nb_agent, int marche){
		int nb_agentf = 0;
		int nb_agentc = 0;
		for(int i=0; i< nb_agent; i++){
			if(agent[i].getMarche()==marche+1) {
				if (agent[i].getState().equals("actif")) {
					if (agent[i].getType() == "fundamental") {
						nb_agentf = nb_agentf + 1;
					} else {
						nb_agentc = nb_agentc + 1;
					}
				}
			}
		}
		this.addNb_agent_c(nb_agentc);
		this.addNb_agent_c(nb_agentc);
		this.addNb_agent_f(nb_agentf);
		this.addNb_agent_f(nb_agentf);
	}


	public void update(Agent[] agent, int numero_marche, int nb_agent, int indice) {
		Double somme = 0.0;
		int nb_agentf = 0;
		int nb_agentc = 0;
		for (int i = 0; i < nb_agent; i++) {
			if (agent[i].getMarche()-1 == numero_marche) {
				if(agent[i].getState().equals("actif")) {
					somme = somme + agent[i].getOrderI(indice - 1) * this.am;
					if (agent[i].getType() == "fundamental") {
						nb_agentf = nb_agentf + 1;
					} else {
						nb_agentc = nb_agentc + 1;
					}
				}
			}
		}
		this.addPrice_s(somme + this.price_s.get(indice - 1));
		this.addNb_agent_c(nb_agentc);
		this.addNb_agent_f(nb_agentf);
	}



}
