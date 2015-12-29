import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class FenetrePause {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public FenetrePause(int a) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Model.getInstance().getA().suspend();
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
		Model model = Model.getInstance();
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	   
	   frame.setBounds(x-(229/2), y-(171/2) ,229, 171);
		
		 frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		frame.getContentPane().setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(12, 13, 183, 104);
		frame.getContentPane().add(layeredPane);
		
		JButton btnRecommencerPartie = new JButton("Recommencer Partie");
		btnRecommencerPartie.setBounds(12, 13, 160, 25);
		layeredPane.add(btnRecommencerPartie);
		btnRecommencerPartie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.getA().resume();
				model.setMain(null);
				model.setRestart(true);
				frame.setVisible(false);
				frame.dispose();
			}
		});
		
		JButton btnRetourAuMenu = new JButton("Retour au menu");
		btnRetourAuMenu.setBounds(12, 39, 160, 25);
		layeredPane.add(btnRetourAuMenu);
		btnRetourAuMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				model.setPartieRapide(-1);
				model.setResetAll(true);
				model.setRestart(true);
				System.exit(0);
			}
		});
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(12, 65, 160, 25);
		layeredPane.add(btnAnnuler);
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				model.getA().resume();
				frame.setVisible(false);
				frame.dispose();
			}
		});
	}
	
	

}

