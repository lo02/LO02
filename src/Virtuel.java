import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
/**
 * 
 * @author Anass et Gilles
 * Classe qui contient le code de Virtuel ainsi que les m�thodes qui vont lui permettre de jouer.
 */
public class Virtuel extends Joueur {
	
	/**
	 * Attribut qui contient la liste des noms
	 */
	public final static List<String> names = Arrays.asList("Thor","Iron Man","Hulk","Black Widow","Spider-Man","Ant-Man","Cap.America","Coulson","Nick Fury","Pr.Xavier","Wolverine","Cyclope","Hawkeyes");
	
	/**
	 * Attribut qui contient la liste des noms disponible
	 */
	private static List<String> namesDisponible = new ArrayList<String>();
	
	/**
	 * Attribut qui contient la strategy du jeu virtuel
	 */
	public Strategy strategy;

	/**
	 * Constructeur d'un joueur Virtuel
	 * @param nbreMenhir
	 * @param nbreGraine
	 */
	public Virtuel( int nbreMenhir, int nbreGraine) {
		//String nom23 = aleaName();
		super(aleaName(), nbreMenhir, nbreGraine);
	}
	/**
	 * M�thode qui initialise les noms qui seront disponible pour les joueurs
	 */
	public static void initialisationNom(){
		namesDisponible.addAll(names);
	}
	
	/**
	 * M�thode qui permet d'attribuer des noms al�atoires parmi une liste
	 * @return
	 * 		le nom du joueur
	 */
	public static String aleaName()
	{
		Random rand = new Random();
		int nombreAleatoire = rand.nextInt(namesDisponible.size());
		String nom = namesDisponible.get(nombreAleatoire);
		namesDisponible.remove(nombreAleatoire);	
		return nom ;
	}
	
	/**
	 * M�thode qui va permettre au joueur virtuel de choisir une carte
	 * @return 
	 * 		une liste qui permet de jouer une carte
	 */
	public List choisirCarte(Strategy strategie)
	{
		return strategie.choisirCarte(this);	
	}
	// M�thode qui va permettre au joueur virtuel de s�l�ctionner la meillleur strat�gie entre normal et offensive
	/**
	 * M�thode qui permet aux joueurs virtuels de choisir la meilleur strat�gie possible.
	 * @param joueur
	 * @param chercherJoueurGrainesMax
	 * @return
	 * 		renvoie la strategie que le joueur virtuel � choisi
	 */
	public Strategy choisirStrategie(Joueur joueur , Joueur joueurCible)
	{	
		boolean farfadet = false ;		
		// on v�rifie si la valeur Farfadet > g�ant 
		if (joueur == joueurCible)
		{
			farfadet = false;
		}
		else
			{
			if(joueur.valeurMaxFarfadet()>joueur.valeurMaxGeant())
			
			{
				// on v�rifie si la cible � un nbre suffisant de graine � voler
				if(joueurCible.getNbreGraine() >= joueur.valeurMaxFarfadet())
				{
					farfadet = true ;
				}
				//Sinon on v�rifie si le geant nous permet d'obtenir plus de graine qu'avec le farfadet
				else
				{
					if(joueurCible.getNbreGraine()>=joueur.valeurMaxGeant())
					{
						farfadet = true;
					}
				}
			}
			}
		
		//on joue alors le farfadet
		if(farfadet)
		{
			Strategy strategy = new StrategyOffensive(joueurCible);
			return strategy;
		}
		//Sinon on joue le G�ant
		else
		{	
			Strategy strategy = new StrategyNormal();
			return strategy;
		}	
	}	
	
	/*M�thode qui va permettre de choisir soit une carte alli�e soit 2 graines en d�but de manche en partie
	 * avanc�e;
	*/
	/**
	 * M�thode qui permet aux joueurs virtuels d'effectuer leurs choix au d�but d'une manche en partie avanc�e
	 */
	public void choixDebutManche(){
		//On regarde le nombre de graine, s'il n'as pas de graine il en prend
		
		if (this.getNbreGraine()==0){
			this.setNbreGraine(this.getNbreGraine()+2);
		}
		else
		{
				if (this.getAllie()!=null)
			{
				this.getAllie().deleteAllie();
			}
			//Sinon il prend un alli�
			else
			{
				
				this.remplirMainJoueurAllie();
			}
		}
		
	}
	/**
	 * M�thode qui permet de jouer la carte alli� chien
	 * @return 
	 * 		le nombre de graine que le chien va sauver
	 */
	public int jouerChien(int valeur, Joueur j, Joueur joueurCible){
		//on regarde si il peut jouer son chien
		if(this.getAllie().getTitre().equals(""))
		{
			return 0;
		}
		else
		{
		if(this.getAllie().getTitre().equals("Chien de garde"))
		{
			if(this.getAllie().getValeur()[Partie.getTour()] > 0){
				int val = this.getAllie().getValeur()[Partie.getTour()];
				this.getAllie().deleteAllie();
				Main.afficherActionAllieeChien(this, val, j);
				return val;
			}
			else{
				return 0;
			}
		}
		else
		{
			return 0;
		}
			
		}
		
		
		
	}
	
	/**
	 * M�thode pour jouer l'alli� taupe d'un joueur virtuel
	 * @param j
	 * @param j2
	 */
	public void jouerTaupe(Joueur joueurCible , Joueur joueurSecond){
		if(this.getAllie() == null){
			
		}
		
		else
		{
			if(joueurCible == this ) 
			{
				joueurCible = joueurSecond;
			}
			else
			{
				
			if(this.getAllie().getTitre().equals("Taupe g�ante")){
		
				//On regarde la saison pour laquelle notre taupe peut d�truire le plus de menhir
				if(Partie.getTour()==this.rechercheMaxTaupe()){
					
					//Si le joueur cible n'a pas assez de Menhir on met � 0
					if(joueurCible.getNbreMenhir()-this.getAllie().getValeur()[Partie.getTour()] < 0){
						joueurCible.setNbreMenhir(0);
					}
					//Sinon on enleve la valeur indiqu�e
					else{
						joueurCible.setNbreMenhir(joueurCible.getNbreMenhir()-this.getAllie().getValeur()[Partie.getTour()]);
					}
					Main.afficherActionAllieeTaupe(this, joueurCible, this.getAllie().getValeur()[Partie.getTour()]);
					this.getAllie().deleteAllie();
				}
			}
			}
		}
	}
	/**
	 * M�thode qui permet de chercher la valer la plus grande d'une carte taupe
	 * @return
	 * 		valuer la plus haute
	 */
	public int rechercheMaxTaupe(){
		int Max = 0, id = 0;
		for (int i =0; i<4; i++){
			if(this.getAllie().getValeur()[i]>=Max)
			{
				Max = this.getAllie().getValeur()[i];
				id = i;
			}
		}
		return id;	
	}
	/**
	 * M�thode qui permet de savoir si le jouuer est virtuel
	 * @return
	 * 		vrai
	 * 	
	 */
	public boolean isVirtuel()
	{
		return true;
	}
}
	
	
	