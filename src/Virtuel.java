import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Virtuel extends Joueur implements Strategy{
	
	
	public  static List<String> names = Arrays.asList("Elkantar","Ilphrin","Jarlaxe","Riklaunim","Nym","Vorn","Ranaghar","Bruherd","Tluth","Welverin","Berg'inyon","Tluth","Nilomim","Dinin","Drizzt","Seldszar","Tebryn","Krondorl","Chaszmyr","Seldszar","Mourn","Welverin","Kalannar","Nilomim","Ilphrin","Ryld","Tluth","Alak","Pharaun","Sabrar","Istolil","Vuzlyn","Ranaghar","Amalica","Ilphrin","Istolil",	"Tathyn","Guldor","Calimar","Malaggar","Nadal","Dilvolg","Duagolth","Chaszmyr","Dilvolg","Tebryn","Vorn");
	private static List<String> namesDisponible;
	
	// La variable strategy contient le type de strategie choisi pour le joueur virtuel
	public Strategy strategy;

	public Virtuel( int nbreMenhir, int nbreGraine) {
		
		//String nom23 = aleaName();
		super(aleaName(), nbreMenhir, nbreGraine);
		
		
	}
	public void jouerCarte(){
		
	}
	public static String aleaName()
	{
		
		/*//namesDisponible.addAll(names);
		Random rand = new Random();
		int nombreAleatoire = rand.nextInt (47);
		String nom = names.get(nombreAleatoire);
		names.remove(nombreAleatoire);
		*/
		return "";
	
	}
	
	
}
