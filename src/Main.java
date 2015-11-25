import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] argc){
		//on demande de joueur physique de se donner un nom
		
		Scanner sc = new Scanner(System.in); 
		System.out.println("Saisissez votre nom : ");
		String nom = sc.nextLine();
		//On met le nom dans le joueur
		Joueur j = new Joueur(nom,0,2); 
		System.out.println("Vous vous appelez : " +nom +"\n" + j);
		
		
		// demande du nombre de joueurs
		Scanner saisie = new Scanner(System.in); 
		System.out.println("Saisissez le nombre de joueurs entre 1 et 5 ");
		Partie partie = new Partie(saisie.nextInt());
		
		//On ajoute le joueur physique dans la liste des joueurs  
		List<Joueur> listeTemp =  new ArrayList<Joueur>(); 
		listeTemp = partie.getListJoueur();
		listeTemp.add(j);
		partie.setListJoueur(listeTemp);
		
		
		//On crée les joueurs virtuel
		partie.factoryJoueurs();
		
		// Partie Rapide ou Avancée 
		boolean vrai = true;
		System.out.println("1 - rapide\n2 - avancée ");
		int reponse = saisie.nextInt();
		while(vrai)
		{
			switch (reponse)
			{
				case 1: partie.initierPartieRapide();
				vrai = false;
				break;
				case 2: partie.jouerPartieAvancee();
				vrai=false;
				break;
				default: System.out.println("Taper 1 ou 2" );
				break;
			}
		}
		
		if(reponse == 1)
		{
			for(int tour=0 ; tour <4 ; tour++) 
			{
				partie.setTour(tour);
				System.out.println(partie.getListJoueur());
				System.out.println("Choisir la carte à jouer 0 - 3 \n"+j.getMain());
				int carte = saisie.nextInt();
				System.out.println("Quel type de jeu : \n0- géant \n1- engrais\n2- Farfadet");
				int jeu = saisie.nextInt();
				if(jeu == 2)
				{
					System.out.print("Quel joueur voulez vous voler ?");
					for(int i = 1 ; i < partie.getListJoueur().size(); i++)
					{
						System.out.println("Tapez "+i+" pour \n"+partie.getListJoueur().get(i));
					}
					int cible = saisie.nextInt();
					System.out.println("Vous avez joué : \n "+j.getMain().get(carte));
					j.poserCarte(carte, partie.getListJoueur().get(cible));
				}else
				{
					System.out.println("Vous avez joué : \n "+j.getMain().get(carte));
					j.poserCarte(carte, jeu);
					
				}
				
				partie.gererTour();
				
			}
			System.out.println("\nScore Final : \n"+partie.getListJoueur()+"\nLe gagnant est : \n"+ partie.getListJoueur().get(partie.chercherGagnant()));
			
			
			
		}
		
		
		
	}
}
