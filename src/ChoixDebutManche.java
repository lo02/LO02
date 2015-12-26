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
import javax.swing.JComboBox;

public class ChoixDebutManche extends JFrame implements ActionListener{

	private JLabel picLabel = new JLabel(new ImageIcon("geant/b.gif"));
	private JTextField textField;
	private JFrame frame2;
	
	private JButton menhirBouton;
	private JButton grainesBouton;
	private JButton graineBouton;
	private Graphique fenetrePrincipale;
	private Model mod = Model.getInstance();
	private Choice choice;

	
	/**
	 * Launch the application.
	 */
	public ChoixDebutManche (int a)
	{
		
		

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChoixDebutManche window = new ChoixDebutManche();
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
	public ChoixDebutManche() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		 frame2 = new JFrame();
		
		JLayeredPane layeredPane = new JLayeredPane();

		 Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		    int x = (int) ((dimension.getWidth() - frame2.getWidth()) / 2);
		    int y = (int) ((dimension.getHeight() - frame2.getHeight()) / 2);
		frame2.setBounds(x-(308/2), y-45 ,308, 123);
		frame2.getContentPane().add(layeredPane, BorderLayout.CENTER);
		
		
		JButton btnNewButton = new JButton("Prendre 2 graines");
		this.grainesBouton=btnNewButton;
		btnNewButton.addActionListener(this);
		
				
				
	
		btnNewButton.setBounds(80, 13, 147, 23);
		layeredPane.add(btnNewButton);
		
		JButton btnPrendreCarteAllie = new JButton("Prendre carte Alli\u00E9e");
		btnPrendreCarteAllie.setBounds(80, 49, 147, 23);
		layeredPane.add(btnPrendreCarteAllie);
		btnPrendreCarteAllie.addActionListener(this);
		
		JPanel panel_1 = new JPanel();
		
		
		
	}
	

		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if(source.equals( this.grainesBouton))
			{
				mod.setChoix(1);;
				frame2.setVisible(false);
				frame2.dispose();
			}else {
				mod.setChoix(2);;
				frame2.setVisible(false);
				frame2.dispose();
			}
		
	
		}
}

