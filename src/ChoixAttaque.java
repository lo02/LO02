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
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.plaf.PanelUI;
import javax.swing.JLayeredPane;
import java.awt.ScrollPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class ChoixAttaque extends JFrame implements ActionListener{

	private JLabel picLabel = new JLabel(new ImageIcon("geant/b.gif"));
	private JTextField textField;
	private JFrame frame2;
	
	private JButton menhirBouton;
	private JButton grainesBouton;
	private JButton graineBouton;
	private Graphique fenetrePrincipale;
	private Model mod = Model.getInstance();


	
	/**
	 * Launch the application.
	 */
	public ChoixAttaque (int a)
	{
		
		

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChoixAttaque window = new ChoixAttaque();
					window.frame2.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	

	/**
	 * Create the application.
	 */
	private ChoixAttaque() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		 frame2 = new JFrame();
		 new Thread() {
	            public void run() {
	            	
	            	while(!(mod.isFinished()))
	            	{
	            		try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	            	}
	            	frame2.setVisible(false);
	            	frame2.dispose();
	            }
		 }.start();
		 Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		    int x = (int) ((dimension.getWidth() - frame2.getWidth()) / 2);
		    int y = (int) ((dimension.getHeight() - frame2.getHeight()) / 2);
		frame2.setBounds(x-(178/2), y-(153/2) ,178, 153);
		
		JLayeredPane layeredPane = new JLayeredPane();
		frame2.getContentPane().add(layeredPane, BorderLayout.CENTER);
		
		
		JButton btnNewButton = new JButton("Jouer g\u00E9ant");
		this.menhirBouton=btnNewButton;
		btnNewButton.addActionListener(this);
		
				
				
	
		btnNewButton.setBounds(10, 11, 142, 23);
		layeredPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Joueur engrais");
		this.graineBouton=btnNewButton_1;
		btnNewButton_1.addActionListener(this);
	
		btnNewButton_1.setBounds(10, 45, 142, 23);
		layeredPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Jouer farfadet ");
		this.grainesBouton=btnNewButton_2;
		
		btnNewButton_2.addActionListener(this);
		btnNewButton_2.setBounds(10, 80, 142, 23);
		layeredPane.add(btnNewButton_2);
		JPanel panel_1 = new JPanel();
		
		
		
	}
	

		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if(source.equals( this.menhirBouton))
			{
			
				mod.setAction(0);
				frame2.setVisible(false);
				frame2.dispose();
				
				
			}
			if(source.equals( this.grainesBouton))
			{
				mod.setAction(2);
				frame2.setVisible(false);
				frame2.dispose();
			}
			if(source.equals( this.graineBouton))
			{
				mod.setAction(1);
				frame2.setVisible(false);
				frame2.dispose();
			}
	
		}
			



public Graphique getFenetrePrincipale() {
	return fenetrePrincipale;
}


public void setFenetrePrincipale(Graphique fenetrePrincipale) {
	this.fenetrePrincipale = fenetrePrincipale;
}









	
}