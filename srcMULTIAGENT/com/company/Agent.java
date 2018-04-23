package com.company;

import org.jfree.data.xy.XYDataset;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by alexa on 23/02/2018.
 */
public class Agent {
	private String type;
	private int marche;
	private String state;

	private ArrayList<Double> order;
	private ArrayList<ArrayList<Double>> fitness;


	private double rationality_c;
	private double acf;
	private double memory_b;

	public Agent(int nbMarche, String type){
		this.type = type;
		if(type.equals("technical")){
			this.acf = 0.05;
		}else{
			this.acf = 0.05;
		}
		int choix = (int)( Math.random()*nbMarche);
		this.marche = choix + 1;
		this.order = new ArrayList<Double>();
		this.order.add(0.0);
		this.order.add(0.0);
		this.fitness = new ArrayList<ArrayList<Double>>();
		ArrayList<Double> fit = new ArrayList<Double>();
		for (int i=0; i<2*nbMarche; i++){
			fit.add(0.0);
		}
		this.fitness.add(fit);
		this.rationality_c = 300.0;
		this.memory_b = 0.975;
		this.state = "actif";

	}

	public Agent(int nbMarche, double memory_b){
		double valeur = Math.random() * 100;
		if (valeur < 50) {
			this.type = "technical";
			this.acf = 0.05;
		} else if (valeur < 101) {
			this.type = "fundamental";
			this.acf = 0.05;
		}
		int choix = (int)( Math.random()*nbMarche);
		this.marche = choix + 1;

		this.order = new ArrayList<Double>();
		this.order.add(0.0);
		this.order.add(0.0);
		this.fitness = new ArrayList<ArrayList<Double>>();
		ArrayList<Double> fit = new ArrayList<Double>();
		for (int i=0; i<2*nbMarche; i++){
			fit.add(0.0);
		}
		this.fitness.add(fit);
		this.fitness.add(fit);
		this.rationality_c = 300.0;
		this.memory_b = memory_b;
		this.state = "actif";

	}

	public Agent(int nbMarche) {
		double valeur = Math.random() * 100;
		if (valeur < 50) {
		this.type = "technical";
		this.acf = 0.05;
	} else if (valeur < 101) {
		this.type = "fundamental";
		this.acf = 0.05;
	}
	int choix = (int)( Math.random()*nbMarche);
		this.marche = choix + 1;

		this.order = new ArrayList<Double>();
		this.order.add(0.0);
		this.order.add(0.0);
		this.fitness = new ArrayList<ArrayList<Double>>();
	ArrayList<Double> fit = new ArrayList<Double>();
		for (int i=0; i<2*nbMarche; i++){
		fit.add(0.0);
	}
		this.fitness.add(fit);
		this.fitness.add(fit);
		this.rationality_c = 300.0;
		this.memory_b = 0.975;
		this.state = "actif";
}

