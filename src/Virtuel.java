import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Virtuel extends Joueur {
	
	public final static List<String> names = Arrays.asList("Thor","Iron Man","Hulk","Black Widow","Spider-Man","Ant-Man","Cap.America","Coulson","Nick Fury","Pr.Xavier","Wolverine","Cyclope","Hawkeyes");
	private static List<String> namesDisponible = new ArrayList<String>();
	
	// La variable strategy contient le type de strategie choisi pour le joueur virtuel
	public Strategy strategy;

	public Virtuel( int nbreMenhir, int nbreGraine) {
		//String nom23 = aleaName();
		super(aleaName(), nbreMenhir, nbreGraine);
	}
	
	public static void initialisationNom(){
		namesDisponible.addAll(names);
	}
	public static String aleaName()
	{
		Random rand = new Random();
		int nombreAleatoire = rand.nextInt(namesDisponible.size());
		String nom = namesDisponible.get(nombreAleatoire);
		namesDisponible.remove(nombreAleatoire);	
		return nom ;
	}
	public List choisirCarte(Strategy strategie)
	{
		return strategie.choisirCarte(this);	
	}
	// Méthode qui va permettre au joueur virtuel de séléctionner la meillleur stratégie entre normal et offensive
	public Strategy choisirStrategie(Joueur joueur , Joueur joueurCible)
	{	
		boolean farfadet = false ;		
		// on vérifie si la valeur Farfadet > géant 
			if(joueur.valeurMaxFarfadet()>joueur.valeurMaxGeant())
			{
				// on vérifie si la cible à un nbre suffisant de graine à voler
				if(joueurCible.getNbreGraine() >= joueur.valeurMaxFarfadet())
				{
					farfadet = true ;
				}
				//Sinon on vérifie si le geant nous permet d'obtenir plus de graine qu'avec le farfadet
				else
				{
					if(joueurCible.getNbreGraine()>=joueur.valeurMaxGeant())
					{
						farfadet = true;
					}
				}
			}
		//on joue alors le farfadet
		if(farfadet)
		{
			Strategy strategy = new StrategyOffensive(joueurCible);
			System.out.println(strategy);
			return strategy;
		}
		//Sinon on joue le Géant
		else
		{	
			Strategy strategy = new StrategyNormal();
			System.out.println(strategy);
			return strategy;
		}	
	}	
	
	/*Méthode qui va permettre de choisir soit une carte alliée soit 2 graines en début de manche en partie
	 * avancée;
	*/
	public void choixDebutManche(){
		//On regarde le nombre de graine, s'il n'as pas de graine il en prend
		if (this.getNbreGraine()==0){
			this.setNbreGraine(this.getNbreGraine()+2);
		}
		//Sinon il prend un allié
		else
		{
			this.remplirMainJoueurAllie();
		}
	}
	
	public int jouerChien(int valeur, String j, Joueur joueurCible){
		//on regarde si il peut jouer son chien
		if(this.getAllie().getValeur()[Partie.getTour()] > 0){
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
		else
		{
			if(this.getAllie().getTitre().equals("Taupe géante")){
		
				//On regarde la saison pour laquelle notre taupe peut détruire le plus de menhir
				if(Partie.getTour()==this.rechercheMaxTaupe()){
					//Si le joueur cible n'a pas assez de Menhir on met à 0
					if(joueurCible.getNbreMenhir()-this.getAllie().getValeur()[Partie.getTour()] < 0){
						joueurCible.setNbreMenhir(0);
					}
					//Sinon on enleve la valeur indiquée
					else{
						joueurCible.setNbreMenhir(joueurCible.getNbreMenhir()-this.getAllie().getValeur()[Partie.getTour()]);
					}
					this.getAllie().deleteAllie();
				}
			}
		}
	}
	
	public int rechercheMaxTaupe(){
		int Max = 0, id = 0;
		for (int i =0; i<4; i++){
			//On regarde qu'elle est la valeur la plus grande pour la taupe et on garde l'indice de la saison dans id que l'on retourne
			if(this.getAllie().getValeur()[i]>=Max)
			{
				Max = this.getAllie().getValeur()[i];
				id = i;
			}
		}
		return id;	
	}
}
	
	
	