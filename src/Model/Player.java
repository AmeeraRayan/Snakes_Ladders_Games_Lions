package Model;



public class Player {
	private int playerId;
	private String name;
	private Color  color;
	private int position;
    private int lastPosition;

	public int getLastPosition() {
		return lastPosition;
	}

	public void setLastPosition(int lastPosition) {
		this.lastPosition = lastPosition;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setPosition(int newPosition) {
        this.position = newPosition; 
    }

	public Player(String name ,Color color) {
	    this.name = name;
	    this.position = 1; // Starting position
	    this.color = color;
		this.lastPosition = 1;
	    
	}

	public String getName() {
	    return name;
	}

	public int getPosition() {
	    return position;
	}
	
	public Color getColor() {
		return color;
	}
	public void setColor(Color  color) {
		this.color = color;
	}
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	
}
