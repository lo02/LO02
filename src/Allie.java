import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;




public class Allie extends Card {

	/**
	 * Attribut qui contient une liste de cartes
	 * @see Allie#getTasDeCartes()
	 * @see Allie#setTasDeCartes(List)
	 * @see Allie#setTasDeCartes(Queue)
	 */
	private static List<Card> tasdecartes = new ArrayList<Card>();	
	/**
	 * Attibut qui contient un tableau de valeur qui va correspondre au point d'action de la carte
	 * @see Allie#getValeur()
	 * @see Allie#setValeur(int[])
	 * @see Allie#setValeur(int, int)
	 * 
	 */
	private int[] valeur = new int[4];
	/**
	 * Attribut qui contient le nom de la carte
	 * @see Allie#getTitre()
	 * @see Allie#setTitre(String)
	 */
	private String titre = "";
	/**
	 * Attribut qui contient le nom du logo d'une carte
	 * @see Allie#getLogo()
	 * @see Allie#setLogo(String)
	 */
	private String logo;
	
	
	
	/**
	 * Méthode qui va permettre de lire les cartes alliées du fichier XML et les disposer dans une liste de cartes
	 */
	public void initialisationCartes() {
		// TODO Auto-generated method stub
		 try {

				File fXmlFile = new File("ac.xml");
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(fXmlFile);	
				NodeList nList;
				List<String> image = new ArrayList<String>();
				image.add("img/taupe.jpg");
				image.add("img/taupe.jpg");
				image.add("img/taupe.jpg");
				image.add("img/chien.jpg");
				image.add("img/chien.jpg");
				image.add("img/chien.jpg");
				
			
				nList = doc.getElementsByTagName("printemps");
				for (int i=0; i< nList.getLength() ; i++)
				{	
					Card carte= new Allie();
					nList = doc.getElementsByTagName("ID"+i);	
					Node nNode = nList.item(0);
					carte.setLogo(image.get(i));
					Element eElement = (Element) nNode;
					carte.setName(eElement.getAttribute("name"));
					
					
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
						carte.setValeur(Integer.parseInt(eElement.getAttribute("valeur")),j);
					}
					
					
					
					tasdecartes.add((Allie) carte);
					Collections.shuffle(tasdecartes);
					
				}
			    } catch (Exception e) {
				e.printStackTrace();
			    }
	}
	
	/**
	 * Supprime une carte allié après son utilisation
	 */
	public void deleteAllie(){
		this.titre = "";
	}
	
	/**
	 * Methode qui donne une liste de carte
	 * @return
	 * 		renvoie une liste de carte
	 */
	public List<Card> getTasDeCartes() {
		return tasdecartes;
	}

	/**
	 * Méthode qui actualise une liste de carte
	 * @param cartes
	 * 		nouvelle liste de carte
	 */
	public void setTasDeCartes(List<Card> cartes) {
		tasdecartes.clear();
		tasdecartes.addAll(cartes);
	}
	
	/**
	 * Méthode qui actualise une liste de carte
	 * @param cartes
	 * 		nouvelle liste de carte
	 */
	public void setTasDeCartes(Queue<Card> cartes) {
		tasdecartes.clear();
		tasdecartes.addAll(cartes);
	}
	
	/**
	 * Méthode qui actualise une valeur
	 * @param valeur
	 * 		nouvelle valeur
	 */
	public void setValeur(int[] valeur) {
		this.valeur = valeur;
	}

	/**
	 * Méthode qui actualise un titre
	 * @param titre
	 * 		nouveau titre
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

	/**
	 * Méthode qui actualise une valeur d'un joueur
	 * @param valeur
	 * 		nouvelle valeur
	 * @param j
	 * 		joueur dont on veux la valeur de la carte
	 */
	public void setValeur(int valeur, int j)
	{
		this.valeur[j]=valeur;
	}


	/**
	 * Méthode qui actualise un nom
	 * @param attribute
	 * 		nouveau nom
	 */
	public void setName(String attribute) {
		// TODO Auto-generated method stub
		this.titre = attribute;
		
	}


	/**
	 * Méthode qui actualise la valeur géant
	 * @param parseInt
	 * @param j
	 * 		
	 */
	public void setGeant(int parseInt, int j) {
		// TODO Auto-generated method stub
		
	}


	/**
	 * Méthode qui actualise la valeur engrais
	 * @param parseInt
	 * @param j
	 * 		
	 */
	public void setEngrais(int parseInt, int j) {
		// TODO Auto-generated method stub
		
	}


	/**
	 * Méthode qui actualise la valeur farfadet
	 * @param parseInt
	 * @param j
	 * 		
	 */
	public void setFarfadet(int parseInt, int j) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Methode qui donne une valeur
	 * @return
	 * 		renvoie une valeur
	 */
	public int[] getValeur() {
		return valeur;
	}

	/**
	 * Methode qui donne un titre
	 * @return
	 * 		renvoie un titre
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * Methode qui donne les valeurs d'un géant
	 * @return
	 * 		renvoie rien
	 */
	public int[] getGeant() {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * Methode qui donne les valeurs d'un engrais
	 * @return
	 * 		renvoie rien
	 */
	public int[] getEngrais() {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * Methode qui donne les valeurs d'un farfadet
	 * @return
	 * 		renvoie rien
	 */
	public int[] getFarfadet() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Méthode qui permet d'afficher un message sur la console.
	 * 
	 */
	public String toString(){
		//Nous permet d'afficher le carte
		return "\t"+this.titre+"\n"+this.valeur[0]+"\t"+this.valeur[1]+"\t"+this.valeur[2]+"\t"+this.valeur[3];
	
	}
	/**
	 * Méthode qui permet d'afficher un message en html sur l'interface graphique.
	 * 
	 */
	public String toString2(){
		if (Partie.getTour()==0){
			return "<html>"+titre+"<table style='width:100%;'><tr><td style='background-color:green; text-align:center'>"+valeur[0]+"</td><td text-align:center'>"+valeur[1]+"</td><td text-align:center'>"+valeur[2]+"</td><td text-align:center'>"+valeur[3]+"</td></tr></table></html>";
		}
		if (Partie.getTour()==1){
			return "<html>"+titre+"<table style='width:100%;'><tr><td text-align:center'>"+valeur[0]+"</td><td style='background-color:green; text-align:center'>"+valeur[1]+"</td><td text-align:center'>"+valeur[2]+"</td><td text-align:center'>"+valeur[3]+"</td></tr></table></html>";
		}
		if (Partie.getTour()==2){
			return "<html>"+titre+"<table style='width:100%;'><tr><td text-align:center'>"+valeur[0]+"</td><td text-align:center'>"+valeur[1]+"</td><td style='background-color:green; text-align:center'>"+valeur[2]+"</td><td text-align:center'>"+valeur[3]+"</td></tr></table></html>";
		}
		if (Partie.getTour()==3){
			return "<html>"+titre+"<table style='width:100%;'><tr><td text-align:center'>"+valeur[0]+"</td><td text-align:center'>"+valeur[1]+"</td><td text-align:center'>"+valeur[2]+"</td><td style='background-color:green; text-align:center'>"+valeur[3]+"</td></tr></table></html>";
		}
		else{
			return "error";
		}
		
	}

	/**
	 * Méthode qui actualise le nom d'un logo
	 * @param logo
	 * 		nouveau nom
	 */
	public void setLogo(String logo) {
		// TODO Auto-generated method stub
		this.logo= logo;
	}

	/**
	 * Methode qui donne le nom du logo
	 * @return
	 * 		renvoie le nom du logo
	 */
	public String getLogo() {
		// TODO Auto-generated method stub
		return logo;
	}

	
	
}
