
public class Virtuel extends Joueur implements Strategy{

	// La variable strategy contient le type de strategie choisie pour le joueur virtuel
	public Strategy strategy;
	public Virtuel(String nom, int nbreMenhir, int nbreGraine, Strategy strategy) {
		super(nom, nbreMenhir, nbreGraine);	
		this.strategy = strategy;
	}
	public void normal(){
		
	}
	public void offensive(){
		
	}
}
