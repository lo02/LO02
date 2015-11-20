import java.util.List;
public abstract class Card {



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


