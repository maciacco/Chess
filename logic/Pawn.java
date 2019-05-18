package logic;

/**
 * @author FrancescoCerruti&MarioCiacco
 */
public class Pawn extends Chessman {

	// data member of Pawn
	private boolean firstMove = true;
	
	// constructor for Pawns
	public Pawn(boolean white, int row, int column) {
		if(white){
			super.setName("WhitePawn");
		}
		else{
			super.setName("BlackPawn");
		}
		super.setWhite(white);
		super.setPosition(new Position(row,column));
	};

	@Override
	public int CheckMotionRule(Position target) {
		if(target!= null && (target.getColumn() == super.getPosition().getColumn())) {
			// first move for Pawn
			if(firstMove){
				// if pawn is white, it can go till row 5
				if(super.isWhite() && (target.getRow() == 5 || target.getRow() == 6) ){
					firstMove = false;
					return 1;
				}	
				// if pawn is black, it can go to row up to 4
				else if(!(super.isWhite()) && (target.getRow() == 3 || target.getRow() == 4) ) {
					firstMove = false;
					return 1;
				}
			}
			// not first move for Pawn
			else{
				if(((super.isWhite() && (target.getRow() == super.getPosition().getRow()-1))) ||
						(!(super.isWhite()) && (target.getRow() == super.getPosition().getRow()+1)))
					return 1;	
			}
		}
		return 0;
	};

	@Override
	public int CheckCaptureRule(Position target) {
		if(target != null) {
			if( (target.getColumn() == super.getPosition().getColumn() + 1) ||
					(target.getColumn() == super.getPosition().getColumn() - 1) ) {
				if((target.getRow() == super.getPosition().getRow() - 1) && isWhite()) {
					if(firstMove)
						firstMove = false;
					return 3;
				}
				else if((target.getRow() == super.getPosition().getRow() + 1) && !isWhite()) {
					if(firstMove)
						firstMove = false;
					return 3;
				}
			}
		}
		return 0;
	};
	
} // end of class Pawn
