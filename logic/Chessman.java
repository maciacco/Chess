package logic;

/**
 * @author FrancescoCerruti&MarioCiacco
 */
public abstract class Chessman {
	
	// data members
	private String name;
	private boolean white;
	private Position position;
	
	// getters and setters
	public String getName() {
		return name;
	};
	
	public void setName(String name) {
		this.name = name;
	};
	
	public boolean isWhite() {
		return white;
	};
	public void setWhite(boolean white) {
		this.white = white;
	};
	
	public Position getPosition() {
		return position;
	};
	public void setPosition(Position position) {
		this.position = position;
	};
		
	// abstract methods
	public abstract int CheckMotionRule(Position target);
	public abstract int CheckCaptureRule(Position target);
		
}

//1---column
//2---row
//3---diagonal
//4---knight