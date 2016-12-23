package zuma;

public class Track {
	
	ListBall listballs;
	int headX;
	
	public Track() {
		listballs = new ListBall();
		headX = 0;
	}
	
	public void addBall(Ball newBall) {
		listballs.addBall(newBall);
		headX++;
	}
	
	public void insertBall(Ball insBall, int pos) {
		listballs.insertBall(pos, insBall);
		
	}
}