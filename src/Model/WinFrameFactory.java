package Model;

import View.BlueWin;
import View.GreenWin;
import View.RedWin;
import View.YellowWin;

public class WinFrameFactory {
	public WinFrame getFrame(Color color)
	{
		switch (color) {
		case RED:
			return new RedWin();
		case GREEN:
			return new GreenWin();
		case BLUE:
			return new BlueWin();
		case YELLOW:
			return new YellowWin();

		} 
		return null;
		
	}

}

