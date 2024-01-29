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
import java.awt.Font;

public class PlayerTurn extends JFrame {

    private Dice dice;
    private Map<Player, Integer> playerRolls;
    private List<Player> players;
    private int currentPlayerIndex;
    private String difficultyLevel;
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel rollLabel;
    //bjbdjsbsj

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
        txtpnHi.setBackground(new java.awt.Color(255, 250, 250));
        
        txtpnHi.setBounds(42, 203, 201, 198);
        contentPane.add(txtpnHi);
        JTextArea txtrPlayer = new JTextArea();
        txtrPlayer.setForeground(new java.awt.Color(0, 0, 0));
        txtrPlayer.setBackground(new java.awt.Color(0, 100, 0));
        txtrPlayer.setBounds(45, 48, 317, 78);
        contentPane.add(txtrPlayer);
        txtrPlayer.setText("\n    Turn : " + players.get(currentPlayerIndex).getName());
        txtrPlayer.setFont(new Font("David", Font.BOLD, 30));
        txtrPlayer.setTabSize(20);
        txtrPlayer.setAlignmentX(0.2f);
        txtrPlayer.setAlignmentY(Component.TOP_ALIGNMENT);
        txtrPlayer.setBackground(new java.awt.Color(120, 180, 20)); // Green
        rollLabel = new JLabel(players.get(currentPlayerIndex).getName()+" Roll the dice!");
        rollLabel.setFont(new Font("Arial", Font.BOLD, 20));
        rollLabel.setBounds(350, 300, 400, 30);
        contentPane.add(rollLabel);
        displayRollLabel();

        diceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // Perform the dice roll for the current player
                rollLabel.setText("");
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
                currentPlayerIndex++;

                // Check if all players have rolled
                if (currentPlayerIndex >= players.size()) {
                    // Initialize the controller and check for ties
                    PreGameController controller = new PreGameController(dice, playerRolls, players, difficultyLevel);
                    if (controller.checkForTies()) {
                        // Get the list of tied players and re-roll for them
                        List<Player> tiedPlayers = controller.getTiedPlayers();
                        controller.reRollForTiedPlayers(tiedPlayers);

                        // Reset currentPlayerIndex and update UI for re-rolls
                        currentPlayerIndex = 0;
                        Player firstTiedPlayer = tiedPlayers.get(currentPlayerIndex);
                        txtrPlayer.setText("\n    Turn : " + firstTiedPlayer.getName());
                        setPlayerBackgroundColor(color[currentPlayerIndex], txtrPlayer);


                        // Clear or update roll results display area
                        txtpnHi.setText("Tie detected! Players re-rolling:\n");

                        // Enable the dice button for re-rolls
                        diceButton.setEnabled(true);

                        // Update roll label for the first tied player
                        rollLabel.setText(firstTiedPlayer.getName() + " Roll the dice!");
                        displayRollLabel();
                    } else {
                        // Handle the continuation of the game in case of no ties
                        StringBuilder turnOrderMessage = controller.displayTurnOrder();
                        currentPlayerIndex = 0;
                        ResultPage(players.size(), controller.players);
                        controller.startNewGame();
                    }
                } else {
                    // Set up the game for the next player's turn
                    rollLabel.setText(players.get(currentPlayerIndex).getName() + " Roll the dice!");
                    txtrPlayer.setText("\n    Turn : " + players.get(currentPlayerIndex).getName());
                    setPlayerBackgroundColor(color[currentPlayerIndex], txtrPlayer);
                    displayRollLabel();
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
        lblNewLabel.setBackground(new java.awt.Color(0, 100, 0));
        lblNewLabel.setIcon(new ImageIcon(PlayerTurn.class.getResource("/images/BounusGame.png")));
        lblNewLabel.setBounds(-277, -11, 1340, 709);
        contentPane.add(lblNewLabel);
        
        JLabel lblNewLabel_2 = new JLabel("New label");
        lblNewLabel_2.setBounds(131, 127, 45, 13);
        contentPane.add(lblNewLabel_2);
        contentPane.setVisible(true);
    }
        
   
        private void displayRollsInTextPane(JTextPane textPane, Map<Player, Integer> rolls) {//display the names of the player and what he got by rolling
            StyledDocument doc = textPane.getStyledDocument();
            
            for (Map.Entry<Player, Integer> entry : rolls.entrySet()) {
                Player player = entry.getKey();
                int rollResult = entry.getValue();

                String message = player.getName() + " --- " + rollResult + "\n";
                AttributeSet attributeSet = null; 

                try {
                    doc.insertString(doc.getLength(), message, attributeSet);
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
            }
        }
        private void setPlayerBackgroundColor(Color color , JTextArea txtrPlayer) {//change the jtext background - by the player color
                switch (color.toString()) {
                case "BLUE":
                    txtrPlayer.setBackground(new java.awt.Color(0, 200, 220)); // Blue
                    break;
                case "GREEN": 
                    txtrPlayer.setBackground(new java.awt.Color(0, 120, 30)); // Green
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
        private void displayRollLabel() {
            rollLabel.setVisible(true);
        }
    
        
        private void ResultPage(int numPlayer,List<Player> playersSortedByOrder) {
        	if(numPlayer == 2) {
        		new BounusResults2(playersSortedByOrder).setVisible(true);
                PlayerTurn.this.setVisible(false);
        	}
        	if(numPlayer == 3){
        		new BounusResults3(playersSortedByOrder).setVisible(true);
                PlayerTurn.this.setVisible(false);
        	}
        	if(numPlayer == 4) {
        		new BounusResults4(playersSortedByOrder).setVisible(true);
                PlayerTurn.this.setVisible(false);
        	}
        }
}
