package Model;

import java.awt.Color;

public class Player {
	private int playerId;
	private String name;
	private Color color;
	private int position;
	
	public Player(String name , Color color) {
	    this.name = name;
	    this.position = 1; // Starting position
	    this.color = color;
	    
	}
	public String getName() {
	    return name;
	}

	public int getPosition() {
	    return position;
	}
	public void setPosition(int position) {
	   this.position = position;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	
}
