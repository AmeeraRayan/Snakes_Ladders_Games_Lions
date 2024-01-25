package View;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.DifficultyLevel;
import Model.PlayerButtonListener;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class DataReception1 extends JFrame {

	private static final long serialVersionUID = 1L;
	public JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public DataReception1() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 549, 531);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 515, 474);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Snake and Leader");
		lblNewLabel.setBounds(216, 5, 82, 13);
		panel.add(lblNewLabel);
		
		JButton easyButton = new JButton("Easy");
		easyButton.setBounds(59, 104, 85, 21);
		panel.add(easyButton);
		
		JButton mediumButton = new JButton("Medium");
		mediumButton.setBounds(191, 104, 85, 21);
		panel.add(mediumButton);
		
		JButton hardButton = new JButton("Hard");
		hardButton.setBounds(334, 104, 85, 21);
		panel.add(hardButton);
		
		  ImageIcon player2Icon = new ImageIcon("src/images/1playerIcon.png");
		    ImageIcon player3Icon = new ImageIcon("src/images/2playerIcon.png");
		    ImageIcon player4Icon = new ImageIcon("src/images/3playerIcon.png");
		// Create buttons for player choices
	    JButton player2Button = new JButton(player2Icon);
	    player2Button.setBounds(59, 104, 85, 21);
	    JButton player3Button = new JButton(player3Icon);
	    player3Button.setBounds(191, 104, 85, 21);
	    JButton player4Button = new JButton(player4Icon);
	    player4Button.setBounds(334, 104, 85, 21);


	    // Add action listeners to player buttons
	    player2Button.addActionListener(new PlayerButtonListener(2));
	    player3Button.addActionListener(new PlayerButtonListener(3));
	    player4Button.addActionListener(new PlayerButtonListener(4));
	    panel.add(player2Button);
	    panel.add(player3Button);
	    panel.add(player4Button);
	    // Add action listeners to buttons
	    easyButton.addActionListener(new DifficultyLevel("Easy"));
	    mediumButton.addActionListener(new DifficultyLevel("Medium"));
	    hardButton.addActionListener(new DifficultyLevel("Hard"));
	}
}
