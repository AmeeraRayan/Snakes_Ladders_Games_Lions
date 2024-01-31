package Model;

import java.util.ArrayList;
import java.util.List;


public class Board {
	private static Board instance = null;
	private int size;
    private Square[][] cells;
    private Snake[] snakes;
    private Ladder[] ladders;
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
        	this.cells = new Square[7][7];
            this.snakes = new Snake[4];
            this.ladders = new Ladder[4];
            initializeSnakesAndLaddersForEasy();
        }
        else if (size==10) {
        	this.cells = new Square[10][10];
            this.snakes = new Snake[6];
            this.ladders = new Ladder[6];
            initializeSnakesAndLaddersForMedium();
        }else {
        	this.cells = new Square[13][13];
            this.snakes = new Snake[8];
            this.ladders = new Ladder[8];
            initializeSnakesAndLaddersForHard();
        }
    }

 
    	private void initializeSnakesAndLaddersForEasy() {
    	    // Initialize 4 snakes
    	    snakes[0] = new Snake(new Square(0, 2, "45", false),new Square(0, 2, "45", false));//RED
    	    snakes[1] = new Snake(new Square(4, 6, "21", false),new Square(6, 5, "6", false));//GREEN
    	    snakes[2] = new Snake(new Square(2, 1,"30" , false),new Square(3,2 , "24", false));//YELLOW
    	    snakes[3] = new Snake(new Square(0, 4, "47", false),new Square( 3, 5,"27", false));//BLUE

    	    // Initialize 4 ladders
    	    ladders[0] = new Ladder(new Square(1, 0, "36", false),new Square(0, 1, "44", false));//1
    	    ladders[1] = new Ladder(new Square(3, 4, "26", false),new Square(1,3 ,"39" , false));//2
    	    ladders[2] = new Ladder(new Square(6,1 , "2", false),new Square(3,1 , "23", false));//3
    	    ladders[3] = new Ladder(new Square(5, 5,"13" , false),new Square(1,6 ,"42" , false));//4
    	    
    	    Square q1=new Square(6, 2, "3", true);
    	    Square q2=new Square(5, 6, "14", true);
    	    Square q3=new Square(0, 3, "46", true);
    	    int counter=1;
    	    for (int i=0;i<7;i++)
    	    {
    	    	   for (int j=0;i<7;j++)
    	    	   {
    	    		   cells[i][j].setValue(String.valueOf(counter));
    	    		   if (counter==3)
    	    		   {
    	    			   cells[i][j].setValue("q1");
    	    		   }
    	    		   if (counter==14)
    	    		   {
    	    			   cells[i][j].setValue("q2");
    	    		   }
    	    		   if (counter==46)
    	    		   {
    	    			   cells[i][j].setValue("q3");
    	    		   }
    	    		   if (counter==45)
    	    		   {
    	    			   cells[i][j].setValue("snakered");
    	    		   }
    	    		   else if (counter==21) {
    	    			   cells[i][j].setValue("snakegreenstart");
    	    		   }
    	    		   else if (counter==6) {
    	    			   cells[i][j].setValue("snakegreenend");
    	    		   }
    	    		   else if (counter==30) {
    	    			   cells[i][j].setValue("snakeyellowstart");
    	    		   }
    	    		   else if (counter==24) {
    	    			   cells[i][j].setValue("snakeyellowend");
    	    		   }
    	    		   else if (counter==47) {
    	    			   cells[i][j].setValue("snakebluestart");
    	    		   }
    	    		   else if (counter==27) {
    	    			   cells[i][j].setValue("snakeblueend");
    	    		   }else if (counter==36) {
    	    			   cells[i][j].setValue("ladder1start");
    	    		   }
    	    		   else if (counter==44) {
    	    			   cells[i][j].setValue("ladder1end");
    	    		   }else if (counter==26) {
    	    			   cells[i][j].setValue("ladder2start");
    	    		   }
    	    		   else if (counter==39) {
    	    			   cells[i][j].setValue("ladder2end");
    	    		   }else if (counter==2) {
    	    			   cells[i][j].setValue("ladder3start");
    	    		   }
    	    		   else if (counter==23) {
    	    			   cells[i][j].setValue("ladder3end");
    	    		   }else if (counter==13) {
    	    			   cells[i][j].setValue("ladder4start");
    	    		   }
    	    		   else if (counter==42) {
    	    			   cells[i][j].setValue("ladder4end");
    	    		   }



    	    		   counter+=1;
    	    				   
    	    	   }
    	}
    	    
 
    	    
    	    


    }
    private void initializeSnakesAndLaddersForMedium() {
        // Ladders with lengths 1 to 6
        for (int i = 0; i < ladders.length; i++) {
            int[] startPosition = generateRandomPosition();
            int[] endPosition = {startPosition[0] + i + 1, startPosition[1]}; // Adjust for board boundaries
            ladders[i] = new Ladder(startPosition[0], startPosition[1], endPosition[0], endPosition[1]);
        }

        // Snakes - two red, two green, one blue, one yellow
        Color[] snakeColors = {Color.RED, Color.RED, Color.GREEN, Color.GREEN, Color.BLUE, Color.YELLOW};
        for (int i = 0; i < snakes.length; i++) {
            int[] startPosition = generateRandomPosition();
            int[] endPosition = generateRandomPosition();
            snakes[i] = new Snake(startPosition[0], startPosition[1], endPosition[0], endPosition[1], snakeColors[i]);
            randomlyAssignQuestionCells(3);

        }
        
    }
    private void initializeSnakesAndLaddersForHard() {
        // Ladders with lengths 1 to 8,
        for (int i = 0; i < ladders.length; i++) {
            int[] startPosition = generateRandomPosition();
            int[] endPosition = {startPosition[0] + i + 1, startPosition[1]}; // Adjust for board boundaries
            ladders[i] = new Ladder(startPosition[0], startPosition[1], endPosition[0], endPosition[1]);
        }

        // Snakes - two of each color
        Color[] snakeColors = {Color.RED, Color.RED, Color.GREEN, Color.GREEN, Color.BLUE, Color.BLUE, Color.YELLOW, Color.YELLOW};
        for (int i = 0; i < snakes.length; i++) {
            int[] startPosition = generateRandomPosition();
            int[] endPosition = generateRandomPosition();
            snakes[i] = new Snake(startPosition[0], startPosition[1], endPosition[0], endPosition[1], snakeColors[i]);
        }
        randomlyAssignQuestionCells(3);

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

	public Square[][] getCells() {
		return cells;
	}

	public void setCells(Square[][] cells) {
		this.cells = cells;
	}

	public Snake[] getSnakes() {
		return snakes;
	}

	public void setSnakes(Snake[] snakes) {
		this.snakes = snakes;
	}

	public Ladder[] getLadders() {
		return ladders;
	}

	public void setLadders(Ladder[] ladders) {
		this.ladders = ladders;
	}




}
