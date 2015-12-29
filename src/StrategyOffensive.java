import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Anass et Gilles
 * classe qui permet aux joueurs virtuel de jouer farfadet ou engrais.
 * Ici les joueurs virtuels ont une technique offensive. Ils cherchent soit � voler des graines, soit
 * � les planter
 */
public class StrategyOffensive implements Strategy{
	/**
	 * Bool�en qui permet d'indiquer que l'on joue farfadet.
	 */
	private boolean farfadet = true;
	/**
	 * Variable de type Joueur qui contient le joueur cible.
	 */
	private Joueur joueurCible;
	
	
	/**
	 * Constructeur de StrategyOffensive
	 * @param joueur
	 */
	public StrategyOffensive(Joueur joueur)
	{
		this.joueurCible = joueur;
	}

	/**
	 * M�thode qui permet de choisir qu'elle carte jouer
	 * @param joueur
	 * 				Variable qui contient le joueur virtuel qui veut choisir une carte
	 * @return
	 * 			Renvoie une liste qui d�pend du choix du joueur virtuel, 
	 */
	public List choisirCarte(Joueur joueur ){
		int choixTemporaire = -1;
		int action ; 
		// on regarde  le nombre de graines 
		if(joueur.nbreGraine> 0 )
		{
			// on jouera engrais 
			// on regarde la carte la plus apropri�e pour planter
			this.farfadet = false;
			choixTemporaire = jouerEngrais(joueur);
			action = 1;
			
			// Si le choixtemporaire retourne -1 cad pas de carte appropri�e
			// On jouera farfadet 
			if (choixTemporaire == -1)
			{
				this.farfadet = true;
				choixTemporaire = jouerFarfadet(joueur);
				action = 2;
			}
		}
		else
		{
			this.farfadet = true;
			choixTemporaire = jouerFarfadet(joueur);
			action = 2;
		}
		
		List liste = new ArrayList();

		
		liste.add(0,choixTemporaire);
		liste.add(action);
		liste.add(2,this.joueurCible );
		return liste;
	}
	/**
	 * M�thode qui permet de jouer farfadet.
	 * @param joueur
	 * @return
	 * 		renvoie la valeur de la carte � jouer.
	 */
	public int jouerFarfadet(Joueur joueur){
		int[] farfadet = joueur.getFarfadetAllCard();
		return  this.cardMax(farfadet ,joueur.getMain().size());	
	}
	
	/**
	 * M�thode qui permet de jouer Engrais
	 * @param joueur
	 * @return
	 * 		renvoie la valeur de la carte � jouer
	 */
	public int jouerEngrais(Joueur joueur){
		// on cr�e une collection de tableau 
		List<int[]> engrais = new ArrayList<int[]>();
		for(int i=0;i<joueur.getMain().size();i++)
		{
			// On cr�e une collection qui contient les valeurs d'engrais de chaque carte de la main 
			engrais.add(joueur.getMain().get(i).getEngrais());	
		}
		
		// Si on est au dernier tour et qu'on a des graines et suffisemment de points pour jouer engrais
		// on joue engrais
		
		int choixTemporaire = -1;
		
		
		if(engrais.get(0)[Partie.getTour()]>0  )
		{
			if(Partie.getTour()==3){
				if(joueur.nbreGraine > 0){
					return 0;
				}
			}	
		}
		else
		{
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
		}
		return choixTemporaire;
	}
	
	// m�thodes qui permet le maximum dans un tableau, ici la valeur max de farfadet
	/**
	 * M�thode qui permet de chercher la carte qui poss�de la plus grande valeur
	 * @param cartes
	 * @param z
	 * @return
	 * 		renvoie la carte la plus forte
	 */
	public int cardMax(int[] cartes, int z )
	{
		int max=0;
		int id=0;
		for(int i=0 ; i<z ; i++)
		{
			if (cartes[i] > max)
			{
				max = cartes[i];
				id = i;
			}
		}
		return id;
	}
	
	/**
	 * M�thode qui permet de savoir si le joueur virtuel v a jouer offensivement
	 * @return
	 * 			renvoie un bool�en 
	 */
	public boolean isOffensive()
	{
		return this.farfadet;
	}
}
