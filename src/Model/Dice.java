package Model;
import java.util.Random;

import javax.swing.*;
import java.util.*;
import Model.SysData;

public class Dice {
	private Random random;
	private String difficulty;
<<<<<<< Updated upstream
	private SysData sysdata= SysData.getInstance();
=======
	private SysData sysData = SysData.getInstance();
>>>>>>> Stashed changes
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

	        Questions question = retrieveRandomQuestion(questionDifficulty);
System.out.println(question.getQuestionText());
	        // Present the question and options to the player
	        presentQuestion(question);

	        // Get player's answer
	        int playerAnswer = getPlayerAnswer();

	        // Verify the answer
	       /* if (verifyAnswer(question, playerAnswer)) {
	            System.out.println("Correct! You move forward.");
	            // Implement logic for moving the player forward
	        } else {
	            System.out.println("Wrong answer. You stay put.");
	            // Implement logic for staying put or moving back if necessary
	        }*/
	    }

	    return roll;
	}
	private void presentQuestion(Questions question) {
	    // Get question details
	    String questionText = question.getQuestionText();
	    String[] options = question.getOptions();
	    int correctAnswer = question.getCorrectOption();
	    int difficultyLevel = question.getDiffculty();

	    // Present the question and options in a dialog
	    String userAnswer = (String) JOptionPane.showInputDialog(
	            this, 
	            questionText, 
	            "Question", 
	            JOptionPane.QUESTION_MESSAGE, 
	            null, 
	            options, 
	            options[0]
	    );
	    // Check if the user provided an answer
	    if (userAnswer != null) {
	        // Determine the number of steps to move back based on difficulty level
	        int stepsBack = 0;
	        if (difficultyLevel==1)
	        {
		            stepsBack = 1;
	        }
	         else if (difficultyLevel==2) {
	            stepsBack = 2;
	        } else if (difficultyLevel==3) {
	            stepsBack = 3;
	        }

	        // Check the answer and respond
	        if (userAnswer.equals(correctAnswer)) {
	            JOptionPane.showMessageDialog(null, "Correct!", "Result", JOptionPane.INFORMATION_MESSAGE);
	            // Logic for moving the player back 'stepsBack' steps
	            movePlayerBack(stepsBack);
	        } else {
	            JOptionPane.showMessageDialog(null, "Incorrect. The correct answer was: " + correctAnswer, "Result", JOptionPane.ERROR_MESSAGE);
	            // No need to move the player back if the answer is incorrect (based on your requirement)
	        }
	    } else {
	        // Handle case where user closes the dialog or presses Cancel
	        JOptionPane.showMessageDialog(null, "No answer selected.", "No Answer", JOptionPane.WARNING_MESSAGE);
	    }
	}

	private int getPlayerAnswer() {
	    // Get player's answer
	    // This is a placeholder for your implementation
	    return 0; // Replace with actual player input
	}
	private void movePlayerBack(int stepsBack) {
	    // Assuming 'currentPlayer' is the Player object for the player whose turn it is
	    // And assuming the Player class has 'getPosition()' and 'setPosition(int)' methods
	    int currentPosition = currentPlayer.getPosition();
	    
	    // Calculate the new position
	    int newPosition = currentPosition - stepsBack;

	    // Ensure the new position is not less than the starting point (e.g., 0 or 1 depending on how you track position)
	    if (newPosition < 0) {
	        newPosition = 0; // or 1, if your game positions start at 1
	    }

	    // Set the player's new position
	    currentPlayer.setPosition(newPosition);

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
	private Questions retrieveRandomQuestion(int difficulty) {
		this.sysdata.LoadQuestions();
	   
	    return this.sysData.getRandomQuestion(difficulty);
	}
    public int rollForTurn() {
        return random.nextInt(6) + 1; // rolls a number between 1 and 6
    }
    public int roll() {
	    return random.nextInt(11)+1; // Roll a number between 1 and 10
	}
}

