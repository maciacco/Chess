package logic;

/**
 * @author FrancescoCerruti&MarioCiacco
 */
public class King extends Chessman {

	// constructor for King
	public King(boolean white, int row, int column) {
		if(white){
			super.setName("WhiteKing");
		}
		else{
			super.setName("BlackKing");		
		}
		super.setWhite(white);
		super.setPosition(new Position(row,column));
	};

	@Override
	public int CheckMotionRule(Position target) {
		if(target != null) {
			if((target.getColumn() == super.getPosition().getColumn()) && 
					(Math.abs(target.getRow() - super.getPosition().getRow()) == 1))
				return 1;
			else if ((target.getRow() == super.getPosition().getRow()) && 
					(Math.abs(target.getColumn() - super.getPosition().getColumn()) == 1)) 
				return 2;
			else if((Math.abs(target.getColumn()-super.getPosition().getColumn()) == 
					Math.abs(target.getRow()-super.getPosition().getRow())) 
					&& ( Math.abs(target.getColumn()-super.getPosition().getColumn()) == 1))

				return 3;
		}
		return 0;
	}


	@Override
	public int CheckCaptureRule(Position target) {
		return CheckMotionRule(target);
	}

}; // end of class King
