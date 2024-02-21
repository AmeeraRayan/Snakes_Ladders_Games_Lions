package Model;

import javax.swing.JLabel;

import Controller.GameController;
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
	public boolean endGame(int index , int result , String type , JLabel playerLabel , int WinValue,Game game,GameController controller) {
		boolean flag=false;
        flag = controller.updatePlayerPosition(index, result, "Dice",playerLabel,WinValue);
		return flag;
	}

	@Override
	public void startGame(Square[][] cellsformeduim, Snake[] snakesformeduim, Ladder[] laddersformeduim,
			Square[] questionSquares, int number) {
		cells = cellsformeduim;
		snakes = snakesformeduim;
		ladders = laddersformeduim;
		questions = questionSquares;
		
	}

	public void openFrameForWinner(Player winner,String time,Game game)
	{
		WinFrameFactory winframe=new WinFrameFactory();
		switch (winner.getColor()) {
		case RED:
			WinFrame redFrame= winframe.getFrame(Model.Color.RED);
			redFrame.createWinFrame(winner.getName(), time, game);
			break;
		case GREEN:
			WinFrame greenFrame= winframe.getFrame(Model.Color.GREEN);
			greenFrame.createWinFrame(winner.getName(), time, game);
			break;
		case BLUE:
			WinFrame blueFrame= winframe.getFrame(Model.Color.GREEN);
			blueFrame.createWinFrame(winner.getName(),time, game);
			break;
		case YELLOW:
			WinFrame yellowFrame= winframe.getFrame(Model.Color.GREEN);
			yellowFrame.createWinFrame(winner.getName(),time, game);
			break;

		}}

}
