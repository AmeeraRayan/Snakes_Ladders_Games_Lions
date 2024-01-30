package Model;

import java.util.ArrayList;
import java.util.List;


public class Board {
	private int id;
	private int size;
    private List<Snake> snakes;
    private List<Ladder> ladders;

    public Board(int size) {
        this.size = size;
        this.snakes = new ArrayList<>();
        this.ladders = new ArrayList<>();
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

    public void addSnake(Snake snake) {
        snakes.add(snake);
    }

    public void addLadder(Ladder ladder) {
        ladders.add(ladder);
    }

}
