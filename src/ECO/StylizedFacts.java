package ECO;

import java.util.LinkedList;
import java.util.List;

public class StylizedFacts {

	// Liste des fonctions pour les calculs de minimum, maximum, volatilité, autocorrélation, moyenne, ecart type, ...
	
	public static Double minimum(List<Double> stylizedFacts) {
		Double test = 0.0;
		for (int i = 1; i < 4999; i++) {
			if (test > stylizedFacts.get(i) - stylizedFacts.get(i-1)) {
				test = stylizedFacts.get(i) - stylizedFacts.get(i-1);
			}
		}
		return test*100;
	}

	public static Double maximum(List<Double> stylizedFacts) {
		Double test = 0.0;
		for (int i = 1; i < 4999; i++) {
			if (test < stylizedFacts.get(i) - stylizedFacts.get(i-1)) {
				test = stylizedFacts.get(i) - stylizedFacts.get(i-1);
			}
		}
		return test*100;
	}

	public static Double volatility(List<Double> stylizedFacts) {
		Double test = 0.0;
		List<Double> vola = new LinkedList<Double>();
/*		for (int i = 1; i < 4999; i++) {
			vola.add( Math.pow((Math.log(stylizedFacts.get(i) / stylizedFacts.get(i - 1))), 2) );
		}
		for (int i = 0; i < 4997; i++) {
			test = test + vola.get(i);
		}
		test = test * 252 / 5000;
		test = Math.sqrt(test);
		return test;*/
		return ( ecartType(stylizedFacts));
	}

	public static Double moyenne(List<Double> stylizedFacts) {
		Double res = 0.0;
		for (int i = 0; i < 4999; i++) {
			res = res + stylizedFacts.get(i);
		}
		res = res / 5000;
		return res;
	}
	
	public static Double moyenneABS(List<Double> stylizedFacts) {
		Double res = 0.0;
		for (int i = 0; i < 4999; i++) {
			res = res + Math.abs(stylizedFacts.get(i));
		}
		res = res / 5000;
		return res;
	}
	
	public static Double ecartType(List<Double> stylizedFacts) {
		Double res = 0.0;
		Double esperance = moyenne(stylizedFacts);
		for (int i = 0; i < 4999; i++) {
			res = res + Math.pow(stylizedFacts.get(i) - esperance, 2);
		}
		res = res / 5000;
		res = Math.sqrt(res);
		return res;
	}
	
	public static Double ecartTypeABS(List<Double> stylizedFacts) {
		Double res = 0.0;
		Double esperance = moyenneABS(stylizedFacts);
		for (int i = 0; i < 4999; i++) {
			res = res + Math.pow(Math.abs(stylizedFacts.get(i)) - esperance, 2);
		}
		res = res / 5000;
		res = Math.sqrt(res);
		return res;
	}

	public static Double kurtosis(List<Double> stylizedFacts) {
		Double test = 0.0;
		Double esperance = moyenne(stylizedFacts);
		Double ecartType = ecartType(stylizedFacts);

		for (int i = 0; i < 4999; i++) {
			test = test + Math.pow((stylizedFacts.get(i) - esperance) / ecartType, 4.0);
		}
		test = test / 5000;
		return test;
	}

	public static Double autocorrelation(List<Double> stylizedFacts, int k) {
		Double autocorrelation = 0.0;
		Double esperance = 0.0;
		Double ecartType = ecartType(stylizedFacts);
		Double moyenne = moyenne(stylizedFacts);
		for (int i = 0; i < 4999 - k; i++) {
			esperance = esperance + (stylizedFacts.get(i) - moyenne) * (stylizedFacts.get(i + k) - moyenne);
		}
		esperance = esperance / (4999-k);
		autocorrelation = esperance / ecartType*ecartType;

		return autocorrelation;
	}
	
	public static Double autocorrelationABS(List<Double> stylizedFacts, int k) {
		Double autocorrelation = 0.0;
		Double esperance = 0.0;
		Double ecartType = ecartTypeABS(stylizedFacts);
		Double moyenne = moyenneABS(stylizedFacts);
		for (int i = 0; i < 4999 - k; i++) {
			esperance = esperance + Math.abs(Math.abs(stylizedFacts.get(i)) - moyenne) * Math.abs((Math.abs(stylizedFacts.get(i + k)) - moyenne));
		}
		esperance = esperance / (4999-k);
		autocorrelation = esperance / ecartType*ecartType;

		return autocorrelation;
	}

}

