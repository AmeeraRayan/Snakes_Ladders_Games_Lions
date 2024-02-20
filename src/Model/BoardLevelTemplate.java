package Model;

public abstract class BoardLevelTemplate  {
	protected int size;
	protected static Square[][] cells;
	protected static Snake[] snakes;
	protected static Ladder[] ladders;
	protected static Square[] questions;
	public abstract void startGame(Square[][] cellsformeduim,Snake[] snakesformeduim,Ladder[] laddersformeduim,Square[] questionSquares,int number);
	public abstract boolean endGame(int index,Game game);	
	 public BoardLevelTemplate(int size) {
	        this.size = size;  
	    }
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public  Square[][] getCells() {
		return cells;
	}
	public  void setCells(Square[][] cells) {
		BoardLevelTemplate.cells = cells;
	}
	public  Snake[] getSnakes() {
		return snakes;
	}
	public static void setSnakes(Snake[] snakes) {
		BoardLevelTemplate.snakes = snakes;
	}
	public Ladder[] getLadders() {
		return ladders;
	}
	public void setLadders(Ladder[] ladders) {
		BoardLevelTemplate.ladders = ladders;
	}
	public  Square[] getQuestions() {
		return questions;
	}
	public  void setQuestions(Square[] questions) {
		BoardLevelTemplate.questions = questions;
	}
	public final void playGame()
	{
		startGame(null, null, null, null,1);
		endGame(0,null);
	}
	
	


}
