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

	private ArrayList<Double> order;
	private ArrayList<ArrayList<Double>> fitness;


	private double rationality_c;
	private double acf;
	private double memory_b;

	public Agent(int nbMarche, String test){
		this.type = "fundamental";
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
		this.acf = 0.05;
		this.memory_b = 0.975;

	}

	public Agent(int nbMarche) {
		double valeur = Math.random() * 100;
		if (valeur < 50) {
			this.type = "technical";
		} else if (valeur < 101) {
			this.type = "fundamental";
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
		this.acf = 0.05;
		this.memory_b = 0.975;
	}

	public Agent(String type, int marche, ArrayList<Double> order, ArrayList<Double> fit, double rationality_c,
			double acf, double memory_b) {
		this.type = type;
		this.marche = marche;
		this.order = order;
		this.fitness = new ArrayList<ArrayList<Double>>();
		this.fitness.add(fit);
		this.rationality_c = rationality_c;
		this.acf = acf;
		this.memory_b = memory_b;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

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
			System.out.println("coucou");
			System.out.println(Math.exp(marche[i].getPrice_sI(indice)));
		}
		for(int i=0; i<nb_marche; i++){
			fit.add((Math.exp(marche[i].getPrice_sI(indice))-Math.exp(marche[i].getPrice_sI(indice-1)))*this.getOrderI(indice-2)
					- marche[i].getTax()*(Math.exp(marche[i].getPrice_sI(indice))
					+Math.exp(marche[i].getPrice_sI(indice-1)))*Math.abs(this.getOrderI(indice-2))
					+this.memory_b*this.getFitessTI(indice-1, i+nb_marche));
		}
		this.fitness.add(fit);
	}

	public void checkWeight(int nb_marche, int indice){
		Double somme = Math.exp(0);
		int i;
		for(i=0; i<2*nb_marche; i++){
			somme = somme + Math.exp(this.rationality_c * this.getFitessTI(indice, i));
		}
//		System.out.println("Fitness vaut " + this.getFitessTI(indice, 0));
		Double r = Math.random();
		i=0;
		Double test = Math.exp(this.rationality_c*this.getFitessTI(indice,i)) / somme;
//		System.out.println("somme " + somme);
		while(test<r&&i<2*nb_marche-1){
			i = i+1;
			test = test+Math.exp(this.rationality_c*this.getFitessTI(indice,i)) / somme;
		}
//		System.out.println("Test vaut " + test);
		if(i<nb_marche){
			this.setType("technical");
			this.setMarche(i+1);
		}else{
			this.setType("fundamental");
			this.setMarche(i-nb_marche+1);
		}
	}

	public void update(Environment[] marche, int nb_marche,  int indice, String model) {
		switch (model) {
		case "model1":
			updateModel1(marche[this.getMarche()-1], indice);
			break;
		case "model2":
			//changement d'etait avec les poids
			checkFitness(marche, nb_marche, indice);
			checkWeight(nb_marche, indice);
			updateModel1(marche[this.getMarche()-1], indice);
			break;
		case "model3":
			//changement des b
			checkFitness(marche, nb_marche, indice);
			//checkB(nb_marche, indice);
			updateModel1(marche[this.getMarche()-1], indice);
			break;
		default:
			System.out.println("Model non defini");
		}
	}

	public void updateModel1(Environment marche, int indice) {
		Random r = new Random();
		if (this.type == "technical") {
			this.addOrder(this.acf * (marche.getPrice_sI(indice) - marche.getPrice_sI(indice - 1)) + r.nextGaussian()*marche.getSigma_c());

		} else if (this.type == "fundamental") {
			this.addOrder(this.acf * (marche.getF() - marche.getPrice_sI(indice)) + r.nextGaussian()*marche.getSigma_f());
		} else {
			this.addOrder(0.0);
		}
	}



}
