package Model;

import java.util.List;

public class Game {
    private List<Player> players;
    private Player currentPlayer;
    private Board board;
    private Dice dice;
    private int turnCount;
    
    public Game(Board board, List<Player> players, Dice dice) {
        this.players = players;
        this.currentPlayer = null; // Initialize based on game rules
        this.board = board;
        this.dice = dice;
        this.turnCount = 0;
    }


    public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Dice getDice() {
		return dice;
	}

	public void setDice(Dice dice) {
		this.dice = dice;
	}

	public int getTurnCount() {
		return turnCount;
	}

	public void setTurnCount(int turnCount) {
		this.turnCount = turnCount;
	}

	
    public void startGame() {
        // Initialize game state, decide starting player, etc.
    }

    public void playTurn() {
        // Logic for a player's turn
    }

    public boolean isGameOver() {
        // Check if the game is over based on certain conditions
        return false;
    }

}

