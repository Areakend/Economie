package ECO;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MatT {

	private List<Double> S;
	private List<Double> DC;
	private List<Double> DF;
	private List<Double> AC;
	private List<Double> AF;
	private List<Double> WC;
	private List<Double> WF;

	public MatT(List<Double> S, List<Double> DC, List<Double> DF, List<Double> AC, List<Double> AF, List<Double> WC,
			List<Double> WF) {
		this.S = S;
		this.DC = DC;
		this.DF = DF;
		this.AC = AC;
		this.AF = AF;
		this.WC = WC;
		this.WF = WF;
	}

	// matrice_1 est la matrice à T-2, matrice est la matrice à t - 1
	public static MatT incrementation(MatT matrice, MatT matrice_1, param parametre) {
		Random r = new Random();
		List<Double> iniS = new LinkedList<Double>();
		List<Double> iniDC = new LinkedList<Double>();
		List<Double> iniDF = new LinkedList<Double>();
		List<Double> iniAC = new LinkedList<Double>();
		List<Double> iniAF = new LinkedList<Double>();
		List<Double> iniWC = new LinkedList<Double>();
		List<Double> iniWF = new LinkedList<Double>();
		for (int i = 0; i < Main.nb_marche; i++) {
			iniS.add(0.0);
			iniDC.add(0.0);
			iniDF.add(0.0);
			iniAC.add(0.0);
			iniAF.add(0.0);
			iniWC.add(0.0);
			iniWF.add(0.0);
		}
		MatT matriceT = new MatT(iniS, iniDC, iniDF, iniAC, iniAF, iniWC, iniWF);
		;
		// Ici on calule la matrice à l'instant T

		for (int i = 0; i < Main.nb_marche; i++) {
			//Les paramètres S, prix des marchés
			matriceT.setSi(i,
					matrice.getS().get(i)
							+ parametre.getAm() * (matrice.getWC().get(i) * matrice.getDC().get(i)
									+ matrice.getWF().get(i) * matrice.getDF().get(i))
							+ r.nextGaussian() * parametre.getSigmaM().get(i));
			// Les paramètres D, ordres générés
			matriceT.setDCi(i,
					parametre.getAc() * (matriceT.getS().get(i) - matrice.getS().get(i))
					+ r.nextGaussian() * parametre.getSigmaC().get(i));

			// On suppose que les Fondamentales sont constantes !
			matriceT.setDFi(i, parametre.getAf() * (parametre.getF().get(i) - matrice.getS().get(i))
					+ r.nextGaussian() * parametre.getSigmaF().get(i));
			
			//Les fitness
			matriceT.setAFi(
					i, (Math.exp(matriceT.getS().get(i)) - Math.exp(matrice.getS().get(i))) * matrice_1.getDF().get(i)
							- parametre.getTaxes().get(i)
									* (Math.exp(matriceT.getS().get(i)) + Math.exp(matrice.getS().get(i)))
									* Math.abs(matrice_1.getDF().get(i))
							+ parametre.getB() * matrice_1.getAF().get(i));

			matriceT.setACi(
					i, (Math.exp(matriceT.getS().get(i)) - Math.exp(matrice.getS().get(i))) * matrice_1.getDC().get(i)
							- parametre.getTaxes().get(i)
									* (Math.exp(matriceT.getS().get(i)) + Math.exp(matrice.getS().get(i)))
									* Math.abs(matrice_1.getDC().get(i))
							+ parametre.getB() * matrice_1.getAC().get(i));

		}
		
		// Puis on calcul les poids
		for (int i = 0; i < Main.nb_marche; i++) {
			matriceT.setWCi(i, CalculW.calWCi(i, matriceT, parametre));
			matriceT.setWFi(i, CalculW.calWFi(i, matriceT, parametre));
		}
		// Ici on incrémente la matrice finale avec toutes les valeurs
		return (matriceT);
	}

	//La liste ci-dessous permet de récuperer les valeurs des variables ou de les mettres à jour dans d'autres classes
	public List<Double> getS() {
		return S;
	}

	public void setS(List<Double> s) {
		S = s;
	}

	public void setSi(int i, Double val) {
		S.set(i, val);
	}

	public List<Double> getDC() {
		return DC;
	}

	public void setDC(List<Double> dC) {
		DC = dC;
	}

	public void setDCi(int i, Double val) {
		DC.set(i, val);
	}

	public void setDFi(int i, Double val) {
		DF.set(i, val);
	}

	public List<Double> getDF() {
		return DF;
	}

	public void setDF(List<Double> dF) {
		DF = dF;
	}

	public List<Double> getAC() {
		return AC;
	}

	public void setAC(List<Double> aC) {
		AC = aC;
	}

	public void setACi(int i, Double val) {
		AC.set(i, val);
	}

	public List<Double> getAF() {
		return AF;
	}

	public void setAFi(int i, Double val) {
		AF.set(i, val);
	}

	public void setAF(List<Double> aF) {
		AF = aF;
	}

	public List<Double> getWC() {
		return WC;
	}

	public void setWCi(int i, Double val) {
		WC.set(i, val);
	}

	public void setWC(List<Double> wC) {
		WC = wC;
	}

	public List<Double> getWF() {
		return WF;
	}

	public void setWF(List<Double> wF) {
		WF = wF;
	}

	public void setWFi(int i, Double val) {
		WF.set(i, val);
	}

	/*
	 * public String toString(){ return "Coucou"; }
	 */
}