	public Agent(String type, int marche, ArrayList<Double> order, ArrayList<Double> fit, double rationality_c,
			double acf, double memory_b, String state) {
		this.type = type;
		this.marche = marche;
		this.order = order;
		this.fitness = new ArrayList<ArrayList<Double>>();
		this.fitness.add(fit);
		this.rationality_c = rationality_c;
		this.acf = acf;
		this.memory_b = memory_b;
		this.state = state;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState(){ return state; }

	public void setState(String state) { this.state = state;}

	public int getMarche() {
		return marche;
	}

	public void setMarche(int marche) {
		this.marche = marche;
	}

	public ArrayList<Double> getOrder() {
		return order;
	}

	public Double getOrderI(int i) {
		return order.get(i);
	}

	public void setOrder(ArrayList<Double> order) {
		this.order = order;
	}

	public void setOrder(int indice, Double valeur) {
		this.order.set(indice, valeur);
	}

	public void addOrder(Double valeur) {
		this.order.add(valeur);
	}

	public ArrayList<ArrayList<Double>> getFitness() {
		return fitness;
	}

	public ArrayList<Double> getFitnessT(int indice) {
		return fitness.get(indice);
	}
	public Double getFitessTI(int indice, int i){
		return fitness.get(indice).get(i);
	}

	public void setFitness(ArrayList<ArrayList<Double>> fitness) {
		this.fitness = fitness;
	}

	public void setFitness(int indice, ArrayList<Double> valeur) {
		this.fitness.set(indice, valeur);
	}

	public void addFitness(ArrayList<Double> valeur) {
		this.fitness.add(valeur);
	}

	public double getRationality_c() {
		return rationality_c;
	}

	public void setRationality_c(double rationality_c) {
		this.rationality_c = rationality_c;
	}

	public double getAcf() {
		return acf;
	}

	public void setAcf(double acf) {
		this.acf = acf;
	}

	public double getMemory_b() {
		return memory_b;
	}

	public void setMemory_b(double memory_b) {
		this.memory_b = memory_b;
	}

	public void checkFitness(Environment[] marche, int nb_marche, int indice){
		ArrayList<Double> fit = new ArrayList<Double>();
		for(int i=0; i<nb_marche; i++){
			fit.add((Math.exp(marche[i].getPrice_sI(indice))-Math.exp(marche[i].getPrice_sI(indice-1)))*this.getOrderI(indice-2)
					- marche[i].getTax()*(Math.exp(marche[i].getPrice_sI(indice))
					+Math.exp(marche[i].getPrice_sI(indice-1)))*Math.abs(this.getOrderI(indice-2))
					+this.memory_b*this.getFitessTI(indice-1, i));
		}
		for(int i=0; i<nb_marche; i++){
			fit.add((Math.exp(marche[i].getPrice_sI(indice))-Math.exp(marche[i].getPrice_sI(indice-1)))*this.getOrderI(indice-2)
					- marche[i].getTax()*(Math.exp(marche[i].getPrice_sI(indice))
					+Math.exp(marche[i].getPrice_sI(indice-1)))*Math.abs(this.getOrderI(indice-2))
					+this.memory_b*this.getFitessTI(indice-1, i+nb_marche));
		}
		System.out.println(fit.get(0));
		System.out.println(fit.get(1));
		this.fitness.add(fit);
	}



	public void checkWeight(int nb_marche, int indice){
		Double somme = Math.exp(0);
		int i;
		for(i=0; i<2*nb_marche; i++){
			somme = somme + Math.exp(this.rationality_c * this.getFitessTI(indice, i));
		}
		Double r = Math.random();
		i=0;
		Double test = Math.exp(this.rationality_c*this.getFitessTI(indice,i)) / somme;
		boolean tester = false;
		while(!tester&&i<2*nb_marche-1){
			if(test>r){
				tester = true;
			}else{
				i = i+1;
				test = test+Math.exp(this.rationality_c*this.getFitessTI(indice,i)) / somme;
			}
		}
		if(test>r){
			tester=true;
		}
		if(!tester){
			this.setState("passif");
		}else {
			if (i <nb_marche) {
				this.setType("technical");
				this.setMarche(i + 1);
				this.setState("actif");
			} else {
				this.setType("fundamental");
				this.setMarche(i - nb_marche + 1);
				this.setState("actif");
			}
		}
	}

	public void checkFitnessMax(int nb_marche, int indice){
		Double test = this.getFitessTI(indice, 2*nb_marche-1);
		int testI = 2*nb_marche-1;
		for(int i =0; i<2*nb_marche-1; i++){
			System.out.println("test");
			System.out.println(test +"entre"+this.getFitessTI(indice, i));
			if(test<this.getFitessTI(indice, i)){
				test = this.getFitessTI(indice, i);
				testI = i;
				System.out.println("testVRAi");
			}
		}
		if (testI <nb_marche) {
			this.setType("technical");
			this.setMarche(testI + 1);
			this.setState("actif");
		} else {
			this.setType("fundamental");
			this.setMarche(testI - nb_marche + 1);
			this.setState("actif");
		}

	}

	public void update(Environment[] marche, int nb_marche,  int indice, String model) {
		switch (model) {
		case "model1":
			updateModel1(marche[this.getMarche()-1], indice);
			break;
		case "model2":
			updateModel1(marche[this.getMarche()-1], indice);
			break;
		case "model3":
			//changement d'etat avec les poids
			checkFitness(marche, nb_marche, indice);
			checkWeight(nb_marche, indice);
			updateModel1(marche[this.getMarche()-1], indice);
			break;
		case "model4":
			//changement d'etat avec la memoire
			checkFitness(marche, nb_marche, indice);
			checkWeight(nb_marche, indice);
			updateModel1(marche[this.getMarche()-1], indice);
			break;
		default:
			System.out.println("Model non defini");
		}
	}

	public void updateModel1(Environment marche, int indice) {
		Random r = new Random();
		if(this.state.equals("passif")){
			this.addOrder(0.0);
		}else {
			if (this.type == "technical") {
				this.addOrder((this.acf * (marche.getPrice_sI(indice) - marche.getPrice_sI(indice - 1)) + r.nextGaussian() * marche.getSigma_c()) / marche.getNb_agent_cI(indice - 1));

			} else if (this.type == "fundamental") {
				this.addOrder((this.acf * (marche.getF() - marche.getPrice_sI(indice)) + r.nextGaussian() * marche.getSigma_f()) / marche.getNb_agent_fI(indice - 1));
			} else {
				this.addOrder(0.0);
			}
		}
	}



}
