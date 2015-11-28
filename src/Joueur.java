import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class Joueur {

	protected String nom = "";
	protected int nbreMenhir = 0;
	protected int nbreGraine = 0;
	protected List<Card> main = new ArrayList<Card>();	
	protected Card allie  ;
	protected int compteurMenhir = 0;
	
	public Joueur (){
		this.nom = "";
	}

	public Joueur(String nom, int nbreMenhir, int nbreGraine) {
		this.nom = nom;
		this.nbreMenhir = nbreMenhir;
		this.nbreGraine = nbreGraine;
	}

	public void choixDebutManche(){};
	
	/*Méthode qui va permettre de choisir soit une carte alliée soit 2 graines en début de manche en partie
	 * avancée;
	 * Pour prendre des graines en met 1, on met 2 pour avoir une carte alliée.
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
	
	public void remplirMainJoueurAllie()
	{
		Card tasDeCarte = new Allie();		
		// linked list mettre un queue
		Queue<Card> tas = new LinkedList<Card>();
		tas.addAll(tasDeCarte.getTasDeCartes());
		this.allie = tas.poll();
		tasDeCarte.setTasDeCartes(tas);
	}

	public Strategy choisirStrategie(Joueur joueur, Joueur chercherJoueurGrainesMax) {
		// TODO Auto-generated method stub
		return null;
	}

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

	// Méthode poser carte qui va permettre de jouer Engrais ou Géant
	public void poserCarte(int index , int action )
	{
		// Si le joueur joue le géant, alors il entre dans cette condition
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

	//Méthode poserCarte pour l'action farfadet
	public void poserCarte(int index , Joueur joueurCible)
	{
	
		//variable qui va compter le nombre de graine de la cible
		int nbrGrainesJoueur = joueurCible.getNbreGraine();
		// variable qui contient le nombre de graine que le farfadet peut voler
		
	
		int nbrGrainesCarte = this.main.get(index).getFarfadet()[Partie.getTour()];
		
		//On mais une condition pour savoir si le joueur à assez de graine
		//Si le joueur en a plus, on lui enlève la quantité qui se trouve sur la carte
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

	//On se place dans le cas d'une partie avancée avec un joueur qui peut jouer un farfadet.
	public void poserCarteBis(int index , Joueur joueurCible)
	{
	
		//variable qui va compter le nombre de graine de la cible
		int nbrGrainesJoueur = joueurCible.getNbreGraine();
		// variable qui contient le nombre de graine que le farfadet peut voler
		
	
		int nbrGrainesCarte = this.main.get(index).getFarfadet()[Partie.getTour()];
		if(joueurCible.getAllie()==null)
		{
			//Sinon on joue normalement
			poserCarte(index , joueurCible);
		}
		else
		{
		//Si le joueur ciblé joue un chien de garde  
		if(joueurCible.getAllie().getTitre().equals("Chien de garde"))
		{
			//Si le joueur joue le chien de garde le nombre de graine diminue en fonction du chien de garde
			nbrGrainesCarte = nbrGrainesCarte - joueurCible.jouerChien(nbrGrainesCarte,this.getNom(),joueurCible);
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
			poserCarte(index , joueurCible);
		}
		}
		
			
		
		//this.main.remove(index);
	}

	public int jouerChien(int valeur, String j, Joueur joueurCible){
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

	public void jouerTaupe(Joueur joueurCible){
		if(this.getAllie() == null){
			
		}
		else{
			if(this.getAllie().getTitre().equals("Taupe géante")){
				//Valeur qui contient le nombre de menhir que la tauoe peu détruire
				int val = this.getAllie().getValeur()[Partie.getTour()];
				this.getAllie().deleteAllie();
				//Si le joueur cible n'a pas assez de Menhir on met à 0
				if(joueurCible.getNbreMenhir()-val < 0){
					joueurCible.setNbreMenhir(0);
				}
				//Sinon on enleve la valeur indiquée
				else{
					joueurCible.setNbreMenhir(joueurCible.getNbreMenhir()-val);
				}
			}
		}
		
	}

	public void jouerTaupe(Joueur j , Joueur j2)
	{
		
	}

	public Strategy choisirStrategie(Joueur joueur )
	{
		return null;
		
	}

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

	public List choisirCarte(Strategy strategie)
	{		
		return null;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setNbreMenhir(int nbreMenhir) {
		this.nbreMenhir = nbreMenhir;
	}

	public void setNbreGraine(int nbreGraine) {
		this.nbreGraine = nbreGraine;
	}
	
	public void setAllie(Card allie) {
		this.allie = allie;
	}

	public void setCompteurMenhir(int compteurMenhir) {
		this.compteurMenhir = compteurMenhir;
	}

	public int getNbreMenhir() {
		return nbreMenhir;
	}

	public int getCompteurMenhir() {
		return compteurMenhir;
	}

	public String getNom() {
		return nom;
	}

	public int getNbreGraine() {
		return nbreGraine;
	}

	public List<Card> getMain(){
		return this.main;
	}

	public Card getAllie() {
		return allie;
	}

	public int[] getFarfadetAllCard()
	{
		int[] farfadet = new int[this.main.size()];
		for(int i=0;i<this.main.size(); i++)
		{
			farfadet[i] = this.main.get(i).getFarfadet()[Partie.getTour()];
		}
	
		return farfadet;
	}
	
	public int[] getGeantAllCard()
	{
		int[] geant = new int[this.main.size()];
		for(int i=0;i<this.main.size(); i++)
		{
			geant[i] = this.main.get(i).getGeant()[Partie.getTour()];
		}
	
		return geant;
	}
	
	public String toString()
	{
		return "Joueur " + this.nom +" :\n- Nombres ménhirs : "+ this.nbreMenhir +"\n- Nombres graines : "+ this.nbreGraine+"\n\n====================\n\n";
	
	}
}