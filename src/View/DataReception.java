package View;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;


public class DataReception extends JFrame {

    private static final long serialVersionUID = 1L;
    public JPanel contentPane;
    private  JButton btnNewButton;
    private int numberOfPlayers;
    private String difficultyLevel;
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
        setBounds(100, 100, 898, 545);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, -17, 898, 574);
        contentPane.add(panel);
        panel.setLayout(null);
        
        // Make the frame undecorated (no title bar, no minimize/maximize/close buttons)
        setUndecorated(true);

        easyButton = new JButton("Easy");
        easyButton.setBounds(173, 207, 96, 34);
        easyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                difficultyLevel = "Easy";
                difficultySelected = true;
                disableOtherDifficultyButtons();
            }
        });
        panel.add(easyButton);

        mediumButton = new JButton("Medium");
        mediumButton.setBounds(384, 207, 101, 34);
        panel.add(mediumButton);
        mediumButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                difficultyLevel = "Medium";
                difficultySelected = true;
                disableOtherDifficultyButtons();
            }
        });

        hardButton = new JButton("Hard");
        hardButton.setBounds(604, 207, 85, 34);
        panel.add(hardButton);
        hardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                difficultyLevel = "Hard";
                difficultySelected = true;
                disableOtherDifficultyButtons();
            }
        });

        player2Button = new JButton("2 players");
        player2Button.setBounds(173, 346, 96, 21);
        player2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numberOfPlayers = 2;
                playersSelected = true;
                disableOtherPlayerButtons();
            }
        });
        player3Button = new JButton("3 players");
        player3Button.setBounds(384, 346, 97, 21);
        player3Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numberOfPlayers = 3;
                playersSelected = true;
                disableOtherPlayerButtons();
            }
        });
        player4Button = new JButton("4 players");
        player4Button.setBounds(604, 345, 96, 23);
        player4Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numberOfPlayers = 4;
                playersSelected = true;
                disableOtherPlayerButtons();
            }
        });

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
        btnNewButton.setBounds(150, 486, 119, 34);
        panel.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Next");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {

                // Set the visibility of the new screen
        		if (difficultySelected && playersSelected) {
        			if(numberOfPlayers == 2 ) {
        				 new PlayerNicknames2(numberOfPlayers, difficultyLevel).setVisible(true);
                         DataReception.this.setVisible(false);
        			}
        			if(numberOfPlayers == 3 ) {
        				 new PlayerNicknames3(numberOfPlayers, difficultyLevel).setVisible(true);
                         DataReception.this.setVisible(false);
        			}
        			if(numberOfPlayers == 4) {
        				 new PlayersNicknames4(numberOfPlayers, difficultyLevel).setVisible(true);
                         DataReception.this.setVisible(false);
        			}
                  
                } else {
                    // Display an error message or handle the case where both are not selected
                }
        	}
        });
        btnNewButton_1.setBounds(618, 486, 119, 34);
        panel.add(btnNewButton_1);
        
         JLabel lblNewLabel = new JLabel("");
         lblNewLabel.setBounds(-27, -223, 1350, 1070);
         panel.add(lblNewLabel);
         lblNewLabel.setIcon(new ImageIcon(DataReception.class.getResource("/images/options (3).png")));
  

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
