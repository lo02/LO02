import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
/**
 * 
 * @author Anass et Gilles
 * Classe qui contient le code de joueur ainsi que les m�thodes qui vont lui permettre de jouer.
 */
public class Joueur {

	/**
	 * Attribut qui contient le nom du joueur
	 * @see Joueur#getNom()
	 * @see Joueur#setNom(String)
	 */
	protected String nom = "";
	/**
	 * Attribut qui contient le nombre de menhirs d'un joueur
	 * @see Joueur#getNbreMenhir()
	 * @see Joueur#setNbreMenhir(int)
	 */
	protected int nbreMenhir = 0;
	/**
	 * Attibut qui contient le nombre de graines d'un joueur
	 * @see Joueur#getNbreGraine()
	 * @see Joueur#setNbreGraine(int)
	 */
	protected int nbreGraine = 0;
	/**
	 * Attribut qui contient la main d'un joueur
	 * @see Joueur#getMain()
	 * 
	 */
	protected List<Card> main = new ArrayList<Card>();
	/**
	 * Atribut qui contient la carte alli� d'un joueur
	 * @see Joueur#getAllie()
	 * @see Joueur#setAllie(Card)
	 */
	protected Card allie  ;
	/**
	 * Attribut qui va compter les menhirs d'un joueur
	 * @see Joueur#getCompteurMenhir()
	 * @see Joueur#setCompteurMenhir(int)
	 */
	protected int compteurMenhir = 0;
	
	/**
	 * Construteur par d�faut de Joueur
	 */
	public Joueur (){
		this.nom = "";
	}
	/**
	 * Construteur de Joueur avec des param�tres
	 * @param nom
	 * @param nbreMenhir
	 * @param nbreGraine
	 */
	public Joueur(String nom, int nbreMenhir, int nbreGraine) {
		this.nom = nom;
		this.nbreMenhir = nbreMenhir;
		this.nbreGraine = nbreGraine;
	}

	/**
	 * M�thode qui permet aux joueurs virtuels d'effectuer leurs choix au d�but d'une manche en partie avanc�e
	 */
	public void choixDebutManche(){};
	
	/*M�thode qui va permettre de choisir soit une carte alli�e soit 2 graines en d�but de manche en partie
	 * avanc�e;
	 * Pour prendre des graines en met 1, on met 2 pour avoir une carte alli�e.
	*/
	/**
	 * M�thode qui permet d'attribuer au joueur physique le choix entre une carte alli�e ou bien deux graines, au
	 * d�but d'une manche en partie avanc�e.
	 * @param choix
	 */
	public void choixDebutManche(int choix){
		if (choix == 1){
			this.setNbreGraine(this.getNbreGraine()+2);
			if (this.getAllie()!=null)
			{
				this.getAllie().deleteAllie();
			}
			
		}
		else
			if (choix == 2){
				this.remplirMainJoueurAllie();
			}
	}

	/**
	 * M�thode qui permet de remplir la main de chaque joueur.
	 */
	public void remplirMainJoueur()
	{
		Card tasDeCarte = new Ingredient();		
		// linked list mettre un queue
		List<Card> tempTas = new ArrayList<Card>();
		tempTas.addAll(tasDeCarte.getTasDeCartes());	
		for(int i=0;i<4;i++)
		{
			main.add(tempTas.get(0));
			tempTas.remove(0);
		}
		tasDeCarte.setTasDeCartes(tempTas);			
	}
	
	/**
	 * M�thode qui permet de mettre un alli� dans la main d'un joueur
	 */
	public void remplirMainJoueurAllie()
	{
		Card tasDeCarte = new Allie();		
		// linked list mettre un queue
		Queue<Card> tas = new LinkedList<Card>();
		tas.addAll(tasDeCarte.getTasDeCartes());
		this.allie = tas.poll();
		tasDeCarte.setTasDeCartes(tas);
	}

	/**
	 * M�thode qui permet aux joueurs virtuels de choisir la meilleur strat�gie possible.
	 * @param joueur
	 * @param chercherJoueurGrainesMax
	 * @return
	 * 		renvoie null, car cette m�thode est red�finie dans virtuel
	 */
	public Strategy choisirStrategie(Joueur joueur, Joueur joueurCible) {
		return null;
	}

