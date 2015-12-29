import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Anass et Gilles
 * Cette classe permet aux joueurs virtuel de jouer Engrais ou Géant.
 * Ici les joueurs virtuels ont une technique normale. Ils cherchent soit à planter des graines, soit à
 * en demander.
 */
public class StrategyNormal implements Strategy {
	
	/**
	 * Méthode qui permet de choisir qu'elle carte jouer
	 * @param joueur
	 * 				Variable qui contient le joueur virtuel qui veut choisir une carte
	 * @return
	 * 			Renvoie une liste qui dépend du choix du joueur virtuel, 
	 */
	public List choisirCarte(Joueur joueur){	
		int choixTemporaire = 0;
		int action=0;
		// on regarde  le nombre de graines 
		if(joueur.nbreGraine> 0 )
		{
			// on jouera engrais 
			// on regarde la carte la plus apropriée pour planter
			choixTemporaire = jouerEngrais(joueur);
			action = 1;
			// Si le choixtemporaire retourne -1 cad pas de carte appropriée
			// On jouera géant 
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
		//Création d'une liste pour renvoyer le choix
		List liste = new ArrayList();	
		
		liste.add(0, choixTemporaire);
		liste.add(1,action);
		return liste;
	}
	
	/**
	 * Méthode qui permet de jouer Engrais
	 * @param joueur
	 * @return
	 * 		renvoie la valeur de la carte à jouer
	 */
	public int jouerEngrais(Joueur joueur)
	{
		// on crée une collection de tableau 
		List<int[]> engrais = new ArrayList<int[]>();
		
		for(int i=0;i<joueur.getMain().size();i++)
			{
				// On crée une collection qui contient les valeurs d'engrais de chaque carte de la main 
				engrais.add(joueur.getMain().get(i).getEngrais());	
			}
		
			int choixTemporaire = -1;
			
			
			for(int i=0;i<joueur.getMain().size();i++)
			{
				// on vérifie si le nombre de graines que le joueur possède correspond au celui indiqué sur la carte 
				if(engrais.get(i)[Partie.getTour()] == joueur.nbreGraine){
				return i;
			}else
			// Si on trouve rien , on mémorise la derniere meilleure des carte
							if(engrais.get(i)[Partie.getTour()] < joueur.nbreGraine && engrais.get(i)[Partie.getTour()]>0)
							{
								choixTemporaire = i;
							}
					}
			
			if(engrais.get(0)[Partie.getTour()]>0 && Partie.getTour()==3 && joueur.nbreGraine > 0)
			{
				choixTemporaire =  0;
			}
					return choixTemporaire;
	}
	
	/**
	 * Méthode qui permet de jouer Géant.
	 * @param joueur
	 * @return
	 * 		renvoie la valeur de la carte à jouer
	 */
	public int jouerGeant(Joueur joueur){
		int choix = 0;
		int choixTemp = 0;
		List<int[]> geant = new ArrayList<int[]>();
		for(int i=0;i<joueur.getMain().size();i++)
		{
			// On crée une collection qui contient les valeurs de geant de chaque carte de la main 
			geant.add(joueur.getMain().get(i).getGeant());	
		}
		for (int i=0; i<joueur.getMain().size(); i++){
			//on va regarder les valeurs de géant, et on va choisir la valeur la plus élevée
			if(geant.get(i)[Partie.getTour()]>0){
				choixTemp = i;
				//on compare la valeur que l'on a trouvé par rapport aux autres et prend la plus grande
				if (geant.get(choixTemp)[Partie.getTour()] > geant.get(choix)[Partie.getTour()]){
					choix = choixTemp;
				}
			}	
		}
		return choix;
	}
	
	/**
	 * Méthode qui permet de savoir si le joueur virtuel v a jouer offensivement
	 * @return
	 * 			renvoie un booléen 
	 */
	public boolean isOffensive()
	{
	return false;
	}		
}