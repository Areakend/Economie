package com.company;
import javafx.scene.chart.AreaChart;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Main extends Application{
	public static final int nb_marche = 2;
	public static final int nb_agent = 10;
	public static final int nb_cycle = 200;
	Stage[] page = new Stage[nb_marche];
	static XYChart.Series[] serie = new XYChart.Series[nb_marche];

	public static void main(String[] args) {

		//Choix des caractéristiques du modele
		final String model  = "model1";

		int i;
		int j;


		// Instanciation des agents
		Agent[] agent = new Agent[nb_agent];
		for (i = 0; i < nb_agent; i++) {
			agent[i] = new Agent(nb_marche);
			System.out.println("agent " +i+" de type : " + agent[i].getType() + " sur le marche " +agent[i].getMarche());
		}


		// Instanciation des marches
		Environment[] marche = new Environment[nb_marche];
		for (i = 0; i < nb_marche; i++) {
			marche[i] = new Environment();
			marche[i].getFirstweight(agent, nb_agent);
		}



		// Iteration
		for (i = 2; i < nb_cycle; i++) {
			for (j = 0; j < nb_marche; j++) {
				marche[j].update(agent, j, nb_agent, i);
			}
			for (j = 0; j < nb_agent; j++) {
				agent[j].update(marche[agent[j].getMarche()-1], i, model);
			}
		}
		System.out.println("nb de technical sur marche 1 : " +marche[0].getNb_agent_cI(10));
		System.out.println("nb de fundamental sur marche 1 : " +marche[0].getNb_agent_fI(10));
		System.out.println("nb de technical sur marche 2 : " +marche[1].getNb_agent_cI(10));
		System.out.println("nb de fundamental sur marche 1 : "+marche[1].getNb_agent_fI(10));
		System.out.println("Finish");

		for(i=0; i<nb_marche; i++){
			serie[i] = new XYChart.Series();
		}

		for (i=0; i< nb_cycle; i++){
			for(j=0; j<nb_marche; j++) {
				serie[j].getData().add(new XYChart.Data(i, marche[j].getPrice_sI(i)));
			}
		}

		//Affichage graphes


		/*
		Stage stage = new Stage();
		NumberAxis xAxis = new NumberAxis();
		NumberAxis yAxis = new NumberAxis();
		LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
		lineChart.setTitle("cc");
		XYChart.Series serie = new XYChart.Series();
		for (i = 0; i < nb_cycle; i++) {
			serie.getData().add(new XYChart.Data(i,marche[0].getPrice_sI(i)));
		}
		serie.setName("cc");
		Scene sceneS1 = new Scene(lineChart, 800, 600);
		lineChart.getData().add(serie);
		lineChart.setCreateSymbols(false);
		stage.setScene(sceneS1);
		stage.show();
		*/
		launch(args);

	}

	public void start(Stage stage) {
		int j;
		NumberAxis[] xAxis = new NumberAxis[nb_marche];
		NumberAxis[] yAxis = new NumberAxis[nb_marche];

		ArrayList<LineChart<Number, Number>> lineChart = new ArrayList<LineChart<Number, Number>>();
		Scene[] scene = new Scene[nb_marche];

		for(j=0; j<nb_marche; j++){
			page[j] = new Stage();
		}

		for(j=0; j<nb_marche; j++){
			xAxis[j] = new NumberAxis();
			yAxis[j] = new NumberAxis();
			lineChart.add(new LineChart<Number, Number>(xAxis[j], yAxis[j]));
			lineChart.get(j).setTitle("Prix du marche "+(j+1));
			serie[j].setName("price market " +(j+1));
			scene[j] = new Scene(lineChart.get(j), 800, 600);
			lineChart.get(j).getData().add(serie[j]);
			lineChart.get(j).setCreateSymbols(false);
			page[j].setScene(scene[j]);
			page[j].show();
		}

		/*Scene scene = new Scene(lineChart, 800, 600);
		stage1[0].setScene(scene);
		stage1[0].show();
		*/
	}



}
