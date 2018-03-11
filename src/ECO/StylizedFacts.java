package ECO;

import java.util.LinkedList;
import java.util.List;

public class StylizedFacts {
	
	public static Double minimum(List<Double> stylizedFacts) {
		Double test = 0.0;
		for (int i = 0; i < 4999; i++) {
			if (test > stylizedFacts.get(i)) {
				test = stylizedFacts.get(i);
			}
		}
		return test;
	}
	
	public static Double maximum(List<Double> stylizedFacts) {
		Double test = 0.0;
		for (int i = 0; i < 4999; i++) {
			if (test < stylizedFacts.get(i)) {
				test = stylizedFacts.get(i);
			}
		}
		return test;
	}
	
	public Double volatility(LinkedList<Double> prix) {
		Double test = 0.0;
		List<Double> vola = new LinkedList<Double>();
		for (int i = 1; i < 4999; i++) {
			vola.add((prix.get(i)/prix.get(i-1) - 1)*100);
		}
		for (int i = 0; i < 4999; i++) {
			test = test + vola.get(i);
		}
		test = test/5000;
//		test = Math.sqrt(5000)*test;
		return test;
	}
	
	public static Double kurtosis(List<Double> stylizedFacts) {
		Double test = 0.0;
		Double esperance = 0.0;
		Double ecartType = 0.0;
		for (int i = 0; i < 4999; i++) {
			esperance = esperance + stylizedFacts.get(i);
		}
		esperance = esperance / 5000;
		for (int i = 0; i < 4999; i++) {
			ecartType = ecartType + Math.pow(stylizedFacts.get(i) - esperance, 2);
		}
		ecartType = ecartType / 5000;
		ecartType = Math.sqrt(ecartType);
		for (int i = 0; i < 4999; i++) {
			test = test + Math.pow( (stylizedFacts.get(i) - esperance) / ecartType, 4.0);
		}
		test = test / 5000;
		return test;
	}

}
