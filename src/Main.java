import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
	
	public static void main(String[] argc) throws InterruptedException{
		 
		
		 Model mod = Model.getInstance();
		 
		 //Référencement du Thread actuel dans le Singleton
		 mod.setA(Thread.currentThread());
		
		 if(!(mod.isDoNotRelaunch()))
		 {
			 	
			 	// Création de la fenêtre principale avec un fond noir 
			 
			 	Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			    int x = (int) ((dimension.getWidth() ));
			    int y = (int) ((dimension.getHeight()));
			    JFrame frame = new JFrame();    
			    frame.setBounds(0, 0 ,x, y);
			    frame.setUndecorated(true);
			    frame.setFocusableWindowState(false);
			    JPanel panel5 = new JPanel();
			    panel5.setBounds(0, 0, x, y);
			    panel5.setBackground(Color.black);
			    frame.add(panel5);
			    frame.setVisible(true);
			    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    Graphique inter = new Graphique(0);
		 }
		
		//En attente du nom du joueur 
		 while(mod.getNomJoueur() == null)
		{
			Thread.sleep(100);
		}
		String nom = mod.getNomJoueur();
		
		//On met le nom dans le joueur
		Joueur j = new Joueur(nom,0,0); 
		mod.setJoueurPrincipal(j);
	
		
		
		// Creation d'une partie avec le nombre de joueurs demandé
		Partie partie = new Partie(mod.getNombreJoueurs());
		
		// Création d'un nouveau Thread 
		 new Thread() {
	            public void run() {
	            	// Tant qu'on ne redemarre pas la partie
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
	            		// On redemare la partie
	            		mod.setRestart(false);
	            		mod.setResetPrincipalInterface(true);
	            		mod.setFinished(true);
	            		// On vérifie si on doit revenir au menu principal ou juste recommencer la partie
	            		if(!(mod.isResetAll()))
	            		{
	            			mod.setDoNotRelaunch(true);
	            		}
	            		else
	            		{
	            			 	// Pour revenir au menu , on quitte le jeu puis on le relance.
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
									e.printStackTrace();
								}
	            		        
	            		        // On essaye de quitter le jeu 
	            		        while(true)
	            		        {
	            		        	System.exit(0);
	            		        }
	            		        
	            		}
	            		
	            		//Autrement on ne veut pas quitter le jeu donc 
	            		// On arrête le main 
	            		//Et on rénitialise toutes les autres fenêtres.
	            		mod.getA().interrupt();
	            		Thread.sleep(500);
	            		mod.setFinished(false);
	            		mod.getA().interrupt();
	            		mod.clearBox();
	            		Thread.sleep(1000);
	            		Ingredient.resetTasDeCartes();
						// on RELANCE le main
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
		
		
		//On crée les joueurs virtuels
		partie.factoryJoueurs();
		
		
		boolean vrai = true;
		int reponse = mod.getPartieRapide();
		// On récupère le type de partie et on l'initie.
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
			// On crée une fenêtre affichant le score
			Score boiteDeScore = new Score(0);
			//On crée une fenêtre affichant les messages 
			MessageBox message = new MessageBox(0);
			for(int tour=0 ; tour <4 ; tour++) 
			{ 
				partie.setTour(tour);
				mod.setMessage("<br><font color=green>saison : "+partie.saisonFromInt(tour)+"<br></font>");
				
				//Donne la liste des joueurs qui composent la partie
				mod.setJoueursPoints(partie.getListJoueur().toString());
				mod.setMain(j.getMain());
				
				//On affiche la fenêtre qui affiches les différentes cartes
				SelectionDeCarte c = new SelectionDeCarte(0);
				
				//En attente du joueur qui choisit sa carte 
				while(mod.getCarteChoisie()==-1)
				{
					Thread.sleep(100);
				}
				// En attente du joueur qui choisit son action 
				while(mod.getAction()==-1)
				{
					Thread.sleep(100);
				}
				
				// on indique l'index de la carte choisie
				int carte = mod.getCarteChoisie();
				mod.setCarteChoisie(-1);
				
				// On indique l'index du type de jeu choisi
				int jeu = mod.getAction();
				mod.setAction(-1);
				if(jeu == 2)
				{
					//Si on choisit de joueur farfadet 
					//On crée une fenêtre qui affiche la cible du joueur
					ChoixJoueur b = new ChoixJoueur(0);
					while(mod.getIndexJoueurCible()==-1)
					{
						Thread.sleep(250);
					}
					// On affiche une animation qui montre le joueur entrain de voler 
					mod.setFarfadetAnimation2(true);
					//l'animation dure 9.5 sec
					Thread.sleep(9500);	
					int cible = mod.getIndexJoueurCible();
					mod.setIndexJoueurCible(-1);
					//On affiche le message comme quoi je joueur a volé un autre joueur
					mod.setMessage("Vous avez volé "+j.getMain().get(carte).getFarfadet()[Partie.getTour()]+ " graine(s) au joueur "+partie.getListJoueur().get(cible).getNom());
					j.poserCarte(carte, partie.getListJoueur().get(cible));
				}else
				{
					
					if(jeu == 0)
					{
						//Demande de graines au géant
						mod.setMessage("Vous demandez "+j.getMain().get(carte).getGeant()[Partie.getTour()]+" graine(s)");
						// Animation du géant qui donne des graines 
						mod.setGeantAnimation(true);
						Thread.sleep(8000);
					}	
					else
					{	
						//On joue engrais 
						if(j.getNbreGraine()>=j.getMain().get(carte).getEngrais()[Partie.getTour()] )
						{
							//Affichage de l'animation et message correspondant
							mod.setMessage("Vous plantez "+j.getMain().get(carte).getEngrais()[Partie.getTour()]+" graine(s)");
							mod.setMenhir(j.getMain().get(carte).getEngrais()[Partie.getTour()]);
						}
						else
						{
							mod.setMessage("Vous plantez "+j.getNbreGraine()+" graine(s)");
							mod.setMenhir(j.getNbreGraine());
						}
					}
					j.poserCarte(carte, jeu);
					mod.setJoueursPoints(partie.getListJoueur().toString());
				}
				// on fait jouer les autres joueurs virtuels 
				partie.gererTour();
			
			}
			mod.setJoueursPoints(partie.getListJoueur().toString());
			mod.setListeJoueurGagnant(partie.chercherGagnantRapide());
			// On affiche la fenêtre de fin de partie 
			FenetreFin f = new FenetreFin(0);	
		}
		else
		if(reponse == 2)
		{
			mod.setJoueursPoints(partie.getListJoueur().toString());
			mod.setListeJoueur(partie.getListJoueur());
			// création fenêtre de score et de messages 
			Score boiteDeScore = new Score(0);
			MessageBox message = new MessageBox(0);
			
			//Création d'un nouveau thread
			new Thread() {
	            public void run() {
	            //Pour pouvoir jouer la taupe n'importe quand dans la partie
	            	while(!(mod.isRestart()))
	            	{
	            		try {
							Thread.sleep(300);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	            		if (!(mod.getIndexJoueurCibleTaupe()==99))
	            		{
	            			
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
				
				mod.setMessage("<br><font color=4B98FD>Début de la manche "+ (manche+1)+"</font><br>");
				// Fenetre pour le choix du début d'une manche
				ChoixDebutManche m = new ChoixDebutManche(0);
				//En attente du choix
				while(mod.getChoix()== 0)
				{
					Thread.sleep(100);
				}
				int choix = mod.getChoix();
				mod.setChoix(0);
				j.choixDebutManche(choix);
				if (choix == 2)
				{
					mod.setAllie(j.getAllie());
					SelectionCarteAlliee B = new SelectionCarteAlliee(0);
				}
				
				for(int tour=0 ; tour <4 ; tour++) 
				{
					partie.setTour(tour);
					mod.setMessage("<br><font color=green>saison : "+partie.saisonFromInt(tour)+"<br></font>");
					
					for(int i=0;i<partie.getListJoueur().size();i++)
					{
						List<Joueur> listTemp = new LinkedList<Joueur>();
						listTemp.addAll(partie.arrangerOrdreListe(manche));	
						mod.setJoueursPoints(partie.getListJoueur().toString());
						mod.setMain(j.getMain());
						mod.setAllie(j.getAllie());
						if(listTemp.get(i)==j)
						{
							// le code est long , pour le rendre lisible 
							//nous l'avons mise dans une méthode statique
							Main.deroulementJoueur(j, partie);
						}else
						{
							partie.gererTourAvancee(listTemp.get(i));
							//temporisation une seconde 
							pause();
						}		
					}
				}
				mod.setMenhir(-1);
				partie.initierPartieAvancee();
			}
			
			partie.finPartie();
			mod.setJoueurGagnant(partie.getListJoueur().get(partie.chercherGagnantAvancee()));
			//On montre le joueur gagnant
			FenetreFin f = new FenetreFin(0);
		}
		
		
		
	}
	
	/**
	 * Methode qui avertit le joueur d'une attaque ennemi avec un farfadet 
	 * @param int valeur indique le nombre maximal de graines que le joueur ennemi puisse voler 
	 * @param Joueur j Le joueur qui veut  voler 
	 * @param Joueur j2 Le joueur cible qui est ciblée par le joueur qui veut voler
	 * 
	 * @return boolean - true : pour dire d'utiliser le chien de garde 
	 * @return boolean - false : pour ne pas utiliser le Chien de garde
	 */
	public static boolean danger(int valeur, Joueur j, Joueur joueurCible)
	{
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
		
		if (model.getChienDeGardeAction()==1)
		{	
			model.setChienDeGarde(true);
			model.setMessage("Le joueur "+model.getJoueurPrincipal().getNom()+"Défends "+model.getJoueurPrincipal().getAllie().getValeur()[Partie.getTour()]+" de ses graines");
			model.setChienDeGardeAction(-1);
			return true;
		}
		else
		{
			model.setFarfadetAnimation(true);
			model.setChienDeGardeAction(-1);
			return false;
		}
	}
	
	/**
	 * Affiche dans la boite de dialogue, l'action "Chien de garde " effectuée par un des joueurs, cela indique 
	 * que le joueur ciblé par les farfadets s'est protégé avec un chien de garde 
	 * 
	 * @param joueur : qui attaque 
	 * @param joueurcible : joueur ciblée par les attaques et qui se défends 
	 * @param carte : la carte alliée jouée par le joueur qui se défend 
	 */
	public static void afficherActionoff(Joueur joueur, Joueur joueurcible, int carte)
	{
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
					else
					{
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
	
	
	
	/**
	 * 
	 * Affiche dans la boite de dialogue l'action faite par le joueur 
	 * 
	 * @param joueur 
	 * @param carte
	 * @param action
	 */
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
    		try {
				Thread.sleep(3500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		
		
		while(mod.getAction()==-1)
		{
			Thread.sleep(100);
		}
		int carte = mod.getCarteChoisie();
		mod.setCarteChoisie(-1);
		int jeu = mod.getAction();
		mod.setAction(-1);
		
		if(jeu == 2)
		{		
			@SuppressWarnings("unused")
			ChoixJoueur b = new ChoixJoueur(0);
			
			while(mod.getIndexJoueurCible()==-1)
			{
				Thread.sleep(250);
			}
			
			int cible = mod.getIndexJoueurCible();	
			mod.setMessage("Vous avez volé "+j.getMain().get(carte).getFarfadet()[Partie.getTour()]+ " graine(s) au joueur "+partie.getListJoueur().get(cible).getNom());
			j.poserCarteBis(carte, partie.getListJoueur().get(cible));
			
			while(mod.getCas()==-1)
			{
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
		}
		else
		{
			if(jeu == 0)
			{
				mod.setMessage("Vous demandez "+j.getMain().get(carte).getGeant()[Partie.getTour()]+" graine(s)");
				mod.setGeantAnimation(true);
				Thread.sleep(8000);
			}	
			else
			{	
				if(j.getNbreGraine()>=j.getMain().get(carte).getEngrais()[Partie.getTour()] ){
				mod.setMessage("Vous plantez "+j.getMain().get(carte).getEngrais()[Partie.getTour()]+" graine(s)");
				mod.setMenhir(j.getMain().get(carte).getEngrais()[Partie.getTour()]);		
			}
			else
			{
				mod.setMessage("Vous plantez "+j.getNbreGraine()+" graine(s)");
				mod.setMenhir(j.getNbreGraine());}
			}	
			j.poserCarte(carte, jeu);
			mod.setJoueursPoints(partie.getListJoueur().toString());
		}				
	}
	
	
		
	
	
	public static int saisie(String saisie , int min , int max )
	{
		System.out.println(saisie);
		Scanner saisie2 = new Scanner(System.in);		
		String a = saisie2.nextLine();
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
