package Controller;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Model.Dice;
import Model.Player;

public class PreGameController {
	  private Dice dice;
	    private Map<Player, Integer> playerRolls;
	    public List<Player> players;
	    private String difficultyLevel;
	    
	 public PreGameController(Dice dice, Map<Player, Integer> playerRolls, List<Player> players,
				String difficultyLevel) {
			super();
			this.dice = dice;
			this.playerRolls = playerRolls;
			this.players = players;
			this.difficultyLevel = difficultyLevel;
		}
	 
//comment
	public Dice getDice() {
		return dice;
	}


	public void setDice(Dice dice) {
		this.dice = dice;
	}


	public Map<Player, Integer> getPlayerRolls() {
		return playerRolls;
	}


	public void setPlayerRolls(Map<Player, Integer> playerRolls) {
		this.playerRolls = playerRolls;
	}


	public List<Player> getPlayers() {
		return players;
	}


	public void setPlayers(List<Player> players) {
		this.players = players;
	}


	public String getDifficultyLevel() {
		return difficultyLevel;
	}


	public void setDifficultyLevel(String difficultyLevel) {
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
	        // newGame.startGame(); 
	    }
	 // Method to check for ties in player rolls
	    public boolean checkForTies() {
	        Set<Integer> uniqueRolls = new HashSet<>(playerRolls.values());
	        return uniqueRolls.size() < playerRolls.size(); // If there are fewer unique rolls than players, there's a tie
	    }

	    // Method to get the list of players who have tied
	    public List<Player> getTiedPlayers() {
	        Map<Integer, List<Player>> rollToPlayers = new HashMap<>();
	        for (Map.Entry<Player, Integer> entry : playerRolls.entrySet()) {
	            rollToPlayers.computeIfAbsent(entry.getValue(), k -> new ArrayList<>()).add(entry.getKey());
	        }

	        List<Player> tiedPlayers = new ArrayList<>();
	        for (List<Player> playerList : rollToPlayers.values()) {
	            if (playerList.size() > 1) {
	                tiedPlayers.addAll(playerList);
	            }
	        }
	        return tiedPlayers;
	    }

	    // Method to handle re-rolls for tied players
	    public void reRollForTiedPlayers(List<Player> tiedPlayers) {
	        for (Player player : tiedPlayers) {
	            int newRoll = dice.rollForTurn();
	            playerRolls.put(player, newRoll);
	        }
	    }
	    public boolean checkForTie(Map<Player, Integer> rolls) {
	        Set<Integer> uniqueRolls = new HashSet<>(rolls.values());
	        return uniqueRolls.size() == 1; // If there's only one unique roll, it's a tie
	    }

	    public List<Player> findTiedPlayers(Map<Player, Integer> rolls) {
	        List<Player> tiedPlayers = new ArrayList<>();
	        int tieValue = rolls.values().iterator().next(); // Get the value of the first roll

	        for (Map.Entry<Player, Integer> entry : rolls.entrySet()) {
	            if (entry.getValue() == tieValue) {
	                tiedPlayers.add(entry.getKey());
	            }
	        }


	        return tiedPlayers;
	    }
	    public void processEndOfRollPhase() {
	        if (checkForTies()) {
	            List<Player> tiedPlayers = getTiedPlayers();
	            reRollForTiedPlayers(tiedPlayers);

	            if (tiedPlayers.size() == 2) {
	                // Sort the tied players by name alphabetically
	                tiedPlayers.sort(Comparator.comparing(Player::getName));

	                // Create and display the custom message for two tied players
	                String messageText = "Player " + tiedPlayers.get(0).getName() + " and Player " + 
	                                     tiedPlayers.get(1).getName() + " got tied. " +
	                                     "As per alphabetical order, Player " + 
	                                     tiedPlayers.get(0).getName() + " will be first.";
	                JOptionPane.showMessageDialog(null, messageText, "Tie Detected", JOptionPane.INFORMATION_MESSAGE);
	            }
	        }
	        // Continue with the next phase of the game
	        startNewGame();
	    }

}
