package ECO;

import java.util.Random;

public class MatT {
	
	private Double S1;
	private Double S2;
	private Double DC1;
	private Double DC2;
	private Double DF1;
	private Double DF2;
	private Double AC1;
	private Double AF1;
	private Double AC2;
	private Double AF2;
	private Double WC1;
	private Double WF1;
	private Double WC2;
	private Double WF2;
	
	public MatT(Double S1, Double S2, Double DC1, Double DC2, Double DF1, Double DF2, Double AC1, Double AF1, Double AC2, Double AF2, Double WC1, Double WF1, Double WC2, Double WF2) {
		this.S1 = S1;
		this.S2 = S2;
		this.DC1 = DC1;
		this.DC2 = DC2;
		this.DF1 = DF1;
		this.DF2 = DF2;
		this.AC1 = AC1;
		this.AF1 = AF1;
		this.AC2 = AC2;
		this.AF2 = AF2;
		
	}
	
	//matrice_1 est la matrice à T-2, matrice est la matrice à t - 1
	public static MatT incrementation(MatT matrice, MatT matrice_1, param parametre) {
		Random r = new Random();
		MatT matriceT = new MatT (0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);;
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
	
	public Double getS1() {
		return S1;
	}

	public void setS1(Double s1) {
		S1 = s1;
	}

	public Double getS2() {
		return S2;
	}

	public void setS2(Double s2) {
		S2 = s2;
	}

	public Double getDC1() {
		return DC1;
	}

	public void setDC1(Double dC1) {
		DC1 = dC1;
	}

	public Double getDC2() {
		return DC2;
	}

	public void setDC2(Double dC2) {
		DC2 = dC2;
	}

	public Double getDF1() {
		return DF1;
	}

	public void setDF1(Double dF1) {
		DF1 = dF1;
	}

	public Double getDF2() {
		return DF2;
	}

	public void setDF2(Double dF2) {
		DF2 = dF2;
	}

	public Double getAC1() {
		return AC1;
	}

	public void setAC1(Double aC1) {
		AC1 = aC1;
	}

	public Double getAF1() {
		return AF1;
	}

	public void setAF1(Double aF1) {
		AF1 = aF1;
	}

	public Double getAC2() {
		return AC2;
	}

	public void setAC2(Double aC2) {
		AC2 = aC2;
	}

	public Double getAF2() {
		return AF2;
	}

	public void setAF2(Double aF2) {
		AF2 = aF2;
	}

	public Double getWC1() {
		return WC1;
	}

	public void setWC1(Double wC1) {
		WC1 = wC1;
	}

	public Double getWF1() {
		return WF1;
	}

	public void setWF1(Double wF1) {
		WF1 = wF1;
	}

	public Double getWC2() {
		return WC2;
	}

	public void setWC2(Double wC2) {
		WC2 = wC2;
	}

	public Double getWF2() {
		return WF2;
	}

	public void setWF2(Double wF2) {
		WF2 = wF2;
	}

  /*  public String toString(){
        return "Coucou";
    }
*/
}
