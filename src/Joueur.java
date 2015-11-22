import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Joueur {
	

	protected String nom = "";
	protected int nbreMenhir = 0;
	protected int nbreGraine = 0;
	protected List<Card> main = new ArrayList<Card>();	
	
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
				this.nbreMenhir = this.nbreMenhir + nbrGrainesJoueur;
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
		return "\nJoueur " + this.nom +"\n- Nombres ménhirs : "+ this.nbreMenhir +"\n- Nombres graines : "+ this.nbreGraine;
	
	}
	
	
}
