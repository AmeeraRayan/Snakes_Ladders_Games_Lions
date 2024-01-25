package Model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import javafx.scene.input.Clipboard;

public class Game {
	private int GameId;
	private String diffcultylevel;
    private Queue<Player> players;
    private Dice dice;
    
	public Game(int gameId, String diffcultylevel, Queue<Player> players, Dice dice) {
		super();
		GameId = gameId;
		this.diffcultylevel = diffcultylevel;
		this.players = players;
		this.dice = dice;
		/*private board = new board();*/
	}
	public Game(String diffcultylevel, Queue<Player> players, Dice dice) {
		this.diffcultylevel = diffcultylevel;
		this.players = players;
		this.dice = dice;
	}
	public int getGameId() {
		return GameId;
	}
	public void setGameId(int gameId) {
		GameId = gameId;
	}
	public String getDiffcultylevel() {
		return diffcultylevel;
	}
	public void setDiffcultylevel(String diffcultylevel) {
		this.diffcultylevel = diffcultylevel;
	}
	public Queue<Player> getPlayers() {
		return players;
	}
	public void setPlayers(Queue<Player> players) {
		this.players = players;
	}
	public Dice getDice() {
		return dice;
	}
	public void setDice(Dice dice) {
		this.dice = dice;
	}
	/*public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}*/
    

    
}
