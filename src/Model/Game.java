package Model;

import java.util.List;

import Controller.MangQuestionControl;


public class Game {
    private List<Player> players;
    private Player currentPlayer;
    private Board board;
    private Dice dice;
    private int turnCount;
    private int currentPlayerIndex = 0; // Add this variable to track the current player index
    private MangQuestionControl mngControl= new MangQuestionControl();
    
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


	public void playGame() {
        while (true) {
            Player currentPlayer = getCurrentPlayer();
            int diceRoll = dice.roll();

            if (diceRoll > 0 && diceRoll <= 6) {
                System.out.println(currentPlayer.getName() + " rolled a " + diceRoll);

                // Move the player on the board
                int newPosition = currentPlayer.getPosition() + diceRoll;
                newPosition = handleSnakesAndLadders(currentPlayer, newPosition);
                currentPlayer.setPosition(newPosition);

                // Check for a winning position
                if (newPosition >= board.getSize()) {
                    System.out.println(currentPlayer.getName() + " wins!");
                    return;
                }

                // Handle questions based on the dice roll
                if (diceRoll > 0 && diceRoll <= 6) {
                    handleQuestion(currentPlayer, "easy");
                    handleQuestion(currentPlayer, "medium");
                    handleQuestion(currentPlayer, "hard");
                }
            } else {
                // Handle the case where a question is triggered
                handleQuestion(currentPlayer, "easy");
                handleQuestion(currentPlayer, "medium");
                handleQuestion(currentPlayer, "hard");
            }

            // Switch to the next player
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }
    }
	private int handleSnakesAndLadders(Player currentPlayer, int newPosition) {
	    // Check for snakes and ladders on the board
	    for (Snake snake : board.getSnakes()) {
	        if (newPosition == snake.getStart()) {
	            System.out.println("Oops! Snake bite! " + currentPlayer.getName() + " goes back to " + snake.getEnd());
	            return snake.getEnd();
	        }
	    }

	    for (Ladder ladder : board.getLadders()) {
	        if (newPosition == ladder.getStart()) {
	            System.out.println("Great! " + currentPlayer.getName() + " climbs the ladder to " + ladder.getEnd());
	            return ladder.getEnd();
	        }
	    }

	    return newPosition;
	}

    private void handleQuestion(Player currentPlayer, String level) {
        Questions question =mngControl.getRandomQuestion(level);
        if (question != null) {
            System.out.println("Question for " + currentPlayer.getName() + ": " + question.getQuestionText());
            // Display options and get user input for the answer
            // Check if the answer is correct and move the player accordingly
            // Update player position based on correct/incorrect answer
        } else {
            System.out.println("No questions available for " + level + " level.");
        }
    }

        public void playTurn() {
            Player currentPlayer = getCurrentPlayer();
            int diceRoll = dice.roll();

            if (diceRoll > 0 && diceRoll <= 6) {
                System.out.println(currentPlayer.getName() + " rolled a " + diceRoll);

                // Move the player on the board
                int newPosition = currentPlayer.getPosition() + diceRoll;
                newPosition = handleSnakesAndLadders(currentPlayer, newPosition);
                currentPlayer.setPosition(newPosition);

                // Handle questions based on the dice roll
                if (diceRoll > 0 && diceRoll <= 6) {
                    handleQuestion(currentPlayer, "easy");
                    handleQuestion(currentPlayer, "medium");
                    handleQuestion(currentPlayer, "hard");
                }
            } else {
                // Handle the case where a question is triggered
                handleQuestion(currentPlayer, "easy");
                handleQuestion(currentPlayer, "medium");
                handleQuestion(currentPlayer, "hard");
            }

            // Switch to the next player
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }

        public boolean isGameOver() {
            // Check if any player has reached the end of the board
            for (Player player : players) {
                if (player.getPosition() >= board.getSize()) {
                    return true; // Game is over
                }
            }
            return false; // Game is not over
        }

        public Player getWinner() {
            // Find the player who has reached the end of the board first
            for (Player player : players) {
                if (player.getPosition() >= board.getSize()) {
                    return player; // This player is the winner
                }
            }
            return null; // No winner yet
        }

}

