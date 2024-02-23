package Model;

public class GameBoard {
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

    public GameBoard(int size) {
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
           // initializeSnakesAndLaddersForHard();
        }
    }

	private void initializeSnakesAndLaddersForEasy() {
		// TODO Auto-generated method stub
		
	}
	private void initializeSnakesAndLaddersForMedium() {
		// TODO Auto-generated method stub
		
	}

}
