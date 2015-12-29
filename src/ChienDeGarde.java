import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import java.awt.BorderLayout;
import java.awt.Label;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChienDeGarde {

	private JFrame frame;
	private Model model = Model.getInstance();
	private String message;
	/**
	 * Launch the application.
	 */
	public ChienDeGarde(String arg) {
	
	
		
		ChienDeGarde window = new ChienDeGarde();
		window.frame.setVisible(true);
				
	
	}

	/**
	 * Create the application.
	 */
	private ChienDeGarde() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 432, 183);
		 new Thread() {
	            public void run() {
	            	
	            	while(!(model.isFinished()))
	            	{
	            		try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	            	}
	            	frame.setVisible(false);
	            	frame.dispose();
	            }
		 }.start();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLayeredPane layeredPane = new JLayeredPane();
		frame.getContentPane().add(layeredPane, BorderLayout.CENTER);
		
		JLabel label = new JLabel(model.getMessage2());
		label.setBounds(10, 10, 396, 81);
		layeredPane.add(label);
		
		JButton btnNewButton = new JButton("Oui");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				frame.dispose();
				model.setChienDeGardeAction(1);
			}
		});
		btnNewButton.setBounds(10, 110, 89, 23);
		layeredPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Non");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
				model.setChienDeGardeAction(0);
				
			}
		});
		btnNewButton_1.setBounds(317, 110, 89, 23);
		layeredPane.add(btnNewButton_1);
	}
}
