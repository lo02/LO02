import java.util.List;
/**
 * 
 * @author Gilles
 * L'interface Strategy permet aux joueurs virtuels de choisir la strat�gie � prendre
 */
public interface Strategy {	
	
	/**
	 * M�thode qui permet de choisir qu'elle carte jouer
	 * @param joueur
	 * 				Variable qui contient le joueur virtuel qui veut choisir une carte
	 * @return
	 */
	public List choisirCarte(Joueur joueur);
	
	/**
	 * M�thode qui permet de savoir si le joueur virtuel v a jouer offensivement
	 * @return
	 */
	public boolean isOffensive();
}
