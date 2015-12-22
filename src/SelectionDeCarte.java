import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectionDeCarte {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectionDeCarte window = new SelectionDeCarte();
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
	public SelectionDeCarte() {
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
		frame.setBounds(x-(1024/2), y-(542/2) ,1013, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(11, 2, 982, 335);
		frame.getContentPane().add(layeredPane);
		
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
		
		JPanel panel_2 = new JPanel();
		panel_2.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				JOptionPane.showMessageDialog(null, "Vous avez séléctionné la carte : ");
				System.exit(0);
			}
		});
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

		
	}
}
