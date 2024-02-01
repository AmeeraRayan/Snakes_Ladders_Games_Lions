package Model;
import java.util.Random;

public class Dice {
	private Random random;
	private String difficulty;
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
    public int rollForTurn() {
        return random.nextInt(6) + 1; // rolls a number between 1 and 6
    }
    public int roll() {
	    return random.nextInt(11)+1; // Roll a number between 1 and 10
	}
}

