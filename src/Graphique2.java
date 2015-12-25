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

public class Graphique2 extends Graphique implements ActionListener{

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
	public Graphique2 (Graphique a)
	{
		
		

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Graphique2 window = new Graphique2();
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
	public Graphique2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		 frame2 = new JFrame();
		
		JLayeredPane layeredPane = new JLayeredPane();
		frame2.getContentPane().add(layeredPane, BorderLayout.CENTER);
		frame2.setBounds(0,0 , 110, 300);
		
		JButton btnNewButton = new JButton("+1 menhir");
		this.menhirBouton=btnNewButton;
		btnNewButton.addActionListener(this);
		
				
				
	
		btnNewButton.setBounds(10, 11, 100, 23);
		layeredPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("-1 graine");
		this.graineBouton=btnNewButton_1;
		btnNewButton_1.addActionListener(this);
	
		btnNewButton_1.setBounds(10, 45, 100, 23);
		layeredPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("+1 graine");
		this.grainesBouton=btnNewButton_2;
		
		btnNewButton_2.addActionListener(this);
		btnNewButton_2.setBounds(10, 80, 100, 23);
		layeredPane.add(btnNewButton_2);
		 Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		JPanel panel_1 = new JPanel();
		
		
		
	}
	

		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if(source.equals( this.menhirBouton))
			{
			
				mod.setMenhir(1);
				
				
			}
			if(source.equals( this.grainesBouton))
					{
						mod.setGeantAnimation(true);
					}
			if(source.equals( this.graineBouton))
			{
				mod.setFarfadetAnimation(true);
			}
	
		}
			



public Graphique getFenetrePrincipale() {
	return fenetrePrincipale;
}


public void setFenetrePrincipale(Graphique fenetrePrincipale) {
	this.fenetrePrincipale = fenetrePrincipale;
}


public JPanel getPanel() {
	return panel;
}


public void setPanel(JPanel panel) {
	this.panel = panel;
}





	
}