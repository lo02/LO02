	import java.util.List;
	import javax.xml.parsers.DocumentBuilderFactory;
	import javax.xml.parsers.DocumentBuilder;
	import org.w3c.dom.Document;
	import org.w3c.dom.NodeList;
	import org.w3c.dom.Node;
	import org.w3c.dom.Element;
	import java.io.File;
	import java.util.*;



public class Ingredient extends Card {
	private String name = "";
	private int[] geant = new int[4];
	private int[] farfadet = new int[4];
	private int[] engrais = new int[4];
	private static List<Card> tasdecartes = new ArrayList<Card>();	

	

	public void initialisationCartes() {
		//System.out.println("dsqdqcxvcxdddddsd");
	    try {

		File fXmlFile = new File("ab.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);	
		NodeList nList;
		
	
		nList = doc.getElementsByTagName("printemps");
		for (int i=0; i< nList.getLength() ; i++)
		{	
			Card carte= new Ingredient();
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
				carte.setGeant(Integer.parseInt(eElement.getAttribute("géant")),j); 
				carte.setEngrais(Integer.parseInt(eElement.getAttribute("engrais")),j); 
				carte.setFarfadet(Integer.parseInt(eElement.getAttribute("farfadet")),j); 
			}
			
			
			
			tasdecartes.add(carte);
			Collections.shuffle(tasdecartes);
			
		}
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	  }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int[] getGeant() {
		return geant;
	}

	public void setGeant(int geant,int index) {
		this.geant[index] = geant;
	}

	public int[] getFarfadet() {
		return farfadet;
	}

	public void setFarfadet(int farfadet, int index) {
		this.farfadet[index] = farfadet;
	}

	public int[] getEngrais() {
		return engrais;
	}
	
	public  List<Card> getTasDeCartes() {
		return tasdecartes;
	}

	public void setEngrais(int engrais ,int index) {
		this.engrais[index] = engrais ;
	}
	
	public void setTasDeCartes(List<Card> cartes) {
		tasdecartes.clear();
		tasdecartes.addAll(cartes);
	}

	
	public String toString(){
		
		return "\n\n\t"+name+"\n"+geant[0]+"\t"+geant[1]+"\t"+geant[2]+"\t"+geant[3]+"\n"+engrais[0]+"\t"+engrais[1]+"\t"+engrais[2]+"\t"+engrais[3]+"\n"+farfadet[0]+"\t"+farfadet[1]+"\t"+farfadet[2]+"\t"+farfadet[3];
	}
	
	

	@Override
	public void setValeur(int parseInt, int j) {
		// TODO Auto-generated method stub
		
	}



	}
	

