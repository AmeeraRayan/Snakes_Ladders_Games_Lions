package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;

public class MainBonosFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainBonosFrame frame = new MainBonosFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainBonosFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 997, 633);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton DiceButton = new JButton("");
		DiceButton.setIcon(new ImageIcon(MainBonosFrame.class.getResource("/images/dice 4.jpg")));		

        DiceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int result = rollDice();
				String path = "/images/dice "+result+".jpg";
				DiceButton.setIcon(new ImageIcon(MainBonosFrame.class.getResource(path)));		
			}
		});
        DiceButton.setBackground(SystemColor.controlLtHighlight);
		DiceButton.setForeground(SystemColor.activeCaptionBorder);
		DiceButton.setBounds(675, 269, 112, 110);
		contentPane.add(DiceButton);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(MainBonosFrame.class.getResource("/images/Bounus 9.png")));
		lblNewLabel.setBounds(-253, -164, 1299, 813);
		contentPane.add(lblNewLabel);
	}
	  private int rollDice() {
	        // Generate a random number between 1 and 6
	        Random random = new Random();
	        int result = random.nextInt(6) + 1;
            return result;
	        
	    }

}
