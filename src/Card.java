import java.util.List;
import java.util.Queue;
public abstract class Card {
	/**
	 * Méthode qui va permettre de lire les cartes alliées du fichier XML et les disposer dans une liste de cartes
	 */
		public abstract  void initialisationCartes();
		/**
		 * Methode qui donne une liste de carte
		 * @return
		 * 		renvoie une liste de carte
		 */
		public abstract List<Card> getTasDeCartes();
		/**
		 * Méthode qui actualise une liste de carte
		 * @param cartes
		 * 		nouvelle liste de carte
		 */
		public abstract void setTasDeCartes(List<Card> cartes);
		/**
		 * Méthode qui actualise une liste de carte
		 * @param cartes
		 * 		nouvelle liste de carte
		 */
		public abstract void setTasDeCartes(Queue<Card> cartes);
		/**
		 * Méthode qui actualise la valeur géant
		 * @param parseInt
		 * @param j
		 * 		
		 */
		public abstract void setGeant(int parseInt, int j);
		/**
		 * Méthode qui actualise la valeur engrais
		 * @param parseInt
		 * @param j
		 * 		
		 */
		public abstract void setEngrais(int parseInt, int j);
		/**
		 * Methode qui donne les valeurs d'un géant
		 * @return
		 * 		renvoie un tableau
		 */
		public abstract int[] getGeant();
		/**
		 * Methode qui donne les valeurs d'un engrais
		 * @return
		 * 		renvoie un tableau
		 */
		public abstract int[] getEngrais();
		/**
		 * Methode qui donne les valeurs d'un farfadet
		 * @return
		 * 		renvoie un tableau
		 */
		public abstract int[] getFarfadet();
		/**
		 * Méthode qui actualise le nom d'un logo
		 * @param logo
		 * 		nouveau nom
		 */
		public abstract void setLogo(String logo);
		/**
		 * Methode qui donne le nom du logo
		 * @return
		 * 		renvoie le nom du logo
		 */
		public abstract String getLogo();
		/**
		 * Méthode qui actualise la valeur farfadet
		 * @param parseInt
		 * @param j
		 * 		
		 */
		public abstract void setFarfadet(int parseInt, int j);
		/**
		 * Méthode qui actualise un nom
		 * @param attribute
		 * 		nouveau nom
		 */
		public abstract void setName(String attribute);
		/**
		 * Méthode qui actualise une valeur d'un joueur
		 * @param parseInt
		 * 		nouvelle valeur
		 * @param j
		 * 		joueur dont on veux la valeur de la carte
		 */
		public abstract void setValeur(int parseInt, int j);
		/**
		 * Methode qui donne une valeur
		 * @return
		 * 		renvoie une valeur
		 */
		public abstract int[] getValeur();
		/**
		 * Méthode qui permet d'afficher un message sur la console.
		 * 
		 */
		public abstract String toString();
		/**
		 * Methode qui donne un titre
		 * @return
		 * 		renvoie un titre
		 */
		public abstract String getTitre();
		/**
		 * Supprime une carte allié après son utilisation
		 */
		public abstract void deleteAllie();	
		/**
		 * Méthode qui permet d'afficher un message en html sur l'interface graphique.
		 * 
		 */
		public abstract String toString2();
	}


