import java.util.List;
/**
 * 
 * @author Anass et Gilles 
 * L'interface Strategy permet aux joueurs virtuels de choisir la strat�gie � prendre
 */
public interface Strategy {	
	
	/**
	 * M�thode qui permet de choisir qu'elle carte jouer
	 * @param joueur
	 * 				Variable qui contient le joueur virtuel qui veut choisir une carte
	 * @return
	 * 			Renvoie une liste qui d�pend du choix du joueur virtuel, 
	 */
	public List choisirCarte(Joueur joueur);
	
	/**
	 * M�thode qui permet de savoir si le joueur virtuel v a jouer offensivement
	 * @return
	 * 			renvoie un bool�en 
	 */
	public boolean isOffensive();
}
