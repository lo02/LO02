import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.List;
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
import static java.awt.GraphicsDevice.WindowTranslucency.*;

import java.awt.BorderLayout;

import javax.swing.border.BevelBorder;
import java.awt.Panel;

public class SelectionDeCarte {

	private JFrame frmVotreMain;
	private JLabel picLabel = new JLabel(new ImageIcon("img/transparent.png"));
	private Model model = Model.getInstance();
	

	/**
	 * Launch the application.
	 */
	public  SelectionDeCarte(int a) {
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
	private SelectionDeCarte() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		
		frmVotreMain = new JFrame();
		frmVotreMain.setTitle("Votre main");
		frmVotreMain.setUndecorated(true);
		frmVotreMain.setBackground(new Color(1.0f,1.0f,1.0f,0));
		
		 Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		    int x = (int) ((dimension.getWidth() - frmVotreMain.getWidth()) / 2);
		    int y = (int) ((dimension.getHeight() - frmVotreMain.getHeight()) / 2);
		    if (model.getPartieRapide()==1)
		    {
		    	frmVotreMain.setBounds(x-(738/2), y ,738, 400);
		    }
		    else
		    {
		    	frmVotreMain.setBounds(x-(938/2), y ,938, 400);
		    }
		
		frmVotreMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVotreMain.getContentPane().setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBackground(new Color(0, 0,0,0));
		layeredPane.setBounds(11, 2, 911, 335);
		
		frmVotreMain.getContentPane().add(layeredPane);
		

		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		layeredPane_1.setForeground(new Color(0, 0, 102));
		layeredPane_1.setBackground(new Color(0, 0, 102));
		layeredPane_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				Model model = Model.getInstance();
				model.setCarteChoisie(0);
				frmVotreMain.setVisible(false);
				frmVotreMain.dispose();
				
			}
		});
		layeredPane_1.setBounds(10, 75, 154, 260);
		layeredPane.add(layeredPane_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 136, 136);
		layeredPane_1.add(panel);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(10, 158, 137, 91);
		layeredPane_1.add(panel_5);
		
		JLabel lblRayonDeLune = new JLabel(model.getMain().get(0).toString2());
		panel_5.add(lblRayonDeLune);
		
		if (model.getPartieRapide()==2)
		{
		JLayeredPane layeredPane_5 = new JLayeredPane();
		layeredPane_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("carte 1");
			}
		});
		layeredPane_5.setBounds(722, 75, 154, 236);
		layeredPane.add(layeredPane_5);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 11, 136, 136);
		layeredPane_5.add(panel_4);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBounds(10, 158, 137, 67);
		layeredPane_5.add(panel_9);
		}
		
		if(model.getMain().size()>=2)
		{
		JLayeredPane layeredPane_2 = new JLayeredPane();
		layeredPane_2.setForeground(new Color(0, 0, 102));
		layeredPane_2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		layeredPane_2.setBackground(new Color(0, 0, 102));
		layeredPane_2.setBounds(174, 75, 154, 260);
		layeredPane_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				Model model = Model.getInstance();
				model.setCarteChoisie(1);
				frmVotreMain.setVisible(false);
				frmVotreMain.dispose();
				
			}
		});
		layeredPane.add(layeredPane_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 136, 136);
		layeredPane_2.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 158, 137, 91);
		layeredPane_2.add(panel_2);
		
		JLabel label = new JLabel(model.getMain().get(1).toString2());
				panel_2.add(label);
		}
		
		if(model.getMain().size()>=3)
		{
		JLayeredPane layeredPane_3 = new JLayeredPane();
		layeredPane_3.setForeground(new Color(0, 0, 102));
		layeredPane_3.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		layeredPane_3.setBackground(new Color(0, 0, 102));
		layeredPane_3.setBounds(338, 75, 154, 260);
		layeredPane_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				Model model = Model.getInstance();
				model.setCarteChoisie(2);
				frmVotreMain.setVisible(false);
				frmVotreMain.dispose();
				
			}
		});
		layeredPane.add(layeredPane_3);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 11, 136, 136);
		layeredPane_3.add(panel_3);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(10, 158, 137, 91);
		layeredPane_3.add(panel_6);
		
		JLabel label_1 = new JLabel(model.getMain().get(2).toString2());
		panel_6.add(label_1);
		}
		
		
		if(model.getMain().size()>=4)
		{
		JLayeredPane layeredPane_4 = new JLayeredPane();
		layeredPane_4.setForeground(new Color(0, 0, 102));
		layeredPane_4.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		layeredPane_4.setBackground(new Color(0, 0, 102));
		layeredPane_4.setBounds(502, 75, 154, 260);
		layeredPane_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				Model model = Model.getInstance();
				model.setCarteChoisie(3);
				frmVotreMain.setVisible(false);
				frmVotreMain.dispose();
				
			}
		});
		layeredPane.add(layeredPane_4);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(10, 11, 136, 136);
		layeredPane_4.add(panel_7);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBounds(10, 158, 137, 91);
		layeredPane_4.add(panel_8);
		
		JLabel label_2 = new JLabel(model.getMain().get(3).toString2());
		panel_8.add(label_2);
		}
		
	}
}
