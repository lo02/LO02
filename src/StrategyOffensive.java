import java.util.ArrayList;
import java.util.List;

public class StrategyOffensive implements Strategy{
	private boolean farfadet = true;
	private Joueur joueurCible;
	
	public List choisirCarte(Joueur joueur ){
		int choixTemporaire = 0;
		int action ; 
		// on regarde  le nombre de graines 
		if(joueur.nbreGraine> 0 )
		{
			// on jouera engrais 
			// on regarde la carte la plus apropri�e pour planter
			choixTemporaire = jouerEngrais(joueur);
			action = 1;
			this.farfadet = false;
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
		liste.add(1,this.joueurCible );

		return liste;
	}
	
	public StrategyOffensive(Joueur joueur)
	{
		this.joueurCible = joueur;
	}
	public int jouerFarfadet(Joueur joueur){
		int[] farfadet = joueur.getFarfadetAllCard();
		return  this.cardMax(farfadet ,joueur.getMain().size());	
	}
	public int jouerEngrais(Joueur joueur){
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
	
	// m�thodes qui permet le maximum dans un tableau, ici la valeur max de farfadet
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
	
	public String toString()
	{
		return "";
	}

	public boolean isOffensive()
	{
		return farfadet;
	}
}
