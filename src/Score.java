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
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JLayeredPane;
import java.awt.ScrollPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
public class Score extends JFrame implements Runnable {

	protected JFrame frame;
	protected JLabel picLabel = new JLabel(new ImageIcon("geant/b.gif"));
	private JLayeredPane layeredPane;
	private JLabel picLabel3 = new JLabel(new ImageIcon("geant/c.gif"));
	private JLabel picLabel4 = new JLabel(new ImageIcon("geant/d.gif"));
	private JTextField textField;
	protected Button button2;
	private Choice choice;
	private Thread t;
	private JLabel lblNewLabel;
	private Model model = Model.getInstance();
	
	
	
	/**
	 * Launch the application.
	 */
	public Score(int a)
	{


		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Score window = new Score();
					Thread thread = new Thread(window);
					thread.start();
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
	
	private Score() {
		
		layeredPane = new JLayeredPane();
		getContentPane().add(layeredPane, BorderLayout.CENTER);
		
		lblNewLabel = new JLabel("<html><table> <tr> <td>Village : <br><ul><li>Nombre de menhirs</li><li>Nombre de graines</li></ul></td></tr><tr><td>Village : <br><ul><li>Nombre de menhirs</li><li>Nombre de graines</li></ul></td></tr></table></html> ");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(0, 0, 400, 90*(model.getNombreJoueurs()+1));
		layeredPane.add(lblNewLabel);
		initialize();
		model = Model.getInstance();
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.add(layeredPane);
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
		frame.setUndecorated(true);
		frame.setBackground(new Color(1.0f,1.0f,1.0f,0));
		 Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		    int x = (int) dimension.getWidth();
		  
		frame.setBounds(x-190,0 ,180, 110*(model.getNombreJoueurs()+1));
		frame.setFocusableWindowState(false);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	
		
		
		
	}
	


	public void actionPerformed(ActionEvent e) {
		
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			String points = "<html><table>";
			for(int i = 0 ;i<model.getListeJoueur().size();i++)
			{
				points = points + model.getListeJoueur().get(i).toString();
			
			}
			
			if(lblNewLabel.getText().equals(points))
			{
				try {
					Thread.sleep(250);
					points = "";
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else
			{
				
				lblNewLabel.setText(points);
			}
		}
		
	}
}


		
	