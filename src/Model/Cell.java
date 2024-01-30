package Model;

public class Cell {
    private int row;
    private int col;
    private CellType type;

    public Cell(int row, int col, CellType type) {
        this.row = row;
        this.col = col;
        this.type = type;
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
	
	public CellType getType() {
		return type;
	}

	public void setType(CellType type) {
		this.type = type;
	}

	public void applyEffect(Player player) {
        //Apply the player movement according to cell type
        switch (type) {
            case QUESTION:
                // Apply the effect of landing on a question cell
                break;
            case SURPRISE:
                // Apply the effect of landing on a surprise cell
                break;
            // Add more cases for other cell types if needed
        }
    }

}

