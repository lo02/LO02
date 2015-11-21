import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;




public class Allie extends Card {

	private static List<Card> tasdecartes = new ArrayList<Card>();	
	private int[] valeur = new int[4];
	private String titre = "";
	
/*	TEST SEULEMENT
 * public static void main(String[] args)
	{
		Card allie = new Allie();
		allie.initialisationCartes();
		System.out.println(tasdecartes.toString());
		
	}*/
	
	
	public void initialisationCartes() {
		// TODO Auto-generated method stub
		 try {

				File fXmlFile = new File("ac.xml");
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(fXmlFile);	
				NodeList nList;
				
			
				nList = doc.getElementsByTagName("printemps");
				for (int i=0; i< nList.getLength() ; i++)
				{	
					Card carte= new Allie();
					nList = doc.getElementsByTagName("ID"+i);
					Node nNode = nList.item(0);
					
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
	

	@Override
	public List<Card> getTasDeCartes() {
		// TODO Auto-generated method stub
		return tasdecartes;
	}

	@Override
	public void setTasDeCartes(List<Card> cartes) {
		// TODO Auto-generated method stub
		tasdecartes.clear();
		tasdecartes.addAll(cartes);
	}
	
	public void setValeur(int valeur, int j)
	{
		this.valeur[j]=valeur;
	}


	@Override
	public void setName(String attribute) {
		// TODO Auto-generated method stub
		this.titre = attribute;
		
	}


	@Override
	public String toString(){
		//Nous permet d'afficher le carte
		return "\t"+this.titre+"\n"+this.valeur[0]+"\t"+this.valeur[1]+"\t"+this.valeur[2]+"\t"+this.valeur[3];
	
	}


	@Override
	public void setGeant(int parseInt, int j) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setEngrais(int parseInt, int j) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setFarfadet(int parseInt, int j) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public int[] getGeant() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int[] getEngrais() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
