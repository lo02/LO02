import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import javax.swing.JFrame;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JLayeredPane;
import java.awt.ScrollPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
public class Graphique extends JFrame implements ActionListener, Runnable{

	protected JFrame frame;
	protected JLabel picLabel = new JLabel(new ImageIcon("geant/b.gif"));

	private JLabel picLabel3 = new JLabel(new ImageIcon("geant/c.gif"));
	private JLabel picLabel4 = new JLabel(new ImageIcon("geant/d.gif"));
	private JTextField textField;
	protected JLayeredPane layeredPane;
	protected JPanel panel;
	protected Model model;
	protected Button button;
	protected Button button2;
	private Thread t;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Graphique window = new Graphique();
					Thread thread = new Thread(window);
					thread.start();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					
				}
				
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Graphique() {
		initialize();
		model = Model.getInstance();
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		
		
		 Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setBounds(x-(1024/2), y-(542/2) ,1024, 542);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.panel = new JPanel( new FlowLayout(FlowLayout.LEFT, 0, 0) );
		panel.setBounds(0, 0, 1008, 503);
		JPanel panel_1 = new JPanel();
		
		
		layeredPane = new JLayeredPane();
		frame.getContentPane().add(layeredPane, BorderLayout.CENTER);
		
		this.button = new Button("Nouvelle partie");
		button.addActionListener(this);
		
		

		
		button.setBounds(461, 192, 100, 22);
		layeredPane.add(button);
		
		Button button_1 = new Button("Param\u00E8tres ");
		button_1.setBounds(461, 220, 100, 22);
		layeredPane.add(button_1);
		
		Button button_2 = new Button("A propos");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Ce jeu a été créer par Ezzaamari Anass & Jauvion Gilles dans le cadre de l'UV LO02.");
			}
		});
		button_2.setBounds(461, 248, 100, 22);
		layeredPane.add(button_2);
		
		Button button_3 = new Button("Quitter");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		button_3.setBounds(461, 276, 100, 22);
		layeredPane.add(button_3);
		
		
		layeredPane.add(panel);
		
		
		
	}
	


	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source.equals( this.button))
		{
			
					layeredPane.removeAll();
					
					
					
					textField = new JTextField();
					textField.setBounds(462, 202, 100, 20);
					layeredPane.add(textField);
					textField.setColumns(10);
					
					JLabel lblNomDuVillage = new JLabel("Nom du village :");
					lblNomDuVillage.setBounds(462, 177, 100, 14);
					layeredPane.add(lblNomDuVillage);
					
					JLabel lblNombresDeJoueurs = new JLabel("Nombres de joueurs : ");
					lblNombresDeJoueurs.setBounds(462, 233, 100, 14);
					layeredPane.add(lblNombresDeJoueurs);
					
					Choice choice = new Choice();
					choice.add("1");
					choice.add("2");
					choice.add("3");
					choice.add("4");
					choice.add("5");
					layeredPane.setLayer(choice, 0);
					choice.setBounds(462, 253, 100, 14);
					layeredPane.add(choice);
					
					this.button2 = new Button("Partie rapide");
					button2.addActionListener(this);
					panel.setBackground(new Color(0,0,0,0));
					
					
								
						
				
					
					button2.setBounds(412, 279, 100, 32);
					layeredPane.add(button2);
					
					Button button_1 = new Button("Partie avanc\u00E9e");
					button_1.setBounds(512, 279, 100, 32);
					layeredPane.add(button_1);
					layeredPane.add(panel);
				}
		
			if(source.equals( this.button2))
			{

				Graphique2 graph = new Graphique2(this);
			
				
				
			    frame.add(picLabel, BorderLayout.CENTER);
			  
			    
			    
			   // layeredPane.moveToFront(panel);
			    layeredPane.revalidate();
			    layeredPane.repaint();
			    
			    
			    //layeredPane.moveToFront(panel);
			    frame.revalidate();
			    frame.repaint();
			 
			   
			}
			
		}
	
		public void addMenhir(){
			panel.setBounds(270, 0, 470, 80);
		    //panel_1.add(pic2);
		    //panel.add(panel_1);
		    
			JLabel pic2 = new JLabel(new ImageIcon("png/menhir.png"));
			panel.add(pic2);
		    frame.add(picLabel, BorderLayout.CENTER);
		  
		    
		    
		   // layeredPane.moveToFront(panel);
		    layeredPane.revalidate();
		    layeredPane.repaint();
		    
		    
		    //layeredPane.moveToFront(panel);
		    frame.revalidate();
		    frame.repaint();
			
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			while(true)
			{
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if (model.getMenhir()!=0) {
					for(int i=0;i<model.getMenhir();i++)
					{
						addMenhir();
					}
					model.setMenhir(0);
				}
				else
				{
				if(model.isGeantAnimation())
				{
					panel.setVisible(false);
					frame.remove(picLabel);
					ImageIcon icon = new ImageIcon("geant/e.gif");
					JLabel picLabel2 = new JLabel(icon);
					icon.getImage().flush();
					frame.add(picLabel2, BorderLayout.CENTER);
				    frame.revalidate();
				    frame.repaint();
					try {
						Thread.sleep(3998);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					panel.setVisible(true);
					frame.remove(picLabel2);
					frame.add(picLabel3, BorderLayout.CENTER);
				    frame.revalidate();
				    frame.repaint();
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					frame.remove(picLabel3);
					frame.add(picLabel, BorderLayout.CENTER);
				    frame.revalidate();
				    frame.repaint();
					
					
					model.setGeantAnimation(false);
				}
				
				if(model.isFarfadetAnimation()==true)
				{
					frame.remove(picLabel);
					
					frame.add(picLabel4, BorderLayout.CENTER);
				    frame.revalidate();
				    frame.repaint();
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					frame.remove(picLabel4);
					
					frame.add(picLabel, BorderLayout.CENTER);
					frame.revalidate();
				    frame.repaint();
					model.setFarfadetAnimation(false);
				}
				}
				
			}
		}
		
		  
	}


		
	