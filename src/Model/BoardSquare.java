package Model;

public class BoardSquare {
	    private int row;
	    private int col;
	    private int value;
	    private int boundsX;
	    private int boundsY;
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
	
		public int getboundsX() {
			return boundsX;
		}
		public void setboundsX(int boundsX) {
			this.boundsX = boundsX;
		}
		public int getboundsY() {
			return boundsY;
		}
		public void setboundsY(int boundsY) {
			this.boundsY = boundsY;
		}
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
		public BoardSquare(int row, int col, int value,int boundsX,int boundsY ) {
			super();
			this.row = row;
			this.col = col;
			this.value = value;
			this.boundsX = boundsX;
			this.boundsY = boundsY; 
		}
		@Override
		public String toString() {
			return "Square [row=" + row + ", col=" + col + ", value=" + value + "boundsX=" + boundsX +"boundsX=" + boundsX +"]";
		}

	   
	
}
