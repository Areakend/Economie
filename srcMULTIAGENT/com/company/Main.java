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
	Stage[] stage1 = new Stage[nb_marche];
	static XYChart.Series[] serie = new XYChart.Series[nb_marche];




	public static void main(String[] args) {

		//Choix des caractéristiques du modlee
		final String model  = "model1";

		int i;
		int j;

		//Instanciation pour



		// Instanciation des marches
		Environment[] marche = new Environment[nb_marche];
		for (i = 0; i < nb_marche; i++) {
			marche[i] = new Environment();
		}

		// Instanciation des agents
		Agent[] agent = new Agent[nb_agent];
		for (i = 0; i < nb_agent; i++) {
			agent[i] = new Agent();
		}

		// Iteration
		for (i = 2; i < nb_cycle; i++) {
			for (j = 0; j < nb_marche; j++) {
				marche[j].update(agent, j, nb_agent, i);
				System.out.println(marche[j].getPrice_sI(i));
			}
			for (j = 0; j < nb_agent; j++) {
				agent[j].update(marche[agent[j].getMarche()-1], i, model);
			}
		}
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
		NumberAxis xAxis = new NumberAxis();
		NumberAxis yAxis = new NumberAxis();

		final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);


		for(j=0; j<nb_marche; j++){
			stage1[j] = new Stage();
		}


		lineChart.setTitle("Prix des marche");
		for(j=0; j<nb_marche; j++){
			serie[j].setName("price market " +j);

		}
		for(j=0; j<nb_marche;j++){

		}
		Scene scene = new Scene(lineChart, 800, 600);
		//Scene[] scene = new Scene[nb_marche];

		for(j=0; j<nb_marche; j++ ){
			lineChart.getData().add(serie[j]);
		}
		lineChart.setCreateSymbols(false);
		stage1[0].setScene(scene);
		stage1[0].show();
	}


}
