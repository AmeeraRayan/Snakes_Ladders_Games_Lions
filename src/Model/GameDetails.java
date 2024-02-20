package Model;

public class GameDetails {
	public String winnerName;
	public String difficulty;
	public String time;
	public String getWinnerName() {
		return winnerName;
	}
	public void setWinnerName(String winnerName) {
		this.winnerName = winnerName;
	}
	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "GameDetails [winnerName=" + winnerName + ", difficulty=" + difficulty + ", time=" + time + "]";
	}
    

}
