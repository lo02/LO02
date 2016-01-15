import java.util.List;
import java.util.Queue;
public abstract class Card {
	/**
	 * M�thode qui va permettre de lire les cartes alli�es du fichier XML et les disposer dans une liste de cartes
	 */
		public abstract  void initialisationCartes();
		/**
		 * Methode qui donne une liste de carte
		 * @return
		 * 		renvoie une liste de carte
		 */
		public abstract List<Card> getTasDeCartes();
		/**
		 * M�thode qui actualise une liste de carte
		 * @param cartes
		 * 		nouvelle liste de carte
		 */
		public abstract void setTasDeCartes(List<Card> cartes);
		/**
		 * M�thode qui actualise une liste de carte
		 * @param cartes
		 * 		nouvelle liste de carte
		 */
		public abstract void setTasDeCartes(Queue<Card> cartes);
		/**
		 * M�thode qui actualise la valeur g�ant
		 * @param parseInt
		 * @param j
		 * 		
		 */
		public abstract void setGeant(int parseInt, int j);
		/**
		 * M�thode qui actualise la valeur engrais
		 * @param parseInt
		 * @param j
		 * 		
		 */
		public abstract void setEngrais(int parseInt, int j);
		/**
		 * Methode qui donne les valeurs d'un g�ant
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
		 * M�thode qui actualise le nom d'un logo
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
		 * M�thode qui actualise la valeur farfadet
		 * @param parseInt
		 * @param j
		 * 		
		 */
		public abstract void setFarfadet(int parseInt, int j);
		/**
		 * M�thode qui actualise un nom
		 * @param attribute
		 * 		nouveau nom
		 */
		public abstract void setName(String attribute);
		/**
		 * M�thode qui actualise une valeur d'un joueur
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
		 * M�thode qui permet d'afficher un message sur la console.
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
		 * Supprime une carte alli� apr�s son utilisation
		 */
		public abstract void deleteAllie();	
		/**
		 * M�thode qui permet d'afficher un message en html sur l'interface graphique.
		 * 
		 */
		public abstract String toString2();
	}


