import java.util.List;

public class Model {
	
	
	
	// Communication inter-interfaces 
	private int menhir = 0;
	private boolean geantAnimation = false ; 
	private boolean farfadetAnimation = false;
	private boolean farfadetAnimation2 = false;
	private boolean chienDeGarde = false;
	private boolean chienDeGardeEnnemi = false;
	private boolean taupeEnnemi = false;
	private boolean taupe = false;
	
	
	
	// Echange entre interface et le jeu 
	private int partieRapide;
	private String nomJoueur;
	private int nombreJoueurs;
	private String joueursPoints = new String();
	private List<Card> main;
	private int carteChoisie= -1 ;
	private int action= -1;
	private Joueur joueurPrincipal;
	private List<Joueur> listeJoueur;
	private int indexJoueurCible=-1;
	private String message="";
	private int choix = 0;
	private int choixTaupe = 0;
	private Card allie;
	private int menhirADetruire = -1;
	
	
	private static Model instance;

	private Model()
	{
		
	}
	
	public int getMenhir() {
		return menhir;
	}

	public void setMenhir(int menhir) {
		this.menhir = menhir;
	}
	
	public static Model getInstance()
	{
		
		if (instance == null)
		{
			instance = new Model();
		}
		return instance;
		
	}

	public boolean isGeantAnimation() {
		return geantAnimation;
	}
	
	

	public void setGeantAnimation(boolean geantAnimation) {
		this.geantAnimation = geantAnimation;
	}

	public boolean isFarfadetAnimation() {
		return farfadetAnimation;
	}

	public void setFarfadetAnimation(boolean farfadetAnimation) {
		this.farfadetAnimation = farfadetAnimation;
	}


	

	public String getNomJoueur() {
		return nomJoueur;
	}

	public void setNomJoueur(String nomJoueur) {
		this.nomJoueur = nomJoueur;
	}

	public int getNombreJoueurs() {
		return nombreJoueurs;
	}

	public void setNombreJoueurs(int nombreJoueurs) {
		this.nombreJoueurs = nombreJoueurs;
	}

	public int getPartieRapide() {
		return partieRapide;
	}

	public void setPartieRapide(int partieRapide) {
		this.partieRapide = partieRapide;
	}

	public String getJoueursPoints() {
		return joueursPoints;
	}

	public void setJoueursPoints(String joueursPoints) {
		this.joueursPoints = joueursPoints;
	}

	public List<Card> getMain() {
		return main;
	}

	public void setMain(List<Card> main) {
		this.main = main;
	}
	
	public Card getAllie(){
		return allie;
	}
	
	public void setAllie(Card allie){
		this.allie = allie;
	}

	public int getCarteChoisie() {
		return carteChoisie;
	}

	public void setCarteChoisie(int carteChoisie) {
		this.carteChoisie = carteChoisie;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	
	public int getChoix() {
		return choix;
	}

	public void setChoix(int choix) {
		this.choix =choix;
	}
	
	public Joueur getJoueurPrincipal() {
		return joueurPrincipal;
	}

	public void setJoueurPrincipal(Joueur joueurPrincipal) {
		this.joueurPrincipal = joueurPrincipal;
	}

	public List<Joueur> getListeJoueur() {
		return listeJoueur;
	}

	public void setListeJoueur(List<Joueur> listeJoueur) {
		this.listeJoueur = listeJoueur;
	}

	public int getIndexJoueurCible() {
		return indexJoueurCible;
	}

	public void setIndexJoueurCible(int indexJoueurCible) {
		this.indexJoueurCible = indexJoueurCible;
	}

	public String getMessage() {
		return "<html><table style='background-color:black; color:white; width:100%'><tr><td>"+message+"</tr></td></table></html>";
	}

	public void setMessage(String message) {
		
		this.message = this.message +"<br>"+ message;
	}

	public boolean isFarfadetAnimation2() {
		return farfadetAnimation2;
	}

	public void setFarfadetAnimation21(boolean farfadetAnimation2) {
		this.farfadetAnimation2 = farfadetAnimation2;
	}

	public void setFarfadetAnimation2(boolean farfadetAnimation22) {
		// TODO Auto-generated method stub
		this.farfadetAnimation2 = farfadetAnimation22;
		
	}

	public boolean isChienDeGarde() {
		return chienDeGarde;
	}

	public void setChienDeGarde(boolean chienDeGarde) {
		this.chienDeGarde = chienDeGarde;
	}

	public boolean isChienDeGardeEnnemi() {
		return chienDeGardeEnnemi;
	}

	public void setChienDeGardeEnnemi(boolean chienDeGardeEnnemi) {
		this.chienDeGardeEnnemi = chienDeGardeEnnemi;
	}

	public boolean isTaupeEnnemi() {
		return taupeEnnemi;
	}

	public void setTaupeEnnemi(boolean taupeEnnemi) {
		this.taupeEnnemi = taupeEnnemi;
	}

	public boolean isTaupe() {
		return taupe;
	}

	public void setTaupe(boolean taupe) {
		this.taupe = taupe;
	}

	public int getMenhirADetruire() {
		return menhirADetruire;
	}

	public void setMenhirADetruire(int menhirADetruire) {
		this.menhirADetruire = menhirADetruire;
	}
	public int getChoixTaupe(){
		return choixTaupe;
	}
	public void setChoixTaupe(int choixTaupe){
		this.choixTaupe = choixTaupe;
	}
	
	
	
}
