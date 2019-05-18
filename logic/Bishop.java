package logic;

/**
 * @author FrancescoCerruti&MarioCiacco
 */
public class Bishop extends Chessman {

	public Bishop(boolean white, int row, int column) {
		if(white){
			super.setName("WhiteBishop");
		}
		else{
			super.setName("BlackBishop");
		}
		super.setWhite(white);
		super.setPosition(new Position(row,column));
	};

	@Override
	public int CheckMotionRule(Position target) {
		if(target != null) {
			if((Math.abs(target.getRow() - super.getPosition().getRow()) == 
					Math.abs(target.getColumn() - super.getPosition().getColumn()))
					)
				return 3;
		}
		return 0;
	}

	@Override
	public int CheckCaptureRule(Position target) {
		return CheckMotionRule(target);
	}

}; // end of class Bishop