	/**
	 * Recherche la valeur maximum d'une carte pour l'action G�ant
	 * @return
	 * 		renvoie la carte qui poss�de la valeur la plus grande pour l'action G�ant
	 */
	public int valeurMaxGeant()
	{
		int max= 0;
		for(int i = 0 ; i< this.getMain().size() ; i++)
		{
			if ( this.getMain().get(i).getGeant()[Partie.getTour()]> max )
			{
				max = this.getMain().get(i).getGeant()[Partie.getTour()];
			}
		}
		return max;
	}

	// M�thode poser carte qui va permettre de jouer Engrais ou G�ant
	/**
	 * M�thode qui permet de poser une carte lorsque l'on joue Engrais ou G�ant
	 * @param index
	 * @param action
	 */
	public void poserCarte(int index , int action )
	{
		// Si le joueur joue le g�ant, alors il entre dans cette condition
		if (action==0)
		{
			//Le nombre de graines du joueur augmentent
			this.nbreGraine=this.getNbreGraine()+this.main.get(index).getGeant()[Partie.getTour()];
		}
		// si il joue l'engrais il entre alors dans cette condition  
		else {
			
			if (action==1)
			{
				// Il plante alors ses grainnes pour en faire des Menhirs
				this.planterGraines(this.main.get(index).getEngrais()[Partie.getTour()]);
			}
		}
		this.main.remove(index);
	}

	//M�thode poserCarte pour l'action farfadet
	/**
	 * M�thide qui permet de poser une carte lorsque l'on joue Farfadet
	 * @param index
	 * @param joueurCible
	 */
	public void poserCarte(int index , Joueur joueurCible)
	{
	
		//variable qui va compter le nombre de graine de la cible
		int nbrGrainesJoueur = joueurCible.getNbreGraine();
		// variable qui contient le nombre de graine que le farfadet peut voler
		
	
		int nbrGrainesCarte = this.main.get(index).getFarfadet()[Partie.getTour()];
		
		//On mais une condition pour savoir si le joueur � assez de graine
		//Si le joueur en a plus, on lui enl�ve la quantit� qui se trouve sur la carte
		if(nbrGrainesCarte <= nbrGrainesJoueur)
		{
			
			joueurCible.setNbreGraine(nbrGrainesJoueur - nbrGrainesCarte); 
			this.nbreGraine = this.nbreGraine + nbrGrainesCarte;
		}else
			//sinon on va lui retirer toute ses graines
		{
			if(nbrGrainesCarte > this.nbreGraine)
			{
				
				this.nbreGraine = this.nbreGraine + nbrGrainesJoueur;
				joueurCible.setNbreGraine(0);
			}
		}
		this.main.remove(index);
	}

	//On se place dans le cas d'une partie avanc�e avec un joueur qui peut jouer un farfadet.
	/**
	 * M�thode qui permet de poser une carte losque l'on joue Farfadet lors d'une partie avanc�e. 
	 * Il faut prendre en compte si le joueur attaqu� poss�de un chien de garde.
	 * @param index
	 * @param joueurCible
	 */
	public void poserCarteBis(int index , Joueur joueurCible)
	{
	
		//variable qui va compter le nombre de graine de la cible
		int nbrGrainesJoueur = joueurCible.getNbreGraine();
		// variable qui contient le nombre de graine que le farfadet peut voler
		
		Model model = Model.getInstance();
		int nbrGrainesCarte = this.main.get(index).getFarfadet()[Partie.getTour()];
		if(joueurCible.getAllie()==null)
		{
			//Sinon on joue normalement
			if (!(this.isVirtuel()))
			{
				System.out.println("kjkj");
				model.setCas(0);
			}
			
			poserCarte(index , joueurCible);
		}
		else
		{
		//Si le joueur cibl� joue un chien de garde  
		if(joueurCible.getAllie().getTitre().equals("Chien de garde"))
		{
			//Si le joueur joue le chien de garde le nombre de graine diminue en fonction du chien de garde
			int points = joueurCible.jouerChien(nbrGrainesCarte,this,joueurCible);
		
			nbrGrainesCarte = nbrGrainesCarte - points;
			
			if (!(this.isVirtuel()))
			{
				if(points == 0)
				{
					model.setCas(0);
				}
				else
				{
					model.setCas(1);
				}
				
				// WESH 
			}
			// On joue seulement si on peut lui prendre des graines
			if (nbrGrainesCarte > 0){
				if(nbrGrainesCarte <= nbrGrainesJoueur)
				{
					
					
					joueurCible.setNbreGraine(nbrGrainesJoueur - nbrGrainesCarte); 
					this.nbreGraine = this.nbreGraine + nbrGrainesCarte;
				}else
					//sinon on va lui retirer toute ses graines
				{
					if(nbrGrainesCarte > this.nbreGraine)
					{
						this.nbreGraine = this.nbreGraine + nbrGrainesJoueur;
						joueurCible.setNbreGraine(0);
					}
				}
				this.main.remove(index);
			}
			}
		else
		{
			model.setCas(0);
			poserCarte(index , joueurCible);
			
			
		}
		}
		
			
		
		//this.main.remove(index);
	}

