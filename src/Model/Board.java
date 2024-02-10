package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Board {
	private static Board instance = null;
	private int size;
    private static Snake[] snakes;
    private static Ladder[] ladders;
    private static Square[] questions;
//  Singleton Instance
	public static Board getInstance(int size) {
		if (instance == null) {
			instance = new Board(size);
		}
		return instance;
	}

    public Board(int size) {
        this.size = size;  
        if (size==7) {
            this.snakes = new Snake[4];
            this.ladders = new Ladder[4];
            this.questions=new Square[3];
            }
        else if (size==10) {
            this.snakes = new Snake[6];
            this.ladders = new Ladder[6];
            //initializeSnakesAndLaddersForMedium();
        }else {
            this.snakes = new Snake[8];
            this.ladders = new Ladder[8];
           // initializeSnakesAndLaddersForHard();
        }
    }
    
    

	public static void initializeSnakesAndLaddersForEasy1() {//easybord1
	    // Initialize 4 snakes	
	   snakes[0] = new Snake(new Square(5, 3, 45),new Square(6, 0, 1));//RED
	    snakes[1] = new Snake(new Square(4, 6, 21  ),new Square(6, 5, 6 ));//GREEN
	    snakes[2] = new Snake(new Square(5, 0,8  ),new Square(6,1 , 2 ));//YELLOW
	    snakes[3] = new Snake(new Square(0, 0, 43 ),new Square( 3, 2,24 ));//BLUE
	    // Initialize 4 ladders
	    ladders[0] = new Ladder(new Square(1, 4, 40 ),new Square(0, 5, 48 ));//1
	    ladders[1] = new Ladder(new Square(3, 3, 25  ),new Square(1,2 ,38));//2
	    ladders[2] = new Ladder(new Square(6,1 , 2),new Square(3,1 , 23 ));//3
	    ladders[3] = new Ladder(new Square(5, 4,12 ),new Square(1,5 ,41));//4
	    //Initialize 3 questions 
	    questions[0]=new Square(4, 2, 3);
	    questions[1]=new Square(6, 4, 5);
	    questions[2]=new Square(2, 0, 29);
}
	
	public static void initializeSnakesAndLaddersForEasy3() {//boradeasy3
	    // Initialize 4 snakes
	   snakes[0] = new Snake(new Square(3, 0, 22),new Square(6, 0, 1));//RED
	    snakes[1] = new Snake(new Square(1, 2, 38  ),new Square(3, 2, 24 ));//GREEN
	    snakes[2] = new Snake(new Square(0, 0,43   ),new Square(1,1 , 37 ));//YELLOW
	    snakes[3] = new Snake(new Square(0, 4, 47  ),new Square( 3, 5,27 ));//BLUE
	    // Initialize 4 ladders
	    ladders[0] = new Ladder(new Square(6, 3, 4 ),new Square(5, 4, 12 ));//1
	    ladders[1] = new Ladder(new Square(3, 4, 26  ),new Square(1,3 ,39));//2
	    ladders[2] = new Ladder(new Square(6,1 , 2),new Square(3,1 , 23 ));//3
	    ladders[3] = new Ladder(new Square(5, 5,13 ),new Square(1,6 ,42));//4
	    // Initialize 3 questions
	    questions[0]=new Square(6, 2, 3);
	    questions[1]=new Square(5, 6, 14);
	    questions[2]=new Square(0, 3, 46);

}
	
 
    	public static void initializeSnakesAndLaddersForEasy() {//boradeasy2
    	    // Initialize 4 snakes
    	   snakes[0] = new Snake(new Square(0, 2, 45),new Square(6, 0, 1));//RED
    	    snakes[1] = new Snake(new Square(4, 6, 21  ),new Square(6, 5, 6 ));//GREEN
    	    snakes[2] = new Snake(new Square(2, 1,30   ),new Square(3,2 , 24 ));//YELLOW
    	    snakes[3] = new Snake(new Square(0, 4, 47  ),new Square( 3, 5,27 ));//BLUE
    	    // Initialize 4 ladders
    	    ladders[0] = new Ladder(new Square(1, 0, 36 ),new Square(0, 1, 44 ));//1
    	    ladders[1] = new Ladder(new Square(3, 4, 26  ),new Square(1,3 ,39));//2
    	    ladders[2] = new Ladder(new Square(6,1 , 2),new Square(3,1 , 23 ));//3
    	    ladders[3] = new Ladder(new Square(5, 5,13 ),new Square(1,6 ,42));//4
    	    //initialize 3 questions
    	    questions[0]=new Square(6, 2, 3);
    	    questions[1]=new Square(5, 6, 14);
    	    questions[2]=new Square(0, 3, 46);

    }
    	

	public static Board getInstance() {
		return instance;
	}

	public static void setInstance(Board instance) {
		Board.instance = instance;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Snake[] getSnakes() {
		return snakes;
	}

	public Ladder[] getLadders() {
		return ladders;
	}
	public Square[] getQuestions() {
		return questions;
	}

	@Override
	public String toString() {
		return "Board [size=" + size + "]";
	}

}
