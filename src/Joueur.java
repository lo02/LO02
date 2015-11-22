import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Joueur {
	

	protected String nom = "";
	protected int nbreMenhir = 0;
	protected int nbreGraine = 0;
	protected List<Card> main = new ArrayList<Card>();	
	
	
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
	
	public void poserCarte(int index , int action )
	{
		if (action==0)
		{
			this.nbreGraine=this.getNbreGraine()+this.main.get(index).getGeant()[Partie.getTour()];
		}
		else {
			if (action==1)
			{
				this.planterGraines(this.main.get(index).getEngrais()[Partie.getTour()]);
			}
		}
	}
	
	public void poserCarte(int index , Joueur joueurCible)
	{
	
		int nbrGrainesJoueur = joueurCible.getNbreGraine();
		int nbrGrainesCarte = this.main.get(index).getFarfadet()[Partie.getTour()];
		if(nbrGrainesCarte <= nbrGrainesJoueur)
		{
			joueurCible.setNbreGraine(nbrGrainesJoueur - nbrGrainesCarte); 
			this.nbreMenhir = this.nbreMenhir + nbrGrainesCarte;
		}else
		{
			if(nbrGrainesCarte > this.nbreGraine)
			{
				this.nbreMenhir = this.nbreMenhir + nbrGrainesJoueur;
				joueurCible.setNbreGraine(0);
			}
		}
		
		
				
		
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
		return "Joueur " + this.nom +"\n- Nombres ménhirs : "+ this.nbreMenhir +"\n- Nombres graines : "+ this.nbreGraine;
	
	}
	
	public String aleaName()
	{
		
		
		String[] names = {"Elkantar",
				"Ilphrin",
				"Jarlaxe",
				"Riklaunim",
				"Nym",
				"Vorn",
				"Ranaghar",
				"Bruherd",
				"Tluth",
				"Welverin",
				"Berg'inyon",
				"Tluth",
				"Nilomim",
				"Dinin",
				"Drizzt",
				"Seldszar",
				"Tebryn",
				"Krondorl",
				"Chaszmyr",
				"Seldszar",
				"Mourn",
				"Welverin",
				"Kalannar",
				"Nilomim",
				"Ilphrin",
				"Ryld",
				"Tluth",
				"Alak",
				"Pharaun",
				"Sabrar",
				"Istolil",
				"Vuzlyn",
				"Ranaghar",
				"Amalica",
				"Ilphrin",
				"Istolil",
				"Tathyn",
				"Guldor",
				"Calimar",
				"Malaggar",
				"Nadal",
				"Dilvolg",
				"Duagolth",
				"Chaszmyr",
				"Dilvolg",
				"Tebryn",
				"Vorn"};
		
		Random rand = new Random();
		int nombreAleatoire = rand.nextInt( 49 );
		return names[nombreAleatoire];
	
	}
}
