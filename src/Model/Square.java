package Model;

public class Square {
    private int row;
    private int col;
    private String value;
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Square(int row, int col, String value) {
		super();
		this.row = row;
		this.col = col;
		this.value = value;
	}
	@Override
	public String toString() {
		return "Square [row=" + row + ", col=" + col + ", value=" + value + "]";
	}

   
}
