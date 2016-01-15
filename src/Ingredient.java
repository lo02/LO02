	import javax.xml.parsers.DocumentBuilderFactory;
	import javax.xml.parsers.DocumentBuilder;
	import org.w3c.dom.Document;
	import org.w3c.dom.NodeList;
	import org.w3c.dom.Node;
	import org.w3c.dom.Element;
	import java.io.File;
	import java.util.*;



public class Ingredient extends Card {
	private boolean cheatCard = false;
	/**
	 * Attribut qui contient le nom de la carte
	 * @see Ingredient#getName()
	 * @see Ingredient#setName(String)
	 */
	private String name = "";
	/**
	 * Attibut qui contient un tableau de valeur qui va correspondre au point géant de la carte
	 * @see Ingredient#getGeant()
	 * @see Ingredient#setGeant(int, int)
	 * 
	 */
	private int[] geant = new int[4];
	/**
	 * Attibut qui contient un tableau de valeur qui va correspondre au point farfadet de la carte
	 * @see Ingredient#getFarfadet()
	 * @see Ingredient#setFarfadet(int, int)
	 * 
	 */
	private int[] farfadet = new int[4];
	/**
	 * Attibut qui contient un tableau de valeur qui va correspondre au point géant de la carte
	 * @see Ingredient#getEngrais()
	 * @see Ingredient#setEngrais(int, int)
	 * 
	 */
	private int[] engrais = new int[4];
	/**
	 * Attribut qui contient le nom du logo d'une carte
	 * @see Ingredient#getLogo()
	 * @see Ingredient#setLogo(String)
	 */
	private String logo;
	/**
	 * Attribut qui contient une liste de cartes
	 * @see Ingredient#getTasDeCartes()
	 * @see Ingredient#setTasDeCartes(List)
	 * @see Ingredient#setTasDeCartes(Queue)
	 */
	private static List<Card> tasdecartes = new ArrayList<Card>();	
	
	
	/**
	 * Méthode qui va permettre de lire les cartes alliées du fichier XML et les disposer dans une liste de cartes
	 */
	public void initialisationCartes() {
		//System.out.println("dsqdqcxvcxdddddsd");
	    try {

		File fXmlFile = new File("ab.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);	
		NodeList nList;
		List<String> image = new ArrayList<String>();
		
		image.add("img/fee_verte.jpg");
		image.add("img/rayonlune.jpg");
		image.add("img/rayonlune.jpg");
		image.add("img/rayonlune.jpg");
		image.add("img/sirene.jpg");
		image.add("img/sirene.jpg");
		image.add("img/sirene.jpg");
		image.add("img/Dryade_bleu.jpg");
		image.add("img/Dryade_bleu.jpg");
		image.add("img/Dryade_bleu.jpg");
		image.add("img/fontaine.jpg");
		image.add("img/fontaine.jpg");
		image.add("img/fontaine.jpg");
		image.add("img/poudre.jpg");
		image.add("img/poudre.jpg");
		image.add("img/poudre.jpg");
		image.add("img/arcenciel.jpg");
		image.add("img/arcenciel.jpg");
		image.add("img/arcenciel.jpg");
		image.add("img/dolmen.jpg");
		image.add("img/dolmen.jpg");
		image.add("img/dolmen.jpg");
		image.add("img/fee_verte.jpg");
		image.add("img/fee_verte.jpg");
		image.add("img/fee_verte.jpg");
		
		
		nList = doc.getElementsByTagName("printemps");
		for (int i=0; i< nList.getLength() ; i++)
		{	
			Card carte= new Ingredient();
			nList = doc.getElementsByTagName("ID"+i);
			Node nNode = nList.item(0);
			carte.setLogo(image.get(i));
			Element eElement = (Element) nNode;
			carte.setName(eElement.getAttribute("name"));
			
			
			if (eElement.getAttribute("name").equals("Cheat Carte") )
			{
				if(this.cheatCard)
				{
					for( int j=0;j<4;j++)
					{
						String saison = "";
					switch (j)
					{
					case 0: saison="printemps";
						break;
					case 1: saison="été";
					break;
					case 2: saison="automne";
					break;
					case 3: saison="hiver";
					break;
					}
					
					nList = doc.getElementsByTagName(saison);
					nNode = nList.item(i);
					eElement = (Element) nNode;
					carte.setGeant(Integer.parseInt(eElement.getAttribute("géant")),j); 
					carte.setEngrais(Integer.parseInt(eElement.getAttribute("engrais")),j); 
					carte.setFarfadet(Integer.parseInt(eElement.getAttribute("farfadet")),j); 
					
					}
				tasdecartes.add(carte);
				}
				else
				{
				}
				
			}
			else
			{
				
				for( int j=0;j<4;j++)
				{
					String saison = "";
					switch (j)
					{
					case 0: saison="printemps";
						break;
					case 1: saison="été";
					break;
					case 2: saison="automne";
					break;
					case 3: saison="hiver";
					break;
					}
					
					nList = doc.getElementsByTagName(saison);
					nNode = nList.item(i);
					eElement = (Element) nNode;
					carte.setGeant(Integer.parseInt(eElement.getAttribute("géant")),j); 
					carte.setEngrais(Integer.parseInt(eElement.getAttribute("engrais")),j); 
					carte.setFarfadet(Integer.parseInt(eElement.getAttribute("farfadet")),j); 
				}
				tasdecartes.add(carte);
				
			}
		Collections.shuffle(tasdecartes);
			
			
		}
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	  }

	/**
	 * Supprime une carte allié après son utilisation
	 */
	public void deleteAllie() {
		
		
	}
	/**
	 * Méthode qui actualise un nom
	 * @param attribute
	 * 		nouveau nom
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Méthode qui actualise la valeur géant
	 * @param parseInt
	 * @param j
	 * 		
	 */
	public void setGeant(int geant,int index) {
		this.geant[index] = geant;
	}
	/**
	 * Méthode qui actualise la valeur farfadet
	 * @param parseInt
	 * @param j
	 * 		
	 */
	public void setFarfadet(int farfadet, int index) {
		this.farfadet[index] = farfadet;
	}
	/**
	 * Méthode qui actualise la valeur engrais
	 * @param parseInt
	 * @param j
	 * 		
	 */
	public void setEngrais(int engrais ,int index) {
		this.engrais[index] = engrais ;
	}
	
	public void setTasDeCartes(List<Card> cartes) {
		tasdecartes.clear();
		tasdecartes.addAll(cartes);
	}

	public static void resetTasDeCartes()
	{
		tasdecartes.clear();
	}
	/**
	 * Méthode qui actualise une valeur d'un joueur
	 * @param parseInt
	 * 		nouvelle valeur
	 * @param j
	 * 		joueur dont on veux la valeur de la carte
	 */
	public void setValeur(int parseInt, int j) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Méthode qui actualise une liste de carte
	 * @param cartes
	 * 		nouvelle liste de carte
	 */
	public void setTasDeCartes(Queue<Card> cartes) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Methode qui donne un nom
	 * @return
	 * 		renvoie un nom
	 */
	public String getName() {
		return name;
	}

	/**
	 * Methode qui donne les valeurs d'un géant
	 * @return
	 * 		renvoie un tableau
	 */
	public int[] getGeant() {
		return geant;
	}

	/**
	 * Methode qui donne les valeurs d'un farfadet
	 * @return
	 * 		renvoie un tableau
	 */
	public int[] getFarfadet() {
		return farfadet;
	}

	/**
	 * Methode qui donne les valeurs d'un engrais
	 * @return
	 * 		renvoie un tableau
	 */
	public int[] getEngrais() {
		return engrais;
	}

	/**
	 * Methode qui donne une liste de carte
	 * @return
	 * 		renvoie une liste de carte
	 */
	public  List<Card> getTasDeCartes() {
		return tasdecartes;
	}


	/**
	 * Methode qui donne un titre
	 * @return
	 * 		renvoie un titre
	 */
	public String getTitre() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Methode qui donne une valeur
	 * @return
	 * 		renvoie une valeur
	 */
	public int[] getValeur() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Méthode qui permet d'afficher un message sur la console.
	 * 
	 */
	public String toString(){
		
		return "\n\n\t"+name+"\n"+geant[0]+"\t"+geant[1]+"\t"+geant[2]+"\t"+geant[3]+"\n"+engrais[0]+"\t"+engrais[1]+"\t"+engrais[2]+"\t"+engrais[3]+"\n"+farfadet[0]+"\t"+farfadet[1]+"\t"+farfadet[2]+"\t"+farfadet[3];
	}
	/**
	 * Méthode qui permet d'afficher un message en html sur l'interface graphique.
	 * 
	 */
	public String toString2()
	{
		/*if (Partie.getTour() == 0){
			return "<html>"+name+"<table style='width:100%;  '><tr><td style='background-color:green;'>"+geant[0]+"</td><td>"+geant[1]+"</td><td>"+geant[2]+"</td><td>"+geant[3]+"</td></tr><tr><td style='background-color:green;'>"+engrais[0]+"</td><td>"+engrais[1]+"</td><td>"+engrais[2]+"</td><td>"+engrais[3]+"</td></tr><tr><td style='background-color:green;'>"+farfadet[0]+"</td><td>"+farfadet[1]+"</td><td>"+farfadet[2]+"</td><td>"+farfadet[3]+"</td></tr></table></html>";
		}*/
		
		if (Partie.getTour()==0)
		{
			return "<html>"+name+"<table style='width:100%;'><tr><td style='background-color:green; text-align:center'>"+geant[0]+"<br>"+engrais[0]+"<br>"+farfadet[0]+"</td><td style='text-align:center;'>"+geant[1]+"<br>"+engrais[1]+"<br>"+farfadet[1]+"</td><td style='text-align:center;'>"+geant[2]+"<br>"+engrais[2]+"<br>"+farfadet[2]+"</td><td style='text-align:center;'>"+geant[3]+"<br>"+engrais[3]+"<br>"+farfadet[3]+"</td></tr></table></html>";
		}
		if (Partie.getTour()==1)
		{
			return "<html>"+name+"<table style='width:100%;'><tr><td style='text-align:center;'>"+geant[0]+"<br>"+engrais[0]+"<br>"+farfadet[0]+"</td><td style='background-color:green;text-align:center;'>"+geant[1]+"<br>"+engrais[1]+"<br>"+farfadet[1]+"</td><td style='text-align:center;'>"+geant[2]+"<br>"+engrais[2]+"<br>"+farfadet[2]+"</td><td style='text-align:center;'>"+geant[3]+"<br>"+engrais[3]+"<br>"+farfadet[3]+"</td></tr></table></html>";
		}
		if (Partie.getTour()==2)
		{
			return "<html>"+name+"<table style='width:100%;'><tr><td style='text-align:center;'>"+geant[0]+"<br>"+engrais[0]+"<br>"+farfadet[0]+"</td><td>"+geant[1]+"<br>"+engrais[1]+"<br>"+farfadet[1]+"</td><td style='background-color:green;text-align:center;'>"+geant[2]+"<br>"+engrais[2]+"<br>"+farfadet[2]+"</td><td style='text-align:center;'>"+geant[3]+"<br>"+engrais[3]+"<br>"+farfadet[3]+"</td></tr></table></html>";
		}
		if (Partie.getTour()==3)
		{
			return "<html>"+name+"<table style='width:100%;'><tr><td style='text-align:center;'>"+geant[0]+"<br>"+engrais[0]+"<br>"+farfadet[0]+"</td><td>"+geant[1]+"<br>"+engrais[1]+"<br>"+farfadet[1]+"</td><td style='text-align:center;'>"+geant[2]+"<br>"+engrais[2]+"<br>"+farfadet[2]+"</td><td style='background-color:green;text-align:center;'>"+geant[3]+"<br>"+engrais[3]+"<br>"+farfadet[3]+"</td></tr></table></html>";
		}
		else{
			return "error";
		}
	}

	/**
	 * Methode qui donne le nom du logo
	 * @return
	 * 		renvoie le nom du logo
	 */
	public String getLogo() {
		return logo;
	}


	/**
	 * Méthode qui actualise le nom d'un logo
	 * @param logo
	 * 		nouveau nom
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}
}
	

