package ECO;

import java.util.LinkedList;
import java.util.List;

public class param {

	private double am;
	private double b;
	private double ac;
	private double af;
	private List<Double> sigmaC = new LinkedList<Double>();
	private List<Double> sigmaF = new LinkedList<Double>();
	private List<Double> sigmaM = new LinkedList<Double>();
	private List<Double> taxes = new LinkedList<Double>();
	private double c;
	private List<Double> F = new LinkedList<Double>();

	// On crée une variable appelée param, qui contient les paramètres listés ci-dessus
	public param(double am, double b, double ac, double af, List<Double> sigmaC, List<Double> taxes, double c,
			List<Double> sigmaF, List<Double> sigmaM, List<Double> F) {
		this.am = am;
		this.b = b;
		this.ac = ac;
		this.af = af;
		this.sigmaC = sigmaC;
		this.sigmaF = sigmaF;
		this.sigmaM = sigmaM;
		this.taxes = taxes;
		this.c = c;
		this.F = F;
	}

	//La liste ci-dessous permet de récuperer les valeurs des variables ou de les mettres à jour dans d'autres classes

	public double getAm() {
		return am;
	}

	public void setAm(double am) {
		this.am = am;
	}

	public double getB() {
		return b;
	}

	public void setB(double b) {
		this.b = b;
	}

	public double getAc() {
		return ac;
	}

	public void setAc(double ac) {
		this.ac = ac;
	}

	public double getAf() {
		return af;
	}

	public void setAf(double af) {
		this.af = af;
	}

	public List<Double> getSigmaC() {
		return sigmaC;
	}
	
	public Double getSigmaCi(int i) {
		return sigmaC.get(i);
	}
	
	public Double getSigmaFi(int i) {
		return sigmaF.get(i);
	}
	
	public void setSigmaC(List<Double> sigmaC) {
		this.sigmaC = sigmaC;
	}

	public List<Double> getSigmaF() {
		return sigmaF;
	}

	public void setSigmaF(List<Double> sigmaF) {
		this.sigmaF = sigmaF;
	}

	public List<Double> getSigmaM() {
		return sigmaM;
	}

	public void setSigmaM(List<Double> sigmaM) {
		this.sigmaM = sigmaM;
	}

	public List<Double> getTaxes() {
		return taxes;
	}

	public void setTaxes(List<Double> taxes) {
		this.taxes = taxes;
	}

	public double getC() {
		return c;
	}

	public void setC(double c) {
		this.c = c;
	}

	public List<Double> getF() {
		return F;
	}

	public void setF(List<Double> f) {
		F = f;
	}

	

}
