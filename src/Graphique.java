import java.awt.EventQueue;
import javazoom.jl.player.Player;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import javax.swing.JFrame;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
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
import javax.print.DocFlavor.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class Graphique extends JFrame implements ActionListener, Runnable{

	protected JFrame frame;
	protected JLabel picLabel = new JLabel(new ImageIcon("geant/b.gif"));
    private Player player; 
	private JLabel picLabel3 = new JLabel(new ImageIcon("geant/c.gif"));
	private JLabel picLabel4 = new JLabel(new ImageIcon("geant/d.gif"));
	private JTextField textField;
	protected JLayeredPane layeredPane;
	protected JPanel panel;
	protected Model model;
	protected Button button;
	protected Button button2;
	protected Button button3;
	private Choice choice;
	private Thread thread;
	private Thread t2;
	/**
	 * Launch the application.
	 */
	public Graphique(int a)
	{
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Graphique window = new Graphique();
					thread = new Thread(window);
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
	
	private Graphique()   {
		initialize();
		model = Model.getInstance();
		//frame.addKeyListener(this);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		
		
		 Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setBounds(x-(1024/2), 0 ,1024, 542);
		
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
		
		
		new Thread()
		{ public void run(){
			Model model = Model.getInstance();
					while(true){
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(model.isResetAll())
						{
							frame.setVisible(false);
							frame.dispose();
							
							model.setResetAll(false);
						}
						else
						{
							if(model.isRestart())
							{
								
								t2.interrupt();
								try {
									Thread.sleep(700);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								t2.interrupt();
								layeredPane.removeAll();
								layeredPane.add(panel);
							}
						}
						
						
					}}
		}.start();
					
		/*frame.addKeyListener(new KeyAdapter() {
			/**
		       * When you type the character "esc" into the text field you will see
		       * an information dialog box
		       
		      public void keyReleased(KeyEvent ke) {
		        int keyCode = ke.getKeyCode();
		        //(ke.getKeyCode() == KeyEvent.VK_ESCAPE
		        if (keyCode == KeyEvent.VK_ESCAPE) {
		          System.out.println("You Typed esc ");
		        }
		      }
		 });*/
				
		frame.addKeyListener(new KeyAdapter() {
	         public void keyReleased(KeyEvent e) {                
	             if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
	            	 //System.out.println("You Typed esc ");
	            	 model.getA().suspend();
	            	 FenetrePause p = new FenetrePause(0);
	            	 
	             }
	          }        
	       });
		
		
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
					
					choice = new Choice();
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
					
					this.button3 = new Button("Partie avanc\u00E9e");
					button3.setBounds(512, 279, 100, 32);
					layeredPane.add(button3);
					button3.addActionListener(this);
					
				}
		
			if(source.equals( this.button2))
			{
				
				  try {
			            FileInputStream fis     = new FileInputStream("mp3/mainSong.mp3");
			            BufferedInputStream bis = new BufferedInputStream(fis);
			            player = new Player(bis);
			            model.setSong(player);
			        }
			        catch (Exception e1) {
			            System.out.println("Problem playing file mainsong");
			            System.out.println(e1);
			        }

			        // run in new thread to play in background
			        new Thread() {
			            public void run() {
			                try { player.play();
			                	}
			                catch (Exception e) { System.out.println(e); }
			                
			            }
			        }.start();
				layeredPane.removeAll();
				layeredPane.add(panel);
				model.setPartieRapide(1);
				model.setNomJoueur(textField.getText());
				model.setNombreJoueurs(Integer.parseInt(choice.getSelectedItem()));
				
			
				
				
			    frame.add(picLabel, BorderLayout.CENTER);
			  
			    
			    
			   // layeredPane.moveToFront(panel);
			    layeredPane.revalidate();
			    layeredPane.repaint();
			    
			    
			    //layeredPane.moveToFront(panel);
			    frame.revalidate();
			    frame.repaint();
			 
			   
			}
			if (source.equals(this.button3)){
				Graphique2 gra = new Graphique2(this);
				 try {
			            FileInputStream fis     = new FileInputStream("mp3/mainSong.mp3");
			            BufferedInputStream bis = new BufferedInputStream(fis);
			            player = new Player(bis);
			            model.setSong(player);
			        }
			        catch (Exception e1) {
			            System.out.println("Problem playing file mainsong");
			            System.out.println(e1);
			        }

			        // run in new thread to play in background
			        new Thread() {
			            public void run() {
			            	
			                try { player.play(); 
			                }
			                catch (Exception e) { System.out.println(e); }
			               
			            }
			        }.start();
			        
				layeredPane.removeAll();
				layeredPane.add(panel);
				model.setPartieRapide(2);
				model.setNomJoueur(textField.getText());
				model.setNombreJoueurs(Integer.parseInt(choice.getSelectedItem()));
				
			
				
				
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
			panel.setBackground(new Color(0,0,0,0));
			panel.setOpaque(false);
		    //panel_1.add(pic2);
		    //panel.add(panel_1);
		    ImageIcon icone = new ImageIcon("png/menhir.gif");
		    icone.getImage().flush();
			JLabel pic2 = new JLabel();
			pic2.setBackground(new Color(0,0,0,0));
			pic2.setIcon(icone);
			frame.setOpacity(1);
			panel.add(pic2);
			
		    frame.add(picLabel, BorderLayout.CENTER);
		    
		    
		  
		    
		    
		   // layeredPane.moveToFront(panel);
		    layeredPane.revalidate();
		    layeredPane.repaint();
		    
		    
		    //layeredPane.moveToFront(panel);
		    frame.revalidate();
		    frame.repaint();
		    try {
				Thread.sleep(1300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    ImageIcon icone2 = new ImageIcon("png/menhir.png");
		    panel.remove(pic2);
		    pic2.setIcon(icone2);
		    panel.add(pic2);
		    
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			setT2(Thread.currentThread());
			while(true)
			{
				
			 	if (model.isResetPrincipalInterface())
				{
            		
					panel.removeAll();
					model.setResetPrincipalInterface(false);
				
				    
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				// animation rajout menhirs 
				if (model.getMenhir()!=0) {
					for(int i=0;i<model.getMenhir();i++)
					{
						addMenhir();
					}
					model.setMenhir(0);
				}
				else
				{
					
					// animation Joueur principal demande des graines
				if(model.isGeantAnimation())
				{
					panel.setBounds(270, 0, 470, 80);
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
					
				    panel.setBounds(270, 0, 470, 80);
					model.setGeantAnimation(false);
				}
				// Animation joueur principal  qui se fait voler 
				if(model.isFarfadetAnimation()==true)
				{
					frame.remove(picLabel);
					panel.setBounds(270, 0, 470, 80);
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
				
				// Animation joueur principal qui se fait voler mais réponds par un chien de garde
				if(model.isChienDeGarde()==true)
				{
					panel.setBounds(270, 0, 470, 80);
					frame.remove(picLabel);
					JLabel picLabel9 = new JLabel(new ImageIcon("geant/chiendegarde.gif"));
					frame.add(picLabel9, BorderLayout.CENTER);
				    frame.revalidate();
				    frame.repaint();
					try {
						Thread.sleep(6880);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					frame.remove(picLabel9);
					
					frame.add(picLabel, BorderLayout.CENTER);
					frame.revalidate();
				    frame.repaint();
					model.setChienDeGarde(false);
				}
				
				
				// animation joueur principal qui vole 
				if(model.isFarfadetAnimation2()==true)
				{
					panel.setBounds(270, 0, 470, 80);
					frame.remove(picLabel);
					panel.removeAll();
					for (int i=0; i<model.getListeJoueur().get(model.getIndexJoueurCible()).getNbreMenhir();i++)
					{
						 	ImageIcon icone2 = new ImageIcon("png/menhir.png");
						 	JLabel pic2 = new JLabel();
						    pic2.setIcon(icone2);
						    panel.add(pic2);
						
					}
					ImageIcon icon17 = new ImageIcon("geant/farfadet.gif");
					JLabel picLabel21 = new JLabel(icon17);
					frame.add(picLabel21, BorderLayout.CENTER);
				    frame.revalidate();
				    frame.repaint();
					try {
						Thread.sleep(9500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					panel.removeAll();
					for (int i=0;i<model.getJoueurPrincipal().getNbreMenhir();i++)
					{
						 	ImageIcon icone2 = new ImageIcon("png/menhir.png");
						 	JLabel pic2 = new JLabel();
						    pic2.setIcon(icone2);
						    panel.add(pic2);
						
					}
					frame.remove(picLabel21);
					frame.add(picLabel, BorderLayout.CENTER);
					frame.revalidate();
				    frame.repaint();
					model.setFarfadetAnimation2(false);
					
					
				}
				
				if(model.isChienDeGardeEnnemi()==true)
				{
					panel.setBounds(270, 0, 470, 80);
					frame.remove(picLabel);
					panel.removeAll();
					
					for (int i=0; i<model.getListeJoueur().get(model.getIndexJoueurCible()).getNbreMenhir();i++)
					{
						 	ImageIcon icone2 = new ImageIcon("png/menhir.png");
						 	JLabel pic2 = new JLabel();
						    pic2.setIcon(icone2);
						    panel.add(pic2);
						
					}
					ImageIcon icon17 = new ImageIcon("geant/CHIENDEGARDEennemi.gif");
					JLabel picLabel21 = new JLabel(icon17);
					frame.add(picLabel21, BorderLayout.CENTER);
				    frame.revalidate();
				    frame.repaint();
					try {
						Thread.sleep(12160);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					panel.removeAll();
					for (int i=0;i<model.getJoueurPrincipal().getNbreMenhir();i++)
					{
						 	ImageIcon icone2 = new ImageIcon("png/menhir.png");
						 	JLabel pic2 = new JLabel();
						    pic2.setIcon(icone2);
						    panel.add(pic2);
						
					}
					frame.remove(picLabel21);
					frame.add(picLabel, BorderLayout.CENTER);
					frame.revalidate();
				    frame.repaint();
					model.setChienDeGardeEnnemi(false);
					
					
				}
				if(model.isTaupe()==true)
				{
					panel.setBounds(270, 0, 470, 80);
					panel.removeAll();
					
					frame.remove(picLabel);
					JLabel picLabel9 = new JLabel(new ImageIcon("geant/taupe.gif"));
					frame.add(picLabel9, BorderLayout.CENTER);
				    frame.revalidate();
				    frame.repaint();
					
				    panel.setBounds(270, 0, 470, 80);
					panel.setBackground(new Color(0,0,0,0));
					panel.setOpaque(false);
					
					for (int i=0; i<model.getAncienPointCible();i++)
					{
							ImageIcon icone2 = new ImageIcon("png/menhir.png");
						 	JLabel pic2 = new JLabel();
						    pic2.setIcon(icone2);
						    panel.add(pic2);
						   	
						 
					}
					panel.revalidate();
					panel.repaint();
					try {
						Thread.sleep(2500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					panel.removeAll();
					for (int i=0; i<model.getListeJoueur().get(model.getIndexJoueurCibleTaupe2()).getNbreMenhir();i++)
					{
							ImageIcon icone2 = new ImageIcon("png/menhir.png");
						 	JLabel pic2 = new JLabel();
						    pic2.setIcon(icone2);
						    panel.add(pic2);
						   	
						 
					}
				if (model.getAncienPointCible()<model.getMenhirADetruire())
				{
					for(int i=0;i<model.getAncienPointCible();i++)
					{
						ImageIcon icone2 = new ImageIcon("png/menhirDetruit.gif");
					 	JLabel pic2 = new JLabel();
					    pic2.setIcon(icone2);
					    panel.add(pic2);
					    panel.revalidate();
					    panel.repaint();
					 
					}
				}
				else
				{
					for(int i=0;i<model.getMenhirADetruire();i++)
					{
						ImageIcon icone2 = new ImageIcon("png/menhirDetruit.gif");
					 	JLabel pic2 = new JLabel();
					    pic2.setIcon(icone2);
					    panel.add(pic2);
					    panel.revalidate();
					    panel.repaint();
					 
					}
				}
					
					 
				        
				            	try {
									Thread.sleep(1280);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
				            	panel.removeAll();
				            	
				            	for (int i=0; i<model.getJoueurPrincipal().getNbreMenhir();i++)
								{
									 	ImageIcon icone2 = new ImageIcon("png/menhir.png");
									 	JLabel pic2 = new JLabel();
									    pic2.setIcon(icone2);
									    panel.add(pic2);	
									    panel.revalidate();
									    panel.repaint();
									    
								}
				            	
				            
				
					 
					frame.remove(picLabel9);
					frame.add(picLabel, BorderLayout.CENTER);
					frame.revalidate();
				    frame.repaint();
					model.setTaupe(false);
				}
				
				if(model.isTaupeEnnemi()==true)
				{
					panel.setBounds(270, 0, 470, 80);
					panel.removeAll();
					
					frame.remove(picLabel);
					JLabel picLabel9 = new JLabel(new ImageIcon("geant/taupeEnnemi.gif"));
					frame.add(picLabel9, BorderLayout.CENTER);
				    frame.revalidate();
				    frame.repaint();
					
				    panel.setBounds(270, 0, 470, 80);
					panel.setBackground(new Color(0,0,0,0));
					panel.setOpaque(false);
					
					for (int i=0; i<model.getAncienPointCible();i++)
					{
							ImageIcon icone2 = new ImageIcon("png/menhir.png");
						 	JLabel pic2 = new JLabel();
						    pic2.setIcon(icone2);
						    panel.add(pic2);
						   	
						 
					}
					panel.revalidate();
					panel.repaint();
					try {
						Thread.sleep(2500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					panel.removeAll();
					for (int i=0; i<model.getJoueurPrincipal().getNbreMenhir();i++)
					{
							ImageIcon icone2 = new ImageIcon("png/menhir.png");
						 	JLabel pic2 = new JLabel();
						    pic2.setIcon(icone2);
						    panel.add(pic2);
						   	
						 
					}
				if (model.getAncienPointCible()<model.getMenhirADetruire())
				{
					for(int i=0;i<model.getAncienPointCible();i++)
					{
						ImageIcon icone2 = new ImageIcon("png/menhirDetruit.gif");
					 	JLabel pic2 = new JLabel();
					    pic2.setIcon(icone2);
					    panel.add(pic2);
					    panel.revalidate();
					    panel.repaint();
					 
					}
				}
				else
				{
					for(int i=0;i<model.getMenhirADetruire();i++)
					{
						ImageIcon icone2 = new ImageIcon("png/menhirDetruit.gif");
					 	JLabel pic2 = new JLabel();
					    pic2.setIcon(icone2);
					    panel.add(pic2);
					    panel.revalidate();
					    panel.repaint();
					 
					}
				}
					
					 
				        
				            	try {
									Thread.sleep(1280);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
				            	panel.removeAll();
				            	
				            	for (int i=0; i<model.getJoueurPrincipal().getNbreMenhir();i++)
								{
									 	ImageIcon icone2 = new ImageIcon("png/menhir.png");
									 	JLabel pic2 = new JLabel();
									    pic2.setIcon(icone2);
									    panel.add(pic2);	
									    panel.revalidate();
									    panel.repaint();
									    
								}
				            	
				            
				
					 
					frame.remove(picLabel9);
					frame.add(picLabel, BorderLayout.CENTER);
					frame.revalidate();
				    frame.repaint();
					model.setTaupeEnnemi(false);
				}
				
				
				
				
				
				}
				
			
		}
		}
		public Thread getT2() {
			return t2;
		}
		public void setT2(Thread t2) {
			this.t2 = t2;
		}
}

		  



		
	