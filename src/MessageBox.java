import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

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
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.plaf.PanelUI;
import javax.swing.JLayeredPane;
import java.awt.ScrollPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;

public class MessageBox extends JFrame implements Runnable{


	private JFrame frame2;
	private JLabel label;
	private JScrollPane scrollPane;
	private JScrollBar barre;
	private Model mod = Model.getInstance();

	
	/**
	 * Launch the application.
	 */
	public MessageBox (int a)
	{
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MessageBox window = new MessageBox();
					Thread thread = new Thread(window);
					thread.start();
					window.frame2.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					
				}
				
			}
		});
		
		
	}
	

	/**
	 * Create the application.
	 */
	public MessageBox() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		 frame2 = new JFrame();
		 frame2.setUndecorated(true);
		frame2.setBackground(new Color(1.0f,1.0f,1.0f,0));
		JLayeredPane layeredPane = new JLayeredPane();

		 Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		    int x = (int) ((dimension.getWidth() - frame2.getWidth()));
		    int y = (int) ((dimension.getHeight() - frame2.getHeight())/1.45);
		frame2.setBounds(0, y ,335, 233);
		frame2.getContentPane().add(layeredPane, BorderLayout.CENTER);
		
	 scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 299, 172);
		scrollPane.getViewport().setBackground(Color.BLACK);
		layeredPane.add(scrollPane);
		
		label = new JLabel("");
		label.setBackground(Color.BLACK);
		scrollPane.setViewportView(label);
		
		JPanel panel_1 = new JPanel();
		
		
		
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			if(label.getText().equals(mod.getMessage()))
			{
				try {
					Thread.sleep(100);
					barre=scrollPane.getVerticalScrollBar();
	                barre.setValue(scrollPane.getMaximumSize().height+30);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			{
				label.setText(mod.getMessage());
				  barre=scrollPane.getVerticalScrollBar();
	                barre.setValue(scrollPane.getMaximumSize().height+30);
			}
		}
		
	}
	

		
}