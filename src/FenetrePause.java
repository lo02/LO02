import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JButton;

public class FenetrePause {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetrePause window = new FenetrePause();
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
	public FenetrePause() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 229, 171);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(12, 13, 183, 104);
		frame.getContentPane().add(layeredPane);
		
		JButton btnRecommencerPartie = new JButton("Recommencer Partie");
		btnRecommencerPartie.setBounds(12, 13, 151, 25);
		layeredPane.add(btnRecommencerPartie);
		
		JButton btnRetourAuMenu = new JButton("Retour au menu");
		btnRetourAuMenu.setBounds(12, 39, 151, 25);
		layeredPane.add(btnRetourAuMenu);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(12, 65, 151, 25);
		layeredPane.add(btnAnnuler);
	}
	
	

}

