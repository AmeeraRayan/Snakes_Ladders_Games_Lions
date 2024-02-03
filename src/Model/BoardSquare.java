package Model;

public class BoardSquare {
	
		private int row;
		private int col;
	    private SquareType type;
	    private int boundsX;
	    private int boundsY;
	    private int value;
	    
	    public BoardSquare(int row, int col, SquareType type, int boundsX, int boundsY, int value) {
			super();
			this.row = row;
			this.col = col;
			this.type = type;
			this.boundsX = boundsX;
			this.boundsY = boundsY;
			this.value = value;
		}
	    
	    
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
		public SquareType getType() {
			return type;
		}
		public void setType(SquareType type) {
			this.type = type;
		}

	
		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
		
	    @Override
		public String toString() {
			return "BoardSquare [row=" + row + ", col=" + col + ", type=" + type + ", boundsX=" + boundsX + ", boundsY="
					+ boundsY + ", value=" + value + "]";
		}
		
	
	
}
