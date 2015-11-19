
public class Joueur {
	protected String nom = "";
	protected int nbreMenhir = 0;
	protected int nbreGraine = 0;

	public String getNom() {
		return nom;
	}

	public Joueur(String nom, int nbreMenhir, int nbreGraine) {
		this.nom = nom;
		this.nbreMenhir = nbreMenhir;
		this.nbreGraine = nbreGraine;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getNbreMenhir() {
		return nbreMenhir;
	}

	public void setNbreMenhir(int nbreMenhir) {
		this.nbreMenhir = nbreMenhir;
	}

	public int getNbreGraine() {
		return nbreGraine;
	}

	public void setNbreGraine(int nbreGraine) {
		this.nbreGraine = nbreGraine;
	}
	
	
}
