//Code r�alis�e par EZZAAMARI Anass et JAUVION Gilles

import java.io.IOException;
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
		Joueur j = new Joueur(nom,0,0); 
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
		
		
		//On cr�e les joueurs virtuel
		partie.factoryJoueurs();
		
		// Partie Rapide ou Avanc�e 
		boolean vrai = true;
		System.out.println("1 - rapide\n2 - avanc�e ");
		int reponse = saisie.nextInt();
		while(vrai)
		{
			switch (reponse)
			{
				case 1: partie.initierPartieRapide();
				vrai = false;
				break;
				case 2: partie.initierPartieAvancee();
				vrai=false;
				break;
				default: System.out.println("Taper 1 ou 2" );
				break;
			}
		}
		
		if(reponse == 1)
		{
			j.setNbreGraine(2);
			for(int tour=0 ; tour <4 ; tour++) 
			{
				partie.setTour(tour);
				//Donne la liste des joueurs qui composent la partie
				System.out.println(partie.getListJoueur());
				System.out.println("Choisir la carte � jouer 0 - 3 \n"+j.getMain());
				int carte = saisie.nextInt();
				System.out.println("Quel type de jeu : \n0- g�ant \n1- engrais\n2- Farfadet");
				int jeu = saisie.nextInt();
				if(jeu == 2)
				{
					System.out.print("Quel joueur voulez vous voler ?");
					for(int i = 1 ; i < partie.getListJoueur().size(); i++)
					{
						System.out.println("Tapez "+i+" pour \n"+partie.getListJoueur().get(i));
					}
					int cible = saisie.nextInt();
					System.out.println("Vous avez jou� : \n "+j.getMain().get(carte));
					j.poserCarte(carte, partie.getListJoueur().get(cible));
				}else
				{
					System.out.println("Vous avez jou� : \n "+j.getMain().get(carte));
					j.poserCarte(carte, jeu);
					
				}
				
				partie.gererTour();
				
			}
			System.out.println("\nScore Final : \n"+partie.getListJoueur()+"\nLe gagnant est : \n"+ partie.getListJoueur().get(partie.chercherGagnant()));
			
			
			
		}
		else
		if(reponse == 2){
			for(int manche = 0 ; manche<partie.getListJoueur().size(); manche++)
			{
				
				System.out.println("D�but de la manche "+ (manche+1));
				System.out.println("Souhaitez vous prendre 2 graines (tapez 1) ou une alli�e (tapez 2)?");
				int choix= sc.nextInt();
				j.choixDebutManche(choix);
				if (choix == 2){
					System.out.println(j.getAllie());
				}
				
				for(int tour=0 ; tour <4 ; tour++) 
				{
					partie.setTour(tour);
					System.out.println("Choisir la carte � jouer 0 - 3 \n"+j.getMain());
					int carte = saisie.nextInt();
					System.out.println("Quel type de jeu : \n0- g�ant \n1- engrais\n2- Farfadet");
					int jeu = saisie.nextInt();
					if(jeu == 2)
					{
						System.out.print("Quel joueur voulez vous voler ?");
						for(int i = 1 ; i < partie.getListJoueur().size(); i++)
						{
							System.out.println("Tapez "+i+" pour \n"+partie.getListJoueur().get(i));
						}
						int cible = saisie.nextInt();
						System.out.println("Vous avez jou� : \n "+j.getMain().get(carte));
						j.poserCarte(carte, partie.getListJoueur().get(cible));
					}else
					{
						System.out.println("Vous avez jou� : \n "+j.getMain().get(carte));
						j.poserCarte(carte, jeu);
						
					}
					if (j.getAllie() == null)
					{
						
					}
					else{
						if(j.getAllie().getTitre().equals("Taupe g�ante")){
							System.out.println("Voulez vous jouer votre Taupe G�ante (O/N)?");
							
							Scanner scs = new Scanner(System.in); 
							String reponse1 = scs.nextLine();
							if (reponse1.equals("O")){
								System.out.print("A quelle joueur souhaitez vous d�truire les menhirs ?");
								for(int i = 1 ; i < partie.getListJoueur().size(); i++)
								{
									System.out.println("Tapez "+i+" pour \n"+partie.getListJoueur().get(i));
								}
								int cible = saisie.nextInt();
								j.jouerTaupe(partie.getListJoueur().get(cible));
							}
							
						}
					}
					
					partie.gererTourAvancee();
					System.out.print("Tapez une touche pour continuer\n");
					pause();
					System.out.println(partie.getListJoueur());
					System.out.print("Tapez une touche pour continuer\n");
					pause();
				}
				
				partie.initierPartieAvancee();
				
			}
		}
		
		
		
	}
	
	public static boolean danger(int valeur, String j, Joueur joueurCible){
		System.out.println("Le joueur "+j+" veut vous voler "+valeur+" graine(s) avec un farfadet!!!\nVoulez vous utilisez votre chien de garde (O/N)?");
		Scanner sc = new Scanner(System.in); 
		String reponse = sc.nextLine();
		if (reponse.equals("O")){
			return true;
		}
		else{
			return false;
		}
	}
	
	public static void afficherActionoff(Joueur joueur, Joueur joueurcible, int carte){
		System.out.println("Le joueur "+joueur.getNom()+" vole "+ joueur.getMain().get(carte).getFarfadet()[Partie.getTour()]+ " graine(s) de "+joueurcible.getNom());
	}
	
	
	public static void afficherAction(Joueur joueur, int carte, int action){
		//G�ant
		if (action == 0){
			System.out.println("Le joueur "+joueur.getNom()+" demande "+ joueur.getMain().get(carte).getGeant()[Partie.getTour()] + " graine(s)" );
		}
		//Engrais
		if (action == 1){
			System.out.println("Le joueur "+joueur.getNom()+" plante "+ joueur.getMain().get(carte).getEngrais()[Partie.getTour()] + " graine(s)");
		}
		
		
	}
	
	public static void afficherActionAllieeTaupe(Joueur joueur , Joueur joueurCible , int valeur)
	{
		System.out.println("Le joueur "+joueur.getNom()+"d�truit "+valeur+" m�nhirs � "+joueurCible);
	}
	public static void afficherActionAllieeChien(Joueur joueur , int valeur)
	{
		System.out.println("Le joueur "+joueur.getNom()+" prot�ge "+valeur+" de ses graines");
	}
	
	public static void pause()
	{
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
