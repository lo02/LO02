import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//C'est dans cette classe que le d�roulement d'une partie va s'organiser
public class Partie {
	protected static int tour = 0;
	protected static int manche = 0;
	protected int nbreJoueur = 0;
	protected List<Joueur> listeJoueur = new ArrayList<Joueur>();
	///protected int typePartie = 0;
	/*public static void main(String[] argc)
	{
		// demande du nombre de joueurs
		Scanner saisie = new Scanner(System.in); 
		System.out.println("Saisissez le nombre de joueurs entre 1 et 5 ");
		Partie partie = new Partie(saisie.nextInt());
		
		// Partie Rapide ou Avanc�e 
		boolean vrai = true;
		while(vrai)
		{
			System.out.println("1 - rapide\n2 - avanc�e ");
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
		
		// On cr�e nos joueurs 
		partie.factoryJoueurs();
		
		// On remplit la main de chaque joueur
		for (int i=0 ; i <partie.getListJoueur().size() ; i++)
		{
			partie.getListJoueur().get(i).remplirMainJoueur();
	
		}
		// On affiche la main 
		//System.out.println(partie.getListJoueur().get(0).getMain());
		//partie.getListJoueur().get(0).poserCarte(1, 0);
		//System.out.println(partie.getListJoueur().get(0).getNbreGraine());
		System.out.println(partie.getListJoueur());
		
	}*/
	public Partie (int nbreJoueur){
		this.nbreJoueur = nbreJoueur;
	}
	public static int getTour() {
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
	//M�thodes qui vont nous permettre de jouer les deux types de partie.
	public void initierPartieRapide(){
		//On cr�e un instance carte qui va contenir notre tas de carte
		Card carte = new Ingredient();
		carte.initialisationCartes();
		// On remplit la main et on met deux graines � chaque joueur
		for (int i=0 ; i <this.getListJoueur().size() ; i++)
		{
			this.getListJoueur().get(i).remplirMainJoueur();
			this.getListJoueur().get(i).setNbreGraine(2);
			this.getListJoueur().get(i).setNbreMenhir(0);
		}	
	}
	//Fonction pour Initier la partie
	public void initierPartieAvancee(){
		Card carte = new Ingredient();
		carte.initialisationCartes();
		//On remplit la main des joueurs
		for (int i=0 ; i <this.getListJoueur().size() ; i++)
		{
			this.getListJoueur().get(i).remplirMainJoueur();
			this.getListJoueur().get(i).setNbreMenhir(0);
		}
		//Les joueurs virtuels font leur choix pour le d�but de la premi�re manche
		for (int k=1; k<this.getListJoueur().size() ; k++){
			this.getListJoueur().get(k).choixDebutManche();
		}
	}
	
	/* M�thodes qui va nous permettre de cr�er des joueurs en partie rapide
	 */
	public void factoryJoueurs()
	{
		// On va cr�er tous les joueurs n�cessaires pour le d�roulement du jeu
		
		Virtuel.initialisationNom();
		for(int i=1;i < this.nbreJoueur+1;i++)
		{
			Virtuel joueur = new Virtuel(0,0);
			this.listeJoueur.add(joueur);
		}
	}
	
	
	
	
	public List<Joueur> getListJoueur()
	{
		return this.listeJoueur;
	}
	
	public void setListJoueur(List<Joueur> listeJoueur)
	{
		this.listeJoueur = listeJoueur;
	}
	
	//M�thode qui va nous permettre de g�rer un tour en partie rapide
	public void gererTour(){
		for(int i=1;i<this.listeJoueur.size();i++)
		{
			// choisirCarte[0] : la carte jouer 
			// choisirCarte[1] : type de jeu ( farfadet , g�ant , engrais ) 
			//Cr�ation de l'objet strat�gie
			//On cherche strat�gie � utiliser
			// et on cherche l'adversaire qui � le plus de graines
			Strategy strategie =  this.listeJoueur.get(i).choisirStrategie(this.listeJoueur.get(i),this.chercherJoueurGrainesMax());
			System.out.println(strategie);
			//this.listeJoueur.get(i).poserCarte( ((int) this.listeJoueur.get(i).choisirCarte(strategie).get(0)), ((int) this.listeJoueur.get(i).choisirCarte(strategie).get(1)));
			
			// En fonction de la r�ponse de isOffensive si vrai on joue offensif sinon normal
			if (strategie.isOffensive())
			{
				//Strat�gie offensive
				// On pose la carte avec en premi�re �l�ment la carte que l'on pose et ensuite le joueur que l'on attaque
				this.listeJoueur.get(i).poserCarte(((int) strategie.choisirCarte(this.listeJoueur.get(i)).get(0)), ((Joueur) strategie.choisirCarte(this.listeJoueur.get(i)).get(1) ));
				
			}
			else
			{
				//Strat�gie normal
				//En premier �l�ment on a la carte que l'on pose et ensuite l'action que l'on r�alise
				this.listeJoueur.get(i).poserCarte(((int) strategie.choisirCarte(this.listeJoueur.get(i)).get(0)),((int) strategie.choisirCarte(this.listeJoueur.get(i)).get(1)));
			
			}	
		}
	}
	
	public void gererTourBis(){
		
		for(int i=1;i<this.listeJoueur.size();i++)
		{
			Strategy strategie =  this.listeJoueur.get(i).choisirStrategie(this.listeJoueur.get(i),this.chercherJoueurGrainesMax());
			System.out.println(strategie);
		
			if (strategie.isOffensive())
			{
				//Strat�gie offensive
				// On pose la carte avec en premi�re �l�ment la carte que l'on pose et ensuite le joueur que l'on attaque
				this.listeJoueur.get(i).poserCarte(((int) strategie.choisirCarte(this.listeJoueur.get(i)).get(0)), ((Joueur) strategie.choisirCarte(this.listeJoueur.get(i)).get(1) ));
			
			}
			else
			{
				//Strat�gie normal
				//En premier �l�ment on a la carte que l'on pose et ensuite l'action que l'on r�alise
				this.listeJoueur.get(i).poserCarte(((int) strategie.choisirCarte(this.listeJoueur.get(i)).get(0)),((int) strategie.choisirCarte(this.listeJoueur.get(i)).get(1)));
		
			}	
		}
		
	}
	
	public int chercherGagnant()
	{	
		int max=0;
		int id = 0;
		
		for(int i=0 ; i<this.listeJoueur.size(); i++)
		{
			if (this.listeJoueur.get(i).getNbreMenhir()>= max ){
				id = i;
				max = this.listeJoueur.get(i).getNbreMenhir();
			}
		}
		return id;
	}
	public Joueur chercherJoueurGrainesMax()
	{
		int max=0;
		int id=0;
		for (int i = 0 ; i< this.listeJoueur.size() ; i++)
		{
			if(this.listeJoueur.get(i).getNbreGraine() > max){
				max = this.listeJoueur.get(i).getNbreGraine();
				id = i;
			}
		}
		return this.listeJoueur.get(id);
	}	
}