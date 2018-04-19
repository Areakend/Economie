package ECO;

import ECO.param;

//Calcul des W :  le d�nominateur est le m�me partout, pour plus de clart�, il est calcul� s�par�ment

public class CalculW {

	public static Double getDenom(MatT matrice, param parametre) {
		Double denominateur = 0.0;
		for (int i = 0; i < Main.nb_marche; i++) {
			denominateur = Math.exp(parametre.getC() * matrice.getAC().get(i))
					+ Math.exp(parametre.getC() * matrice.getAF().get(i));
		}
		denominateur = denominateur + Math.exp(0.0);
		return denominateur;
	}

	public static Double calWCi(int i, MatT matrice, param parametre) {
		return Math.exp(parametre.getC() * matrice.getAC().get(i)) / getDenom(matrice, parametre);
	}

	public static Double calWFi(int i, MatT matrice, param parametre) {
		return Math.exp(parametre.getC() * matrice.getAF().get(i)) / getDenom(matrice, parametre);
	}
}
