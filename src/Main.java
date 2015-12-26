//Code réalisée par EZZAAMARI Anass et JAUVION Gilles

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	// 
	
	
	public static void main(String[] argc) throws InterruptedException{
		 Model mod = Model.getInstance();
		
		// On lance l'interface principale 
		Graphique inter = new Graphique(0);
		while(mod.getNomJoueur() == null)
		{
			Thread.sleep(100);
		}
		
		
		//on demande au joueur physique de se donner un nom
		
		String nom = mod.getNomJoueur();
		
		//On met le nom dans le joueur
		Joueur j = new Joueur(nom,0,0); 
		mod.setJoueurPrincipal(j);
		System.out.println("Vous vous appelez : " +nom +"\n" + j);
		// demande du nombre de joueurs
		Partie partie = new Partie(mod.getNombreJoueurs());
		
		//On ajoute le joueur physique dans la liste des joueurs  
		List<Joueur> listeTemp =  new ArrayList<Joueur>(); 
		listeTemp = partie.getListJoueur();
		listeTemp.add(j);
		partie.setListJoueur(listeTemp);
		
		
		//On crée les joueurs virtuel
		partie.factoryJoueurs();
		
		// Partie Rapide ou Avancée 
		boolean vrai = true;
		
		int reponse = mod.getPartieRapide();
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
				
			}
		}
		
		if(reponse == 1)
		{
			j.setNbreGraine(2);
			mod.setJoueursPoints(partie.getListJoueur().toString());
			mod.setListeJoueur(partie.getListJoueur());
			Score boiteDeScore = new Score(0);
			MessageBox message = new MessageBox(0);
			for(int tour=0 ; tour <4 ; tour++) 
			{ 
				
				partie.setTour(tour);
				//Donne la liste des joueurs qui composent la partie
				mod.setJoueursPoints(partie.getListJoueur().toString());
				
				mod.setMain(j.getMain());
				SelectionDeCarte c = new SelectionDeCarte(0);
				while(mod.getCarteChoisie()==-1)
				{
					Thread.sleep(100);
				}
				
				int carte = mod.getCarteChoisie();
				mod.setCarteChoisie(-1);
				ChoixAttaque a = new ChoixAttaque(0);
				while(mod.getAction()==-1)
				{
					Thread.sleep(100);
				}
				
				int jeu = mod.getAction();
				mod.setAction(-1);
				if(jeu == 2)
				{
					ChoixJoueur b = new ChoixJoueur(0);
					while(mod.getIndexJoueurCible()==-1)
					{
						Thread.sleep(250);
					}
					
					
					int cible = mod.getIndexJoueurCible();
					mod.setIndexJoueurCible(-1);
					
					//System.out.println("Vous avez joué : \n "+j.getMain().get(carte)+"\nVous avez volé "+j.getMain().get(carte).getFarfadet()[Partie.getTour()]+" graine(s)");
					//System.out.println("Vous avez jou Farfadet avec une valeur de "+j.getMain().);
					j.poserCarte(carte, partie.getListJoueur().get(cible));
				}else
				{
					
					if(jeu == 0)
					{
						mod.setMessage("Vous demandez "+j.getMain().get(carte).getGeant()[Partie.getTour()]+" graine(s)");
						mod.setGeantAnimation(true);
					}	
					else
					{	mod.setMessage("Vous plantez "+j.getMain().get(carte).getEngrais()[Partie.getTour()]+" graine(s)");
					mod.setMenhir(j.getMain().get(carte).getEngrais()[Partie.getTour()]);}
					j.poserCarte(carte, jeu);
					mod.setJoueursPoints(partie.getListJoueur().toString());
					
				}
				
				partie.gererTour();
			
			}
			mod.setJoueursPoints(partie.getListJoueur().toString());
			mod.setMessage("Le gagnant est : <br>"+partie.chercherGagnantRapide().toString());
			
			
			
		}
		else
		if(reponse == 2){
			for(int manche = 0 ; manche<partie.getListJoueur().size(); manche++)
			{
				
				System.out.println("Début de la manche "+ (manche+1));
				int choix= Main.saisie("Souhaitez vous prendre 2 graines (tapez 1) ou une alliée (tapez 2)?", 1, 2);
				j.choixDebutManche(choix);
				if (choix == 2){
					System.out.println("Votre carte alliée :\n"+j.getAllie());
				}
				
				for(int tour=0 ; tour <4 ; tour++) 
				{
					partie.setTour(tour);
					for(int i=0;i<partie.getListJoueur().size();i++)
					{
					List<Joueur> listTemp = new LinkedList<Joueur>();
					listTemp.addAll(partie.arrangerOrdreListe(manche));
					if(listTemp.get(i)==j)
					{
						Main.deroulementJoueur(j, partie);
					}else
					{
					partie.gererTourAvancee(listTemp.get(i));
					System.out.println("Tapez une touche pour continuer");
					pause();
					
					}		
					}
					System.out.println(partie.getListJoueur());
					System.out.println("Tapez une touche pour continuer");
					pause();	
				}
				
				partie.initierPartieAvancee();
				
			}
			partie.finPartie();
			System.out.println("\nScore Final : \n"+ partie.getListJoueur()+"\nLe gagnant est : \n"+partie.getListJoueur().get(partie.chercherGagnantAvancee()));
			
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
		Model model = Model.getInstance();
		if (joueurcible.equals(model.getJoueurPrincipal()))
				{
					model.setFarfadetAnimation(true);
				}
		if (joueur.getMain().get(carte).getFarfadet()[Partie.getTour()]<= joueurcible.getNbreGraine())
			model.setMessage("Le joueur "+joueur.getNom()+" vole "+ joueur.getMain().get(carte).getFarfadet()[Partie.getTour()]+ " graine(s) de "+joueurcible.getNom());
		else
			model.setMessage("Le joueur "+joueur.getNom()+" vole "+joueurcible.getNbreGraine() + " graine(s) de "+joueurcible.getNom());
	}
	
	
	public static void afficherAction(Joueur joueur, int carte, int action){
		Model model = Model.getInstance();
		//Géant
		if (action == 0){
			model.setMessage("Le joueur "+joueur.getNom()+" demande "+ joueur.getMain().get(carte).getGeant()[Partie.getTour()] + " graine(s)" );
		}
		//Engrais
		if (action == 1){
			model.setMessage("Le joueur "+joueur.getNom()+" plante "+ joueur.getMain().get(carte).getEngrais()[Partie.getTour()] + " graine(s)");
		}	
	}
	
	public static void afficherActionAllieeTaupe(Joueur joueur , Joueur joueurCible , int valeur)
	{
		System.out.println("Le joueur "+joueur.getNom()+" détruit "+valeur+" ménhirs à "+joueurCible);
	}
	public static void afficherActionAllieeChien(Joueur joueur , int valeur)
	{
		System.out.println("Le joueur "+joueur.getNom()+" protège "+valeur+" de ses graines");
	}
	
	public static void pause() 
	{
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	public static void deroulementJoueur(Joueur j,Partie partie)
	{
		int carte = Main.saisie(j.getMain()+"\nChoisir la carte à jouer 0 - "+(j.getMain().size()-1)+" \n", 0, j.getMain().size()-1);
		int jeu = Main.saisie("Quel type de jeu : \n0- géant \n1- engrais\n2- Farfadet", 0, 2);
		
	if(jeu == 2)
	{
		System.out.print("Quel joueur voulez vous voler ?");
		for(int i = 1 ; i < partie.getListJoueur().size(); i++)
		{
			System.out.println("Tapez "+i+" pour \n"+partie.getListJoueur().get(i));
		}
		int cible = Main.saisie("", 1, partie.getListJoueur().size()-1);
		System.out.println("Vous avez joué : \n "+j.getMain().get(carte)+"\nVous avez volé "+j.getMain().get(carte).getFarfadet()[Partie.getTour()]+" graine(s)");
			
		j.poserCarte(carte, partie.getListJoueur().get(cible));
	}else
	{
		System.out.println("Vous avez joué : \n "+j.getMain().get(carte));
		if(jeu == 0)
			System.out.println("Vous demandez "+j.getMain().get(carte).getGeant()[Partie.getTour()]+" graine(s)");
		else
			System.out.println("Vous plantez "+j.getMain().get(carte).getEngrais()[Partie.getTour()]+" graine(s)");
		
		j.poserCarte(carte, jeu);
		
	}
	if (j.getAllie() == null)
	{
		
	}
	else{
		if(j.getAllie().getTitre().equals("Taupe géante")){
			System.out.println("Voulez vous jouer votre Taupe Géante (O/N)?");
			
			Scanner scs = new Scanner(System.in); 
			String reponse1 = scs.nextLine();
			if (reponse1.equals("O")){
				System.out.print("A quelle joueur souhaitez vous détruire les menhirs ?");
				for(int i = 1 ; i < partie.getListJoueur().size(); i++)
				{
					System.out.println("Tapez "+i+" pour \n"+partie.getListJoueur().get(i));
				}
				int cible = Main.saisie("", 1, partie.getListJoueur().size()-1);
				j.jouerTaupe(partie.getListJoueur().get(cible));
			}
		}
	}
	}
	
	public static int saisie(String saisie , int min , int max )
	{
		System.out.println(saisie);
		Scanner saisie2 = new Scanner(System.in);		
		String a = saisie2.nextLine();
		//saisie2.close();
		try{
		    if (Integer.parseInt(a)<=max && Integer.parseInt(a)>=min)
		    {
				return Integer.parseInt(a);
		    }else{
		    	return Main.saisie(saisie, min, max);
		    }
		}
	    catch(NumberFormatException e){
		     return Main.saisie(saisie, min, max);	
	    }		
	}
}