package ECO;

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
	public static int iteration = 5000;
	// Ne pas mettre la valeur de nb_simulation à 1 ou 0 ! nb_simulation>=2
	public static int nb_simulation = 10;
	static Stage stageS1 = new Stage();
	static Stage stageS2 = new Stage();
	static Stage stagePoids = new Stage();
	@SuppressWarnings("rawtypes")
	static XYChart.Series seriesS1 = new XYChart.Series();
	@SuppressWarnings("rawtypes")
	static XYChart.Series seriesS2 = new XYChart.Series();
	@SuppressWarnings("rawtypes")
	static XYChart.Series seriesC1 = new XYChart.Series();
	@SuppressWarnings("rawtypes")
	static XYChart.Series seriesC2 = new XYChart.Series();
	@SuppressWarnings("rawtypes")
	static XYChart.Series seriesF1 = new XYChart.Series();
	@SuppressWarnings("rawtypes")
	static XYChart.Series seriesF2 = new XYChart.Series();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String args[]) {
		List<MatT> listeMoyenne = new LinkedList<MatT>();
		List<Double> stylizedFacts = new LinkedList<Double>();
		List<Double> stylizedFacts2 = new LinkedList<Double>();
		List<LinkedList<MatT>> listeMatrice = new LinkedList<LinkedList<MatT>>();

		Random rand = new Random();

		param parametre = new param(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
		parametre.setAm(1.0);
		parametre.setB(0.975);
		parametre.setAc(0.05);
		parametre.setAf(0.05);
		parametre.setSigmac1(0.05);
		parametre.setSigmac2(0.05);
		parametre.setSigmaf1(0.01);
		parametre.setSigmaf2(0.01);
		parametre.setSigmam1(0.01);
		parametre.setSigmam2(0.01);
		parametre.setTaxe1(0.0);
		parametre.setTaxe2(0.0);
		parametre.setC(300);

		MatT matrice0 = new MatT(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
		matrice0.setS1(0.0);
		matrice0.setS2(0.0);
		matrice0.setDC1(rand.nextGaussian() * parametre.getSigmac1()); // à
																		// modifier
																		// en
																		// random
																		// de
																		// param.getSigmac1()
		matrice0.setDC2(rand.nextGaussian() * parametre.getSigmac2()); // random
																		// de
																		// param.getSigmac2()
		matrice0.setDF1(rand.nextGaussian() * parametre.getSigmaf1()); // random
																		// de
																		// param.getSigmaf1()
		matrice0.setDF2(rand.nextGaussian() * parametre.getSigmaf1()); // random
																		// de
																		// param.getSigmaf2()
		matrice0.setAC1(0.0);
		matrice0.setAF1(0.0);
		matrice0.setAC2(0.0);
		matrice0.setAF2(0.0);

		MatT matrice1 = new MatT(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
		matrice1.setDC1(rand.nextGaussian() * parametre.getSigmac1()); // à
																		// modifier
																		// en
																		// random
																		// de
																		// param.getSigmac1()
		matrice1.setDC2(rand.nextGaussian() * parametre.getSigmac2()); // random
																		// de
																		// param.getSigmac2()
		matrice1.setDF1(rand.nextGaussian() * parametre.getSigmaf1()); // random
																		// de
																		// param.getSigmaf1()
		matrice1.setDF2(rand.nextGaussian() * parametre.getSigmaf2()); // random
																		// de
																		// param.getSigmaf2()
		matrice1.setAC1(0.0);
		matrice1.setAF1(0.0);
		matrice1.setAC2(0.0);
		matrice1.setAF2(0.0);
		matrice1.setWC1(0.2);
		matrice1.setWF1(0.2);
		matrice1.setWC2(0.2);
		matrice1.setWF2(0.2);

		// On veut faire j tours pour supprimer l'erreur
		System.out.println("Wait for it ...");

		for (int j = 0; j < nb_simulation; j++) {
			// On remet la matrice à 0
			LinkedList<MatT> finalMatrice = new LinkedList<MatT>();

			// On ajoute les matrices initialisées
			finalMatrice.add(matrice0);
			finalMatrice.add(matrice1);

			// Itération
			for (int i = 2; i <= iteration; i++) {
				MatT matrice_incrementation = MatT.incrementation(finalMatrice.get(i - 1), finalMatrice.get(i - 2),
						parametre);
				finalMatrice.add(matrice_incrementation);

			}
			System.out.println("Etape " + Integer.toString(j) + " sur " + Integer.toString(nb_simulation));
			listeMatrice.add(finalMatrice);

		}

		for (int i = 1; i <= iteration - 1; i++) {
			System.out
					.println("Moyenne en cours, etape " + Integer.toString(i) + " sur " + Integer.toString(iteration));

			MatT matriceMoyenne = new MatT(null, null, null, null, null, null, null, null, null, null, null, null, null,
					null);

			for (int j = 1; j < nb_simulation; j++) {

				matriceMoyenne.setS1(
						(listeMatrice.get(j - 1).get(i).getS1() * j + listeMatrice.get(j).get(i).getS1()) / (j + 1));
				matriceMoyenne.setS2(
						(listeMatrice.get(j - 1).get(i).getS2() * j + listeMatrice.get(j).get(i).getS2()) / (j + 1));
				matriceMoyenne.setWC1(
						(listeMatrice.get(j - 1).get(i).getWC1() * j + listeMatrice.get(j).get(i).getWC1()) / (j + 1));
				matriceMoyenne.setWC2(
						(listeMatrice.get(j - 1).get(i).getWC2() * j + listeMatrice.get(j).get(i).getWC2()) / (j + 1));
				matriceMoyenne.setWF1(
						(listeMatrice.get(j - 1).get(i).getWF1() * j + listeMatrice.get(j).get(i).getWF1()) / (j + 1));
				matriceMoyenne.setWF2(
						(listeMatrice.get(j - 1).get(i).getWF2() * j + listeMatrice.get(j).get(i).getWF2()) / (j + 1));
			}
			listeMoyenne.add(matriceMoyenne);
		}

		for (int i = 0; i <= iteration - 2; i++) {
			System.out.println(
					"Génération de l'affichage " + Integer.toString(i + 2) + " sur " + Integer.toString(iteration));
			seriesS1.getData().add(new XYChart.Data(i, listeMoyenne.get(i).getS1()));
			stylizedFacts.add(listeMoyenne.get(i).getS1());
			stylizedFacts2.add(listeMoyenne.get(i).getS1());
			seriesS2.getData().add(new XYChart.Data(i, listeMoyenne.get(i).getS2()));
			seriesC1.getData().add(new XYChart.Data(i, listeMoyenne.get(i).getWC1()));
			seriesF1.getData().add(new XYChart.Data(i, listeMoyenne.get(i).getWF1() + listeMoyenne.get(i).getWC1()));
			seriesC2.getData().add(new XYChart.Data(i, 1 - listeMoyenne.get(i).getWC2()));
			seriesF2.getData()
					.add(new XYChart.Data(i, 1 - listeMoyenne.get(i).getWF2() - listeMoyenne.get(i).getWC2()));
		}
		
		System.out.println("Marché 1 :");
		System.out.println("Minimum : " + StylizedFacts.minimum(stylizedFacts));
		System.out.println("Maximum : " + StylizedFacts.maximum(stylizedFacts));
		System.out.println("Volatilité : " + StylizedFacts.volatility(stylizedFacts));
		System.out.println("Kurtosis : " + StylizedFacts.kurtosis(stylizedFacts));
		System.out.println("Autocorrelation : " + StylizedFacts.autocorrelation(stylizedFacts,1));
		
		System.out.println("Marché 2 :");
		System.out.println("Minimum : " + StylizedFacts.minimum(stylizedFacts2));
		System.out.println("Maximum : " + StylizedFacts.maximum(stylizedFacts2));
		System.out.println("Volatilité : " + StylizedFacts.volatility(stylizedFacts2));
		System.out.println("Kurtosis : " + StylizedFacts.kurtosis(stylizedFacts2));
		System.out.println("Autocorrelation : " + StylizedFacts.autocorrelation(stylizedFacts2,1));
		launch(args);

	}

	@SuppressWarnings("unchecked")
	public void start(Stage stage) {
		stage.setTitle("Line Chart Sample");
		// defining the axes
		final NumberAxis xAxisS1 = new NumberAxis();
		final NumberAxis yAxisS1 = new NumberAxis();
		final NumberAxis xAxisS2 = new NumberAxis();
		final NumberAxis yAxisS2 = new NumberAxis();
		final NumberAxis xAxisP = new NumberAxis();
		final NumberAxis yAxisP = new NumberAxis();

		// creating the chart
		final LineChart<Number, Number> lineChartS1 = new LineChart<Number, Number>(xAxisS1, yAxisS1);
		final LineChart<Number, Number> lineChartS2 = new LineChart<Number, Number>(xAxisS2, yAxisS2);
		final AreaChart<Number, Number> areaChartPoids = new AreaChart<Number, Number>(xAxisP, yAxisP);
		areaChartPoids.setTitle("Poids");

		lineChartS1.setTitle("Prix du marché 1");

		lineChartS2.setTitle("Prix du marché 2");
		// lineChartPoids.setTitle("Poids C1");
		// defining a series
		seriesS1.setName("S1");
		seriesS2.setName("S2");
		seriesC1.setName("C1");
		seriesF1.setName("F1");
		seriesC2.setName("C2");
		seriesF2.setName("F2");

		// populating the series with data

		Scene sceneS1 = new Scene(lineChartS1, 800, 600);
		Scene sceneS2 = new Scene(lineChartS2, 800, 600);
		Scene scenePoids = new Scene(areaChartPoids, 800, 600);

		lineChartS1.getData().add(seriesS1);
		lineChartS1.setCreateSymbols(false);
		lineChartS2.getData().add(seriesS2);
		lineChartS2.setCreateSymbols(false);
		areaChartPoids.getData().addAll(seriesC1, seriesC2, seriesF1, seriesF2);
		areaChartPoids.setCreateSymbols(false);
		
		stageS1.setScene(sceneS1);
		stageS2.setScene(sceneS2);
		stagePoids.setScene(scenePoids);
		stageS1.show();
		stageS2.show();
		stagePoids.show();
		
	}

}