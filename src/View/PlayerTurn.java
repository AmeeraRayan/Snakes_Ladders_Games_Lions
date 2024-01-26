package View;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Controller.PreGameController;
import Model.Color;
import Model.Dice;
import Model.Player;
import Model.Game; // Make sure to import your Game class

public class PlayerTurn extends JFrame {

    private Dice dice;
    private Map<Player, Integer> playerRolls;
    private List<Player> players;
    private int currentPlayerIndex;
    private String difficultyLevel;
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    

    public PlayerTurn(int numberOfPlayers, String difficultyLevel, String[] namesOfPlayers , Color[] color) {
    	super("So what is my turn?");
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

        diceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int rollResult = dice.rollForTurn();
                String path = "/images/dice " + rollResult + ".jpg";
                diceButton.setIcon(new ImageIcon(PlayerTurn.class.getResource(path)));
                Player currentPlayer = players.get(currentPlayerIndex);

                playerRolls.put(currentPlayer, rollResult);

                JOptionPane.showMessageDialog(contentPane, currentPlayer.getName() + " rolled a " + rollResult);

                currentPlayerIndex++;
                if (currentPlayerIndex >= players.size()) {
                    diceButton.setEnabled(false); // Disable the button after all players have rolled
                    PreGameController controller=new PreGameController(dice, playerRolls, players, difficultyLevel) ;
                    StringBuilder turnOrderMessage= controller.displayTurnOrder();
                    JOptionPane.showMessageDialog(contentPane, turnOrderMessage.toString());
                    controller.startNewGame();
                } else {
                    JOptionPane.showMessageDialog(contentPane,
                            players.get(currentPlayerIndex).getName() + "'s turn to roll the dice");
                }
            }
        });
        diceButton.setBackground(SystemColor.controlLtHighlight);
        diceButton.setForeground(java.awt.Color.WHITE);
        diceButton.setBounds(724, 259, 118, 102);
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
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(PlayerTurn.class.getResource("/images/Bounus .png")));
        lblNewLabel.setBounds(-345, -10, 1340, 620);
        contentPane.add(lblNewLabel);
        
        contentPane.setVisible(true);
    }
}
