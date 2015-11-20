package jeubataille;

public class Carte {
	 public final static int PIC = 0;
	    public final static int COEUR = 1;
	    public final static int CARREAU = 2;
	    public final static int TREFLE = 3;

	    public final static int SEPT = 0;
	    public final static int HUIT = 1;
	    public final static int NEUF = 2;
	    public final static int DIX = 3;
	    public final static int VALET = 4;
	    public final static int DAME = 5;
	    public final static int ROI = 6;
	    public final static int AS = 7;

	    public final static String[] COULEURS = {"Pic", "Coeur", "Carreau", "Trefle"};
	    public final static String[] VALEURS = {"Sept", "Huit", "Neuf", "Dix", "Valet", "Dame", "Roi", "As"};

	    private int couleur;
	    private int valeur;

	    public Carte (int valeur, int couleur) {
		this.setCouleur(couleur);
		this.setValeur(valeur);
	    }

	    public Carte (String valeur, String couleur) {

		int i = 0;
		int c = 0, v = 0;
		boolean trouve = false;

		while (i < Carte.COULEURS.length && trouve == false) {
		    if (couleur.equals(Carte.COULEURS[i])) {
			trouve = true;
			c = i;
		    } else {
			i++;
		    }
		}

		i = 0;
		trouve = false;

		while (i < Carte.VALEURS.length && trouve == false) {
		    if (valeur.equals(Carte.VALEURS[i])) {
			trouve = true;
			v = i;
		    } else {
			i++;
		    }
		}

		this.setCouleur(c);
		this.setValeur(v);
	    }

	    public int getCouleur() {
		return couleur;
	    }

	    public void setCouleur(int couleur) {
		if (couleur >= Carte.PIC && couleur <= Carte.TREFLE) {
		    this.couleur = couleur;
		}
	    }

	    public int getValeur() {
		return valeur;
	    }

	    public void setValeur(int valeur) {
		if (valeur >= Carte.SEPT && valeur <= Carte.AS) {
		    this.valeur = valeur;
		}
	    }

	    public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(Carte.VALEURS[this.valeur]);
		sb.append(" de ");
		sb.append(Carte.COULEURS[this.couleur]);
		return sb.toString();
	    }    
	
}
