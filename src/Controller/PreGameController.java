package Controller;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Model.Dice;
import Model.Player;

public class PreGameController {
	  private Dice dice;
	    private Map<Player, Integer> playerRolls;
	    private List<Player> players;
	    private String difficultyLevel;
	 public PreGameController(Dice dice, Map<Player, Integer> playerRolls, List<Player> players,
				String difficultyLevel) {
			super();
			this.dice = dice;
			this.playerRolls = playerRolls;
			this.players = players;
			this.difficultyLevel = difficultyLevel;
		}
	 

	public StringBuilder displayTurnOrder() {
	        players.sort(Comparator.comparing(playerRolls::get).reversed());

	        StringBuilder turnOrderMessage = new StringBuilder("Turn order:\n");
	        for (int i = 0; i < players.size(); i++) {
	            turnOrderMessage.append(i + 1).append(". ").append(players.get(i).getName()).append("\n");
	        }	 
	        return turnOrderMessage;
	        }

	    public void startNewGame() {
	        Queue<Player> sortedPlayers = new ArrayDeque<>(players);
	      /*  Game newGame = new Game(difficultyLevel, sortedPlayers, dice);*/
	        // newGame.startGame(); // You'll need to implement this method in your Game class
	    }

}
