package Model;

import java.util.List;

import Controller.MangQuestionControl;
import View.BoardEasyView2Players;


public class Game {
	private static Game instance = null;
    private List<Player> players;
    private Player currentPlayer;
    private BoardEasyView2Players boardView;
    private Board board;
    private Dice dice;
    private int turnCount;
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
        this.currentPlayer = null; // Initialize based on game rules
        this.turnCount = 0;
        // Set board size based on difficulty
        int boardSize = difficulty.equals("Easy") ? 7 : difficulty.equals("Medium") ? 10 : 13;

        // Create the board
        Board board = new Board(boardSize);
        this.boardView = boardView;

        // Create the dice
        Dice dice = new Dice(difficulty);

    }

    public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public  Player getCurrentPlayer() {
        if (currentPlayerIndex >= 0 && currentPlayerIndex < players.size()) {
            return players.get(currentPlayerIndex);
        } else {
            // Handle the case where the index is out of bounds
            return null;
        }
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

    public void rollDiceAndMovePlayer() {
        int roll = dice.roll();
        Player currentPlayer = getCurrentPlayer();
        movePlayer(currentPlayer, roll);
        checkForSnakesAndLadders(currentPlayer);
        updateBoardView();
        if (hasPlayerWon(currentPlayer)) {
            endGame(currentPlayer);
        } else {
            advanceToNextPlayer();
        }
    }

    private void movePlayer(Player player, int roll) {
        int newPosition = player.getPosition() + roll;
        newPosition = Math.min(newPosition, board.getSize() * board.getSize()); // Assuming a square board
        player.setPosition(newPosition);
    }

    private void checkForSnakesAndLadders(Player player) {
        for (Snake snake : board.getSnakes()) {
            if (player.getPosition() == Integer.parseInt(snake.getSquareStart().getValue()) ) {
                player.setPosition(Integer.parseInt(snake.getSquareEnd().getValue()));
                break;
            }
        }
        
        for (Ladder ladder : board.getLadders()) {
            if (player.getPosition() == Integer.parseInt(ladder.getSquareStart().getValue()) ) {
                player.setPosition(Integer.parseInt(ladder.getSquareEnd().getValue()));
                break;
            }
        }
    }

    private void updateBoardView() {
        Player currentPlayer = getCurrentPlayer();

        // Convert the player's position to x and y coordinates on the board
        int boardSize = board.getSize();
        int x = (currentPlayer.getPosition() - 1) % boardSize;
        int y = (currentPlayer.getPosition() - 1) / boardSize;

        // Update the GUI component of the currentPlayer
        boardView.updatePlayerPosition(currentPlayer, x, y);
    }
    private boolean hasPlayerWon(Player player) {
        return player.getPosition() == board.getSize() * board.getSize();
    }

   }



