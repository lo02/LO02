import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

//C'est dans cette classe que le déroulement d'une partie va s'organiser
/**
 * 
 * @author Gilles
 * Partie est la classe qui permet de gérer tous le contenu du jeu du menhir.
 */
public class Partie {
	/**
	 * Valeur qui va gérer les tours
	 * @see Partie#setTour(int)
	 * @see Partie#getTour()
	 */
	protected static int tour = 0;
	/**
	 * Valeur de la manche en cours
	 * @see Partie#setManche(int)
	 * @see Partie#getManche()
	 */
	protected static int manche = 0;
	/**
	 * Valeur qui contient le nombre de joueurs dans la partie
	 * @see Partie#setNbreJoueur(int)
	 * @see Partie#getNbreJoueur()
	 */
	protected int nbreJoueur = 0;
	/**
	 * Liste qui contient chaque joueur de la partie en cours
	 * @see Partie#setListJoueur(List)
	 * @see Partie#getListJoueur()
	 */
	protected List<Joueur> listeJoueur = new LinkedList<Joueur>();
	/**
	 * 
	 */
	//protected int action;
	/**
	 * 
	 */
	//protected int valeur;
	

	/**
	 * Construteur d'une Partie de jeu du menhir.
	 * Il prend en paramètre le nombre de joueur qui compose une partie.
	 * @param nbreJoueur
	 * 					C'est le nombre de joueur
	 */
	public Partie (int nbreJoueur){
		this.nbreJoueur = nbreJoueur;
	}
	//Méthodes qui vont nous permettre de jouer les deux types de partie.
	/**
	 * Cette méthode permet d'initier une partie rapide. Elle va se charger de créer le carte ingrédient,
	 * de remplir la main de chaque joueur ainsi que de leur attribuer les 2 graines pour le début de la 
	 * partie.
	 */
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
	/**
	 * Cette méthode permet d'initier une partie avancée. Elle va se charger de créer les cartes Ingrédient, 
	 * ainsi que les cartes alliées. Elle permet de remplir la main de chaque joueurs. A chaque début de manche,
	 * elle va enregistrer le nombre de menhir de la manche précedente pour chaque joueur. Elle va 
	 * également demander pour chaque joueur virtuel, le choix qu'il souhaite faire pour le début de la manche.
	 */
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
	/**
	 * Méthode qui va permettre de créer les joueurs pour chaque partie.
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
	/**
	 * Méthode qui va permettre de gérer un tour lors d'une partie avancée
	 * pour les joueurs virtuels.
	 */
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
	/**
	 * Méthode permettant de gérer un tour lors d'une partie avancée pour les joueurs virtuels.
	 * @param joueur
	 * 			Variable qui contient les données d'une joueur virtuel
	 */
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
	/**
	 * Méthode qui va chercher un gagant. Elle retourne la position du joueur dans la liste.
	 * @return 
	 * 				Position du joueur dans la liste des joueurs
	 */
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
	/**
	 * Méthode qui va chercher un gagnant dans une partie rapide.
	 * @return
	 */
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
	/**
	 * Méthodes qui va chercher un gagnant dans une partie avancée
	 * @return
	 */
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
	
	/**
	 * Méthodes qui va chercher le joueur qui posséde le plus de graines.
	 * @return 
	 * 			Retourne le joueur qui posséde le plus de graines
	 */
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
	
	/**
	 * Méthode qui va chercher le joueur qui posséde le plus de menhir.
	 * @return
	 * 			Retourne le joueur qui à le plus de menhir
	 * 
	 */
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
	
	/**
	 * 
	 * @return
	 */
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
	
	/**
	 * Méthode qui permettre de changer l'ordre des joueurs à chaque début de manche lors d'une partie
	 * avancée.
	 * @param decale
	 * 
	 * @return
	 * 			Retourne la nouvelle liste de joueurs
	 */
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
	
	/**
	 * Méthode qui va
	 */
	public void finPartie(){
		for(int i=0; i<this.getListJoueur().size(); i++ ){
			this.getListJoueur().get(i).setNbreMenhir(this.getListJoueur().get(i).getCompteurMenhir());
		}
	}
	
	/**
	 * Méthode qui donne la valeur du tour.
	 * @return
	 * 		renvoie la valeur du tour
	 */
	public static int getTour() {
		return tour;
	}
	
	/**
	 * Méthode qui donne la valeur de la manche.
	 * @return
	 * 		renvoie la valeur de la manche
	 */
	public int getManche() {
		return manche;
	}
	
	/**
	 * Méthode qui donne la liste de joueur de la partie.
	 * @return
	 * 		renvoie la liste de joueur de la partie
	 */
	public List<Joueur> getListJoueur()
	{
		return this.listeJoueur;
	}
	
	/**
	 * Méthode qui donne le nombre de joueur de la partie.
	 * @return
	 * 		renvoie le nombre de joueur de la partie
	 */
	public int getNbreJoueur() {
		return nbreJoueur;
	}
	
	/**
	 * Méthode qui actualise la valeur de la manche
	 * @param manche
	 * 				nouvelle valeur de la manche 
	 */
	public void setManche(int manche) {
		this.manche = manche;
	}
	
	/**
	 * Méthode qui actualise la valeur de la manche.
	 * @param manche
	 * 				nouvelle valeur de la manche 
	 */
	public void setNbreJoueur(int nbreJoueur) {
		this.nbreJoueur = nbreJoueur;
	}
	
	/**
	 * Méthode qui actualise la valeur du tour.
	 * @param manche
	 * 				nouvelle valeur du tour
	 */
	public void setTour(int tour) {
		this.tour = tour;
	}
	
	/**
	 * Méthode qui actualise la liste de joueur
	 * @param manche
	 * 				nouvelle liste de joueur
	 */
	public void setListJoueur(List<Joueur> listeJoueur)
	{
		this.listeJoueur = listeJoueur;
	}
}