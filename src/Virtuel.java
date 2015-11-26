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
	// M�thode qui va permettre au joueur virtuel de s�l�ctionner la meillleur strat�gie entre normal et offensive
	public Strategy choisirStrategie(Joueur joueur , Joueur joueurCible)
	{	
		boolean farfadet = false ;		
		// on v�rifie si la valeur Farfadet > g�ant 
			if(joueur.valeurMaxFarfadet()>joueur.valeurMaxGeant())
			{
				// on v�rifie si la cible � un nbre suffisant de graine � voler
				if(joueurCible.getNbreGraine() >= joueur.valeurMaxFarfadet())
				{
					farfadet = true ;
				}
				//Sinon on v�rifie si le geant nous permet d'obtenir plus de graine qu'avec le farfadet
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
		//Sinon on joue le G�ant
		else
		{	
			Strategy strategy = new StrategyNormal();
			System.out.println(strategy);
			return strategy;
		}	
	}	
	
	/*M�thode qui va permettre de choisir soit une carte alli�e soit 2 graines en d�but de manche en partie
	 * avanc�e;
	*/
	public void choixDebutManche(){
		//On regarde le nombre de graine, s'il n'as pas de graine il en prend
		if (this.getNbreGraine()==0){
			this.setNbreGraine(this.getNbreGraine()+2);
		}
		//Sinon il prend un alli�
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
			if(this.getAllie().getTitre().equals("Taupe g�ante")){
		
				//On regarde la saison pour laquelle notre taupe peut d�truire le plus de menhir
				if(Partie.getTour()==this.rechercheMaxTaupe()){
					//Si le joueur cible n'a pas assez de Menhir on met � 0
					if(joueurCible.getNbreMenhir()-this.getAllie().getValeur()[Partie.getTour()] < 0){
						joueurCible.setNbreMenhir(0);
					}
					//Sinon on enleve la valeur indiqu�e
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
	
	
	