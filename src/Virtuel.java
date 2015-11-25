import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Virtuel extends Joueur {
	
	public final static List<String> names = Arrays.asList("Elkantar","Ilphrin","Jarlaxe","Riklaunim","Nym","Vorn","Ranaghar","Bruherd","Tluth","Welverin","Berg'inyon","Tluth","Nilomim","Dinin","Drizzt","Seldszar","Tebryn","Krondorl","Chaszmyr","Seldszar","Mourn","Welverin","Kalannar","Nilomim","Ilphrin","Ryld","Tluth","Alak","Pharaun","Sabrar","Istolil","Vuzlyn","Ranaghar","Amalica","Ilphrin","Istolil","Tathyn","Guldor","Calimar","Malaggar","Nadal","Dilvolg","Duagolth","Chaszmyr","Dilvolg","Tebryn","Vorn");
	private static List<String> namesDisponible = new ArrayList<String>();
	
	// La variable strategy contient le type de strategie choisi pour le joueur virtuel
	public Strategy strategy;

	public Virtuel( int nbreMenhir, int nbreGraine) {
		//String nom23 = aleaName();
		super(aleaName(), nbreMenhir, nbreGraine);
	}
	public void jouerCarte(){	
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
}