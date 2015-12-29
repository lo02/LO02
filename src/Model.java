import java.util.List;

import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;

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
	private int partieRapide = -1;
	private String nomJoueur;
	private int nombreJoueurs;
	private String joueursPoints = new String();
	private List<Card> main;
	private int carteChoisie= -1 ;
	private int action= -1;
	private Joueur joueurPrincipal;
	private List<Joueur> listeJoueur;
	//contient liste des gagnant partie rapide
	private List<Joueur> listeJoueurGagnant;
	//contient joueur gagnant partie avancée
	private Joueur joueurGagnant;
	private int indexJoueurCible=-1;
	private String message="";
	private int choix = 0;
	private int choixTaupe = 0;
	private Card allie;
	private int menhirADetruire = -1;
	private int indexJoueurCibleTaupe = 99;
	private int indexJoueurCibleTaupe2 = 0;
	private int ancienPointCible;
	private int chienDeGardeAction = -1;
	private String message2;
	private boolean possetionChienDeGarde= false;
	private int cas=-1;
	private Thread a;
	private MyAudioPlayer audio;
	
	
	
	private boolean restart = false ; // Juste pour amorcer le reset 
	private boolean doNotRelaunch = false; // Juste pour ne pas relancer l'interface graphique 
	private boolean resetPrincipalInterface = false; // reset l'interface graphique 
	private boolean finished = false; // ferme toutes les autres fenêtres 
	private boolean resetAll = false ; // Ferme et re-ouvre l'interface principale  
	private AdvancedPlayer song;
	
	
	
	
	
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

	public int getIndexJoueurCibleTaupe() {
		return indexJoueurCibleTaupe;
	}

	public void setIndexJoueurCibleTaupe(int indexJoueurCibleTaupe) {
		this.indexJoueurCibleTaupe = indexJoueurCibleTaupe;
	}

	public int getIndexJoueurCibleTaupe2() {
		return indexJoueurCibleTaupe2;
	}

	public void setIndexJoueurCibleTaupe2(int indexJoueurCibleTaupe2) {
		this.indexJoueurCibleTaupe2 = indexJoueurCibleTaupe2;
	}

	public int getAncienPointCible() {
		return ancienPointCible;
	}

	public void setAncienPointCible(int ancienPointCible) {
		this.ancienPointCible = ancienPointCible;
	}

	public int getChienDeGardeAction() {
		return chienDeGardeAction;
	}

	public void setChienDeGardeAction(int chienDeGardeAction) {
		this.chienDeGardeAction = chienDeGardeAction;
	}

	public String getMessage2() {
		return message2;
	}

	public void setMessage2(String message2) {
		this.message2 = message2;
	}

	public boolean isPossetionChienDeGarde() {
		return possetionChienDeGarde;
	}

	public void setPossetionChienDeGarde(boolean possetionChienDeGarde) {
		this.possetionChienDeGarde = possetionChienDeGarde;
	}

	public int getCas() {
		return cas;
	}

	public void setCas(int cas) {
		this.cas = cas;
	}

	public Thread getA() {
		return a;
	}

	public void setA(Thread a) {
		this.a = a;
	}

	public boolean isRestart() {
		return restart;
	}

	public void setRestart(boolean restart) {
		this.restart = restart;
	}

	public boolean isDoNotRelaunch() {
		return doNotRelaunch;
	}

	public void setDoNotRelaunch(boolean doNotRelaunch) {
		this.doNotRelaunch = doNotRelaunch;
	}

	public boolean isResetPrincipalInterface() {
		return resetPrincipalInterface;
	}

	public void setResetPrincipalInterface(boolean resetPrincipalInterface) {
		this.resetPrincipalInterface = resetPrincipalInterface;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	
	public void clearBox()
	{
		this.message="";
	}

	public boolean isResetAll() {
		return resetAll;
	}

	public void setResetAll(boolean resetAll) {
		this.resetAll = resetAll;
		this.setNomJoueur(null);
	}

	public AdvancedPlayer getSong() {
		return song;
	}

	public void setSong(AdvancedPlayer player) {
		this.song = player;
	}

	public List<Joueur> getListeJoueurGagnant() {
		return listeJoueurGagnant;
	}

	public void setListeJoueurGagnant(List<Joueur> listeJoueurGagnant) {
		this.listeJoueurGagnant = listeJoueurGagnant;
	}

	public Joueur getJoueurGagnant() {
		return joueurGagnant;
	}

	public void setJoueurGagnant(Joueur joueurGagnant) {
		this.joueurGagnant = joueurGagnant;
	}

	public MyAudioPlayer getAudio() {
		return audio;
	}

	public void setAudio(MyAudioPlayer audio) {
		this.audio = audio;
	}
	
	
	
}
