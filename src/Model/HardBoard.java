package Model;

import View.WinnerPage;

public class HardBoard extends BoardLevelTemplate{
	public HardBoard() {
		super(13);
		BoardLevelTemplate.cells = new Square[13][13];
    	BoardLevelTemplate.snakes = new Snake[8];
    	BoardLevelTemplate.ladders = new Ladder[8];
	}


	protected static HardBoard instance = null;
    
//  Singleton Instance
	public static HardBoard getInstance(int size) {
		if (instance == null) {
			instance = new HardBoard();
		}
		return instance;
	}
	
	@Override
	public boolean endGame(int index,Game game) {
		new WinnerPage(index , game).setVisible(true);
     	saveGameDetails(game.getPlayers().get(index));
		return true;
	}

	@Override
	public void startGame(Square[][] cellsformeduim, Snake[] snakesformeduim, Ladder[] laddersformeduim,
			Square[] questionSquares, int number) {
		cells = cellsformeduim;
		snakes = snakesformeduim;
		ladders = laddersformeduim;
		questions = questionSquares;
		
	}

}
