package Model;
import java.util.Random;

import javax.swing.*;
import java.util.*;
import Model.SysData;

public class Dice {
	private Random random;
	private String difficulty;
	private SysData sysdata= SysData.getInstance();
	private SysData sysData = SysData.getInstance();
	public Dice(String difficulty) {
		this.difficulty=difficulty;
	    this.random = new Random();
	}
	public Dice() {
	    this.random = new Random();
	}
	public int rollforEasy(String difficulty) {
		if (difficulty.equals("Easy"))	{
	        return random.nextInt(4) + 1; // rolls a number between 1 and 4
		}
		return 0;
	}
	
	public int rollForEasy() {
	    // rolls a number between 1 and 4
	    int roll = random.nextInt(4) + 1;

	    boolean landsOnQuestion = random.nextFloat() < 0.25;

	    if (landsOnQuestion) {
	        int questionDifficulty = random.nextInt(3) + 1; 

	       // Questions question = retrieveRandomQuestion(questionDifficulty);
          // System.out.println(question.getQuestionText());
	        // Present the question and options to the player
	       

	    }

	    return roll;
	}
	

	private void movePlayerBack(int stepsBack) {
	    // Assuming 'currentPlayer' is the Player object for the player whose turn it is
	    // And assuming the Player class has 'getPosition()' and 'setPosition(int)' methods
	  //  int currentPosition = currentPlayer.getPosition();
	    
	    // Calculate the new position
	   // int newPosition = currentPosition - stepsBack;

	    // Ensure the new position is not less than the starting point (e.g., 0 or 1 depending on how you track position)
	 //   if (newPosition < 0) {
	     //   newPosition = 0; // or 1, if your game positions start at 1
	   // }

	    // Set the player's new position
	   // currentPlayer.setPosition(newPosition);

	    // Optionally, update the game board or UI to reflect the player's new position
	    updateGameBoard();
	}

	// Placeholder for the method to update the game board or UI
	private void updateGameBoard() {
	    // Update the game board or UI here to reflect changes in player positions
	    // This could involve redrawing the player on the board at their new position
	}
	private boolean verifyAnswer(Questions question, int playerAnswer) {
	    // Verify the answer against the correct answer of the question
	    return question.getCorrectOption() == playerAnswer;
	}

    public int rollForTurn() {
        return random.nextInt(6) + 1; // rolls a number between 1 and 6
    }
    public int roll() {
	    return random.nextInt(11)+1; // Roll a number between 1 and 10
	}
}

