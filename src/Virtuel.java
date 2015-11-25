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
		if()
	}
}
	
	
	