	/**
	 * M�thode qui permet de jouer l'alli� chien de garde.
	 * Elle demande � l'utilisateur si il veut se d�fendre.
	 * @param valeur
	 * @param j
	 * @param joueurCible
	 * @return
	 * 		renvoie le choix de l'utilisateur(s'il veut se d�fendre ou non)
	 */
	public int jouerChien(int valeur, Joueur j, Joueur joueurCible){
		if(Main.danger(valeur, j, joueurCible)){
			//On recupere la valeur du chien de garde lors de cette saison
			int val = this.getAllie().getValeur()[Partie.getTour()];
			this.getAllie().deleteAllie();
			return val;
			
		}
		else{
			return 0;
		}
	}

	/**
	 * M�thode qui permet de jouer l'alli� taupe.
	 * @param joueurCible
	 */
	public void jouerTaupe(Joueur joueurCible){
		if(this.getAllie() == null){
			
		}
		else{
			if(this.getAllie().getTitre().equals("Taupe g�ante")){
				//Valeur qui contient le nombre de menhir que la tauoe peu d�truire
				int val = this.getAllie().getValeur()[Partie.getTour()];
				this.getAllie().deleteAllie();
				//Si le joueur cible n'a pas assez de Menhir on met � 0
				if(joueurCible.getNbreMenhir()-val < 0){
					joueurCible.setNbreMenhir(0);
				}
				//Sinon on enleve la valeur indiqu�e
				else{
					joueurCible.setNbreMenhir(joueurCible.getNbreMenhir()-val);
				}
			}
		}
		
	}
	/**
	 * M�thode pour jouer l'alli� taupe d'un joueur virtuel
	 * @param j
	 * @param j2
	 */
	public void jouerTaupe(Joueur j , Joueur j2)
	{
		
	}

	/**
	 * M�thode qui permet de chercher la carte avec la plus grande valeur
	 * @param cartes
	 * @param z
	 * @return
	 * 		renvoie la carte la plus forte
	 */
	public int cardMax(int[] cartes, int z )
	{
		int max=0;
		int id=0;
		for(int i=0 ; i<z ; i++)
		{
			if (cartes[i]>max)
			{
				max = cartes[i];
				id = i;
			}
		}
		return id;
	}
	
	/**
	 * Recherche la valeur maximum d'une carte pour l'action Farfadet
	 * @return
	 * 		renvoie la carte qui poss�de la valeur la plus grande pour l'action Farfadet
	 */
	public int valeurMaxFarfadet()
	{
		int max= 0;
		for(int i = 0 ; i< this.getMain().size() ; i++)
		{
			if ( this.getMain().get(i).getFarfadet()[Partie.getTour()]> max )
			{
				max = this.getMain().get(i).getFarfadet()[Partie.getTour()];
			}
		}
		return max;
	}

	/**
	 * M�thode qui permet de planter des graines.
	 * @param nbrGrainesCarte
	 */
	public void planterGraines(int nbrGrainesCarte){
		if(nbrGrainesCarte <= this.nbreGraine)
		{
			this.nbreGraine = this.nbreGraine - nbrGrainesCarte;
			this.nbreMenhir = this.nbreMenhir + nbrGrainesCarte;
		}else
		{
			if(nbrGrainesCarte > this.nbreGraine)
			{
				this.nbreMenhir = this.nbreMenhir + this.nbreGraine;
				this.nbreGraine = 0;
			}
		}	
	}

