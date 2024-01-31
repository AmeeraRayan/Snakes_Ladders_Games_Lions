package View;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Game;
import Model.Player;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

public class BoardEasyView2Players extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Game game;
	private final int cellWidth = 100; // Width of each square cell in pixels
	private final int cellHeight = 100;
    private Map<Player, JLabel> playerTokenMap = new HashMap<>();

	public BoardEasyView2Players() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		 int playerIndex = 0;
	        for (Player player : game.getPlayers()) {
	            JLabel tokenLabel = null;
	            if (playerIndex == 0) { // First player gets the blue token
	                tokenLabel = new JLabel("C:\\Users\\Maria\\OneDrive\\שולחן העבודה\\Lions\\Snakes_Ladders_Games_Lions\\src\\images\\blueplayer.png");
	            } else if (playerIndex == 1) { // Second player gets the green token
	                tokenLabel = new JLabel("C:\\Users\\Maria\\OneDrive\\שולחן העבודה\\Lions\\Snakes_Ladders_Games_Lions\\src\\images\\greenplayer.png");
	            }
	            tokenLabel.setBounds(0, 0, cellWidth, cellHeight); // Set initial position to the first cell
	            playerTokenMap.put(player, tokenLabel);
	            getContentPane().add(tokenLabel); // Add the token to the JFrame's content pane
	            playerIndex++;
	        }
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Maria\\OneDrive\\שולחן העבודה\\Lions\\Snakes_Ladders_Games_Lions\\src\\images\\bardeasy2.png"));
		lblNewLabel.setBounds(-24, -94, 1063, 752);
		contentPane.add(lblNewLabel);
	}
	public void updatePlayerPosition(Player player, int x, int y) {
	    // This method would be responsible for updating the player's token on the GUI.
	    // You'd need to translate the board position (x, y) to pixel coordinates if your layout requires it.
	    JLabel playerToken = getPlayerToken(player);
	    playerToken.setLocation(calculatePixelX(x), calculatePixelY(y));
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

	private void endGame(Player winner) {
	    // Display a victory message
	    JOptionPane.showMessageDialog(null, winner.getName() + " wins the game!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
	    // You might also want to disable further game actions or offer to restart the game.
	}
	

}
