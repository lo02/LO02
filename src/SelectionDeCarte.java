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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		frame.setBounds(x-(1024/2), y-(542/2) ,1024, 542);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(12, 90, 982, 335);
		frame.getContentPane().add(layeredPane);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 75, 180, 180);
		layeredPane.add(panel);
		panel.setLayout(null);
		
		JRadioButton radioButton = new JRadioButton("1");
		radioButton.setBounds(66, 146, 41, 25);
		panel.add(radioButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(204, 75, 180, 180);
		layeredPane.add(panel_1);
		panel_1.setLayout(null);
		
		JRadioButton radioButton_1 = new JRadioButton("2");
		radioButton_1.setBounds(76, 146, 41, 25);
		panel_1.add(radioButton_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(396, 75, 180, 180);
		layeredPane.add(panel_2);
		panel_2.setLayout(null);
		
		JRadioButton radioButton_2 = new JRadioButton("3");
		radioButton_2.setBounds(73, 146, 41, 25);
		panel_2.add(radioButton_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(588, 75, 180, 180);
		layeredPane.add(panel_3);
		panel_3.setLayout(null);
		
		JRadioButton radioButton_3 = new JRadioButton("4");
		radioButton_3.setBounds(75, 146, 41, 25);
		panel_3.add(radioButton_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(781, 75, 180, 180);
		layeredPane.add(panel_4);
		panel_4.setLayout(null);
		
		JRadioButton radioButton_4 = new JRadioButton("5");
		radioButton_4.setBounds(72, 146, 41, 25);
		panel_4.add(radioButton_4);
		ButtonGroup bg = new ButtonGroup();
		bg.add(radioButton);
		bg.add(radioButton_1);
		bg.add(radioButton_2);
		bg.add(radioButton_3);
		bg.add(radioButton_4);
		
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Vous avez séléctionné la carte : ");
			}
		});
		btnNewButton.setBounds(395, 282, 97, 25);
		layeredPane.add(btnNewButton);
		
	}
}
