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

	
	public MatT(List<Double> S, List<Double> DC, List<Double> DF, List<Double> AC, List<Double>AF, List<Double> WC, List<Double> WF) {
		this.S = S;
		this.DC = DC;
		this.DF = DF;
		this.AC = AC;
		this.AF = AF;		
	}
	
	//matrice_1 est la matrice à T-2, matrice est la matrice à t - 1
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
		MatT matriceT = new MatT (iniS, iniDC, iniDF, iniAC, iniAF, iniWC, iniWF);;
		// Ici on calule la matrice à l'instant T
		matriceT.setS1(matrice.getS1() + parametre.getAm()*(matrice.getWC1()*matrice.getDC1() + matrice.getWF1()*matrice.getDF1())
				+ r.nextGaussian()*parametre.getSigmam1());
		
		matriceT.setS2(matrice.getS2() + parametre.getAm()*(matrice.getWC2()*matrice.getDC2() + matrice.getWF2()*matrice.getDF2())
				+ r.nextGaussian()*parametre.getSigmam2());
		
		matriceT.setDC1(parametre.getAc()*(matriceT.getS1() - matrice.getS1()) + r.nextGaussian()*parametre.getSigmac1());
		
		matriceT.setDC2(parametre.getAc()*(matriceT.getS2() - matrice.getS2()) + r.nextGaussian()*parametre.getSigmac2());
		
		//On suppose que les Fondamentales sont constantes !
		matriceT.setDF1(parametre.getAf()*(parametre.getF1() - matriceT.getS1()) + r.nextGaussian()*parametre.getSigmaf1());

		matriceT.setDF2(parametre.getAf()*(parametre.getF2() - matriceT.getS2()) + r.nextGaussian()*parametre.getSigmaf2());

		matriceT.setAC1( (Math.exp(matriceT.getS1()) - Math.exp(matrice.getS1()))*matrice_1.getDC1() 
				- parametre.getTaxe1()*(Math.exp(matriceT.getS1()) + Math.exp(matrice.getS1()))*Math.abs(matrice_1.getDC1())
				+ parametre.getB()*matrice.getAC1());
		
		matriceT.setAF1( (Math.exp(matriceT.getS1()) - Math.exp(matrice.getS1()))*matrice_1.getDF1() 
				- parametre.getTaxe1()*(Math.exp(matriceT.getS1()) + Math.exp(matrice.getS1()))*Math.abs(matrice_1.getDF1())
				+ parametre.getB()*matrice.getAF1());
		
		matriceT.setAC2( (Math.exp(matriceT.getS2()) - Math.exp(matrice.getS2()))*matrice_1.getDC2() 
				- parametre.getTaxe2()*(Math.exp(matriceT.getS2()) + Math.exp(matrice.getS2()))*Math.abs(matrice_1.getDC2())
				+ parametre.getB()*matrice.getAC2());
		
		matriceT.setAF2( (Math.exp(matriceT.getS2()) - Math.exp(matrice.getS2()))*matrice_1.getDF2() 
				- parametre.getTaxe2()*(Math.exp(matriceT.getS2()) + Math.exp(matrice.getS2()))*Math.abs(matrice_1.getDF2())
				+ parametre.getB()*matrice.getAF2());
		
		matriceT.setWC1(CalculW.calWC1(matriceT,parametre));
		matriceT.setWF1(CalculW.calWF1(matriceT,parametre));
		matriceT.setWC2(CalculW.calWC2(matriceT,parametre));
		matriceT.setWF2(CalculW.calWF2(matriceT,parametre));
		
		//Ici on incrémente la matrice finale avec toutes les valeurs
		return(matriceT);
		
	}

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

	

  /*  public String toString(){
        return "Coucou";
    }
*/
}
