package View;

import javax.swing.*;

import Model.Board;
import Model.Dice;
import Model.Game;
import Model.Ladder;
import Model.Player;
import Model.Snake;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class EasyGame extends JFrame {
    private JLabel[][] boardLabels;
    private JLabel[] playerLabels;
    private JButton rollDiceButton;
    private JLabel currentPlayerLabel;
    private JLabel timerLabel;

    private Game Easygame;
    private Timer gameTimer;

    private int timeElapsed;


	public EasyGame(Game game) {
        this.Easygame = game;
        initializeUI();
        initializeTimer();
    }

    private void initializeUI() {
        setTitle("Snakes and Ladders Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize boardLabels
        int boardSize = Easygame.getBoard().getSize();
        boardLabels = new JLabel[boardSize][boardSize];
        JPanel boardPanel = new JPanel(new GridLayout(boardSize, boardSize));

        for (int i = boardSize - 1; i >= 0; i--) {
            for (int j = 0; j < boardSize; j++) {
                boardLabels[i][j] = new JLabel(Integer.toString(i * boardSize + j + 1), SwingConstants.CENTER);
                boardLabels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                assignColorToSquare(boardLabels[i][j]); // Assign color to each square
                boardPanel.add(boardLabels[i][j]);
            }
        }
        // Initialize playerLabels
        playerLabels = new JLabel[Easygame.getPlayers().size()];
        JPanel playersPanel = new JPanel(new FlowLayout());

        for (int i = 0; i < Easygame.getPlayers().size(); i++) {
            Player player = Easygame.getPlayers().get(i);
            playerLabels[i] = new JLabel(player.getName() + ": " + player.getPosition());
            playersPanel.add(playerLabels[i]);
        }

        // Initialize currentPlayerLabel
        currentPlayerLabel = new JLabel("Current Player: " + Easygame.getCurrentPlayer().getName());

        // Initialize timerLabel
        timerLabel = new JLabel("Time: " + timeElapsed + "s");

        // Initialize rollDiceButton
        rollDiceButton = new JButton("Roll Dice");
        rollDiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simulateRollDice();
            }
        });

        // Add components to the frame
        add(boardPanel, BorderLayout.CENTER);
        add(playersPanel, BorderLayout.SOUTH);
        add(currentPlayerLabel, BorderLayout.WEST);
        add(timerLabel, BorderLayout.EAST);
        add(rollDiceButton, BorderLayout.NORTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeTimer() {
        timeElapsed = 0;

        gameTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTimer();
            }
        });

        gameTimer.start();
    }

    private void updateTimer() {
        timerLabel.setText("Time: " + timeElapsed + "s");
        timeElapsed++;

        // Placeholder: Check for game over based on time
        if (Easygame.isGameOver()) {
            gameTimer.stop(); // Stop the timer when the game is over
            JOptionPane.showMessageDialog(this, "Game Over! " + Easygame.getWinner().getName() + " wins!");
            System.exit(0);
        }
    }

    private void updatePlayerLabels() {
        for (int i = 0; i < Easygame.getPlayers().size(); i++) {
            Player player = Easygame.getPlayers().get(i);
            playerLabels[i].setText(player.getName() + ": " + player.getPosition());
        }

        currentPlayerLabel.setText("Current Player: " + Easygame.getCurrentPlayer().getName());
    }

    private void simulateRollDice() {
        Player currentPlayer = Easygame.getCurrentPlayer();
        Easygame.playTurn();

        // Update GUI components based on the game state
        updatePlayerLabels();

        // Check for a winner
        if (Easygame.isGameOver()) {
            gameTimer.stop(); // Stop the timer when the game is over
            JOptionPane.showMessageDialog(this, "Game Over! " + Easygame.getWinner().getName() + " wins!");
            System.exit(0);
        } else {
            JOptionPane.showMessageDialog(this, currentPlayer.getName() + "'s turn.");
        }
    }
 // Add this method to assign colors randomly to each square
    private void assignColorToSquare(JLabel label) {
        int randomColor = (int) (Math.random() * 4); // Random number between 0 and 3

        switch (randomColor) {
            case 0:
                label.setBackground(Color.YELLOW);
                break;
            case 1:
                label.setBackground(Color.RED);
                break;
            case 2:
                label.setBackground(Color.GREEN);
                break;
            case 3:
                label.setBackground(Color.BLUE);
                break;
            case 4:
                label.setBackground(Color.WHITE);
        }

        label.setOpaque(true);
    }
    public static void main(String[] args) {
        // Create players
        Player player1 = new Player("Player 1", null);
        Player player2 = new Player("Player 2",null);
        Player player3 = new Player("Player 3",null);


        // Create the game board
        Board board = new Board(7);

        // Add snakes and ladders to the board
        // Add snakes
        board.addSnake(new Snake(20, 10));  // Example: Snake from square 20 to square 10
        board.addSnake(new Snake(35, 15));  // Example: Snake from square 35 to square 15

        // Add ladders
        board.addLadder(new Ladder(5, 25));  // Example: Ladder from square 5 to square 25
        board.addLadder(new Ladder(12, 30));  // Example: Ladder from square 12 to square 30

        // Create the game cube
        Dice dice = new Dice();

        

        // Create the game instance
        Game game = new Game(board, Arrays.asList(player1, player2,player3), dice);

        // Create the GUI
        SwingUtilities.invokeLater(() -> new EasyGame(game));
    }
}
