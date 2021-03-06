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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

public class SelectionDeCarte {

	private JFrame frmVotreMain;
	
	private Model model = Model.getInstance();
	

	/**
	 * Launch the application.
	 */
	public  SelectionDeCarte(int a) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectionDeCarte window = new SelectionDeCarte();
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
	private SelectionDeCarte() {
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
		frmVotreMain.addKeyListener(new KeyAdapter() {
	         public void keyReleased(KeyEvent e) {                
	             if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
	            	 //System.out.println("You Typed esc ");
	            	
	            	 FenetrePause p = new FenetrePause(1);
	            	 
	             }
	          }        
	       });
		frmVotreMain.setTitle("Votre main");
		frmVotreMain.setUndecorated(true);
		frmVotreMain.setBackground(new Color(1.0f,1.0f,1.0f,0));
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
		 
		 Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		    int x = (int) ((dimension.getWidth() - frmVotreMain.getWidth()) / 2);
		    int y = (int) ((dimension.getHeight() - frmVotreMain.getHeight()) / 2);
		   
		   frmVotreMain.setBounds(x-(938/2), y ,738, 400);
		    
		    
		
		
		frmVotreMain.getContentPane().setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBackground(new Color(0, 0,0,0));
		layeredPane.setBounds(11, 2, 911, 335);
		
