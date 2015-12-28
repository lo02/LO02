import java.util.List;
/**
 * 
 * @author Gilles
 * L'interface Strategy permet aux joueurs virtuels de choisir la stratégie à prendre
 */
public interface Strategy {	
	
	/**
	 * Méthode qui permet de choisir qu'elle carte jouer
	 * @param joueur
	 * 				Variable qui contient le joueur virtuel qui veut choisir une carte
	 * @return
	 */
	public List choisirCarte(Joueur joueur);
	
	/**
	 * Méthode qui permet de savoir si le joueur virtuel v a jouer offensivement
	 * @return
	 */
	public boolean isOffensive();
}
