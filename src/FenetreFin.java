import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class FenetreFin {

	private JFrame frame;
	Model model = Model.getInstance();
	/**
	 * Launch the application.
	 */
	public FenetreFin(int a){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetreFin window = new FenetreFin();
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
	private FenetreFin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 910, 553);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 892, 506);
		frame.getContentPane().add(layeredPane);
		
		JButton btnRecommencerUnePartie = new JButton("Recommencer une Partie");
		btnRecommencerUnePartie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRestart(true);
				frame.setVisible(false);
				frame.dispose();
			}
		});
		btnRecommencerUnePartie.setBounds(12, 467, 205, 25);
		layeredPane.add(btnRecommencerUnePartie);
		
		JButton btnRetour = new JButton("Retour \u00E0 la page d'accueil");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.getSong().close();
				model.setPartieRapide(-1);
				model.setResetAll(true);
				model.setRestart(true);
				System.exit(0);
			}
		});
		btnRetour.setBounds(675, 467, 205, 25);
		layeredPane.add(btnRetour);
		
		JPanel panelImageResultat = new JPanel();
		if (model.getPartieRapide()==1)
		{
		if(model.getListeJoueurGagnant().contains(model.getJoueurPrincipal()))
		{
			ImageIcon img89;
			try {
				img89 = new ImageIcon(ImageIO.read(new File("img/sdad.png"))
				        .getScaledInstance(892, 215, Image.SCALE_SMOOTH));
				JLabel pic76 = new JLabel(img89);
				panelImageResultat.add(pic76);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else
		{
		ImageIcon img89;
		try {
			img89 = new ImageIcon(ImageIO.read(new File("img/defeat.png"))
			        .getScaledInstance(892, 215, Image.SCALE_SMOOTH));
			JLabel pic76 = new JLabel(img89);
			panelImageResultat.add(pic76);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}
	}else
	{
		
		if(model.getJoueurGagnant().equals(model.getJoueurPrincipal()))
		{
			ImageIcon img89;
			try {
				img89 = new ImageIcon(ImageIO.read(new File("img/sdad.png"))
				        .getScaledInstance(892, 215, Image.SCALE_SMOOTH));
				JLabel pic76 = new JLabel(img89);
				panelImageResultat.add(pic76);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else
		{
		ImageIcon img89;
		try {
			img89 = new ImageIcon(ImageIO.read(new File("img/defeat.png"))
			        .getScaledInstance(892, 215, Image.SCALE_SMOOTH));
			JLabel pic76 = new JLabel(img89);
			panelImageResultat.add(pic76);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}
		
		
	}
	
		//JLabel pic = new JLabel(new ImageIcon(model.getMain().get(0).getLogo()));
		
		panelImageResultat.setOpaque(true);
		panelImageResultat.setBackground(Color.BLACK);
		
		panelImageResultat.setBounds(0, 0, 892, 215);
		layeredPane.add(panelImageResultat);
		layeredPane.setBackground(Color.BLACK);
		JPanel panelScore = new JPanel();
		panelScore.setBackground(Color.BLACK);
		panelScore.setBounds(125, 234, 647, 189);
		layeredPane.add(panelScore);
		panelScore.setLayout(null);
		if(model.getPartieRapide() == 1){
			//partie rapide
			List<Joueur> listGagnant = new ArrayList<Joueur>();
			listGagnant.addAll(model.getListeJoueurGagnant());
			String message = "<html><table style='background-color:black; color:white; width:100%;'><tr><td>Le(s) Gagnant(s) est(sont):<br> ";
			for(int i = 0; i<listGagnant.size();i++){
				message = message + "Le village "+listGagnant.get(i).getNom()+" avec "+listGagnant.get(i).getNbreMenhir()+" Menhir(s) et " +listGagnant.get(i).getNbreGraine()+" graine(s)<br>";
			}
			message = message +"<br>Les scores des autres joueurs sont :<br>";
			List<Joueur> temp = new ArrayList<Joueur>();
			temp.addAll(model.getListeJoueur());
			temp.removeAll(listGagnant);
			model.setListeJoueur(temp);
			for(int i = 0; i<model.getListeJoueur().size();i++){
				
					message = message + "Le village "+model.getListeJoueur().get(i).getNom()+" avec "+model.getListeJoueur().get(i).getNbreMenhir()+" Menhir(s) et " +model.getListeJoueur().get(i).getNbreGraine()+" graine(s)<br>";
				
			}
			message = message + " </td></tr></table></html>";
			
			JLabel labelScore = new JLabel(message);
			labelScore.setBounds(0, 0, 625, 189);
			panelScore.add(labelScore);
		}else{
			String message = "<html><table style='background-color:black; color:white; width:100%;'><tr><td>Le(s) Gagnant(s) est(sont):<br> ";
			message = message + "Le village "+model.getJoueurGagnant().getNom()+" avec "+model.getJoueurGagnant().getNbreMenhir()+" Menhir(s) et " +model.getJoueurGagnant().getNbreGraine()+" graine(s)<br>";
			List<Joueur> temp = new ArrayList<Joueur>();
			temp.addAll(model.getListeJoueur());
			temp.remove(model.getJoueurGagnant());
			message = message +"<br>Les scores des autres joueurs sont :<br>";

			for(int i = 0; i<model.getListeJoueur().size();i++){
				
				message = message + "Le village "+model.getListeJoueur().get(i).getNom()+" avec "+model.getListeJoueur().get(i).getNbreMenhir()+" Menhir(s) et " +model.getListeJoueur().get(i).getNbreGraine()+" graine(s)<br>";
			
			}
			message = message + " </td></tr></table></html>";
			JLabel labelScore = new JLabel(message);
			labelScore.setBounds(0, 0, 625, 189);
			panelScore.add(labelScore);
			
			
			
		}
	}

}
