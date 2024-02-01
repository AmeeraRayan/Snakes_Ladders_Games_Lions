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
	            if (playerIndex == 0 && player.getColor()==Color.GREEN) {
	                tokenLabel = new JLabel(new ImageIcon(PlayerTurn.class.getResource("/images/greenplayer.png")));
		            playerTokenMap.put(player, tokenLabel);
	            } else if (playerIndex == 1 && player.getColor()==Color.BLUE) { 
	                tokenLabel = new JLabel(new ImageIcon(PlayerTurn.class.getResource("/images/blueplayer.png")));;
		            playerTokenMap.put(player, tokenLabel);
	            } else if (playerIndex == 1 && player.getColor()==Color.GREEN) { 
	                tokenLabel = new JLabel(new ImageIcon(PlayerTurn.class.getResource("/images/greenplayer.png")));;
		            playerTokenMap.put(player, tokenLabel);
	            } else if (playerIndex == 0 && player.getColor()==Color.BLUE) { 
	                tokenLabel = new JLabel(new ImageIcon(PlayerTurn.class.getResource("/images/blueplayer.png")));;
		            playerTokenMap.put(player, tokenLabel);
	            }
	            tokenLabel.setBounds(0, 0, cellWidth, cellHeight); 
	            getContentPane().add(tokenLabel); 
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
	    JLabel playerToken = getPlayerToken(player);
	    if (playerToken != null) {
	        // Calculate new pixel positions
	        int pixelX = calculatePixelX(x);
	        int pixelY = calculatePixelY(y);
	        playerToken.setBounds(pixelX, pixelY, playerToken.getWidth(), playerToken.getHeight());
	        
	        System.out.println(player.getName() + " moves to X: " + x + " Y: " + y + " (PixelX: " + pixelX + ", PixelY: " + pixelY + ")");
	    } else {
	        System.err.println("Token not found for player: " + player.getName());
	    }

	    contentPane.revalidate();
	    contentPane.repaint();
	}


	private JLabel getPlayerToken(Player player) {
	    return playerTokenMap.get(player);
	}

	private int calculatePixelX(int boardX) {
	    return boardX * cellWidth;
	}

	private int calculatePixelY(int boardY) {
	    return boardY * cellHeight;
	}

	

private void updateCurrentPlayerDisplay(Player currentPlayer) {
    String bluePlayerIconPath = "/images/blueplayer.png";
    String greenPlayerIconPath = "/images/greenplayer.png";

    for (Map.Entry<Player, JLabel> entry : playerTokenMap.entrySet()) {
        Player player = entry.getKey();
        JLabel tokenLabel = entry.getValue();
        System.out.println("LABLE"+tokenLabel);
        ImageIcon icon = new ImageIcon(getClass().getResource(
            player.getColor() == Color.GREEN ? greenPlayerIconPath:bluePlayerIconPath ));
        tokenLabel.setIcon(icon);
    }
    this.repaint(); 
}
	private void updateBoardView() {
	    currentPlayer = game.getCurrentPlayer();

	    int boardSize = game.getBoard().getSize();
	    int position = currentPlayer.getPosition() - 1; // Subtract 1 to start from 0 index
	    int y = position % boardSize;
	    int x = position / boardSize;

	    if (x % 2 == 1) {
	        y = boardSize - 1 - y;
	    }

	    x= (boardSize - 1) - x;

	    this.updatePlayerPosition(currentPlayer, x, y);
	    
	}
	private void performDiceRollAndMove() {
	    diceButton.setEnabled(false);
	    rollResult = game.getDice().rollForEasy();
	    ImageIcon diceIcon = new ImageIcon(getClass().getResource("/images/dice " + rollResult + ".jpg"));
	    diceButton.setIcon(diceIcon);
	    movePlayer(currentPlayer, rollResult);
	    checkForSnakesAndLadders(currentPlayer);
	    updateBoardView();

	    if (hasPlayerWon(currentPlayer)) {
	        endGame(currentPlayer);
	    } else {
	        advanceToNextPlayer();
	    }
	}
	private boolean hasPlayerWon(Player player) {
	    int maxPosition = game.getBoard().getSize() * game.getBoard().getSize();
	    return player.getPosition() >= maxPosition;
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
	    JOptionPane.showMessageDialog(this, winner.getName() + " wins the game!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
	    diceButton.setEnabled(false);
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
	    private void advanceToNextPlayer() {
	        currentPlayerIndex = (currentPlayerIndex + 1) % game.getPlayers().size();
	        currentPlayer = game.getPlayers().get(currentPlayerIndex);
	        
	        updateCurrentPlayerDisplay(currentPlayer);
	        displayCurrentPlayer(); 
	        
	        diceButton.setEnabled(true); 
	    }

	    private void displayCurrentPlayer() {
	        if (currentPlayer != null) {
	            currentPlayerLabel.setText("Current Player: " + currentPlayer.getName());
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