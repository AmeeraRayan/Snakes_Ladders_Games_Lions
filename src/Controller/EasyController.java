package Controller;

import java.awt.Point;
import Model.Game;
import Model.Player;

public class EasyController {
	private Game game;

	public EasyController(Game game) {
		super();
		this.game = game;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Point boardPositionToPixel(int boardPosition,Player currentPlayer) {
	    int xDiff = 80; // the horizontal distance between squares
	    int yDiff = 75; // vertical distance between squares

	    int row = (boardPosition - 1) / 7;
	    int col = (boardPosition - 1) % 7;

	    int x = 0;
	    int y = 0;
	    Model.Color color = currentPlayer.getColor();
	    if (color.equals(Model.Color.BLUE)) {
	        x = 290;
	        y = 630;
	    } else if (color.equals(Model.Color.GREEN)) {
	        x = 320;
	        y = 630;
	    } else if (color.equals(Model.Color.RED)) {
	        x = 290;
	        y = 660;
	    } else if (color.equals(Model.Color.YELLOW)) {
	        x = 320;
	        y = 660;
	    }


	    x += col * xDiff;
	    y -= row * yDiff;

	    return new Point(x, y);
	}
	
	  public void updatePlayerPosition(int steps,Player currentPlayer) {
	        int currentPosition = currentPlayer.getPosition();
	        int newPosition = currentPosition + steps;
	        if (newPosition <= 0) {
	            newPosition = 1; // Prevent moving beyond the start
	        }

	        currentPlayer.setPosition(newPosition);
          game.getCurrentPlayer().setPosition(newPosition);
  	    currentPlayer.setPosition(newPosition);
  	    game.updatePlayerPositionInList(currentPlayer.getName(), newPosition);
  	    

	    }
	
}
