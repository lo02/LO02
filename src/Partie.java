import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//C'est dans cette classe que le déroulement d'une partie va s'organiser
public class Partie {
	protected int tour = 4;
	protected int manche = 4;
	protected int nbreJoueur = 0;
	protected List<Joueur> listeJoueur = new ArrayList<Joueur>();
	
	
	public static void main(String[] argc)
	{
		// demande du nombre de joueurs
		Scanner saisie = new Scanner(System.in); 
		System.out.println("Saisissez le nombre de joueurs entre 1 et 5 ");
		Partie partie = new Partie(saisie.nextInt());
		
		// Partie Rapide ou Avancée 
		boolean vrai = true;
		while(vrai)
		{
			System.out.println("1 - rapide\n 2 - avancée ");
			int reponse = saisie.nextInt();
			switch (reponse)
			{
			case 1: partie.jouerPartieRapide();
			vrai = false;
			break;
			case 2: partie.jouerPartieAvancee();
			vrai=false;
			break;
			default: System.out.println("Taper 1 ou 2" );
			break;
			}
		}
		
		// On crée nos joueurs 
		partie.factoryJoueurs();
		
		// On remplit la main de chaque joueur
		for (int i=0 ; i <partie.getListJoueur().size() ; i++)
		{
			partie.getListJoueur().get(i).remplirMainJoueur();
			System.out.println(partie.getListJoueur().get(i).getMain());
		}		
	}
	
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
		Card carte = new Ingredient();
		carte.initialisationCartes();
		
		
	}
	public void jouerPartieAvancee(){
		
	}
	
	public void factoryJoueurs()
	{
		// On va créer tous les joueurs nécessaires pour le déroulement du jeu
		
		for(int i=0;i < this.nbreJoueur+1;i++)
		{
			Joueur joueur = new Joueur("",0,2);
			this.listeJoueur.add(joueur);
		}
	}
	
	public List<Joueur> getListJoueur()
	{
		return this.listeJoueur;
	}
	
}
