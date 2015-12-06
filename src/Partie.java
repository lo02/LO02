import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

//C'est dans cette classe que le déroulement d'une partie va s'organiser
public class Partie {
	protected static int tour = 0;
	protected static int manche = 0;
	protected int nbreJoueur = 0;
	protected List<Joueur> listeJoueur = new LinkedList<Joueur>();
	
	protected int action;
	protected int valeur;
	

	public Partie (int nbreJoueur){
		this.nbreJoueur = nbreJoueur;
	}
	//Méthodes qui vont nous permettre de jouer les deux types de partie.
	public void initierPartieRapide(){
		//On crée un instance carte qui va contenir notre tas de carte
		Card carte = new Ingredient();
		carte.initialisationCartes();
		// On remplit la main et on met deux graines à chaque joueur
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
		Card carte2 = new Allie();
		carte2.initialisationCartes();
		//On remplit la main des joueurs
		for (int i=0 ; i <this.getListJoueur().size() ; i++)
		{
			this.getListJoueur().get(i).remplirMainJoueur();
			//On sauvegarde les points de chaque joueurs.
			this.getListJoueur().get(i).setCompteurMenhir(this.getListJoueur().get(i).getNbreMenhir()+this.getListJoueur().get(i).getCompteurMenhir());
			this.getListJoueur().get(i).setNbreMenhir(0);
		}
		//Les joueurs virtuels font leur choix pour le début de la première manche
		for (int k=1; k<this.getListJoueur().size() ; k++){
			this.getListJoueur().get(k).choixDebutManche();
		}
	}
	/* Méthodes qui va nous permettre de créer des joueurs en partie rapide
	 */
	public void factoryJoueurs()
	{
		// On va créer tous les joueurs nécessaires pour le déroulement du jeu
		
		Virtuel.initialisationNom();
		for(int i=1;i < this.nbreJoueur+1;i++)
		{
			Virtuel joueur = new Virtuel(0,0);
			this.listeJoueur.add(joueur);
		}
	}
	//Méthode qui va nous permettre de gérer un tour en partie rapide
	public void gererTour(){
		for(int i=1;i<this.listeJoueur.size();i++)
		{
			// choisirCarte[0] : la carte jouer 
			// choisirCarte[1] : type de jeu ( farfadet , géant , engrais ) 
			//Création de l'objet stratégie
			//On cherche stratégie à utiliser
			// et on cherche l'adversaire qui à le plus de graines
			Strategy strategie =  this.listeJoueur.get(i).choisirStrategie(this.listeJoueur.get(i),this.chercherJoueurGrainesMax());
		
			
			//this.listeJoueur.get(i).poserCarte( ((int) this.listeJoueur.get(i).choisirCarte(strategie).get(0)), ((int) this.listeJoueur.get(i).choisirCarte(strategie).get(1)));
			
			// En fonction de la réponse de isOffensive si vrai on joue offensif sinon normal
			int carte = (int) strategie.choisirCarte(this.listeJoueur.get(i)).get(0);
			
			if (strategie.isOffensive())
			{
				//Stratégie offensive
				// On pose la carte avec en première élément la carte que l'on pose et ensuite le joueur que l'on attaque
				//int carte = (int) strategie.choisirCarte(this.listeJoueur.get(i)).get(0);
				Joueur joueurcible = (Joueur) strategie.choisirCarte(this.listeJoueur.get(i)).get(2);
				Joueur joueur = this.listeJoueur.get(i);
		
				Main.afficherActionoff(joueur, joueurcible, carte);
				this.listeJoueur.get(i).poserCarte(carte, joueurcible);
				Main.pause();
				
			}
			else
			{
				//Stratégie normal
				//En premier élément on a la carte que l'on pose et ensuite l'action que l'on réalise
				//int carte = (int) strategie.choisirCarte(this.listeJoueur.get(i)).get(0);
				int action = (int) strategie.choisirCarte(this.listeJoueur.get(i)).get(1);
				Joueur joueur = this.listeJoueur.get(i);
				Main.afficherAction(joueur, carte, action);
				this.listeJoueur.get(i).poserCarte(carte,action);
				Main.pause();
			}	
		}
	}
	public void gererTourAvancee(Joueur joueur){
		
			// choisirCarte[0] : la carte jouer 
			// choisirCarte[1] : type de jeu ( farfadet , géant , engrais ) 
			//Création de l'objet stratégie
			//On cherche stratégie à utiliser
			// et on cherche l'adversaire qui à le plus de graines
			Strategy strategie =  joueur.choisirStrategie(joueur,this.chercherJoueurGrainesMax());
			//joueur.poserCarte( ((int) this.listeJoueur.get(i).choisirCarte(strategie).get(0)), ((int) this.listeJoueur.get(i).choisirCarte(strategie).get(1)));
			
			// En fonction de la réponse de isOffensive si vrai on joue offensif sinon normal
			int carte = (int) strategie.choisirCarte(joueur).get(0);
			if (strategie.isOffensive())
			{
				//Stratégie offensive
				//On pose la carte avec en première élément la carte que l'on pose et ensuite le joueur que l'on attaque
				//On joue avec poserCarteBis pour vois si l'adverssairepeut jouer un chien
				
				Joueur joueurcible = (Joueur) strategie.choisirCarte(joueur).get(2);
			
				Main.afficherActionoff(joueur, joueurcible, carte);
				joueur.poserCarteBis(carte, joueurcible);
				//On cherche si on peut jouer la taupe
				joueur.jouerTaupe(this.chercherJoueurMenhirMax(),this.chercherJoueurSecondMax());
			}
			else
			{
				//Stratégie normal
				//En premier élément on a la carte que l'on pose et ensuite l'action que l'on réalise
				//int carte = (int) strategie.choisirCarte(joueur).get(0);
				int action = (int) strategie.choisirCarte(joueur).get(1);
			//	Joueur joueur = joueur;
				Main.afficherAction(joueur, carte, action);
				joueur.poserCarte(carte,action);
				joueur.jouerTaupe(this.chercherJoueurMenhirMax(),this.chercherJoueurSecondMax());
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
	
	public List<Joueur> chercherGagnantRapide(){
		int max=0;
		int id = 0;
		
		//Cette première boucle cherche le joueur qui à le plus de Menhir
		for(int i=0 ; i<this.listeJoueur.size(); i++)
		{
			if (this.listeJoueur.get(i).getNbreMenhir()>= max ){
				id = i;
				max = this.listeJoueur.get(i).getNbreMenhir();
			}
		}
		//On crée une liste temporaire qui va nous servir à contenir les joueurs qui auront le même nbre de menhir
		List<Joueur> listeJoueurBis = new LinkedList<Joueur>();
		//Boucle qui va ajouter tous les joueurs qui ont le nombre max de menhir
		for(int i=0 ; i<this.listeJoueur.size(); i++)
		{
			if (this.listeJoueur.get(i).getNbreMenhir() == max ){
				
				listeJoueurBis.add(this.listeJoueur.get(i));
			}
		}
		//On crée une nouvelle liste qui va contenir les joueurs qui auront le même nbre de graines
		List<Joueur> listeJoueurBis2 = new LinkedList<Joueur>();
		//On cherche la valeur max pour le nombre de graine parmi les joueurs
		int max2 = this.chercherJoueurGrainesMax().getNbreGraine();
		//Boucle qui va chercher tous les joueurs qui ont le plus de graine
		for(int i=0; i<listeJoueurBis.size(); i++)
		{
			if (listeJoueurBis.get(i).getNbreGraine() == max2 ){
				listeJoueurBis2.add(listeJoueurBis.get(i));
			}
		}
		//Si les joueurs ont le même nbre de menhir, on renvoie alors les joueurs qui ont le plus de graines
		if(listeJoueurBis2.size()>0)
			return listeJoueurBis2;
		//Sinon on renvoie le joueur qui a le plus de menhir
		else
			return listeJoueurBis;
	}
	
	public int chercherGagnantAvancee(){
		int max = 0;
		int id = 0;
		
		for(int i=0 ; i<this.listeJoueur.size(); i++)
		{
			if (this.listeJoueur.get(i).getCompteurMenhir()>= max ){
				id = i;
				max = this.listeJoueur.get(i).getNbreMenhir();
			}
		}
		
		return id;
		
		//return listeJoueur;
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
	public Joueur chercherJoueurMenhirMax()
	{
		int max=0;
		int id=0;
		for (int i = 0 ; i< this.listeJoueur.size() ; i++)
		{
			if(this.listeJoueur.get(i).getNbreMenhir() > max){
				max = this.listeJoueur.get(i).getNbreMenhir();
				id = i;
			}
		}
		return this.listeJoueur.get(id);
	}
	public Joueur chercherJoueurSecondMax()
	{
		
		int max = 0;
		int id[] = new int[2];
		for (int i = 0 ; i< this.listeJoueur.size() ; i++)
		{
			if(this.listeJoueur.get(i).getNbreMenhir() > max){
				
				id[1] = id[0];
				max = this.listeJoueur.get(i).getNbreMenhir();
				id[0] = i;
			}
		}
		return this.listeJoueur.get(id[1]);
	}
	public List<Joueur> arrangerOrdreListe(int decale)
	{
		
		List<Joueur> nouvelleListe = new LinkedList<Joueur>();
		int j=0;
		
		for(int i = 0 ;i<this.listeJoueur.size() ;i++)
		{
			if(i+decale >= this.getListJoueur().size())
			{
				nouvelleListe.add( this.getListJoueur().get(j));
				j++;
			}else
			{
			nouvelleListe.add( this.getListJoueur().get(i+decale));	
			}
			
		}
		
		return nouvelleListe;
		
	}
	
	public void finPartie(){
		for(int i=0; i<this.getListJoueur().size(); i++ ){
			this.getListJoueur().get(i).setNbreMenhir(this.getListJoueur().get(i).getCompteurMenhir());
		}
	}
	public static int getTour() {
		return tour;
	}
	public int getManche() {
		return manche;
	}
	public List<Joueur> getListJoueur()
	{
		return this.listeJoueur;
	}
	public int getNbreJoueur() {
		return nbreJoueur;
	}
	public void setManche(int manche) {
		this.manche = manche;
	}
	public void setNbreJoueur(int nbreJoueur) {
		this.nbreJoueur = nbreJoueur;
	}
	public void setTour(int tour) {
		this.tour = tour;
	}
	public void setListJoueur(List<Joueur> listeJoueur)
	{
		this.listeJoueur = listeJoueur;
	}
}