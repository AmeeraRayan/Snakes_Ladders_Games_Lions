package View;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import Controller.PreGameController;
import Model.Color;
import Model.Game;
import Model.Ladder;
import Model.Player;
import Model.Snake;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BoardEasyView2Players extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private Map<Player, Integer> playerRolls;
	private Game game;
	private Player currentPlayer;
	private int currentPlayerIndex;
	private final int cellWidth = 100; // Width of each square cell in pixels
	private final int cellHeight = 100;
    private JLabel rollLabel;
    private int rollResult;
    private Map<Player, JLabel> playerTokenMap = new HashMap<>();
	private JButton diceButton;
	private JLabel currentPlayerLabel;
	private JTextPane txtpnHi;

	public BoardEasyView2Players(Game game ) {
        playerRolls = new LinkedHashMap<>();
		this.currentPlayer=game.getCurrentPlayer();
		this.game=game;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		// Add this inside the constructor
		currentPlayerLabel = new JLabel("");
		currentPlayerLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		currentPlayerLabel.setBounds(20, 20, 300, 50); 
		contentPane.add(currentPlayerLabel);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		 int playerIndex = 0;
	        for (Player player : game.getPlayers()) {
	            JLabel tokenLabel = null;
	            if (playerIndex == 0) { // First player gets the blue token
	                tokenLabel = new JLabel(new ImageIcon(PlayerTurn.class.getResource("/images/greenplayer.png")));;
	            } else if (playerIndex == 1) { // Second player gets the green token
	                tokenLabel = new JLabel(new ImageIcon(PlayerTurn.class.getResource("/images/blueplayer.png")));;
	            }
	            tokenLabel.setBounds(0, 0, cellWidth, cellHeight); // Set initial position to the first cell
	            System.out.println("label"+tokenLabel);
	            playerTokenMap.put(player, tokenLabel);
	            getContentPane().add(tokenLabel); // Add the token to the JFrame's content pane
	            playerIndex++;
	        }
	         txtpnHi = new JTextPane();
	        txtpnHi.setFont(new Font("David", Font.BOLD | Font.ITALIC, 27));
	        txtpnHi.setForeground(new java.awt.Color(0, 0, 0));
	        txtpnHi.setBackground(new java.awt.Color(255, 250, 250));
	        
	        txtpnHi.setBounds(10, 202, 180, 239);
	        contentPane.add(txtpnHi);
	      
		 diceButton = new JButton("");
        diceButton.setIcon(new ImageIcon(PlayerTurn.class.getResource("/images/dice 4.jpg")));
		diceButton.setBounds(870, 240, 160, 160);
		contentPane.add(diceButton);
   
        JLabel lblNewLabel = new JLabel("");

		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Maria\\Downloads\\bardeasy2.png"));
		lblNewLabel.setBounds(-17, -89, 1063, 752);
		contentPane.add(lblNewLabel);
		startGame();
		
	}
	public void updatePlayerPosition(Player player, int x, int y) {
	    System.out.println("Updating position for player: " + player.getName()); 
	    JLabel playerToken = getPlayerToken(player);
	    if (playerToken == null) {
	        System.out.println("Player token is null for player: " + player); 
	        return; // Prevent NullPointerException by exiting early if playerToken is null
	    }
	    playerToken.setLocation(calculatePixelX(y), calculatePixelY(x));
	    System.out.println("lablel location x="+playerToken.getLocation().y+"y"+playerToken.getLocation().x);
	}

	private JLabel getPlayerToken(Player player) {
	    // Retrieve the JLabel associated with the player
	    // This assumes you have a way to map from Player objects to their JLabel tokens.
	    return playerTokenMap.get(player);
	}

	private int calculatePixelX(int boardX) {
	    // Translate the board x-coordinate to a pixel coordinate for the GUI
	    return boardX * cellWidth; // cellWidth is the width of a cell in your GUI
	}

	private int calculatePixelY(int boardY) {
	    // Translate the board y-coordinate to a pixel coordinate for the GUI
	    return boardY * cellHeight; // cellHeight is the height of a cell in your GUI
	}

	
	public void updateCurrentPlayerDisplay(Player currentPlayer) {
        // Iterate over all entries in the playerTokenMap
        for (Map.Entry<Player, JLabel> entry : playerTokenMap.entrySet()) {
            Player player = entry.getKey();
            JLabel tokenLabel = entry.getValue();

            if (player.equals(currentPlayer)) {
                // Highlight the current player's token
               // tokenLabel.setBorder(BorderFactory.createStrokeBorder(Color.GREEN, 3));
            } else {
                // Remove highlight from other players' tokens
                tokenLabel.setBorder(null);
            }
        }

        this.repaint(); // Repaint the board to show the updates
    }
	private void updateBoardView() {
	    currentPlayer = game.getCurrentPlayer();

	    // Convert the player's position to x and y coordinates on the board
	    int boardSize = game.getBoard().getSize();
	    int position = currentPlayer.getPosition() - 1; // Subtract 1 to start from 0 index
	    int y = position % boardSize;
	    int x = position / boardSize;

	    // Check if we are on an even or an odd row
	    if (x % 2 == 1) {
	        // On odd rows, we reverse the x direction
	        y = boardSize - 1 - y;
	    }

	    // The y direction is top to bottom, so we invert it
	    x= (boardSize - 1) - x;

	    // Update the GUI component of the currentPlayer
	    this.updatePlayerPosition(currentPlayer, x, y);
	    
	}
	private void performDiceRollAndMove() {
	    // Disable the dice button to prevent multiple rolls
	    diceButton.setEnabled(false);

	    // Perform the dice roll for the current player
	    rollResult = game.getDice().rollforEasy(game.getDifficulty());
	    
	    // Update the GUI with the dice roll result
	    ImageIcon diceIcon = new ImageIcon(getClass().getResource("/images/dice " + rollResult + ".jpg"));
	    diceButton.setIcon(diceIcon);

	    // Move the player based on the dice roll
	    movePlayer(currentPlayer, rollResult);
	    
	    // Check if the player encountered snakes or ladders
	    checkForSnakesAndLadders(currentPlayer);
	    
	    // Update the board view to reflect the player's new position
	    updateBoardView();
	    
	    // Update the text pane to show the roll results
	    displayRollsInTextPane(txtpnHi, playerRolls);

	    // Check if the current player has won the game
	    if (hasPlayerWon(currentPlayer)) {
	        // End the game if the player has won
	        endGame(currentPlayer);
	    } else {
	        // Advance to the next player and update the display
	        advanceToNextPlayer();
	    }
	}

	private void rollDiceAndMovePlayer() {
	    currentPlayer = game.getCurrentPlayer();
	    displayCurrentPlayer(); // Display the current player's name
	    enableDiceRollForCurrentPlayer();
	}

	private void enableDiceRollForCurrentPlayer() {
	    diceButton.setEnabled(true); // Enable the dice button for the current player
	    diceButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent arg0) {
	            diceButton.setEnabled(false); // Disable the button to prevent multiple rolls
	            performDiceRollAndMove();
	        }
	    });
	}

	  private void endGame(Player winner) {
		    JOptionPane.showMessageDialog(null, winner.getName() + " wins the game!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
		}
	    private void movePlayer(Player player, int roll) {
	        int newPosition = player.getPosition() + roll;
	        newPosition = Math.min(newPosition, game.getBoard().getSize() * game.getBoard().getSize()); // Assuming a square board
	        player.setPosition(newPosition);
	        System.out.println("player="+player.getName()+" "+player.getPosition());

	    }
	    private void checkForSnakesAndLadders(Player player) {
	        for (Snake snake : game.getBoard().getSnakes()) {
	            if (player.getPosition() == Integer.parseInt(snake.getSquareStart().getValue()) ) {
	                player.setPosition(Integer.parseInt(snake.getSquareEnd().getValue()));
	    	        System.out.println("player if snake="+player.getName()+" "+player.getPosition());
	                break;
	            }
	        }
	        
	        for (Ladder ladder : game.getBoard().getLadders()) {
	            if (player.getPosition() == Integer.parseInt(ladder.getSquareStart().getValue()) ) {
	                player.setPosition(Integer.parseInt(ladder.getSquareEnd().getValue()));
	    	        System.out.println("player if ladder="+player.getName()+" "+player.getPosition());
	                break;
	            }
	        }
	    }

	    private boolean hasPlayerWon(Player player) {
	        return player.getPosition() == game.getBoard().getSize() * game.getBoard().getSize();
	    }
	    private void advanceToNextPlayer() {
	        currentPlayerIndex = (currentPlayerIndex + 1) % game.getPlayers().size();
	        currentPlayer = game.getPlayers().get(currentPlayerIndex);
	        displayCurrentPlayer();
	        updateCurrentPlayerDisplay(currentPlayer);
	        enableDiceRollForCurrentPlayer(); // Enable the dice roll for the next player
	    }
	    private void displayCurrentPlayer() {
	        currentPlayerLabel.setText("Current Player: " + currentPlayer.getName());
	    }

	    private void updateCurrentPlayerDisplay() {
	        String bluePlayerActiveIconPath = "/images/blueplayer.png";  
	        String greenPlayerActiveIconPath = "/images/greenplayer.png";  

	        for (Map.Entry<Player, JLabel> entry : playerTokenMap.entrySet()) {
	            Player player = entry.getKey();
	            JLabel tokenLabel = entry.getValue();

	            if (player.equals(currentPlayer)) {
	                // Set the icon to the active version
	                ImageIcon activeIcon = new ImageIcon(getClass().getResource(
	                    player.getColor() == Color.BLUE ? bluePlayerActiveIconPath : greenPlayerActiveIconPath));
	                tokenLabel.setIcon(activeIcon);
	            } 
	        }
	    }


	    public void startGame() {
	        rollDiceAndMovePlayer(); 
	    }
	    private void displayRollsInTextPane(JTextPane textPane, Map<Player, Integer> rolls) {
	        StyledDocument doc = textPane.getStyledDocument();
	        textPane.setText(""); // Clear previous text
	        for (Map.Entry<Player, Integer> entry : rolls.entrySet()) {
	            Player player = entry.getKey();
	            Integer rollResult = entry.getValue();
	            String playerRollText = player.getName() + " rolled a " + rollResult + "\n";
	            try {
	                doc.insertString(doc.getLength(), playerRollText, null); // Append roll result for each player
	            } catch (BadLocationException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	  
}
