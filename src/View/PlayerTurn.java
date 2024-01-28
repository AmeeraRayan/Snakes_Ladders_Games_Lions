package View;

import java.awt.EventQueue;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import Controller.PreGameController;
import Model.Color;
import Model.Dice;
import Model.Player;
import Model.Color;
import java.awt.Font;
import java.awt.Font; // Make sure to import your Game class

public class PlayerTurn extends JFrame {

    private Dice dice;
    private Map<Player, Integer> playerRolls;
    private List<Player> players;
    private int currentPlayerIndex;
    private String difficultyLevel;
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    

    public PlayerTurn(int numberOfPlayers, String difficultyLevel, String[] namesOfPlayers , Color[] color) {
        this.difficultyLevel = difficultyLevel;
        dice = new Dice();
        playerRolls = new LinkedHashMap<>();
        players = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(new Player(namesOfPlayers[i],color[i]));
        }
        currentPlayerIndex = 0;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 997, 633);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
     
        JButton diceButton = new JButton("");
        diceButton.setHorizontalAlignment(SwingConstants.LEADING);
        diceButton.setIcon(new ImageIcon(PlayerTurn.class.getResource("/images/dice 4.jpg")));
        
        JLabel lblNewLabel_1 = new JLabel("Message");
        lblNewLabel_1.setFont(new Font("Yu Gothic Light", Font.BOLD | Font.ITALIC, 40));
        lblNewLabel_1.setBounds(327, 190, 344, 77);
        contentPane.add(lblNewLabel_1);
        
        JTextPane txtpnHi = new JTextPane();
        
        txtpnHi.setBounds(46, 190, 205, 237);
        contentPane.add(txtpnHi);
        JTextArea txtrPlayer = new JTextArea();
        txtrPlayer.setBounds(46, 25, 249, 67);
        contentPane.add(txtrPlayer);
        txtrPlayer.setText(players.get(currentPlayerIndex).getName());
        txtrPlayer.setFont(new Font("Monospaced", Font.BOLD, 40));
        txtrPlayer.setTabSize(20);
        JOptionPane.showMessageDialog(contentPane,currentPlayerIndex);
        diceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	
             
                int rollResult = dice.rollForTurn();
                String path = "/images/dice " + rollResult + ".jpg";
                diceButton.setIcon(new ImageIcon(PlayerTurn.class.getResource(path)));
                Player currentPlayer = players.get(currentPlayerIndex);
                playerRolls.put(currentPlayer, rollResult);
                JLabel message = new JLabel();
                displayTimedMessage(message,players.get(currentPlayerIndex).getName(), 3000);
                contentPane.add(message);

             
                	txtpnHi.setText(namesOfPlayers.toString());
                
               // JOptionPane.showMessageDialog(contentPane, currentPlayer.getName() + " rolled a " + rollResult);
                currentPlayerIndex++;
              
                if (currentPlayerIndex >= players.size()) {
                    diceButton.setEnabled(false); // Disable the button after all players have rolled
                    PreGameController controller=new PreGameController(dice, playerRolls, players, difficultyLevel) ;
                    StringBuilder turnOrderMessage= controller.displayTurnOrder();
                    
                    currentPlayerIndex = 0 ;
                    controller.startNewGame();
                    
                } else {
//                    JOptionPane.showMessageDialog(contentPane,
//                            players.get(currentPlayerIndex).getName() + "'s turn to roll the dice");
                           txtrPlayer.setText(players.get(currentPlayerIndex).getName());
                         //  JOptionPane.showMessageDialog(contentPane,currentPlayerIndex );
                }
            }
        });
        
       
        
      
        diceButton.setBackground(SystemColor.controlLtHighlight);
        diceButton.setForeground(java.awt.Color.WHITE);
        diceButton.setBounds(700, 250, 120, 100);
        contentPane.add(diceButton);
        
        JButton btnNewButton = new JButton("Back");
        btnNewButton.setBounds(46, 527, 130, 43);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            PlayerTurn.this.setVisible(false);
				new DataReception().setVisible(true);
            }
        });
        
        contentPane.add(btnNewButton);
        JLabel lblWhatIsMy = new JLabel("So what is my turn?");
        lblWhatIsMy.setHorizontalAlignment(SwingConstants.CENTER);
        lblWhatIsMy.setFont(new Font("Monotype Corsiva", Font.ITALIC, 32));
        lblWhatIsMy.setForeground(new java.awt.Color(255, 255, 255));
        lblWhatIsMy.setBounds(200, 29, 400, 40);
        contentPane.add(lblWhatIsMy);
        
        JLabel lblNewLabel_11 = new JLabel("Roll me!!");
        lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_11.setForeground(new java.awt.Color(51, 0, 0));
        lblNewLabel_11.setFont(new Font("Tw Cen MT Condensed", Font.BOLD | Font.ITALIC, 20));
        lblNewLabel_11.setBounds(720, 210, 100, 30);
        contentPane.add(lblNewLabel_11);
        
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(PlayerTurn.class.getResource("/images/Bounus .png")));
        lblNewLabel.setBounds(-345, -10, 1340, 620);
        contentPane.add(lblNewLabel);
        
        JLabel lblNewLabel_2 = new JLabel("New label");
        lblNewLabel_2.setBounds(131, 127, 45, 13);
        contentPane.add(lblNewLabel_2);
        contentPane.setVisible(true);
    }
        
        private static void displayTimedMessage(JLabel label, String message, int duration) {
            label.setText(message); // Set the initial message

            // Create a Timer that will update the label after the specified duration
            Timer timer = new Timer(duration, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Disable the label (or perform any other action)
                    label.setEnabled(false);
                }
            });
    }
}
