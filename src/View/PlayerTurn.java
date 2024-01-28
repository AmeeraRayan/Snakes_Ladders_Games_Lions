package View;

import java.awt.Component;
import java.awt.EventQueue;

import java.awt.SystemColor;
import java.awt.TextComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyledDocument;

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
        JTextPane txtpnHi = new JTextPane();
        txtpnHi.setFont(new Font("David", Font.BOLD | Font.ITALIC, 27));
        txtpnHi.setForeground(new java.awt.Color(0, 0, 0));
        txtpnHi.setBackground(new java.awt.Color(153, 255, 153));
        
        txtpnHi.setBounds(42, 203, 201, 198);
        contentPane.add(txtpnHi);
        JTextArea txtrPlayer = new JTextArea();
        txtrPlayer.setForeground(new java.awt.Color(0, 0, 0));
        
        txtrPlayer.setBackground(new java.awt.Color(102, 255, 153));
        txtrPlayer.setBounds(45, 48, 317, 78);
        contentPane.add(txtrPlayer);
        txtrPlayer.setText("\n    Turn : " + players.get(currentPlayerIndex).getName());
        txtrPlayer.setFont(new Font("David", Font.BOLD, 30));
        txtrPlayer.setTabSize(20);
        txtrPlayer.setAlignmentX(0.2f);
        txtrPlayer.setAlignmentY(Component.TOP_ALIGNMENT);
        txtrPlayer.setBackground(new java.awt.Color(0, 255, 0)); // Green

        diceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	
             
                int rollResult = dice.rollForTurn();
                String path = "/images/dice " + rollResult + ".jpg";
                diceButton.setIcon(new ImageIcon(PlayerTurn.class.getResource(path)));
                Player currentPlayer = players.get(currentPlayerIndex);
                playerRolls.put(currentPlayer, rollResult);
                JLabel message = new JLabel();
                contentPane.add(message);
                txtpnHi.setText("");
                displayRollsInTextPane(txtpnHi, playerRolls);
                txtpnHi.setFont(new Font("Yu Gothic Light", Font.BOLD | Font.ITALIC, 14));

             
                	
                
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
                           txtrPlayer.setText( "\n    Turn : "+players.get(currentPlayerIndex).getName());
                           setPlayerBackgroundColor(color[currentPlayerIndex] , txtrPlayer);
                         //  JOptionPane.showMessageDialog(contentPane,currentPlayerIndex );
                }
            }
        });
        
       
        
      
        diceButton.setBackground(SystemColor.controlLtHighlight);
        diceButton.setForeground(java.awt.Color.WHITE);
        diceButton.setBounds(761, 292, 120, 100);
        contentPane.add(diceButton);
        
        JButton btnNewButton = new JButton("Back");
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
        btnNewButton.setBounds(42, 519, 120, 36);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            PlayerTurn.this.setVisible(false);
				new DataReception().setVisible(true);
            }
        });
        
        contentPane.add(btnNewButton);
        
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(PlayerTurn.class.getResource("/images/BounusGame.png")));
        lblNewLabel.setBounds(-277, -11, 1340, 709);
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
        private void displayRollsInTextPane(JTextPane textPane, Map<Player, Integer> rolls) {//display the names of the player and what he got by rolling
            StyledDocument doc = textPane.getStyledDocument();
            
            for (Map.Entry<Player, Integer> entry : rolls.entrySet()) {
                Player player = entry.getKey();
                int rollResult = entry.getValue();

                String message = player.getName() + " --- " + rollResult + "\n";
                AttributeSet attributeSet = null;  // You can set specific styling here if needed

                try {
                    doc.insertString(doc.getLength(), message, attributeSet);
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
            }
        }
        private void setPlayerBackgroundColor(Color color , JTextArea txtrPlayer) {//change the jtext background - by the player color
        	    System.out.println(color.toString());
                switch (color.toString()) {
                case "BLUE":
                    txtrPlayer.setBackground(new java.awt.Color(204, 255, 255)); // Blue
                    break;
                case "GREEN": 
                    txtrPlayer.setBackground(new java.awt.Color(0, 204, 102)); // Green
                    break;
                case "RED":
                    txtrPlayer.setBackground(new java.awt.Color(255, 102, 102)); // Red
                    break;
                case "YELLOW":
                    txtrPlayer.setBackground(new java.awt.Color(255, 255, 153)); // Yellow
                    break;
                default:
                    // Default color for other players
                    txtrPlayer.setBackground(new java.awt.Color(192, 192, 192));
                    break;
                } }
}