	/**
	 * M�thode qui permet de choisir un carte pour un joueur virtuel
	 * @param strategie
	 * @return
	 * 		
	 */
	public List choisirCarte(Strategy strategie)
	{		
		return null;
	}
	/**
	 * M�thode qui actualise le nom d'un  joueur
	 * @param nom
	 * 		nouveau nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * M�thode qui actualise le nombre de Menhir d'un joueur
	 * @param nom
	 * 		nouveau nombre de menhir
	 */
	public void setNbreMenhir(int nbreMenhir) {
		this.nbreMenhir = nbreMenhir;
	}
	/**
	 * M�thode qui actualise le nombre de graine d'un  joueur
	 * @param nom
	 * 		nouveau nombre de graine
	 */
	public void setNbreGraine(int nbreGraine) {
		this.nbreGraine = nbreGraine;
	}
	
	/**
	 * M�thode qui actualise une carte allie
	 * @param nom
	 * 		nouvelle carte alli�e
	 */
	public void setAllie(Card allie) {
		this.allie = allie;
	}
	
	/**
	 * M�thode qui actualise la valeur pour compter les menhirs d'un  joueur
	 * @param nom
	 * 		nouvelle valeur de menhirs
	 */
	public void setCompteurMenhir(int compteurMenhir) {
		this.compteurMenhir = compteurMenhir;
	}

	/**
	 * Methode qui donne le nombre de menhir d'un joueur
	 * @return
	 * 		renvoie le nombre de menhir
	 */
	public int getNbreMenhir() {
		return nbreMenhir;
	}
	
	/**
	 * Methode qui donne le compteur de menhir d'un joueur
	 * @return
	 * 		renvoie le compteur de menhir
	 */
	public int getCompteurMenhir() {
		return compteurMenhir;
	}

	/**
	 * Methode qui donne le nom d'un joueur
	 * @return
	 * 		renvoie le nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Methode qui donne le nombre de graine d'un joueur
	 * @return
	 * 		renvoie le nombre de graine
	 */
	public int getNbreGraine() {
		return nbreGraine;
	}

	/**
	 * Methode qui donne la main d'un joueur
	 * @return
	 * 		renvoie la main
	 */
	public List<Card> getMain(){
		return this.main;
	}
	/**
	 * Methode qui donne a carte alli�e d'un joueur
	 * @return
	 * 		renvoie la carte alli�e
	 */
	public Card getAllie() {
		return allie;
	}
	
	/**
	 * Methode qui donne toutes les valeurs de Farfadet d'un main
	 * @return
	 * 		renvoie un tableau avec les valeurs de farfadet
	 */
	public int[] getFarfadetAllCard()
	{
		int[] farfadet = new int[this.main.size()];
		for(int i=0;i<this.main.size(); i++)
		{
			farfadet[i] = this.main.get(i).getFarfadet()[Partie.getTour()];
		}
	
		return farfadet;
	}
	
	/**
	 * Methode qui donne toutes les valeurs de G�ant d'un main
	 * @return
	 * 		renvoie un tableau avec les valeurs de G�ant
	 */
	public int[] getGeantAllCard()
	{
		int[] geant = new int[this.main.size()];
		for(int i=0;i<this.main.size(); i++)
		{
			geant[i] = this.main.get(i).getGeant()[Partie.getTour()];
		}
	
		return geant;
	}
	
	/**
	 * M�thode qui permet d'afficher un message sur la console.
	 * 
	 */
	public String toString()
	{
		return "<tr><td style='background-color:677DC1; color:white;'>Village : " + this.nom +" <br><ul><li>Nombres m�nhirs : "+ this.nbreMenhir +
				"</li><li>Nombres graines : "+ this.nbreGraine+"</li></ul>";
	
	}
	/**
	 * Methode qui permet de savoir si le joueur est un joueur virtuel
	 * @return
	 * 		return un bool�en
	 */
	public boolean isVirtuel()
	{
		return false;
	}
}
