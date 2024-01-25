package View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

import javax.swing.*;

import Model.Dice;
import Model.Player;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Model.Dice;
import Model.Player;
import Model.Game; // Make sure to import your Game class

public class PlayerTurn {

    private JFrame frame;
    private Dice dice;
    private Map<Player, Integer> playerRolls;
    private List<Player> players;
    private int currentPlayerIndex;
    private String difficultyLevel;

    public PlayerTurn(int numberOfPlayers, String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
        dice = new Dice();
        playerRolls = new LinkedHashMap<>();
        players = new ArrayList<>();
        for (int i = 1; i <= numberOfPlayers; i++) {
            players.add(new Player("Player " + i));
        }
        currentPlayerIndex = 0;
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        JButton rollButton = new JButton("Roll Dice");
        frame.getContentPane().add(rollButton);

        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Player currentPlayer = players.get(currentPlayerIndex);
                int rollResult = dice.roll();
                playerRolls.put(currentPlayer, rollResult);

                JOptionPane.showMessageDialog(frame, currentPlayer.getName() + " rolled a " + rollResult);

                currentPlayerIndex++;
                if (currentPlayerIndex >= players.size()) {
                    rollButton.setEnabled(false); // Disable the button after all players have rolled
                    displayTurnOrder();
                } else {
                    JOptionPane.showMessageDialog(frame, players.get(currentPlayerIndex).getName() + " has to roll the dice now");
                }
            }
        });

        frame.setVisible(true);
    }

    private void displayTurnOrder() {
        players.sort(Comparator.comparing(playerRolls::get).reversed());

        StringBuilder turnOrderMessage = new StringBuilder("Turn order:\n");
        for (int i = 0; i < players.size(); i++) {
            turnOrderMessage.append(i + 1).append(". ").append(players.get(i).getName()).append("\n");
        }

        JOptionPane.showMessageDialog(frame, turnOrderMessage.toString());
        startNewGame();
    }

    private void startNewGame() {
        Queue<Player> sortedPlayers = new ArrayDeque<>(players);
        Game newGame = new Game( difficultyLevel, sortedPlayers, dice);
        /*newGame.startGame(); // You'll need to implement this method in your Game class*/
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PlayerTurn window = new PlayerTurn(4, "Easy"); // Example difficulty level
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
