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

import Model.PlayerButtonListener;

public class DataReception extends JFrame {

    private static final long serialVersionUID = 1L;
    public JPanel contentPane;
    private  JButton btnNewButton;
    private int numberOfPlayers;
    private String difficultyLevel;
 // Flags to track whether a difficulty level or number of players has been selected
    private boolean difficultySelected = false;
    private boolean playersSelected = false;
    private  JButton easyButton;
    private  JButton mediumButton;
    private  JButton hardButton;
    private  JButton player2Button;
    private  JButton player3Button;
    private  JButton player4Button;


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

        easyButton = new JButton("Easy");
        easyButton.setBounds(59, 104, 85, 21);
        easyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                difficultyLevel = "Easy";
                difficultySelected = true;
                disableOtherDifficultyButtons();
            }
        });
        panel.add(easyButton);

        mediumButton = new JButton("Medium");
        mediumButton.setBounds(191, 104, 85, 21);
        panel.add(mediumButton);
        mediumButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                difficultyLevel = "Medium";
                difficultySelected = true;
                disableOtherDifficultyButtons();
            }
        });

        hardButton = new JButton("Hard");
        hardButton.setBounds(334, 104, 85, 21);
        panel.add(hardButton);
        hardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                difficultyLevel = "Hard";
                difficultySelected = true;
                disableOtherDifficultyButtons();
            }
        });

        // ImageIcon player2Icon = new ImageIcon("src/images/1playerIcon.png");
        // ImageIcon player3Icon = new ImageIcon("src/images/2playerIcon.png");
        // ImageIcon player4Icon = new ImageIcon("src/images/3playerIcon.png");
        player2Button = new JButton("2 players");
        player2Button.setBounds(59, 199, 85, 21);
        player2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numberOfPlayers = 2;
                playersSelected = true;
                disableOtherPlayerButtons();
            }
        });
        player3Button = new JButton("3 players");
        player3Button.setBounds(191, 199, 85, 21);
        player3Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numberOfPlayers = 3;
                playersSelected = true;
                disableOtherPlayerButtons();
            }
        });
        player4Button = new JButton("4 players");
        player4Button.setBounds(334, 199, 85, 21);
        player4Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numberOfPlayers = 4;
                playersSelected = true;
                disableOtherPlayerButtons();
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
        		if (difficultySelected && playersSelected) {
                    // Both difficulty and players are selected, proceed to the next screen
                    new PlayersNacknames(numberOfPlayers, difficultyLevel).setVisible(true);
                    DataReception.this.setVisible(false);
                } else {
                    // Display an error message or handle the case where both are not selected
                }
        	}
        });
        btnNewButton_1.setBounds(420, 264, 85, 21);
        panel.add(btnNewButton_1);
  

    }
 // Disable other difficulty buttons when one is selected
    private void disableOtherDifficultyButtons() {
        if (this.difficultyLevel.equals("Easy")) {
            this.mediumButton.setEnabled(false);
            this.hardButton.setEnabled(false);
        } else if (difficultyLevel.equals("Medium")) {
            this.easyButton.setEnabled(false);
            this.hardButton.setEnabled(false);
        } else if (difficultyLevel.equals("Hard")) {
            this.easyButton.setEnabled(false);
            this.mediumButton.setEnabled(false);
        }
    }

    // Disable other player buttons when one is selected
    private void disableOtherPlayerButtons() {
        if (numberOfPlayers == 2) {
            this.player3Button.setEnabled(false);
            this.player4Button.setEnabled(false);
        } else if (numberOfPlayers == 3) {
            this.player2Button.setEnabled(false);
            this.player4Button.setEnabled(false);
        } else if (numberOfPlayers == 4) {
            this.player2Button.setEnabled(false);
            this.player3Button.setEnabled(false);
        }
    }

}
