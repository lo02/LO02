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
	private String name = "";
	private int[] geant = new int[4];
	private int[] farfadet = new int[4];
	private int[] engrais = new int[4];
	private String logo;
	private static List<Card> tasdecartes = new ArrayList<Card>();	
	
	

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

	
	public void deleteAllie() {
		
		
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGeant(int geant,int index) {
		this.geant[index] = geant;
	}

	public void setFarfadet(int farfadet, int index) {
		this.farfadet[index] = farfadet;
	}

	public void setEngrais(int engrais ,int index) {
		this.engrais[index] = engrais ;
	}
	
	public void setTasDeCartes(List<Card> cartes) {
		tasdecartes.clear();
		tasdecartes.addAll(cartes);
	}

	
	@Override
	public void setValeur(int parseInt, int j) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTasDeCartes(Queue<Card> cartes) {
		// TODO Auto-generated method stub
		
	}

	public String getName() {
		return name;
	}


	public int[] getGeant() {
		return geant;
	}


	public int[] getFarfadet() {
		return farfadet;
	}


	public int[] getEngrais() {
		return engrais;
	}


	public  List<Card> getTasDeCartes() {
		return tasdecartes;
	}


	@Override
	public String getTitre() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getValeur() {
		// TODO Auto-generated method stub
		return null;
	}


	public String toString(){
		
		return "\n\n\t"+name+"\n"+geant[0]+"\t"+geant[1]+"\t"+geant[2]+"\t"+geant[3]+"\n"+engrais[0]+"\t"+engrais[1]+"\t"+engrais[2]+"\t"+engrais[3]+"\n"+farfadet[0]+"\t"+farfadet[1]+"\t"+farfadet[2]+"\t"+farfadet[3];
	}
	public String toString2()
	{
		return "<html>"+name+"<table style='width:100%;'><tr><td>"+geant[0]+"</td><td>"+geant[1]+"</td><td>"+geant[2]+"</td><td>"+geant[3]+"</td></tr><tr><td>"+engrais[0]+"</td><td>"+engrais[1]+"</td><td>"+engrais[2]+"</td><td>"+engrais[3]+"</td></tr><tr><td>"+farfadet[0]+"</td><td>"+farfadet[1]+"</td><td>"+farfadet[2]+"</td><td>"+farfadet[3]+"</td></tr></table></html>";
	}


	public String getLogo() {
		return logo;
	}


	public void setLogo(String logo) {
		this.logo = logo;
	}



	

	}
	

