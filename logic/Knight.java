package logic;

/**
 * @author FrancescoCerruti&MarioCiacco
 */
public class Knight extends Chessman {

	public Knight(boolean white, int row, int column) {
		if(white){
			super.setName("WhiteKnight");
		}
		else{
			super.setName("BlackKnight");
		}
		super.setWhite(white);
		super.setPosition(new Position(row,column));
	};

	@Override
	public int CheckMotionRule(Position target) {
		if( target!= null && ((Math.abs(target.getRow()-super.getPosition().getRow()) ==1 || 
				Math.abs(target.getRow()-super.getPosition().getRow()) == 2) &&
				(Math.abs(target.getColumn()-super.getPosition().getColumn()) == 1 ||
				Math.abs(target.getColumn()-super.getPosition().getColumn()) == 2)	&&
				(Math.abs(target.getColumn()-super.getPosition().getColumn()) 
						+ Math.abs(target.getRow()-super.getPosition().getRow())==3))
				)
			return 4;
		return 0;
	};

	@Override
	public int CheckCaptureRule(Position target) {
		return CheckMotionRule(target);
	};

} // end of class Knight
