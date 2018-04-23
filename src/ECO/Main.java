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
	public static int iteration = 5000;
	// Ne pas mettre la valeur de nb_simulation à 1 ou 0 ! nb_simulation>=2
	public static int nb_simulation = 10;
	public static Double nb_marche = 2.0;
	public static int nb_marcheInt = 2;

	// Initialisation des variables de stockage pour l'affichage
	Stage[] page = new Stage[nb_marcheInt + 1];
	static XYChart.Series[] serie = new XYChart.Series[nb_marcheInt];
	static XYChart.Series[] serieWeight = new XYChart.Series[2 * nb_marcheInt];

	static List<Stage> stageS = new LinkedList<Stage>();

	static Stage stagePoids = new Stage();

	// Initialisation des listes qui contiendrons nos variables

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String args[]) {

		// Liste moyenne une fois toute les itérations effectuées
		List<MatT> listeMoyenne = new LinkedList<MatT>();

		// Liste qui permettra de calculer les valeurs caractéristique de notre
		// série (moyenne, volatilité, ...)
		List<Double> stylizedFacts = new LinkedList<Double>();

		// Variable nécessaires au calculs matriciel, elles suivent les
		// notations du dossier de Frank H. Westerhoff et Roberto Dieci
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

		// Variable temporaire utilisée lors de calcul pour garder en mémoire
		// d'anciennes valeurs d'une liste lorsque une des valeurs de celle-ci
		// est modifié mais qu'il nous faut la valeur originale pour calculer un
		// autre élément
		List<Double> temp = new LinkedList<Double>();
		Double temp2;

		// La liste contenant toute les itérations
		List<LinkedList<MatT>> listeMatrice = new LinkedList<LinkedList<MatT>>();

		// Un objet "random" pour les calculs aléatoires
		Random rand = new Random();

		for (int i = 0; i < nb_marche; i++) {

			// On ajoute à nos listes autant de valeurs de que marché (on
			// initialise la taille des listes)

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

		// On crée l'objet paramètre
		param parametre = new param(0.0, 0.0, 0.0, 0.0, sigmaC, taxes, 0.0, sigmaF, sigmaM, F);

		// On fixe des valeurs de paramètre
		parametre.setAm(1.0);
		parametre.setB(0.975);
		parametre.setAc(0.05);
		parametre.setAf(0.05);
		parametre.setC(300);

		// Création de la matrice à t = 0
		MatT matrice0 = new MatT(S, DC, DF, AC, AF, WC, WF);

		for (int i = 0; i < nb_marche; i++) { // Lire "Pour i allant de 0 à
												// nb_marche faire les
												// instructions entre accolades,
												// ajouter 1 à i à chaque tour

			// Quelques initialisation de la matrice à t 0
			matrice0.setDCi(i, rand.nextGaussian() * parametre.getSigmaCi(i));
			matrice0.setDFi(i, rand.nextGaussian() * parametre.getSigmaFi(i));
			matrice0.setACi(i, 0.0);
			matrice0.setAFi(i, 0.0);

			// Initialisation des poids la matrice à t 0 (autant de chaque
			// agents)

			matrice0.setWCi(i, 1 / (2 * nb_marche));
			matrice0.setWFi(i, 1 / (2 * nb_marche));
		}

		// Creation de la matrice à t = 1
		MatT matrice1 = new MatT(S, DC, DF, AC, AF, WC, WF);

		for (int i = 0; i < nb_marche; i++) {
			// Quelques initialisation de la matrice à t 1

			matrice1.setDCi(i, rand.nextGaussian() * parametre.getSigmaCi(i));
			matrice1.setDFi(i, rand.nextGaussian() * parametre.getSigmaFi(i));
			matrice1.setACi(i, 0.0);
			matrice1.setAFi(i, 0.0);
			matrice1.setWCi(i, 1 / (2 * nb_marche));
			matrice1.setWFi(i, 1 / (2 * nb_marche));
		}

		// On veut faire j simulations pour supprimer les erreurs d'aléa trop
		// importantes

		for (int j = 0; j < nb_simulation; j++) {
			// Cette matrice correspond à la matrice à la simulation j, on la
			// remet à 0
			// à chaque simulation
			LinkedList<MatT> finalMatrice = new LinkedList<MatT>();

			// On ajoute les matrices initialisées, t=0 et t=1
			finalMatrice.add(matrice0);
			finalMatrice.add(matrice1);

			// Itération
			for (int i = 2; i <= iteration; i++) {
				// On calcule la matrice à t=i à partir de la matrice à t=i-1 et
				// t=i-2
				MatT matrice_incrementation = MatT.incrementation(finalMatrice.get(i - 1), finalMatrice.get(i - 2),
						parametre);

				// On ajoute la matrice à l'instant t dans la liste
				// "finalMatrice", correspondant à la liste de toutes les
				// matrice de la simulation j
				finalMatrice.add(matrice_incrementation);
			}

			// Affichage de l'anvancement
			System.out.println("Etape " + Integer.toString(j) + " sur " + Integer.toString(nb_simulation));

			// On ajoute la liste correspondant à la simulation j à la liste de
			// toutes les simulations
			listeMatrice.add(finalMatrice);

		}

		// Creation de la matrice des moyennes
		MatT matriceMoyenne = new MatT(temp, temp, temp, temp, temp, temp, temp);
		for (int i = 1; i <= iteration - 1; i++) {
			// Pour tout les temps
			// Affichage de l'avancement de la moyenne
			System.out
					.println("Moyenne en cours, etape " + Integer.toString(i) + " sur " + Integer.toString(iteration));

			for (int j = 1; j < nb_simulation; j++) {
				// Moyenne sur les simulations
				matriceMoyenne = listeMatrice.get(j).get(i);

				for (int k = 0; k < nb_marche; k++) {
					// On effectue la moyenne sur le marche k

					// Pour le prix, S
					matriceMoyenne.setSi(k,
							((matriceMoyenne.getS().get(k) * j + listeMatrice.get(j).get(i).getS().get(k)) / (j + 1)));

					// Pour les poids des chartistes, WC
					matriceMoyenne.setWCi(k,
							(matriceMoyenne.getWC().get(k) * j + listeMatrice.get(j).get(i).getWC().get(k)) / (j + 1));

					// Pour les poids des fondamentalistes, WF
					matriceMoyenne.setWFi(k,
							(matriceMoyenne.getWF().get(k) * j + listeMatrice.get(j).get(i).getWF().get(k)) / (j + 1));
				}
			}
			// On ajoute la matrice moyenne de t=k à la liste
			listeMoyenne.add(matriceMoyenne);
		}

		for (int i1 = 0; i1 <= iteration - 2; i1++) {
			// Affichage de l'avancement de l'affichage
			System.out.println(
					"Génération de l'affichage " + Integer.toString(i1 + 2) + " sur " + Integer.toString(iteration));
			for (int k = 0; k < nb_marche; k++) {

				stylizedFacts.add(listeMoyenne.get(i1).getS().get(k));

				// On ajoute les valeurs de prix à une liste contenant seulement
				// les prix
				serie[k].getData().add(new XYChart.Data(i1, listeMoyenne.get(i1).getS().get(k)));

				// On ajoute le poids des chartistes sur le marche k a l'instant
				// i1 à une liste contenant seulement les poids des chartistes
				serieWeight[k].getData().add(new XYChart.Data(i1, listeMoyenne.get(i1).getWC().get(k)));

				// On ajoute le de poids des fondamentaliste sur le marche k à
				// l'intant i1 à une liste contenant seulement les poids des
				// fondamentalistes
				serieWeight[k + nb_marcheInt].getData().add(new XYChart.Data(i1, listeMoyenne.get(i1).getWF().get(k)));
			}

		}

		// Afficahge des calculs de manimum, minimum, volatilité, kurtosis,
		// autocorrelation pour le marché 1
		System.out.println("Marché 1 :");
		System.out.println("Minimum : " + StylizedFacts.minimum(stylizedFacts));
		System.out.println("Maximum : " + StylizedFacts.maximum(stylizedFacts));
		System.out.println("Volatilité : " + StylizedFacts.volatility(stylizedFacts));
		System.out.println("Kurtosis : " + StylizedFacts.kurtosis(stylizedFacts));
		System.out.println("Autocorrelation : " + StylizedFacts.autocorrelation(stylizedFacts, 1));

		// Lancement de l'affichage
		launch(args);

	}

	@SuppressWarnings("unchecked")
	public void start(Stage stage) {
		stage.setTitle("Line Chart Sample");

		// Definition des axes

		NumberAxis[] xAxis = new NumberAxis[nb_marcheInt + 1];
		NumberAxis[] yAxis = new NumberAxis[nb_marcheInt + 1];

		// Creation du graph
		ArrayList<LineChart<Number, Number>> lineChartS = new ArrayList<LineChart<Number, Number>>();

		Scene[] scene = new Scene[nb_marcheInt + 1];

		for (int j = 0; j < nb_marche + 1; j++) {
			page[j] = new Stage();
		}

		
		for (int j = 0; j < nb_marche; j++) {
			xAxis[j] = new NumberAxis();
			yAxis[j] = new NumberAxis();
			lineChartS.add(new LineChart<Number, Number>(xAxis[j], yAxis[j]));
			lineChartS.get(j).setTitle("Prix du marche " + (j + 1));
			serie[j].setName("price market " + (j + 1));
			scene[j] = new Scene(lineChartS.get(j), 800, 600);
			lineChartS.get(j).getData().add(serie[j]);
			lineChartS.get(j).setCreateSymbols(false);
			page[j].setScene(scene[j]);
		}

		xAxis[nb_marcheInt] = new NumberAxis();
		yAxis[nb_marcheInt] = new NumberAxis();
		LineChart<Number, Number> lineChartWeight = new LineChart<Number, Number>(xAxis[nb_marcheInt],
				yAxis[nb_marcheInt]);
		lineChartWeight.setTitle("Poids");
		for (int j = 0; j < nb_marche; j++) {
			serieWeight[j].setName("C" + j);
			serieWeight[j + nb_marcheInt].setName("F" + j);
			lineChartWeight.getData().add(serieWeight[j]);
			lineChartWeight.getData().add(serieWeight[j + nb_marcheInt]);
		}

		lineChartWeight.setCreateSymbols(false);
		scene[nb_marcheInt] = new Scene(lineChartWeight, 800, 600);
		page[nb_marcheInt].setScene(scene[nb_marcheInt]);

		for (int j = 0; j < nb_marche + 1; j++) {
			page[j].show();
		}
	}

}