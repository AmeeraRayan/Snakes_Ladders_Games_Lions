package Model;

public class Ladder {
	private Square squareStart;
	private Square squareEnd;
	public Square getSquareStart() {
		return squareStart;
	}
	public void setSquareStart(Square squareStart) {
		this.squareStart = squareStart;
	}
	public Square getSquareEnd() {
		return squareEnd;
	}
	public void setSquareEnd(Square squareEnd) {
		this.squareEnd = squareEnd;
	}
	public Ladder(Square squareStart, Square squareEnd) {
		super();
		this.squareStart = squareStart;
		this.squareEnd = squareEnd;
	}

}
