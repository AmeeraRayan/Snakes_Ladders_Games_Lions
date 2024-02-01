package Model;
import java.util.Random;

public class Dice {
	private Random random;
	private String difficulty;
	private SysData sysdata=new SysData();
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

	    // Suppose there is a 25% chance to land on a question square
	    boolean landsOnQuestion = random.nextFloat() < 0.25;

	    if (landsOnQuestion) {
	        // If landed on question, randomly select a difficulty level for the question
	        int questionDifficulty = random.nextInt(3) + 1; // This will give you 1, 2, or 3

	        // Now, retrieve a random question based on the selected difficulty
	        Questions question = retrieveRandomQuestion(questionDifficulty);

	        // Present the question and options to the player
	        presentQuestion(question);

	        // Get player's answer
	        int playerAnswer = getPlayerAnswer();

	        // Verify the answer
	        if (verifyAnswer(question, playerAnswer)) {
	            System.out.println("Correct! You move forward.");
	            // Implement logic for moving the player forward
	        } else {
	            System.out.println("Wrong answer. You stay put.");
	            // Implement logic for staying put or moving back if necessary
	        }
	    }

	    return roll;
	}
	private void presentQuestion(Questions question) {
	    // Present the question and options to the player
	    // This is a placeholder for your implementation
	}

	private int getPlayerAnswer() {
	    // Get player's answer
	    // This is a placeholder for your implementation
	    return 0; // Replace with actual player input
	}

	private boolean verifyAnswer(Questions question, int playerAnswer) {
	    // Verify the answer against the correct answer of the question
	    return question.getCorrectOption() == playerAnswer;
	}
	private Questions retrieveRandomQuestion(int difficulty) {
	   
	    return this.sysdata.getRandomQuestion(difficulty);
	}
    public int rollForTurn() {
        return random.nextInt(6) + 1; // rolls a number between 1 and 6
    }
    public int roll() {
	    return random.nextInt(11)+1; // Roll a number between 1 and 10
	}
}

