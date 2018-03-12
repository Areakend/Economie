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
	private ArrayList<Double> fitness;

	private double rationality_c;
	private double acf;
	private double memory_b;

	public Agent() {
		Random r = new Random();
		double valeur = r.nextGaussian()*100;
		if (valeur < 25) {
			this.type = "technical";
			this.marche = 1;
		}
		else 
			if (valeur < 50) {
				this.type = "fundamental";
				this.marche = 1;
			}
			else 
				if (valeur < 75) {
					this.type = "technical";
					this.marche = 2;
				}else
				{	this.type = "fundamental";
						this.marche = 2;
				}
		this.order = new ArrayList<Double>();
        this.order.add(0.0);
        this.order.add(0.0);
		this.fitness = new ArrayList<Double>();
		this.fitness.add(0.0);
        this.fitness.add(0.0);
		this.rationality_c = 300.0;
		this.acf = 0.05;
		this.memory_b = 0.975;
	}

	public Agent(int nb_marche){
		Random r = new Random();
		double valeur = r.nextGaussian();
		if



		this.order = new ArrayList<Double>();
		this.order.add(0.0);
		this.order.add(0.0);
		this.fitness = new ArrayList<Double>();
		this.fitness.add(0.0);
		this.fitness.add(0.0);
		this.rationality_c = 300.0;
		this.acf = 0.05;
		this.memory_b = 0.975;



	}

	public Agent(String type, int marche, ArrayList<Double> order, ArrayList<Double> fitness, double rationality_c,	double acf, double memory_b) {
		this.type = type;
		this.marche = marche;
		this.order = order;
		this.fitness = fitness;
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

	public ArrayList<Double> getFitness() {
		return fitness;
	}

	public Double getFitnessI(int i) {
		return fitness.get(i);
	}

	public void setFitness(ArrayList<Double> fitness) {
		this.fitness = fitness;
	}

	public void setFitness(int indice, Double valeur) {
		this.fitness.set(indice, valeur);
	}

	public void addFitness(Double valeur) {
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

	public void checkFitness(Environment marche, int indice) {
		Double A = (Math.exp(marche.getPrice_sI(indice)) - Math.exp(marche.getPrice_sI(indice - 1)))
				* getOrderI(indice - 2) - marche.getTax() * Math.exp(marche.getPrice_sI(indice))
				+ Math.exp(marche.getPrice_sI(indice - 1)) * Math.abs(getOrderI(indice - 2))
				+ this.memory_b * getFitnessI(indice - 1);
	}


	public void update(Environment marche, int indice,String model){
		switch (model){
			case "model1":
				updateModel1(marche, indice);
				break;
			default:
				System.out.println("Model non defini");
		}
	}

	public void updateModel1(Environment marche, int indice){
		Random r = new Random();

		if(this.type == "technical"){
			this.addOrder(this.acf * (marche.getPrice_sI(indice) - marche.getPrice_sI(indice - 1)) +  r.nextGaussian());

		} else if (this.type == "fundamental") {
			this.addOrder(this.acf * (marche.getF() - marche.getPrice_sI(indice))+  r.nextGaussian() );
		} else {
			this.addOrder(0.0);
		}
	}

}
