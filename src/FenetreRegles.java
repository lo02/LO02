import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;

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
		//frame.setBounds(100, 100, 1024, 596);
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	   
	   frame.setBounds(x-(1024/2), y-(596/2) ,1024, 596);
		
		 frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 11, 984, 525);
		frame.getContentPane().add(layeredPane);
		
		JPanel panel = new JPanel();
		
		//JPanel panel = new JPanel( new FlowLayout(FlowLayout.LEFT, 0, 0) );
		panel.setBounds(0, 0, 972, 476);
		layeredPane.add(panel);
		
		JButton btnPartieRapide = new JButton("Partie rapide");
		btnPartieRapide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageIcon img89;
				try {
					//JOptionPane.showMessageDialog(null, "Ce jeu a été créer par Ezzaamari Anass & Jauvion Gilles dans le cadre de l'UV LO02.");
					panel.removeAll();
					img89 = new ImageIcon(ImageIO.read(new File("img/reglesrapides.jpg"))
					        .getScaledInstance(972, 476, Image.SCALE_SMOOTH));
					JLabel pic76 = new JLabel(img89);
					pic76.setBounds(0, 0 ,972, 476);
					panel.add(pic76);
					layeredPane.moveToFront(panel);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnPartieRapide.setBounds(239, 489, 120, 23);
		layeredPane.add(btnPartieRapide);
		
		JButton btnPartieAvance = new JButton("Partie avanc\u00E9e");
		btnPartieAvance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageIcon img89;
				try {
					//JOptionPane.showMessageDialog(null, "Ce jeu a été créer par Ezzaamari Anass & Jauvion Gilles dans le cadre de l'UV LO02.");
					panel.removeAll();
					img89 = new ImageIcon(ImageIO.read(new File("img/reglesavancees.jpg"))
					        .getScaledInstance(972, 476, Image.SCALE_SMOOTH));
					JLabel pic76 = new JLabel(img89);
					pic76.setBounds(0, 0 ,972, 476);
					panel.add(pic76);
					layeredPane.moveToFront(panel);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnPartieAvance.setBounds(412, 489, 120, 23);
		layeredPane.add(btnPartieAvance);
		
		JButton btnUtilisationDeLinterface = new JButton("utilisation de l'interface");
		btnUtilisationDeLinterface.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageIcon img89;
				try {
					//JOptionPane.showMessageDialog(null, "Ce jeu a été créer par Ezzaamari Anass & Jauvion Gilles dans le cadre de l'UV LO02.");
					panel.removeAll();
					img89 = new ImageIcon(ImageIO.read(new File("img/conseil.png"))
					        .getScaledInstance(972, 476, Image.SCALE_SMOOTH));
					JLabel pic76 = new JLabel(img89);
					pic76.setBounds(0, 0 ,972, 476);
					panel.add(pic76);
					layeredPane.moveToFront(panel);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnUtilisationDeLinterface.setBounds(35, 489, 167, 23);
		layeredPane.add(btnUtilisationDeLinterface);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setBounds(852, 489, 120, 23);
		layeredPane.add(btnQuitter);
		btnQuitter.addActionListener(new  ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				frame.setVisible(false);
				frame.dispose();
			}
		});
	}
	private String texte="<html> Bonjour </html>";
}
