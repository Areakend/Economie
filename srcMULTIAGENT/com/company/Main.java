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
	public static final int nb_marche =2 ;
	public static final int nb_agent = 10;
	public static final int nb_cycle = 100;
	Stage[] page = new Stage[nb_marche+1];
	static XYChart.Series[] serie = new XYChart.Series[nb_marche];
	static XYChart.Series[] serieWeight = new XYChart.Series[2*nb_marche];




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
			marche[i].getFirstweight(agent, nb_agent, i);
		}

		// Iteration
		for (i = 2; i < nb_cycle; i++) {
			for (j = 0; j < nb_marche; j++) {
				marche[j].update(agent, j, nb_agent, i);
			}
			for (j = 0; j < nb_agent; j++) {
				agent[j].update(marche,nb_marche, i, model);
			}
		}

		System.out.println("Calcul finis");

		for(i=0; i<nb_marche; i++){
			serie[i] = new XYChart.Series();
			serieWeight[i] = new XYChart.Series();
			serieWeight[i+nb_marche] = new XYChart.Series();
		}


		for (i=0; i< nb_cycle; i++){
			for(j=0; j<nb_marche; j++) {
				serie[j].getData().add(new XYChart.Data(i, marche[j].getPrice_sI(i)));
				serieWeight[j].getData().add(new XYChart.Data(i, marche[j].getNb_agent_cI(i)));
				serieWeight[j+nb_marche].getData().add(new XYChart.Data(i, marche[j].getNb_agent_fI(i)));
			}
		}


		launch(args);

	}

	public void start(Stage stage) {
		int j;
		NumberAxis[] xAxis = new NumberAxis[nb_marche+1];
		NumberAxis[] yAxis = new NumberAxis[nb_marche+1];

		ArrayList<LineChart<Number, Number>> lineChart = new ArrayList<LineChart<Number, Number>>();

		Scene[] scene = new Scene[nb_marche+1];

		for(j=0; j<nb_marche+1; j++){
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
		}
		xAxis[nb_marche] = new NumberAxis();
		yAxis[nb_marche] = new NumberAxis();
		LineChart<Number, Number> lineChartWeight = new LineChart<Number, Number>(xAxis[nb_marche], yAxis[nb_marche]);
		lineChartWeight.setTitle("Poids");
		for(j=0; j<nb_marche; j++){
			serieWeight[j].setName("C"+j);
			serieWeight[j+nb_marche].setName("F"+j);
			lineChartWeight.getData().add(serieWeight[j]);
			lineChartWeight.getData().add(serieWeight[j+nb_marche]);

		}
		lineChartWeight.setCreateSymbols(false);
		scene[nb_marche] = new Scene(lineChartWeight, 800, 600);
		page[nb_marche].setScene(scene[nb_marche]);

		for(j=0; j<nb_marche+1; j++){
			page[j].show();

		}


		/*Scene scene = new Scene(lineChart, 800, 600);
		stage1[0].setScene(scene);
		stage1[0].show();
		*/
	}



}
