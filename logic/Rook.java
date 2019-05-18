package logic;

/**
 * @author FrancescoCerruti&MarioCiacco
 */
public class Rook extends Chessman {

	// constructor for Rook
	public Rook(boolean white, int row, int column) {
		if(white){
			super.setName("WhiteRook");
		}
		else{
			super.setName("BlackRook");		
		}
		super.setWhite(white);
		super.setPosition(new Position(row,column));
	};

	@Override
	public int CheckMotionRule(Position target) {
		if(target != null) {
			if(target.getColumn() == super.getPosition().getColumn())
				return 1;

			else if (target.getRow() == super.getPosition().getRow()) 
				return 2;
		}
		return 0;
	}

	@Override
	public int CheckCaptureRule(Position target) {
		return CheckMotionRule(target);
	}

}; // end of class Rook
