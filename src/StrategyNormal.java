import java.util.ArrayList;
import java.util.List;

public class StrategyNormal implements Strategy {

	public List choisirCarte(Joueur joueur){	
		int choixTemporaire = 0;
		int action=0;
		int[] retourne= new int[2];
		// on regarde  le nombre de graines 
		if(joueur.nbreGraine> 0 )
		{
			// on jouera engrais 
			// on regarde la carte la plus apropri�e pour planter
			choixTemporaire = jouerEngrais(joueur);
			action = 1;
			// Si le choixtemporaire retourne -1 cad pas de carte appropri�e
			// On jouera g�ant 
			if (choixTemporaire == -1)
			{
				choixTemporaire = jouerGeant(joueur);
				action = 0;
			}
			
		}
		else
		{
			choixTemporaire = jouerGeant(joueur);
			action = 0;
		}
		//Cr�ation d'une liste pour renvoyer le choix
		List liste = new ArrayList();	
		
		liste.add(0, choixTemporaire);
		liste.add(1,action);
		return liste;
	}
	public int jouerEngrais(Joueur joueur)
	{
		// on cr�e une collection de tableau 
		List<int[]> engrais = new ArrayList<int[]>();
		for(int i=0;i<joueur.getMain().size();i++)
			{
				// On cr�e une collection qui contient les valeurs d'engrais de chaque carte de la main 
				engrais.add(joueur.getMain().get(i).getEngrais());	
			}
			int choixTemporaire = -1;
			for(int i=0;i<joueur.getMain().size();i++)
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
	public int jouerGeant(Joueur joueur){
		int choix = 0;
		int choixTemp = 0;
		List<int[]> geant = new ArrayList<int[]>();
		for(int i=0;i<joueur.getMain().size();i++)
		{
			// On cr�e une collection qui contient les valeurs de geant de chaque carte de la main 
			geant.add(joueur.getMain().get(i).getGeant());	
		}
		for (int i=0; i<joueur.getMain().size(); i++){
			//on va regarder les valeurs de g�ant, et on va choisir la valeur la plus �lev�e
			if(geant.get(i)[Partie.getTour()]>0){
				choixTemp = i;
				//on compare la valeur que l'on a trouv� par rapport aux autres et prend la plus grande
				if (geant.get(choixTemp)[Partie.getTour()] > geant.get(choix)[Partie.getTour()]){
					choix = choixTemp;
				}
			}	
		}
		return choix;
	}
	public String toString()
	{
		return "strat normal";
	}
	public boolean isOffensive()
	{
	return false;
	}	
}