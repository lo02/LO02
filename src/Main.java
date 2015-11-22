import java.util.Scanner;

public class Main {
	public static void main(String[] argc){
		//on demande de joueur physique de se donner un nom
		Joueur j = new Joueur(); 
		Scanner sc = new Scanner(System.in); 
		System.out.println("Saisissez votre nom : ");
		String nom = sc.nextLine();
		//On met le nom dans le joueur
		j.setNom(nom);
		System.out.println("Vous vous appelez : " +nom +"\n" + j);
		
		
		// demande du nombre de joueurs
		Scanner saisie = new Scanner(System.in); 
		System.out.println("Saisissez le nombre de joueurs entre 1 et 5 ");
		Partie partie = new Partie(saisie.nextInt());
		
		//partie.setListJoueur();
		//On crée les joueurs virtuel
		partie.factoryJoueurs();
		
		// Partie Rapide ou Avancée 
		boolean vrai = true;
		while(vrai)
		{
			System.out.println("1 - rapide\n2 - avancée ");
			int reponse = saisie.nextInt();
			switch (reponse)
			{
				case 1: partie.jouerPartieRapide();
				vrai = false;
				break;
				case 2: partie.jouerPartieAvancee();
				vrai=false;
				break;
				default: System.out.println("Taper 1 ou 2" );
				break;
			}
		}
		
		
	}
}