		frmVotreMain.getContentPane().add(layeredPane);
		

		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		layeredPane_1.setForeground(new Color(0, 0, 0,0));
		layeredPane_1.setBackground(new Color(0, 0, 102,90));
		layeredPane_1.setOpaque(true);
		layeredPane_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				ChoixAttaque a = new ChoixAttaque(0);
				Model model = Model.getInstance();
				model.setCarteChoisie(0);
				frmVotreMain.setVisible(false);
				frmVotreMain.dispose();
				
			}
		});
		layeredPane_1.setBounds(10, 75, 154, 260);
		layeredPane.add(layeredPane_1);
		
		JPanel panel = new JPanel();
		ImageIcon img89 = new ImageIcon(ImageIO.read(new File(model.getMain().get(0).getLogo()))
                .getScaledInstance(136, 136, Image.SCALE_SMOOTH));
		JLabel pic76 = new JLabel(img89);
		//JLabel pic = new JLabel(new ImageIcon(model.getMain().get(0).getLogo()));
		panel.add(pic76);
		panel.setOpaque(true);
		panel.setBackground(new Color(0,0,0,0));
		
		
		panel.setBounds(10, 11, 136, 136);
		layeredPane_1.add(panel);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(10, 158, 137, 91);
		layeredPane_1.add(panel_5);
		
		JLabel lblRayonDeLune = new JLabel(model.getMain().get(0).toString2());
		panel_5.add(lblRayonDeLune);
		
		/*if (model.getPartieRapide()==2)
		{
			if(model.getAllie() != null){
				
					if (model.getAllie().getTitre() != ""){
					
					JLayeredPane layeredPane_5 = new JLayeredPane();
					layeredPane_5.addMouseListener(new MouseAdapter() {
						public void mouseReleased(MouseEvent arg0) {
							/*
							 * Model model = Model.getInstance();
							 
							ChoixJoueurTaupe t = new ChoixJoueurTaupe(0);
							while(model.getIndexJoueurCible()==-1)
							{
								try {
									Thread.sleep(250);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							int cible = model.getIndexJoueurCible();
							model.setIndexJoueurCible(-1);
							
							model.setMenhirADetruire(model.getAllie().getValeur()[Partie.getTour()]);;
							frmVotreMain.setVisible(false);
							frmVotreMain.dispose();
							
						}
					});
					layeredPane_5.setBounds(722, 75, 154, 236);
					layeredPane.add(layeredPane_5);
					
					
					
					JPanel panel_4 = new JPanel();
					ImageIcon img01 = new ImageIcon(ImageIO.read(new File(model.getAllie().getLogo()))
			                .getScaledInstance(136, 136, Image.SCALE_SMOOTH));
					JLabel pic2 = new JLabel(img01);
					panel_4.add(pic2);
					
					panel_4.setBounds(10, 11, 136, 136);
					//layeredPane_5.add(panel_4);
					
				
					JPanel panel_9 = new JPanel();
					panel_9.setBounds(10, 158, 137, 67);
					//layeredPane_5.add(panel_9);
					
					JLabel label = new JLabel(model.getAllie().toString2());
					panel_9.add(label);
				}
			}
		}*/
		
		if(model.getMain().size()>=2)
		{
		JLayeredPane layeredPane_2 = new JLayeredPane();
		layeredPane_2.setForeground(new Color(0, 0, 102));
		layeredPane_2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		layeredPane_2.setBackground(new Color(0, 0, 102));
		layeredPane_2.setBounds(174, 75, 154, 260);
		layeredPane_2.setBackground(new Color(0, 0, 102,90));
		layeredPane_2.setOpaque(true);
		layeredPane_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				ChoixAttaque a = new ChoixAttaque(0);
				Model model = Model.getInstance();
				model.setCarteChoisie(1);
				frmVotreMain.setVisible(false);
				frmVotreMain.dispose();
				
			}
		});
		layeredPane.add(layeredPane_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(true);
		panel_1.setBackground(new Color(0,0,0,0));
		ImageIcon img0 = new ImageIcon(ImageIO.read(new File(model.getMain().get(1).getLogo()))
                .getScaledInstance(136, 136, Image.SCALE_SMOOTH));
		JLabel pic9 = new JLabel(img0);
		panel_1.add(pic9);
		panel_1.setBounds(10, 11, 136, 136);
		layeredPane_2.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 158, 137, 91);
		layeredPane_2.add(panel_2);
		
		JLabel label = new JLabel(model.getMain().get(1).toString2());
				panel_2.add(label);
		}
		
		if(model.getMain().size()>=3)
		{
		JLayeredPane layeredPane_3 = new JLayeredPane();
		layeredPane_3.setForeground(new Color(0, 0, 102));
		layeredPane_3.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		layeredPane_3.setBackground(new Color(0, 0, 102));
		layeredPane_3.setBounds(338, 75, 154, 260);
		layeredPane_3.setBackground(new Color(0, 0, 102,90));
		layeredPane_3.setOpaque(true);
		layeredPane_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				ChoixAttaque a = new ChoixAttaque(0);
				Model model = Model.getInstance();
				model.setCarteChoisie(2);
				frmVotreMain.setVisible(false);
				frmVotreMain.dispose();
				
			}
		});
		layeredPane.add(layeredPane_3);
		
		JPanel panel_3 = new JPanel();
		ImageIcon img2 = new ImageIcon(ImageIO.read(new File(model.getMain().get(2).getLogo()))
                .getScaledInstance(136, 136, Image.SCALE_SMOOTH));
		JLabel pic4 = new JLabel(img2);
		panel_3.setOpaque(true);
		panel_3.setBackground(new Color(0,0,0,0));
		panel_3.add(pic4);
		panel_3.setBounds(10, 11, 136, 136);
		layeredPane_3.add(panel_3);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(10, 158, 137, 91);
		layeredPane_3.add(panel_6);
		
		JLabel label_1 = new JLabel(model.getMain().get(2).toString2());
		panel_6.add(label_1);
		}
		
		
		if(model.getMain().size()>=4)
		{
		JLayeredPane layeredPane_4 = new JLayeredPane();
		layeredPane_4.setForeground(new Color(0, 0, 102));
		layeredPane_4.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		layeredPane_4.setBackground(new Color(0, 0, 102));
		layeredPane_4.setBounds(502, 75, 154, 260);
		layeredPane_4.setBackground(new Color(0, 0, 102,90));
		layeredPane_4.setOpaque(true);
		layeredPane_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				ChoixAttaque a = new ChoixAttaque(0);
				Model model = Model.getInstance();
				model.setCarteChoisie(3);
				frmVotreMain.setVisible(false);
				frmVotreMain.dispose();
				
			}
		});
		layeredPane.add(layeredPane_4);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(10, 11, 136, 136);
		layeredPane_4.add(panel_7);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBounds(10, 158, 137, 91);
		panel_7.setOpaque(true);
		panel_7.setBackground(new Color(0,0,0,0));
		ImageIcon img = new ImageIcon(ImageIO.read(new File(model.getMain().get(3).getLogo()))
                .getScaledInstance(136, 136, Image.SCALE_SMOOTH));
		JLabel pic5 = new JLabel(img);
		panel_7.add(pic5);
		layeredPane_4.add(panel_8);
		
		JLabel label_2 = new JLabel(model.getMain().get(3).toString2());
		panel_8.add(label_2);
		}
		
	}
}
