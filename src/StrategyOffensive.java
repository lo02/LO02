
import java.util.ArrayList;
import java.util.List;

public class StrategyOffensive implements Strategy{
	public int[] choisirCarte(Joueur joueur){
		int choixTemporaire = 0;
		// on regarde  le nombre de graines 
		if(joueur.nbreGraine> 0 )
		{
			// on jouera engrais 
			// on regarde la carte la plus apropri�e pour planter
			choixTemporaire = jouerEngrais(joueur);
			
			// Si le choixtemporaire retourne -1 cad pas de carte appropri�e
			// On jouera g�ant 
			if (choixTemporaire == -1)
			{
				choixTemporaire = jouerFarfadet(joueur);
			}
		}
		else
		{
			choixTemporaire = jouerFarfadet(joueur);
		}
		return null;
	
	}
	
	public int jouerFarfadet(Joueur joueur){
		int choix = 0;
		
		
		
		return choix;
	}
	
	public int jouerEngrais(Joueur joueur){
		// on cr�e une collection de tableau 
		List<int[]> engrais = new ArrayList<int[]>();
		for(int i=0;i<4;i++)
		{
			// On cr�e une collection qui contient les valeurs d'engrais de chaque carte de la main 
			engrais.add(joueur.getMain().get(i).getEngrais());	
		}
		int choixTemporaire = -1;
		for(int i=0;i<4;i++)
		{
			// on v�rifie si le nombre de graines que le joueur poss�de correspond au celui indiqu� sur la carte 
			if(engrais.get(i)[Partie.getTour()] == joueur.nbreGraine){
				return i;
			}else
				// Si on trouve rien , on m�morise la derniere meilleure des carte
				if(engrais.get(i)[Partie.getTour()] < joueur.nbreGraine && engrais.get(i)[Partie.getTour()]>0)
				{
					choixTemporaire = i;
				}				
		}
		return choixTemporaire;

	}
}
