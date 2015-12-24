import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
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
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.awt.Font;
import javax.swing.JInternalFrame;
import javax.swing.JToggleButton;

public class SelectionDeCarte {

	private JFrame frmVotreMain;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	public SelectionDeCarte() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVotreMain = new JFrame();
		frmVotreMain.setTitle("Votre main");
		 Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		    int x = (int) ((dimension.getWidth() - frmVotreMain.getWidth()) / 2);
		    int y = (int) ((dimension.getHeight() - frmVotreMain.getHeight()) / 2);
		frmVotreMain.setBounds(x-(1024/2), y-(542/2) ,1013, 400);
		frmVotreMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVotreMain.getContentPane().setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(11, 2, 982, 335);
		frmVotreMain.getContentPane().add(layeredPane);
		
		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {	
			public void mouseClicked(MouseEvent arg0) {
				JOptionPane.showMessageDialog(null, "Vous avez séléctionné la carte : ");
				System.exit(0);
			}
		});
		panel.setBackground(Color.GRAY);
		panel.setBounds(12, 75, 180, 180);
		layeredPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				JOptionPane.showMessageDialog(null, "Vous avez séléctionné la carte : ");
				System.exit(0);
			}
		});
		
		
		JPanel panel_1 = new JPanel();
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Vous avez séléctionné la carte : ");
				System.exit(0);

			}
		});
		panel_1.setBackground(Color.GRAY);
		panel_1.setBounds(204, 75, 180, 180);
		layeredPane.add(panel_1);
		panel_1.setLayout(null);
		panel_2.setBackground(Color.GRAY);
		panel_2.setBounds(396, 75, 180, 180);
		layeredPane.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				JOptionPane.showMessageDialog(null, "Vous avez séléctionné la carte : ");
				System.exit(0);
			}
		});
		panel_3.setBackground(Color.GRAY);
		panel_3.setBounds(588, 75, 180, 180);
		layeredPane.add(panel_3);
		panel_3.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				JOptionPane.showMessageDialog(null, "Vous avez séléctionné la carte : ");
				System.exit(0);
			}
		});
		panel_4.setBackground(Color.GRAY);
		panel_4.setBounds(781, 75, 180, 180);
		layeredPane.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblSelectionnerLaCarte = new JLabel("Selectionner la carte que vous voulez jouer ?");
		lblSelectionnerLaCarte.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSelectionnerLaCarte.setBounds(46, 29, 338, 16);
		layeredPane.add(lblSelectionnerLaCarte);

		
	}
}
