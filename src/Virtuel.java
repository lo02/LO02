import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Virtuel extends Joueur {
	
	public final static List<String> names = Arrays.asList("Thor","Ilphrin","Jarlaxe","Riklaunim","Nym","Vorn","Ranaghar","Bruherd","Tluth","Welverin","Berg'inyon","Tluth","Nilomim","Dinin","Drizzt","Seldszar","Tebryn","Krondorl","Chaszmyr","Seldszar","Mourn","Welverin","Kalannar","Nilomim","Ilphrin","Ryld","Tluth","Alak","Pharaun","Sabrar","Istolil","Vuzlyn","Ranaghar","Amalica","Ilphrin","Istolil","Tathyn","Guldor","Calimar","Malaggar","Nadal","Dilvolg","Duagolth","Chaszmyr","Dilvolg","Tebryn","Vorn");
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
		if()
	}
}
	
	
	