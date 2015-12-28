import java.util.List;
/**
 * 
 * @author Anass et Gilles 
 * L'interface Strategy permet aux joueurs virtuels de choisir la stratégie à prendre
 */
public interface Strategy {	
	
	/**
	 * Méthode qui permet de choisir qu'elle carte jouer
	 * @param joueur
	 * 				Variable qui contient le joueur virtuel qui veut choisir une carte
	 * @return
	 * 			Renvoie une liste qui dépend du choix du joueur virtuel, 
	 */
	public List choisirCarte(Joueur joueur);
	
	/**
	 * Méthode qui permet de savoir si le joueur virtuel v a jouer offensivement
	 * @return
	 * 			renvoie un booléen 
	 */
	public boolean isOffensive();
}
