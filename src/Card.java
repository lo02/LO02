import java.util.List;
import java.util.Queue;
public abstract class Card {


		// Je pense qu'il y problème, on devrait plutôt mettre setGean, setEngrais,setFarfadet dans la classe
		// Ingrédiant
		public abstract  void initialisationCartes();
		public abstract List<Card> getTasDeCartes();
		public abstract void setTasDeCartes(List<Card> cartes);
		public abstract void setTasDeCartes(Queue<Card> cartes);
		public abstract void setGeant(int parseInt, int j);
		public abstract void setEngrais(int parseInt, int j);
		public abstract int[] getGeant();
		public abstract int[] getEngrais();
		public abstract int[] getFarfadet();
		public abstract void setFarfadet(int parseInt, int j);
		public abstract void setName(String attribute);
		public abstract void setValeur(int parseInt, int j);
		public abstract int[] getValeur();
		public abstract String toString();
		public abstract String getTitre();
		public abstract void deleteAllie();
		
	}


