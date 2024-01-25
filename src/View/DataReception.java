package View;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.DifficultyLevel;
import Model.PlayerButtonListener;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class DataReception extends JFrame {
	public DataReception() {
	}


	public static void createAndShowGUI() {
	    // Create the JFrame
	    JFrame frame = new JFrame("Snake and Ladder");

	    // Set default close operation
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    // Create a panel to hold the components
	    JPanel panel = new JPanel();
	    panel.setLayout(new GridLayout(7, 1));

	    // Create title label
	    JLabel titleLabel = new JLabel("Snake and Ladder");
	    titleLabel.setHorizontalAlignment(JLabel.CENTER);

	    // Create icons (replace with actual image files)
	    ImageIcon snakeIcon = new ImageIcon("src/images/snake.png");
	    ImageIcon player2Icon = new ImageIcon("src/images/1playerIcon.png");
	    ImageIcon player3Icon = new ImageIcon("src/images/2playerIcon.png");
	    ImageIcon player4Icon = new ImageIcon("src/images/3playerIcon.png");

	    // Create buttons
	    JButton easyButton = new JButton("Easy");
	    JButton mediumButton = new JButton("Medium");
	    JButton hardButton = new JButton("Hard");

	    // Add action listeners to buttons
	    easyButton.addActionListener(new DifficultyLevel("Easy"));
	    mediumButton.addActionListener(new DifficultyLevel("Medium"));
	    hardButton.addActionListener(new DifficultyLevel("Hard"));

	    // Create buttons for player choices
	    JButton player2Button = new JButton(player2Icon);
	    JButton player3Button = new JButton(player3Icon);
	    JButton player4Button = new JButton(player4Icon);

	    // Add action listeners to player buttons
	    player2Button.addActionListener(new PlayerButtonListener(2));
	    player3Button.addActionListener(new PlayerButtonListener(3));
	    player4Button.addActionListener(new PlayerButtonListener(4));

	    // Add components to the panel
	    panel.add(titleLabel);
	    panel.add(new JLabel(snakeIcon)); // Snake icon
	    panel.add(easyButton);
	    panel.add(mediumButton);
	    panel.add(hardButton);
	    panel.add(player2Button);
	    panel.add(player3Button);
	    panel.add(player4Button);

	    // Add panel to the frame
	    frame.getContentPane().add(panel);

	    // Set frame properties
	    frame.setSize(400, 600);
	    frame.setLocationRelativeTo(null); // Center the frame
	    frame.setVisible(true);
	}


}
