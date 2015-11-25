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
	
	public Joueur (){
		this.nom = "";
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
	
	public List<Card> getMain(){
		return this.main;
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
			this.nbreMenhir = this.nbreMenhir + nbrGrainesCarte;
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
	
	public String toString()
	{
		return "Joueur " + this.nom +":\n- Nombres ménhirs : "+ this.nbreMenhir +"\n- Nombres graines : "+ this.nbreGraine+"\n\n====================\n\n";
	
	}

	public List choisirCarte(Strategy strategie)
	{		
		return null;
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

	public Strategy choisirStrategie(Joueur joueur, Joueur chercherJoueurGrainesMax) {
		// TODO Auto-generated method stub
		return null;
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
	
	/*Méthode qui va permettre de choisir soit une carte alliée soit 2 graines en début de manche en partie
	 * avancée;
	 * Pour prendre des graines en met 1, on met 2 pour avoir une carte alliée.
	*/
	public void choixDebutManche(int choix){
		if (choix == 1){
			this.setNbreGraine(2);
		}
		else
			if (choix == 2){
				this.remplirMainJoueurAllie();
			}
	}
}