import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;



public class LanPlayer extends Joueur implements Runnable  {
	
	private InetAddress ipAdressHost;
	private InetAddress myLocalIp;
	private static List<LanPlayer> listeJoueurPhysique = new ArrayList<LanPlayer>();
	
	
	
	public LanPlayer(String nom, int nbreMenhir, int nbreGraine )
	{
		super(nom,nbreMenhir,nbreGraine);
		//this.start();
		
	}
	
	public void connectToHost() throws UnknownHostException, IOException
	{
		this.send("Player/"+this.nom+this.getLocalAddress()+"@");
	}
	
	
	// définit le hôte 
	public void setHost(String address) throws UnknownHostException
	{
		ipAdressHost = InetAddress.getByName(address);
			
	}
	
	public void addPlayer(String name , String ipAddress)
	{
		LanPlayer newPlayer = new LanPlayer(name,0,0);
		try {
			myLocalIp= InetAddress.getByName(ipAddress);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.listeJoueurPhysique.add(newPlayer);
		
	}
	
	
	
	
	
	
	
	
	
	// Serveur client UDP en multi-thread 
	 public void start()
	   {
		   Thread t1 = new Thread(this,"test2");
	  	t1.start();
	   }
	   
	  
	 
	 	
	 	public void run()
	   {	   
		   // serveur
		   
		   DatagramSocket serverSocket = null;
		try {
			serverSocket = new DatagramSocket(9877);
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	//   System.out.println("test");
	       byte[] receiveData = new byte[1024];
	       byte[] sendData = new byte[1024];
	       while(true)
	          {
	             DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
	             try {
					serverSocket.receive(receivePacket);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	             String sentence = new String( receivePacket.getData());
	             
	             if (sentence.contains("Player")){
	             String name = new String((String) sentence.subSequence(sentence.indexOf("/")+1, sentence.indexOf("/", sentence.indexOf("/")+1)));
	             String oIP = new String((String) sentence.subSequence(sentence.indexOf("/", sentence.indexOf("/")+1)+1, sentence.indexOf("@")-1));
	             this.addPlayer(name, oIP);
	              
	             }
	             
	             System.out.println("RECEIVED: " + sentence);
	             InetAddress IPAddress = receivePacket.getAddress();
	             int port = receivePacket.getPort();
	             String capitalizedSentence = sentence.toUpperCase();
	             sendData = capitalizedSentence.getBytes();
	             DatagramPacket sendPacket =
	             new DatagramPacket(sendData, sendData.length, IPAddress, port);
	             try {
					serverSocket.send(sendPacket);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	          }
	   }
	   
	   
	   public void send(String sentence) throws IOException
	   {
		   			BufferedReader inFromUser =
			   	      new BufferedReader(new InputStreamReader(System.in));
			   	      DatagramSocket clientSocket = new DatagramSocket();
			   	      //InetAddress IPAddress = InetAddress.getByName("localhost");
			   	      byte[] sendData = new byte[1024];
			   	      byte[] receiveData = new byte[1024];
			   	      //String sentence = inFromUser.readLine();
			   	      sendData = sentence.getBytes();
			   	      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, this.ipAdressHost, 9877);
			   	      clientSocket.send(sendPacket);
			   	      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			   	      clientSocket.receive(receivePacket);
			   	      String modifiedSentence = new String(receivePacket.getData());
			   	      System.out.println("FROM SERVER:" + modifiedSentence);
			   	      clientSocket.close();   
	   }
	   
	   
	   


	   	private static InetAddress getLocalAddress(){
	   		try {
	   			Enumeration<NetworkInterface> b = NetworkInterface.getNetworkInterfaces();
	   			while( b.hasMoreElements()){
	   				for ( InterfaceAddress f : b.nextElement().getInterfaceAddresses())
	   					if ( f.getAddress().isSiteLocalAddress())
	   						return f.getAddress();
	   			}
	   		} catch (SocketException e) {
	   			e.printStackTrace();
	   		}
	   		return null;
	   		}


	   
	   
	   
	   
	   
	   public static void main(String[] argc) throws IOException, InterruptedException
	   {
		   LanPlayer joueur = new LanPlayer("anass",0,0);
		   joueur.setHost("192.168.1.105");
		   joueur.connectToHost(); 
		   
	   }
	   
	   
	   
	   
	   

}
	