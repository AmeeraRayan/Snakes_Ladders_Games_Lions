package View;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Model.Dice;
import Model.Player;
import Model.Game; // Make sure to import your Game class

public class PlayerTurn extends JFrame{

    private Dice dice;
    private Map<Player, Integer> playerRolls;
    private List<Player> players;
    private int currentPlayerIndex;
    private String difficultyLevel;
    private static final long serialVersionUID = 1L;
	private JPanel contentPane;

    public PlayerTurn(int numberOfPlayers, String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
        dice = new Dice();
        playerRolls = new LinkedHashMap<>();
        players = new ArrayList<>();
        for (int i = 1; i <= numberOfPlayers; i++) {
            players.add(new Player("Player " + i));
        }
        currentPlayerIndex = 0;
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 997, 633);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton DiceButton = new JButton("");
		DiceButton.setIcon(new ImageIcon(PlayerTurn.class.getResource("/images/dice 4.jpg")));		

        DiceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int rollResult = dice.rollForTurn();
				String path = "/images/dice "+rollResult+".jpg";
				DiceButton.setIcon(new ImageIcon(PlayerTurn.class.getResource(path)));
				 Player currentPlayer = players.get(currentPlayerIndex);
	                
	                playerRolls.put(currentPlayer, rollResult);

	                JOptionPane.showMessageDialog(contentPane, currentPlayer.getName() + " rolled a " + rollResult);

	                currentPlayerIndex++;
	                if (currentPlayerIndex >= players.size()) {
	                	DiceButton.setEnabled(false); // Disable the button after all players have rolled
	                    displayTurnOrder();
	                } else {
	                    JOptionPane.showMessageDialog(contentPane, players.get(currentPlayerIndex).getName() + "'s turn to roll the dice");
	                }
	            }
	        });
        DiceButton.setBackground(SystemColor.controlLtHighlight);
		DiceButton.setForeground(SystemColor.activeCaptionBorder);
		DiceButton.setBounds(675, 269, 112, 110);
		contentPane.add(DiceButton);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(PlayerTurn.class.getResource("/images/Bounus 9.png")));
		lblNewLabel.setBounds(-253, -164, 1299, 813);
		contentPane.add(lblNewLabel);

        contentPane.setVisible(true);
    }

    private void displayTurnOrder() {
        players.sort(Comparator.comparing(playerRolls::get).reversed());

        StringBuilder turnOrderMessage = new StringBuilder("Turn order:\n");
        for (int i = 0; i < players.size(); i++) {
            turnOrderMessage.append(i + 1).append(". ").append(players.get(i).getName()).append("\n");
        }

        JOptionPane.showMessageDialog(contentPane, turnOrderMessage.toString());
        startNewGame();
    }

    private void startNewGame() {
        Queue<Player> sortedPlayers = new ArrayDeque<>(players);
        Game newGame = new Game(difficultyLevel, sortedPlayers, dice);
        // newGame.startGame(); // You'll need to implement this method in your Game class
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PlayerTurn window = new PlayerTurn(4, "Easy"); // Example with 4 players and 'Easy' difficulty
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
