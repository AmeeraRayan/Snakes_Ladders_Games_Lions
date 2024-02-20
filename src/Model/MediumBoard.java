package Model;

import View.WinnerPage;

public class MediumBoard extends BoardLevelTemplate{
	public MediumBoard() {
		super(10);
		BoardLevelTemplate.cells = new Square[10][10];
    	BoardLevelTemplate.snakes = new Snake[6];
    	BoardLevelTemplate.ladders = new Ladder[6];
    	BoardLevelTemplate.questions = new Square[3];
	}

	@Override
	protected boolean endGame(int index, Game game) {
      	 new WinnerPage(index , game).setVisible(true);
		return true;
	}


	@Override
	public void startGame(Square[][] cellsformeduim, Snake[] snakesformeduim, Ladder[] laddersformeduim,
			Square[] questionSquares,int number) {
		cells = cellsformeduim;
		snakes = snakesformeduim;
		ladders = laddersformeduim;
		questions = questionSquares;		
	}

}
