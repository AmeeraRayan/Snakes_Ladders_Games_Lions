package Model;

public class BoardSnake {
	private BoardSquare squareStart;
	private BoardSquare squareEnd;
	private Enum<Model.Color> color;
	public BoardSnake(BoardSquare squares, BoardSquare squares2) {
		super();
		this.squareStart = squares;
		this.squareEnd = squares2;
	}
	public BoardSquare getSquareStart() {
		return squareStart;
	}
	public void setSquareStart(BoardSquare squareStart) {
		this.squareStart = squareStart;
	}
	public BoardSquare getSquareEnd() {
		return squareEnd;
	}
	public void setSquareEnd(BoardSquare squareEnd) {
		this.squareEnd = squareEnd;
	}
	public Enum<Model.Color> getColor() {
		return color;
	}
	public void setColor(Enum<Model.Color> color) {
		this.color = color;
	}
		
}
