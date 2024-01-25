package View;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.DifficultyLevel;
import Model.PlayerButtonListener;

public class DataReception extends JFrame {

    private static final long serialVersionUID = 1L;
    public JPanel contentPane;
    private  JButton btnNewButton;
    private int numberOfPlayers;
    private String difficultyLevel;
    /**
     * Create the frame.
     */
    public DataReception() {
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
        lblNewLabel.setBounds(208, 35, 143, 35);
        panel.add(lblNewLabel);

        JButton easyButton = new JButton("Easy");
        easyButton.setBounds(59, 104, 85, 21);
        easyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                difficultyLevel = "Easy";
            }
        });
        panel.add(easyButton);

        JButton mediumButton = new JButton("Medium");
        mediumButton.setBounds(191, 104, 85, 21);
        panel.add(mediumButton);
        mediumButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                difficultyLevel = "Medium";
            }
        });

        JButton hardButton = new JButton("Hard");
        hardButton.setBounds(334, 104, 85, 21);
        panel.add(hardButton);
        hardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                difficultyLevel = "Hard";
            }
        });

        // ImageIcon player2Icon = new ImageIcon("src/images/1playerIcon.png");
        // ImageIcon player3Icon = new ImageIcon("src/images/2playerIcon.png");
        // ImageIcon player4Icon = new ImageIcon("src/images/3playerIcon.png");
        JButton player2Button = new JButton("2 players");
        player2Button.setBounds(59, 199, 85, 21);
        player2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numberOfPlayers = 2;
            }
        });
        JButton player3Button = new JButton("3 players");
        player3Button.setBounds(191, 199, 85, 21);
        player3Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numberOfPlayers = 3;
            }
        });
        JButton player4Button = new JButton("4 players");
        player4Button.setBounds(334, 199, 85, 21);
        player4Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numberOfPlayers = 4;
            }
        });

        // Add action listeners to player buttons
        player2Button.addActionListener(new PlayerButtonListener(2));
        player3Button.addActionListener(new PlayerButtonListener(3));
        player4Button.addActionListener(new PlayerButtonListener(4));
        panel.add(player2Button);
        panel.add(player3Button);
        panel.add(player4Button);
        
         btnNewButton = new JButton("Back");
         btnNewButton.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
            	 DataReception.this.setVisible(false);
 				new MainScreen().setVisible(true);
             }
         });
        btnNewButton.setBounds(10, 264, 85, 21);
        panel.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Next");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {

                // Set the visibility of the new screen
                new PlayersNacknames(numberOfPlayers, difficultyLevel).setVisible(true);

                // Hide the current screen if needed
                DataReception.this.setVisible(false);
        	}
        });
        btnNewButton_1.setBounds(420, 264, 85, 21);
        panel.add(btnNewButton_1);
  


        // Add action listeners to buttons
        easyButton.addActionListener(new DifficultyLevel("Easy"));
        mediumButton.addActionListener(new DifficultyLevel("Medium"));
        hardButton.addActionListener(new DifficultyLevel("Hard"));
    }

}
