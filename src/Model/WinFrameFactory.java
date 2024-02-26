package Model;

import View.BlueWin;
import View.GreenWin;
import View.RedWin;
import View.YellowWin;

public class WinFrameFactory {
	public WinFrame getFrame(Color color, String winner,String time)
	{
		switch (color) {
		case RED:
			return new RedWin(winner,time);
		case GREEN:
			return new GreenWin(winner,time);
		case BLUE:
			return new BlueWin(winner,time);
		case YELLOW:
		return new YellowWin(winner,time);

		} 
		return null;
		
	}

}

