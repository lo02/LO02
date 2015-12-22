import java.awt.EventQueue;

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

public class Graphique extends JFrame{

	private JFrame frame;
	
	private JTextField textField;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Graphique window = new Graphique();
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
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1008, 503);
		
		JLayeredPane layeredPane = new JLayeredPane();
		frame.getContentPane().add(layeredPane, BorderLayout.CENTER);
		
		Button button = new Button("Nouvelle partie");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
				
				Button button = new Button("Partie rapide");
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null, "Partie Rapide"+choice.getSelectedItem()+"  "+textField.getText());
						  JLabel picLabel = new JLabel(new ImageIcon("background.jpg"));
						    panel.add(picLabel);
						    panel.setPreferredSize(panel.getSize());
						    panel.setBounds(0,0,panel.getWidth(),panel.getHeight());
						    layeredPane.add(panel, JLayeredPane.DEFAULT_LAYER);
					}
				});
				
				button.setBounds(412, 279, 100, 32);
				layeredPane.add(button);
				
				Button button_1 = new Button("Partie avanc\u00E9e");
				button_1.setBounds(512, 279, 100, 32);
				layeredPane.add(button_1);
			}
		});
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
		}
	}
