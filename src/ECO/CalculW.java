package ECO;
import ECO.param;

//Calcul des W :  le dénominateur est le même partout, pour plus de clarté, il est calculé séparément

public class CalculW {
	
	public static Double getDenom(MatT matrice, param parametre) {
		Double denominateur = Math.exp(parametre.getC()*matrice.getAC1()) + Math.exp(parametre.getC()*matrice.getAF1()) 
			+ Math.exp(parametre.getC()*matrice.getAF2()) + Math.exp(parametre.getC()*matrice.getAC2()) + Math.exp(0.0) ;
		return denominateur;
	}
	
	public static Double calWC1 (MatT matrice, param parametre) {
		return Math.exp(parametre.getC()*matrice.getAC1())/getDenom(matrice, parametre);
	}
	
	public static Double calWF1 (MatT matrice, param parametre) {
		return Math.exp(parametre.getC()*matrice.getAF1())/getDenom(matrice, parametre);
	}
	
	public static Double calWC2 (MatT matrice, param parametre) {
		return Math.exp(parametre.getC()*matrice.getAC2())/getDenom(matrice, parametre);
	}
	
	public static Double calWF2 (MatT matrice, param parametre) {
		return Math.exp(parametre.getC()*matrice.getAF2())/getDenom(matrice, parametre);
	}
}
