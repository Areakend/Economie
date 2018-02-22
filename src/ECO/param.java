package ECO;

public class param {
	
	private double am;
	private double b;
	private double ac;
	private double af;
	private double sigmac1;
	private double sigmac2;
	private double sigmaf1;
	private double sigmaf2;
	private double sigmam1;
	private double sigmam2;
	private double taxe1;
	private double taxe2;
	private double c;
	private double f1;
	private double f2;
	
	
	public param(double am, double b, double ac, double af, double sigmac1, double sigmac2, double taxe1, double taxe2, double c, double sigmaf1, double sigmaf2, double sigmam1, double sigmam2, double f1, double f2) {
		this.am = am;
		this.b = b;
		this.ac = ac;
		this.af = af;
		this.sigmac1 = sigmac1;
		this.sigmac2 = sigmac2;
		this.sigmaf1 = sigmaf1;
		this.sigmaf2 = sigmaf2;
		this.sigmam1 = sigmam1;
		this.sigmam2 = sigmam2;
		this.taxe1 = taxe1;
		this.taxe2 = taxe2;
		this.c = c;
		this.f1 = f1;
		this.f2 = f2;
	}

	
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
	public double getSigmac1() {
		return sigmac1;
	}
	public void setSigmac1(double sigmac1) {
		this.sigmac1 = sigmac1;
	}
	public double getSigmac2() {
		return sigmac2;
	}
	public void setSigmac2(double sigmac2) {
		this.sigmac2 = sigmac2;
	}
	public double getSigmaf1() {
		return sigmaf1;
	}
	public void setSigmaf1(double sigmaf1) {
		this.sigmaf1 = sigmaf1;
	}
	public double getSigmaf2() {
		return sigmaf2;
	}
	public void setSigmaf2(double sigmaf2) {
		this.sigmaf2 = sigmaf2;
	}
	public double getSigmam1() {
		return sigmam1;
	}
	public void setSigmam1(double sigmam1) {
		this.sigmam1 = sigmam1;
	}
	public double getSigmam2() {
		return sigmam2;
	}
	public void setSigmam2(double sigmam2) {
		this.sigmam2 = sigmam2;
	}
	public double getTaxe1() {
		return taxe1;
	}
	public void setTaxe1(double taxe1) {
		this.taxe1 = taxe1;
	}
	public double getTaxe2() {
		return taxe2;
	}
	public void setTaxe2(double taxe2) {
		this.taxe2 = taxe2;
	}
	public double getC() {
		return c;
	}
	public void setC(double c) {
		this.c = c;
	}
	public double getF1() {
		return f1;
	}
	public void setF1(double f1) {
		this.f1 = f1;
	}
	public double getF2() {
		return f2;
	}
	public void setF2(double f2) {
		this.f2 = f2;
	}
	
}
