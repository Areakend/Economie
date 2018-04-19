package ECO;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import javafx.scene.chart.AreaChart;

import java.util.List;

import ECO.param;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class Main extends Application {
	public static int iteration = 500;
	// Ne pas mettre la valeur de nb_simulation � 1 ou 0 ! nb_simulation>=2
	public static int nb_simulation = 4;
	public static Double nb_marche = 2.0;
	public static int nb_marcheInt = 2;

	Stage[] page = new Stage[nb_marcheInt + 1];
	static XYChart.Series[] serie = new XYChart.Series[nb_marcheInt];
	static XYChart.Series[] serieWeight = new XYChart.Series[2 * nb_marcheInt];
	/*
	 * public static List<Stage> marches = new LinkedList<Stage>();
	 * 
	 * @SuppressWarnings("rawtypes") public static List<LineChart<Number,
	 * Number>> seriesS = new LinkedList<LineChart<Number, Number>>();
	 * 
	 * @SuppressWarnings({ "rawtypes", "unchecked" }) public static
	 * List<XYChart.Series> seriesC = new LinkedList<XYChart.Series>();
	 * 
	 * @SuppressWarnings({ "rawtypes", "unchecked" }) public static
	 * List<XYChart.Series> seriesF = new LinkedList<XYChart.Series>();
	 */

	static List<Stage> stageS = new LinkedList<Stage>();

	static Stage stagePoids = new Stage();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String args[]) {
		List<MatT> listeMoyenne = new LinkedList<MatT>();
		List<Double> stylizedFacts = new LinkedList<Double>();
		List<Double> stylizedFacts2 = new LinkedList<Double>();
		List<Double> sigmaC = new LinkedList<Double>();
		List<Double> sigmaF = new LinkedList<Double>();
		List<Double> sigmaM = new LinkedList<Double>();
		List<Double> taxes = new LinkedList<Double>();
		List<Double> S = new LinkedList<Double>();
		List<Double> DC = new LinkedList<Double>();
		List<Double> DF = new LinkedList<Double>();
		List<Double> AC = new LinkedList<Double>();
		List<Double> AF = new LinkedList<Double>();
		List<Double> moy = new LinkedList<Double>();
		List<Double> WC = new LinkedList<Double>();
		List<Double> WF = new LinkedList<Double>();
		List<Double> F = new LinkedList<Double>();
		List<Double> temp = new LinkedList<Double>();
		Double temp2;
		List<LinkedList<MatT>> listeMatrice = new LinkedList<LinkedList<MatT>>();

		Random rand = new Random();

		for (int i = 0; i < nb_marche; i++) {
			/*
			 * marches.add(new Stage()); seriesS.add(new XYChart.Series<>());
			 * seriesF.add(new XYChart.Series<>()); seriesC.add(new
			 * XYChart.Series<>());
			 */
			serie[i] = new XYChart.Series();
			serieWeight[i] = new XYChart.Series();
			serieWeight[i + nb_marcheInt] = new XYChart.Series();

			sigmaC.add(0.05);
			sigmaF.add(0.01);
			sigmaM.add(0.01);
			temp.add(0.0);
			F.add(0.0);
			S.add(0.0);
			DC.add(0.0);
			DF.add(0.0);
			AC.add(0.0);
			AF.add(0.0);
			WC.add(0.0);
			WF.add(0.0);
			moy.add(0.0);
			taxes.add(0.0);
		}

		param parametre = new param(0.0, 0.0, 0.0, 0.0, sigmaC, taxes, 0.0, sigmaF, sigmaM, F);
		parametre.setAm(1.0);
		parametre.setB(0.975);
		parametre.setAc(0.05);
		parametre.setAf(0.05);
		parametre.setC(300);

		MatT matrice0 = new MatT(S, DC, DF, AC, AF, WC, WF);

		for (int i = 0; i < nb_marche; i++) {
			matrice0.setDCi(i, rand.nextGaussian() * parametre.getSigmaCi(i));
			matrice0.setDFi(i, rand.nextGaussian() * parametre.getSigmaFi(i));
			matrice0.setACi(i, 0.0);
			matrice0.setAFi(i, 0.0);
		}
		for (int i = 0; i < nb_marche; i++) {
			matrice0.setWCi(i, 1 / (2 * nb_marche));
			matrice0.setWFi(i, 1 / (2 * nb_marche));
		}
		MatT matrice1 = new MatT(S, DC, DF, AC, AF, WC, WF);

		for (int i = 0; i < nb_marche; i++) {
			matrice1.setDCi(i, rand.nextGaussian() * parametre.getSigmaCi(i));
			matrice1.setDFi(i, rand.nextGaussian() * parametre.getSigmaFi(i));
			matrice1.setACi(i, 0.0);
			matrice1.setAFi(i, 0.0);
			matrice1.setWCi(i, 1 / (2 * nb_marche));
			matrice1.setWFi(i, 1 / (2 * nb_marche));
		}


		// On veut faire j tours pour supprimer l'erreur
		System.out.println("Wait for it ...");

		for (int j = 0; j < nb_simulation; j++) {
			// On remet la matrice � 0
			LinkedList<MatT> finalMatrice = new LinkedList<MatT>();

			// On ajoute les matrices initialis�es
			finalMatrice.add(matrice0);
			finalMatrice.add(matrice1);

			// It�ration
			for (int i = 2; i <= iteration; i++) {
				MatT matrice_incrementation = MatT.incrementation(finalMatrice.get(i - 1), finalMatrice.get(i - 2),
						parametre);
				finalMatrice.add(matrice_incrementation);
				System.out.println(matrice_incrementation.getS().get(0));


			}
			System.out.println("Etape " + Integer.toString(j) + " sur " + Integer.toString(nb_simulation));
			listeMatrice.add(finalMatrice);

		}

		MatT matriceMoyenne = new MatT(temp, temp, temp, temp, temp, temp, temp);
		for (int i = 1; i <= iteration - 1; i++) {


			System.out
					.println("Moyenne en cours, etape " + Integer.toString(i) + " sur " + Integer.toString(iteration));

			for (int j = 1; j < nb_simulation; j++) {

				matriceMoyenne = listeMatrice.get(j).get(i);


				for (int k = 0; k < nb_marche; k++) {
					System.out.println("Etape " + Integer.toString(j) + " sur " + Integer.toString(nb_simulation));

					matriceMoyenne.setSi(k, ((matriceMoyenne.getS().get(k) * j
							+ listeMatrice.get(j).get(i).getS().get(k)) / (j + 1)));

					matriceMoyenne.setWCi(k, (matriceMoyenne.getWC().get(k) * j
							+ listeMatrice.get(j).get(i).getWC().get(k)) / (j + 1));

					matriceMoyenne.setWFi(k, (matriceMoyenne.getWF().get(k) * j
							+ listeMatrice.get(j).get(i).getWF().get(k)) / (j + 1));
				}
			}
			listeMoyenne.add(matriceMoyenne);
		}

		for (int i1 = 0; i1 <= iteration - 2; i1++) {
			System.out.println(
					"G�n�ration de l'affichage " + Integer.toString(i1 + 2) + " sur " + Integer.toString(iteration));
			for (int k = 0; k < nb_marche; k++) {
				/*
				 * seriesS.get(k).getData().add(new XYChart.Data(i1,
				 * listeMoyenne.get(i1).getS().get(k))); //
				 * stylizedFacts.add(listeMoyenne.get(i1).getS().get(k)); //
				 * stylizedFacts2.add(listeMoyenne.get(i1).getS().get(k));
				 * seriesC.get(k).getData().add(new XYChart.Data(i1,
				 * listeMoyenne.get(i1).getWC().get(k)));
				 * seriesF.get(k).getData().add(new XYChart.Data(i1,
				 * listeMoyenne.get(i1).getWF().get(k)));
				 */

				serie[k].getData().add(new XYChart.Data(i1, listeMoyenne.get(i1).getS().get(k))); //marche[k].getPrice_sI(i1)));
				serieWeight[k].getData().add(new XYChart.Data(i1, listeMoyenne.get(i1).getWC().get(k))); //marche[k].getNb_agent_cI(i1)));
				serieWeight[k + nb_marcheInt].getData().add(new XYChart.Data(i1, listeMoyenne.get(i1).getWF().get(k))); // marche[k].getNb_agent_fI(i1)));
			}
			System.out.println(listeMoyenne.get(i1).getWC().get(0) + listeMoyenne.get(i1).getWC().get(1) + 
					listeMoyenne.get(i1).getWF().get(1) + listeMoyenne.get(i1).getWF().get(1));
		}
	
	

	
	/*
	 * System.out.println("March� 1 :"); System.out.println("Minimum : " +
	 * StylizedFacts.minimum(stylizedFacts)); System.out.println("Maximum : " +
	 * StylizedFacts.maximum(stylizedFacts)); System.out.println("Volatilit� : "
	 * + StylizedFacts.volatility(stylizedFacts));
	 * System.out.println("Kurtosis : " +
	 * StylizedFacts.kurtosis(stylizedFacts));
	 * System.out.println("Autocorrelation : " +
	 * StylizedFacts.autocorrelation(stylizedFacts, 1));
	 * 
	 * System.out.println("March� 2 :"); System.out.println("Minimum : " +
	 * StylizedFacts.minimum(stylizedFacts2)); System.out.println("Maximum : " +
	 * StylizedFacts.maximum(stylizedFacts2));
	 * System.out.println("Volatilit� : " +
	 * StylizedFacts.volatility(stylizedFacts2));
	 * System.out.println("Kurtosis : " +
	 * StylizedFacts.kurtosis(stylizedFacts2));
	 * System.out.println("Autocorrelation : " +
	 * StylizedFacts.autocorrelation(stylizedFacts2, 1));
	 */
	launch(args);

	}

	@SuppressWarnings("unchecked")
	public void start(Stage stage) {
		stage.setTitle("Line Chart Sample");
		
		// defining the axes

		NumberAxis[] xAxis = new NumberAxis[nb_marcheInt+1];
		NumberAxis[] yAxis = new NumberAxis[nb_marcheInt+1];
		
		// creating the chart
		ArrayList<LineChart<Number, Number>> lineChartS = new ArrayList<LineChart<Number, Number>>();

		Scene[] scene = new Scene[nb_marcheInt+1];

		for(int j=0; j<nb_marche+1; j++){
			page[j] = new Stage();
		}
		
		for(int j=0; j<nb_marche; j++){
			xAxis[j] = new NumberAxis();
			yAxis[j] = new NumberAxis();
			lineChartS.add(new LineChart<Number, Number>(xAxis[j], yAxis[j]));
			lineChartS.get(j).setTitle("Prix du marche "+(j+1));
			serie[j].setName("price market " +(j+1));
			scene[j] = new Scene(lineChartS.get(j), 800, 600);
			lineChartS.get(j).getData().add(serie[j]);
			lineChartS.get(j).setCreateSymbols(false);
			page[j].setScene(scene[j]);
		}
		
		xAxis[nb_marcheInt] = new NumberAxis();
		yAxis[nb_marcheInt] = new NumberAxis();
		LineChart<Number, Number> lineChartWeight = new LineChart<Number, Number>(xAxis[nb_marcheInt], yAxis[nb_marcheInt]);
		lineChartWeight.setTitle("Poids");
		for(int j=0; j<nb_marche; j++){
			serieWeight[j].setName("C"+j);
			serieWeight[j+nb_marcheInt].setName("F"+j);
			lineChartWeight.getData().add(serieWeight[j]);
			lineChartWeight.getData().add(serieWeight[j+nb_marcheInt]);
		}
		
		lineChartWeight.setCreateSymbols(false);
		scene[nb_marcheInt] = new Scene(lineChartWeight, 800, 600);
		page[nb_marcheInt].setScene(scene[nb_marcheInt]);

		for(int j=0; j<nb_marche+1; j++){
			page[j].show();
		}
		
	//	final List<LineChart<Number, Number>> lineChartS = new LinkedList<LineChart<Number, Number>>();
	/*	final AreaChart<Number, Number> areaChartPoids = new AreaChart<Number, Number>(xAxisP, yAxisP);
		areaChartPoids.setTitle("Poids");
		
		for (int i = 0; i < nb_marche; i++) {
			lineChartS.add(new LineChart<Number, Number>(xAxisS, yAxisS));
			lineChartS.get(i).setTitle("Prix du march� " + i);
		}

		// defining a series
		for (int i = 0; i < nb_marche; i++) {
			seriesS.get(i).setName("S" + i);
			seriesC.get(i).setName("C" + i);
			seriesF.get(i).setName("F" + i);

		}

		// populating the series with data

		List<Scene> scenes = new LinkedList<Scene>();
		for (int i = 0; i < nb_marche; i++) {
			scenes.add(new Scene(lineChartS.get(i), 800, 600));
			stageS.add(new Stage());
		}
		Scene scenePoids = new Scene(areaChartPoids, 800, 600);

		for (int i = 0; i < nb_marche; i++) {
			lineChartS.get(i).getData().add(seriesS.get(i));
			lineChartS.get(i).setCreateSymbols(false);

			areaChartPoids.getData().addAll(seriesC.get(i), seriesF.get(i));
			areaChartPoids.setCreateSymbols(false);

			stageS.get(i).setScene(scenes.get(i));
			stagePoids.setScene(scenePoids);
			stagePoids.show();
			stagePoids.setScene(scenePoids);
			stageS.get(i).show();
			stagePoids.show();
		}*/
	}

}