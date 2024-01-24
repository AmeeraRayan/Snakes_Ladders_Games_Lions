package Model;

public class Player {
	private int playerId;
	private String name;
	private String color;
	private int position;
	public Player(String name) {
	    this.name = name;
	    this.position = 1; // Starting position
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
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	
}
