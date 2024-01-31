package Model;

import java.util.ArrayList;
import java.util.List;


public class Board {
	private int id;
	private int size;
    private Square[][] cells;
    private List<Snake> snakes;
    private List<Ladder> ladders;

    public Board(int size) {
        this.size = size;        
        this.cells = new Square[size][size];
        this.snakes = new ArrayList<>();
        this.ladders = new ArrayList<>();
        initializeCells();
    }

    public int getSize() {
        return size;
    }

    public List<Snake> getSnakes() {
        return snakes;
    }

    public List<Ladder> getLadders() {
        return ladders;
    }
 
    private void initializeCells() {
        // Initialize cells with positions, you can customize as needed
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
            	SquareType cellType = (row + col) % 2 == 0 ? SquareType.QUESTION : SquareType.SURPRISE;
                cells[row][col] = new Square(row, col, cellType);
            }
        }
    }

    public void addSnake(Snake snake) {
        snakes.add(snake);
    }

    public void addLadder(Ladder ladder) {
        ladders.add(ladder);
    }

}
