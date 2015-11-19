//C'est dans cette classe que le déroulement d'une partie va s'organiser
public class Partie {
	protected int tour = 4;
	protected int manche = 4;
	protected int nbreJoueur = 0;
	
	public Partie (int nbreJoueur){
		this.nbreJoueur = nbreJoueur;
	}
	
	public int getTour() {
		return tour;
	}
	public void setTour(int tour) {
		this.tour = tour;
	}
	public int getManche() {
		return manche;
	}
	public void setManche(int manche) {
		this.manche = manche;
	}
	public int getNbreJoueur() {
		return nbreJoueur;
	}
	public void setNbreJoueur(int nbreJoueur) {
		this.nbreJoueur = nbreJoueur;
	}
	
	
	//Méthodes qui vont nous permettre de jouer les deux types de partie.
	public void jouerPartieRapide(){
		
	}
	public void jouerPartieAvancee(){
		
	}
	
}
