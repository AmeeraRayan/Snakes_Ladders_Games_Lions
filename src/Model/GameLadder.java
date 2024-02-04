package Model;

public class GameLadder {
	private BoardSquare startSquare;
	private BoardSquare endSquare; 
	
	public GameLadder(BoardSquare startSquare, BoardSquare endSquare) {
		super();
		this.setStartSquare(startSquare);
		this.setEndSquare(endSquare);
	}

	public BoardSquare getStartSquare() {
		return startSquare;
	}

	public void setStartSquare(BoardSquare startSquare) {
		this.startSquare = startSquare;
	}

	public BoardSquare getEndSquare() {
		return endSquare;
	}

	public void setEndSquare(BoardSquare endSquare) {
		this.endSquare = endSquare;
	}
	
}
