package logic;

/**
 * @author FrancescoCerruti&MarioCiacco
 */
public class Queen extends Chessman {

	// constructor for Queen
	public Queen(boolean white, int row, int column) {
		if(white){
			super.setName("WhiteQueen");
		}
		else{
			super.setName("BlackQueen");		
		}
		super.setWhite(white);
		super.setPosition(new Position(row,column));
	};

	@Override
	public int CheckMotionRule(Position target) {
		if(target != null)
			if (target.getColumn() == super.getPosition().getColumn()) 
				return 1;


			else if	(target.getRow() == super.getPosition().getRow()) 
				return 2;

			else if
			(Math.abs(target.getColumn()-super.getPosition().getColumn()) ==
			Math.abs(target.getRow()-super.getPosition().getRow())) 

				return 3;
		return 0;
	};


	@Override
	public int CheckCaptureRule(Position target) {
		return CheckMotionRule(target);
	};
} // end of class Queen
