import java.util.List;
public abstract class Card {


		// Je pense qu'il y probl�me, on devrait plut�t mettre setGean, setEngrais,setFarfadet dans la classe
		// Ingr�diant
		public abstract  void initialisationCartes();
		public abstract List<Card> getTasDeCartes();
		public abstract void setTasDeCartes(List<Card> cartes);
		public abstract void setGeant(int parseInt, int j);
		public abstract void setEngrais(int parseInt, int j);
		public abstract void setFarfadet(int parseInt, int j);
		public abstract void setName(String attribute);
		public abstract void setValeur(int parseInt, int j);
		public abstract String toString();
		
	}


