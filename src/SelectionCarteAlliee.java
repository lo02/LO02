import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.List;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.awt.Font;
import javax.swing.JInternalFrame;
import javax.swing.JToggleButton;
import static java.awt.GraphicsDevice.WindowTranslucency.*;

import java.awt.BorderLayout;

import javax.swing.border.BevelBorder;
import java.awt.Panel;

public class SelectionCarteAlliee {

	private JFrame frmVotreMain;
	
	private Model model = Model.getInstance();
	

	/**
	 * Launch the application.
	 */
	public  SelectionCarteAlliee(int a) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectionCarteAlliee window = new SelectionCarteAlliee();
					window.frmVotreMain.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	private SelectionCarteAlliee() {
		try {
			initialize();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	
	private void initialize() throws IOException {
		
		frmVotreMain = new JFrame();
		 new Thread() {
	            public void run() {
	            	
	            	while(!(model.isFinished()))
	            	{
	            		try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	            	}
	            	frmVotreMain.setVisible(false);
	            	frmVotreMain.dispose();
	            }
		 }.start();
		frmVotreMain.setTitle("Votre main");
		frmVotreMain.setUndecorated(true);
		frmVotreMain.setBackground(new Color(1.0f,1.0f,1.0f,0));
		
		 Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		    int x = (int) ((dimension.getWidth() - frmVotreMain.getWidth()) / 2);
		    int y = (int) ((dimension.getHeight() - frmVotreMain.getHeight()) / 2);
		    	frmVotreMain.setBounds(x+(450/2), y ,167, 400);
		    
		
		frmVotreMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVotreMain.getContentPane().setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBackground(new Color(0, 0,0,0));
		layeredPane.setBounds(11, 2, 158, 335);
		
		frmVotreMain.getContentPane().add(layeredPane);

		//if (model.getPartieRapide()==2)
		{
			if(model.getAllie() != null)
			{
				
					if (model.getAllie().getTitre() != "")
				{
					
					JLayeredPane layeredPane_5 = new JLayeredPane();
					layeredPane_5.setBackground(new Color(0, 0, 102,90));
					layeredPane_5.setOpaque(true);
					if (model.getAllie().getTitre().equals("Taupe géante"))
					{
						layeredPane_5.addMouseListener(new MouseAdapter() {
							
							public void mouseReleased(MouseEvent arg0) {
								
								Model model = Model.getInstance();
							
								ChoixJoueurTaupe t = new ChoixJoueurTaupe(0);
								/*
								while(model.getIndexJoueurCible()==-1)
								{
									try {
										Thread.sleep(250);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}*/
								//int cible = model.getIndexJoueurCible();
								//model.setIndexJoueurCible(-1);
								
								//model.setMenhirADetruire(model.getAllie().getValeur()[Partie.getTour()]);;
								frmVotreMain.setVisible(false);
								frmVotreMain.dispose();
								
							}
						});
					}
					
					layeredPane_5.setBounds(0, 75, 154, 236);
					layeredPane.add(layeredPane_5);
					
					
					JPanel panel_4 = new JPanel();
					panel_4.setOpaque(true);
					panel_4.setBackground(new Color(0,0,0,0));
					ImageIcon img01 = new ImageIcon(ImageIO.read(new File(model.getAllie().getLogo()))
			                .getScaledInstance(136, 136, Image.SCALE_SMOOTH));
					JLabel pic2 = new JLabel(img01);
					panel_4.add(pic2);
					
					panel_4.setBounds(10, 11, 136, 136);
					layeredPane_5.add(panel_4);
					
				
					JPanel panel_9 = new JPanel();
					panel_9.setBounds(10, 158, 137, 67);
					layeredPane_5.add(panel_9);
					
					String texte = model.getAllie().toString2();
					JLabel label = new JLabel(texte);
					panel_9.add(label);
					 new Thread() {
				            public void run() {
				            	int saison = -1;
								while(!(model.getAllie().getTitre().equals("")))
								{
									
									try {
										Thread.sleep(100);
										
										if ( saison != Partie.getTour()){
											saison = Partie.getTour() ;
											String texte2 = model.getAllie().toString2();
											label.setText(texte2);
											
											
										}
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
								}
					frmVotreMain.setVisible(false);
					frmVotreMain.dispose();
				            }
				        }.start();
					
				}
			}
		}
		
		
		
	}
}
