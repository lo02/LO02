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
	public Strategy choisirStrategie(Joueur joueur , Joueur joueurCible)
	{
			
		boolean farfadet = false ;		
		// on v�rifie si Farfadet > g�ant 
			if(joueur.valeurMaxFarfadet()>joueur.valeurMaxGeant())
			{
				if(joueurCible.getNbreGraine() >= joueur.valeurMaxFarfadet())
				{
					farfadet = true ;
				}else
				{
					if(joueurCible.getNbreGraine()>=joueur.valeurMaxGeant())
					{
						farfadet = true;
					}
				}
			}
		if(true)
		{
			Strategy strategy = new StrategyOffensive(joueurCible);
		}else
		{
			Strategy strategy = new StrategyNormal();
		}
		
		return strategy;
		
	}
	
	
	
}
