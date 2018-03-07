package com.company;

import java.util.ArrayList;

/**
 * Created by alexa on 23/02/2018.
 */
public class Environment {
    private ArrayList<Double> price_s;

    private double am;
    private double tax;
    private double F;

    public Environment() {
    }

    public Environment(ArrayList<Double> price_s, double am, double tax, double f) {
        this.price_s = price_s;
        this.am = am;
        this.tax = tax;
        F = f;
    }

    public ArrayList<Double> getPrice_s() {
        return price_s;
    }

    public Double getPrice_sI(int i){ return price_s.get(i);}

    public void setPrice_s(ArrayList<Double> price_s) {
        this.price_s = price_s;
    }

    public void setPrice_s(int indice, Double valeur){
        this.price_s.set(indice, valeur);
    }

    public void addPrice_s(Double valeur){ this.price_s.add(valeur);}

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

    public void update(Agent[] agent, int numero_marche, int nb_agent, int indice){
        Double somme = 0.0;
        for(int i=0; i<nb_agent; i++){
            if(agent[i].getMarche() == numero_marche){
                somme = somme + agent[i].getOrderI(2)*this.am;
            }
        }
        addPrice_s(somme+this.price_s.get(indice-1));
    }

}
