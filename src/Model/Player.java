package Model;

import java.awt.Color;

public class Player {
	private int playerId;
	private String name;
	private Enum<Model.Color> color;
	private int position;
	
	public Player(String name , Enum<Model.Color> color) {
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
	public Enum<Model.Color> getColor() {
		return color;
	}
	public void setColor(Enum<Model.Color> color) {
		this.color = color;
	}
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	
}
