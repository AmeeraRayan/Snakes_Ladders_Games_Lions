package Model;

import java.util.List;

import javax.swing.JOptionPane;

import View.BoardEasyView2Players;


public class Game {
	private static Game instance = null;
    private List<Player> players;
    private Player currentPlayer;
    private Board board;
    private String difficulty;
    private Dice dice;
    private int currentPlayerIndex = 0; // Add this variable to track the current player index
//  Singleton Instance
	public static Game getInstance(List<Player> players,String difficulty) {
		if (instance == null) {
			instance = new Game(difficulty,players);
		}
		return instance;
	}
    
    public Game( String difficulty,List<Player> players) {
        this.players = players;
        this.currentPlayer = players.get(0); 
        this.difficulty=difficulty;
        int boardSize = difficulty.equals("Easy") ? 7 : difficulty.equals("Medium") ? 10 : 13;
         this.board = new Board(boardSize);
        this.dice = new Dice(difficulty);
        
    }
    public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
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

    public static Game getInstance() {
		return instance;
	}

	public static void setInstance(Game instance) {
		Game.instance = instance;
	}

	public int getCurrentPlayerIndex() {
		return currentPlayerIndex;
	}

	public void setCurrentPlayerIndex(int currentPlayerIndex) {
		this.currentPlayerIndex = currentPlayerIndex;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}
  

   }



