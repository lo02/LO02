//Code réalisé par EZZAAMARI Anass et JAUVION Gilles

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JPanel;

public class Main {
	
	public static void main(String[] argc) throws InterruptedException{
		 Model mod = Model.getInstance();
		mod.setA(Thread.currentThread());
	
		 
		// On lance l'interface principale
		 if(!(mod.isDoNotRelaunch()))
		 {
			 Graphique inter = new Graphique(0);
		 }
		
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
		
		 new Thread() {
	            public void run() {
	            	
	            	while(!(mod.isRestart()))
	            	{
	            		try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	            		
	            		
	            	}
	            	
	            	try {
	            		
	            		mod.setRestart(false);
	            		mod.setResetPrincipalInterface(true);
	            		mod.setFinished(true);
	            		if(!(mod.isResetAll()))
	            		{
	            			mod.setDoNotRelaunch(true);
	            		}
	            		else
	            		{
	            			 StringBuilder cmd = new StringBuilder();
	            		        cmd.append(System.getProperty("java.home") + File.separator + "bin" + File.separator + "java ");
	            		        for (String jvmArg : ManagementFactory.getRuntimeMXBean().getInputArguments()) {
	            		            cmd.append(jvmArg + " ");
	            		        }
	            		        cmd.append("-cp ").append(ManagementFactory.getRuntimeMXBean().getClassPath()).append(" ");
	            		        cmd.append(Main.class.getName()).append(" ");
								for (String arg : argc) {
	            		            cmd.append(arg).append(" ");
	            		        }
	            		        try {
									Runtime.getRuntime().exec(cmd.toString());
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
	            		        while(true)
	            		        {
	            		        	System.exit(0);
	            		        }
	            		        
	            		}
	            		mod.getA().interrupt();
	            		Thread.sleep(500);
	            		mod.setFinished(false);
	            		mod.getA().interrupt();
	            		mod.clearBox();
	            		Thread.sleep(1000);
	            		Ingredient.resetTasDeCartes();
						main(argc);
					
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
		 }.start();
		
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
					mod.setFarfadetAnimation2(true);
					Thread.sleep(9500);
					
					int cible = mod.getIndexJoueurCible();
					mod.setIndexJoueurCible(-1);
					
					//System.out.println("Vous avez joué : \n "+j.getMain().get(carte)+"\nVous avez volé "+j.getMain().get(carte).getFarfadet()[Partie.getTour()]+" graine(s)");
					mod.setMessage("Vous avez volé "+j.getMain().get(carte).getFarfadet()[Partie.getTour()]+ " graine(s) au joueur "+partie.getListJoueur().get(cible).getNom());
					j.poserCarte(carte, partie.getListJoueur().get(cible));
				}else
				{
					
					if(jeu == 0)
					{
						mod.setMessage("Vous demandez "+j.getMain().get(carte).getGeant()[Partie.getTour()]+" graine(s)");
						mod.setGeantAnimation(true);
						Thread.sleep(8000);
					}	
					else
					{	if(j.getNbreGraine()>=j.getMain().get(carte).getEngrais()[Partie.getTour()] )
					{
						mod.setMessage("Vous plantez "+j.getMain().get(carte).getEngrais()[Partie.getTour()]+" graine(s)");
					mod.setMenhir(j.getMain().get(carte).getEngrais()[Partie.getTour()]);}
					else
					{
						mod.setMessage("Vous plantez "+j.getNbreGraine()+" graine(s)");
						mod.setMenhir(j.getNbreGraine());}
					
					}
					
					j.poserCarte(carte, jeu);
					mod.setJoueursPoints(partie.getListJoueur().toString());
					
				}
				
				partie.gererTour();
			
			}
			mod.setJoueursPoints(partie.getListJoueur().toString());
			mod.setListeJoueurGagnant(partie.chercherGagnantRapide());
			FenetreFin f = new FenetreFin(0);
			//mod.setMessage("Le gagnant est : <br>"+partie.chercherGagnantRapide().toString());
			
			
			
		}
		else
		if(reponse == 2){
			//SelectionCarteAlliee A = new SelectionCarteAlliee(0);
			mod.setJoueursPoints(partie.getListJoueur().toString());
			mod.setListeJoueur(partie.getListJoueur());
			Score boiteDeScore = new Score(0);
			MessageBox message = new MessageBox(0);
			j.setNbreMenhir(5);
			
			new Thread() {
	            public void run() {
	            
	            	while(true)
	            	{
	            		try {
							Thread.sleep(300);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	            	if (!(mod.getIndexJoueurCibleTaupe()==99)){
	            		mod.setAncienPointCible(partie.getListJoueur().get(mod.getIndexJoueurCibleTaupe()).getNbreMenhir());
	            		mod.setMenhirADetruire(j.getAllie().getValeur()[Partie.getTour()]);
	            		mod.setIndexJoueurCibleTaupe2(mod.getIndexJoueurCibleTaupe());
	            		mod.setTaupe(true);
	            		Main.afficherActionAllieeTaupe(j, mod.getListeJoueur().get(mod.getIndexJoueurCibleTaupe()), mod.getMenhirADetruire());
	            		j.jouerTaupe(partie.getListJoueur().get(mod.getIndexJoueurCibleTaupe()));
	            		try {
							Thread.sleep(4000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	            		mod.setJoueursPoints(partie.getListJoueur().toString());
	            		mod.setIndexJoueurCibleTaupe(99);
	            		
	            	}
	            	
	            	}
	            }
	        }.start();
			for(int manche = 0 ; manche<partie.getListJoueur().size(); manche++)
			{
				
				j.setNbreMenhir(10);
				
				
				
				mod.setMessage("Début de la manche "+ (manche+1)+"<br>");
				ChoixDebutManche m = new ChoixDebutManche(0);
				while(mod.getChoix()== 0)
				{
					Thread.sleep(100);
				}
				
				
				
				int choix = mod.getChoix();
				mod.setChoix(0);
				//int choix= Main.saisie("Souhaitez vous prendre 2 graines (tapez 1) ou une alliée (tapez 2)?", 1, 2);
				j.choixDebutManche(choix);
				
				if (choix == 2){
					mod.setAllie(j.getAllie());
				
					SelectionCarteAlliee B = new SelectionCarteAlliee(0);
				
					
					
					
				}
				
				for(int tour=0 ; tour <4 ; tour++) 
				{
					
					
					partie.setTour(tour);
					for(int i=0;i<partie.getListJoueur().size();i++)
					{
						List<Joueur> listTemp = new LinkedList<Joueur>();
						listTemp.addAll(partie.arrangerOrdreListe(manche));
						
						mod.setJoueursPoints(partie.getListJoueur().toString());
						mod.setMain(j.getMain());
						mod.setAllie(j.getAllie());
						// On veut jouer la taupe ici à n'importe qu'elle moment durant le tour
						//A chaque fois qu'un joueur voudra jouer on pourra utiliser la taupe
						
						if(listTemp.get(i)==j)
						{
							Main.deroulementJoueur(j, partie);
							mod.setMessage("yo1");
							
							
							/*if(j.getAllie() != null){
								
							
								if (j.getAllie().getTitre().equals("Taupe géante")){
									mod.setMessage("yo");
									
									DemandeTaupe t = new DemandeTaupe(0);
									while(mod.getChoixTaupe()== 0)
									{
										Thread.sleep(100);
									}
									int choixTaupe = mod.getChoixTaupe();
									mod.setChoixTaupe(0);
									if(choixTaupe == 1){
										ChoixJoueurTaupe tv = new ChoixJoueurTaupe(0);
										
										while(mod.getChoix()== 0)
										{
											try {
												Thread.sleep(100);
											} catch (InterruptedException e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}
										}
										
										int choixB = mod.getChoix();
										mod.setChoix(0);
										mod.getJoueurPrincipal().jouerTaupe(mod.getListeJoueur().get(choix));
									}
								}
								
							}*/
						}else
						{
							partie.gererTourAvancee(listTemp.get(i));
							//System.out.println("Tapez une touche pour continuer");
							pause();
						}		
					}
					
					//System.out.println("Tapez une touche pour continuer");
					//pause();	
				}
				mod.setMenhir(-1);
				partie.initierPartieAvancee();
				
				
			}
			partie.finPartie();
			mod.setJoueurGagnant(partie.getListJoueur().get(partie.chercherGagnantAvancee()));
			FenetreFin f = new FenetreFin(0);
			System.out.println("\nScore Final : \n"+ partie.getListJoueur()+"\nLe gagnant est : \n"+partie.getListJoueur().get(partie.chercherGagnantAvancee()));
			
		}
		
		
		
	}
	
	public static boolean danger(int valeur, Joueur j, Joueur joueurCible){
		Model model = Model.getInstance();
		model.setMessage2("<html>Le joueur "+j.getNom()+" veut vous voler "+valeur+" graine(s) avec un farfadet!!!<br>Voulez vous utilisez votre chien de garde ?</html>");
		
		ChienDeGarde c = new ChienDeGarde("");
		while(model.getChienDeGardeAction()==-1)
		{
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (model.getChienDeGardeAction()==1){
			model.setChienDeGarde(true);
			model.setMessage("Le joueur "+model.getJoueurPrincipal().getNom()+"Défends "+model.getJoueurPrincipal().getAllie().getValeur()[Partie.getTour()]+" de ses graines");
			model.setChienDeGardeAction(-1);
			return true;
		}
		else{
			model.setFarfadetAnimation(true);
			model.setChienDeGardeAction(-1);
			return false;
		}
	}
	
	public static void afficherActionoff(Joueur joueur, Joueur joueurcible, int carte){
		Model model = Model.getInstance();
		if (joueurcible.equals(model.getJoueurPrincipal()))
				{
					if(model.getPartieRapide()==2)
					{	
				if(model.getAllie()!= null)
				{
					if(model.getAllie().equals("Chien de garde"))
					{
						while(model.getChienDeGardeAction()==-1)
						{
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						if(model.getChienDeGardeAction()==0)
						{
							model.setFarfadetAnimation(true);
							try {
								Thread.sleep(4000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							model.setChienDeGardeAction(-1);
						}
						if(model.getChienDeGardeAction()==1)
						{	
							model.setChienDeGarde(true);
							model.setChienDeGardeAction(-1);
						}
					}
					else{
						Main.farfadetAnimation(joueur, joueurcible, carte);
						}
				}
				else{
					
					Main.farfadetAnimation(joueur, joueurcible, carte);
				}
				
				}
					else
					{
						
						Main.farfadetAnimation(joueur, joueurcible, carte);
					}
				}
		else
		{
			if (joueur.getMain().get(carte).getFarfadet()[Partie.getTour()]<= joueurcible.getNbreGraine())
			{
				
				model.setMessage("Le village "+joueur.getNom()+" a volé "+ joueur.getMain().get(carte).getFarfadet()[Partie.getTour()]+ " graine(s) à "+joueurcible.getNom());
			}else{
				
				model.setMessage("Le village "+joueur.getNom()+" a volé "+joueurcible.getNbreGraine() + " graine(s) à "+joueurcible.getNom());
			}
		}
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
		Model model = Model.getInstance();
		if(joueur.isVirtuel())
		{
			if(!(joueurCible.isVirtuel()))
			{
			
			model.setAncienPointCible(joueurCible.getNbreMenhir()+joueur.getAllie().getValeur()[Partie.getTour()]);
    		model.setMenhirADetruire(joueur.getAllie().getValeur()[Partie.getTour()]);
    		model.setTaupeEnnemi(true);
			}
			
    		
		}
		if(joueurCible.getNbreMenhir()>=valeur)
		{
			model.setMessage("Le joueur "+joueur.getNom()+" détruit "+valeur+" ménhirs à "+joueurCible.getNom());
		}
		else
		{
			model.setMessage("Le joueur "+joueur.getNom()+" détruit "+joueurCible.getNbreMenhir()+" ménhirs à "+joueurCible.getNom());
		}
	}
	public static void afficherActionAllieeChien(Joueur joueur , int valeur , Joueur j)
	{
		Model model = Model.getInstance();
		model.setMessage("Le joueur "+joueur.getNom()+" protège "+valeur+" de ses graines");
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
	

	public static void deroulementJoueur(Joueur j,Partie partie) throws InterruptedException
	{
		//int carte = Main.saisie(j.getMain()+"\nChoisir la carte à jouer 0 - "+(j.getMain().size()-1)+" \n", 0, j.getMain().size()-1);
		//int jeu = Main.saisie("Quel type de jeu : \n0- géant \n1- engrais\n2- Farfadet", 0, 2);
		Model mod = Model.getInstance();
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
			/*System.out.print("Quel joueur voulez vous voler ?");
			for(int i = 1 ; i < partie.getListJoueur().size(); i++)
			{
				System.out.println("Tapez "+i+" pour \n"+partie.getListJoueur().get(i));
			}
			int cible = Main.saisie("", 1, partie.getListJoueur().size()-1);
			System.out.println("Vous avez joué : \n "+j.getMain().get(carte)+"\nVous avez volé "+j.getMain().get(carte).getFarfadet()[Partie.getTour()]+" graine(s)");
				
			j.poserCarte(carte, partie.getListJoueur().get(cible));*/
			
			ChoixJoueur b = new ChoixJoueur(0);
			while(mod.getIndexJoueurCible()==-1)
			{
				Thread.sleep(250);
			}
			//mod.setFarfadetAnimation2(true);
			
			
			int cible = mod.getIndexJoueurCible();
			
			mod.setMessage("Vous avez volé "+j.getMain().get(carte).getFarfadet()[Partie.getTour()]+ " graine(s) au joueur "+partie.getListJoueur().get(cible).getNom());
			j.poserCarteBis(carte, partie.getListJoueur().get(cible));
			while(mod.getCas()==-1)
			{
				System.out.println("en attente");
				Thread.sleep(100);
			}
			if(mod.getCas()==0)
			{
				
				mod.setFarfadetAnimation2(true);
				Thread.sleep(9500);
			}
			else
				if(mod.getCas()==1)
				{
					mod.setChienDeGardeEnnemi(true);
					Thread.sleep(12160);
				}
			mod.setCas(-1);
			mod.setIndexJoueurCible(-1);
			
			
			
			
		}else
		{
			/*System.out.println("Vous avez joué : \n "+j.getMain().get(carte));
			if(jeu == 0)
				System.out.println("Vous demandez "+j.getMain().get(carte).getGeant()[Partie.getTour()]+" graine(s)");
			else
				System.out.println("Vous plantez "+j.getMain().get(carte).getEngrais()[Partie.getTour()]+" graine(s)");
			
			j.poserCarte(carte, jeu);
			*/
			
			if(jeu == 0)
			{
				mod.setMessage("Vous demandez "+j.getMain().get(carte).getGeant()[Partie.getTour()]+" graine(s)");
				mod.setGeantAnimation(true);
				Thread.sleep(8000);
			}	
			else
			{	if(j.getNbreGraine()>=j.getMain().get(carte).getEngrais()[Partie.getTour()] ){
					mod.setMessage("Vous plantez "+j.getMain().get(carte).getEngrais()[Partie.getTour()]+" graine(s)");
					mod.setMenhir(j.getMain().get(carte).getEngrais()[Partie.getTour()]);
				}else{
					mod.setMessage("Vous plantez "+j.getNbreGraine()+" graine(s)");
					mod.setMenhir(j.getNbreGraine());}
			}
		
			j.poserCarte(carte, jeu);
			mod.setJoueursPoints(partie.getListJoueur().toString());
		}
		
		
		
		//On ne veut pas forcément jouer la taupe à la fin de notre tour, on veut pouvoir la jouer à n'importe qu'elle moment
		
		/*if (j.getAllie() == null)
		{}
		else{
			if(j.getAllie().getTitre().equals("Taupe géante")){
				/*System.out.println("Voulez vous jouer votre Taupe Géante (O/N)?");
				
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
		}*/
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
	
	
	public static void farfadetAnimation(Joueur joueur , Joueur joueurcible , int carte)
	{
			Model model = Model.getInstance();
			
			if(joueurcible.getAllie()!= null)
			{
				if(joueurcible.getAllie().getTitre().equals("Chien de garde"))
				{
					
				}
			}
			else
			{
				
			
			model.setFarfadetAnimation(true);
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (joueur.getMain().get(carte).getFarfadet()[Partie.getTour()]<= joueurcible.getNbreGraine())
			{
				
				model.setMessage("Le joueur "+joueur.getNom()+" vous a volé "+ joueur.getMain().get(carte).getFarfadet()[Partie.getTour()]+ " graine(s)");
			}else{
				
				model.setMessage("Le joueur "+joueur.getNom()+" vous a volé "+joueurcible.getNbreGraine() + " graine(s)");
			}
			}
		}

	
}
