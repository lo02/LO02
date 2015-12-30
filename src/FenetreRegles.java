import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class FenetreRegles {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public /*static void main(String[] args)*/  FenetreRegles(int a) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetreRegles window = new FenetreRegles();
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
	private FenetreRegles() {
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		Model model = Model.getInstance();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 856, 482);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 11, 820, 432);
		frame.getContentPane().add(layeredPane);
		
		JPanel panel = new JPanel();
		
		//JPanel panel = new JPanel( new FlowLayout(FlowLayout.LEFT, 0, 0) );
		panel.setBounds(0, 0, 810, 392);
		layeredPane.add(panel);
		
		JButton btnPartieRapide = new JButton("Partie rapide");
		btnPartieRapide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageIcon img89;
				try {
					//JOptionPane.showMessageDialog(null, "Ce jeu a été créer par Ezzaamari Anass & Jauvion Gilles dans le cadre de l'UV LO02.");
					panel.removeAll();
					img89 = new ImageIcon(ImageIO.read(new File("img/reglesrapides.jpg"))
					        .getScaledInstance(810, 392, Image.SCALE_SMOOTH));
					JLabel pic76 = new JLabel(img89);
					pic76.setBounds(0, 0 ,810, 392);
					panel.add(pic76);
					layeredPane.moveToFront(panel);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnPartieRapide.setBounds(336, 398, 120, 23);
		layeredPane.add(btnPartieRapide);
		
		JButton btnPartieAvance = new JButton("Partie avanc\u00E9e");
		btnPartieAvance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageIcon img89;
				try {
					//JOptionPane.showMessageDialog(null, "Ce jeu a été créer par Ezzaamari Anass & Jauvion Gilles dans le cadre de l'UV LO02.");
					panel.removeAll();
					img89 = new ImageIcon(ImageIO.read(new File("img/reglesavancees.jpg"))
					        .getScaledInstance(810, 392, Image.SCALE_SMOOTH));
					JLabel pic76 = new JLabel(img89);
					pic76.setBounds(0, 0 ,810, 392);
					panel.add(pic76);
					layeredPane.moveToFront(panel);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnPartieAvance.setBounds(579, 398, 120, 23);
		layeredPane.add(btnPartieAvance);
		
		JButton btnUtilisationDeLinterface = new JButton("utilisation de l'interface");
		btnUtilisationDeLinterface.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageIcon img89;
				try {
					//JOptionPane.showMessageDialog(null, "Ce jeu a été créer par Ezzaamari Anass & Jauvion Gilles dans le cadre de l'UV LO02.");
					panel.removeAll();
					img89 = new ImageIcon(ImageIO.read(new File("img/conseil.png"))
					        .getScaledInstance(810, 392, Image.SCALE_SMOOTH));
					JLabel pic76 = new JLabel(img89);
					pic76.setBounds(0, 0 ,810, 392);
					panel.add(pic76);
					layeredPane.moveToFront(panel);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnUtilisationDeLinterface.setBounds(105, 398, 167, 23);
		layeredPane.add(btnUtilisationDeLinterface);
	}
	private String texte="<html> Bonjour </html>";
}
