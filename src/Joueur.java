import java.util.ArrayList;
import java.util.List;

public class Joueur {
	

	protected String nom = "";
	protected int nbreMenhir = 0;
	protected int nbreGraine = 0;
	protected List<Card> main = new ArrayList<Card>();	
	
	
	public void remplirMainJoueur()
	{
		Card tasDeCarte = new Ingredient();
		List<Card> tempTas = new ArrayList<Card>();
		tempTas.addAll(tasDeCarte.getTasDeCartes());
		
		for(int i=0;i<4;i++)
		{
			main.add(tempTas.get(i));
			tempTas.remove(i);
			
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
	
	
}
