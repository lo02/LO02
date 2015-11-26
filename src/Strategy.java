import java.util.List;

public interface Strategy {	

	public List choisirCarte(Joueur joueur);
	
	
	public boolean isOffensive();

}
