package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author FrancescoCerruti&MarioCiacco
 */
public class ChessManager {

	// data members
	private static Position CurrentSquare;
	private static Position WhiteKingPosition;
	private static Position BlackKingPosition;
	private static boolean WhitePlayerMove = true;
	private static Factory factory;	

	// methods
	public static void Begin() {
		factory = new Factory();

	};

	public static Factory getFactory(){
		return factory;
	};

	// getter and setter for WhitePlayerMove
	public static boolean isWhitePlayerMove() {
		return WhitePlayerMove;
	};

	public static void setWhitePlayerMove(boolean whitePlayerMove) {
		WhitePlayerMove = whitePlayerMove;
	};

	// getters and setters for CurrentSquare
	public static Position getCurrentSquare() {
		return CurrentSquare;
	};

	public static void setCurrentSquare(Position currentSquare) {
		CurrentSquare = currentSquare;
	};

	public static void resetCurrentSquare(){
		setCurrentSquare(null);
	};

	public static boolean CheckMotion(Chessman chessman, Position target) {
		switch(chessman.CheckMotionRule(target)) {
		case 1:
			return VerticalMotionCheck(chessman, target);
		case 2:
			return HorizontalMotionCheck(chessman, target);
		case 3:
			return DiagonalMotionCheck(chessman, target);
		case 4:
			return true;	
		default: 
			return false;
		}
	};

	public static boolean CheckCapture(Chessman chessman, Position target) {
		switch(chessman.CheckCaptureRule(target)) {
		case 1:
			return VerticalMotionCheck(chessman, target);
		case 2:
			return HorizontalMotionCheck(chessman, target);
		case 3:
			return DiagonalMotionCheck(chessman, target);
		case 4:
			return true;
		default: 
			return false;
		}
	};

	/**
	 * checks Check condition for Black
	 * @return
	 */
	public static boolean WhiteCheck() {
		// gets list of of White Chessmen on the Board
		List<Chessman> WhiteList = factory.getList().stream()
				.filter(pc -> pc.isWhite() == true)
				.collect(Collectors.toList());

		// gets list of Black Chessmen on the board (except for BlackKing)
		List<Chessman> BlackList = factory.getList().stream()
				.filter(pc -> ((pc.isWhite() == false) && pc.getName() != "BlackKing"))
				.collect(Collectors.toList());

		// gets BlackKing Position
		factory.getList().forEach(chessman ->{
			if (chessman.getName()=="BlackKing")
				BlackKingPosition = chessman.getPosition();
		});

		// ArrayList of position used in nested for-loop
		List<Position> ValidPosition = new ArrayList<>();

		// loop over squares surrounding BlackKing
		for(int i=(BlackKingPosition.getRow()-1); i<(BlackKingPosition.getRow()+2);i++) {
			for(int j=(BlackKingPosition.getColumn()-1); j<(BlackKingPosition.getColumn()+2);j++) {
				if((i>0) && (i<9) && (j>0) && (j<9) /*&& (i!=BlackKingPosition.getRow()) &&(j!=BlackKingPosition.getColumn())*/) {
					int k=i;
					int h=j;
					// gets Black and White chessmen in squares surrounding BlackKing
					List<Chessman> BlackValidPosition = BlackList.stream()
							.filter(chessman -> ((chessman.getPosition().getRow() == k )
									&& (chessman.getPosition().getColumn() == h)))
							.collect(Collectors.toList());
					List<Chessman> WhiteValidPosition = WhiteList.stream()
							.filter(chessman -> ((chessman.getPosition().getRow() == k )
									&& (chessman.getPosition().getColumn() == h)))
							.collect(Collectors.toList());
					// if no piece in square (i,j), checks is position is valid for White Chessmen for capture
					if((BlackValidPosition.size() == 0) && (WhiteValidPosition.size() == 0)) {
						ValidPosition.add(new Position(i,j));
						WhiteList.forEach(chessman ->{
							if(CheckCapture(chessman,ValidPosition.get(ValidPosition.size()-1)))
								ValidPosition.get(ValidPosition.size()-1).CheckMateTarget.add(true);
						});					
					}
				};				
			}
		}

		ValidPosition.forEach(e ->{
			System.out.println("Valid position for WhiteCheck: " + e.getRow() + "  " + e.getColumn());	
		}
				);

		// filters ValidPositions whose CheckMateTarget list is not null
		List<Position> ValidPosition1 = ValidPosition.stream()
				.filter(a -> (a.CheckMateTarget.size()!= 0))
				.collect(Collectors.toList());

		if((ValidPosition.size() == ValidPosition1.size()) && (ValidPosition.size()!=0))
			return true;
		return false;
	}
	
	/**
	 * checks Check condition for Black
	 * @return
	 */
	public static boolean BlackCheck() {
		// gets list of of Black Chessmen on the Board
		List<Chessman> BlackList = factory.getList().stream()
				.filter(pc -> pc.isWhite() == false)
				.collect(Collectors.toList());

		// gets list of White Chessmen on the board (except ffor BlackKing)
		List<Chessman> WhiteList = factory.getList().stream()
				.filter(pc -> ((pc.isWhite() == true) && pc.getName() != "WhiteKing"))
				.collect(Collectors.toList());

		// gets BlackKing Position
		factory.getList().forEach(chessman ->{
			if (chessman.getName()=="WhiteKing")
				WhiteKingPosition = chessman.getPosition();
		});

		// ArrayList of position used in nested for-loop
		List<Position> ValidPosition = new ArrayList<>();

		// loop over squares surrounding BlackKing
		for(int i=(WhiteKingPosition.getRow()-1); i<(WhiteKingPosition.getRow()+2);i++) {
			for(int j=(WhiteKingPosition.getColumn()-1); j<(WhiteKingPosition.getColumn()+2);j++) {
				if((i>0) && (i<9) && (j>0) && (j<9) /*&& (i!=BlackKingPosition.getRow()) &&(j!=BlackKingPosition.getColumn())*/) {
					int k=i;
					int h=j;
					// gets Black and White chessmen in squares surrounding BlackKing
					List<Chessman> WhiteValidPosition = BlackList.stream()
							.filter(chessman -> ((chessman.getPosition().getRow() == k )
									&& (chessman.getPosition().getColumn() == h)))
							.collect(Collectors.toList());
					List<Chessman> BlackValidPosition = WhiteList.stream()
							.filter(chessman -> ((chessman.getPosition().getRow() == k )
									&& (chessman.getPosition().getColumn() == h)))
							.collect(Collectors.toList());
					// if no piece in square (i,j), checks is position is valid for White Chessmen for capture
					if((WhiteValidPosition.size() == 0) && (BlackValidPosition.size() == 0)) {
						ValidPosition.add(new Position(i,j));
						BlackList.forEach(chessman ->{
							if(CheckCapture(chessman,ValidPosition.get(ValidPosition.size()-1)))
								ValidPosition.get(ValidPosition.size()-1).CheckMateTarget.add(true);
						});					
					}
				};				
			}
		}

		ValidPosition.forEach(e ->{
			// System.out.println("Valid position for BlackCheck: " + e.getRow() + "  " + e.getColumn());	
		}
				);

		// filters ValidPositions whose CheckMateTarget list is not null
		List<Position> ValidPosition1 = ValidPosition.stream()
				.filter(a -> (a.CheckMateTarget.size()!= 0))
				.collect(Collectors.toList());

		if((ValidPosition.size() == ValidPosition1.size()) && (ValidPosition.size()!=0))
			return true;
		return false;
	}

	// methods for checking allowed motions
	/**
	 * Checks validity of vertical motion
	 * @param chessman
	 * @param target
	 * @return
	 */
	private static boolean VerticalMotionCheck(Chessman chessman, Position target){
		if(chessman.getPosition().getColumn() == target.getColumn()){
			// setting temporary variables
			Integer chessmanRow = chessman.getPosition().getRow();
			Integer targetRow = target.getRow();
			List<Integer> listOfWalls = factory.getList().stream()
					.map(piece -> piece.getPosition())
					.filter(Position -> Position.getColumn() == chessman.getPosition().getColumn() && 
					Position.getRow() > Integer.min(chessmanRow,targetRow) && 
					Position.getRow() < Integer.max(chessmanRow,targetRow))
					.map(Position -> Position.getRow())
					.collect(Collectors.toList());
			if(listOfWalls.size() == 0)
				return true;
			else{
				// System.out.println("\n" + listOfWalls);
			}
		}
		return false;
	}; // end of method VerticalMotionCheck

	/**
	 * Checks validity of horizontal motion
	 * @param chessman
	 * @param target
	 * @return
	 */
	private static boolean HorizontalMotionCheck(Chessman chessman, Position target){
		if(chessman.getPosition().getRow() == target.getRow()){
			// setting temporary variables
			Integer chessmanColumn = chessman.getPosition().getColumn();
			Integer targetColumn = target.getColumn();
			List<Integer> listOfWalls = factory.getList().stream()
					.map(piece -> piece.getPosition())
					.filter(Position -> Position.getRow() == chessman.getPosition().getRow() && 
					Position.getColumn() > Integer.min(chessmanColumn,targetColumn) && 
					Position.getColumn() < Integer.max(chessmanColumn,targetColumn))
					.map(Position -> Position.getColumn())
					.collect(Collectors.toList());
			if(listOfWalls.size() == 0)
				return true;	
			else{
				// System.out.println("\n" + listOfWalls);
			}
		}
		return false;
	}; // end of method HorizontalMotionCheck

	/**
	 * Checks validity of diagonal motion
	 * @param chessman
	 * @param target
	 * @return
	 */
	private static boolean DiagonalMotionCheck(Chessman chessman, Position target){
		if(Math.abs(chessman.getPosition().getRow() - target.getRow()) == 
				Math.abs(chessman.getPosition().getColumn() - target.getColumn())){
			// setting temporary variables
			Integer chessmanRow = chessman.getPosition().getRow();
			Integer chessmanColumn = chessman.getPosition().getColumn();
			Integer targetRow = target.getRow();
			Integer targetColumn = target.getColumn();

			// going-down-right diagonal \
			if((chessmanColumn == Integer.min(chessmanColumn,targetColumn) && 
					chessmanRow == Integer.min(chessmanRow,targetRow)) ||
					(targetColumn == Integer.min(chessmanColumn,targetColumn) && 
					targetRow == Integer.min(chessmanRow,targetRow)) ){

				List<Integer> listOfWalls = factory.getList().stream()
						.map(piece -> piece.getPosition())
						.filter(Position -> (Position.getColumn()-Position.getRow()) == 
						(chessmanColumn-chessmanRow) && 
						(Position.getColumn() > Integer.min(chessmanColumn,targetColumn) && 
								Position.getColumn() < Integer.max(chessmanColumn,targetColumn)) &&
						(Position.getRow() > Integer.min(chessmanRow,targetRow) && 
								Position.getRow() < Integer.max(chessmanRow,targetRow)) )				
						.map(Position -> Position.getColumn())
						.collect(Collectors.toList());
				if(listOfWalls.size() == 0)
					return true;	
				else{
					System.out.println("\n" + listOfWalls);
				}
			}

			// going-up-right diagonal /
			else {
				List<Integer> listOfWalls = factory.getList().stream()
						.map(piece -> piece.getPosition())
						.filter(Position -> (Position.getColumn()+Position.getRow()) == 
						(chessmanColumn+chessmanRow) && 
						(Position.getColumn() > Integer.min(chessmanColumn,targetColumn) && 
								Position.getColumn() < Integer.max(chessmanColumn,targetColumn)) &&
						(Position.getRow() > Integer.min(chessmanRow,targetRow) && 
								Position.getRow() < Integer.max(chessmanRow,targetRow)) )				
						.map(Position -> Position.getColumn())
						.collect(Collectors.toList());
				if(listOfWalls.size() == 0)
					return true;	
				else{
					// System.out.println("\n" + listOfWalls);
				}
			}		
		}

		return false;
	}; // end of method DiagonalMotionCheck

} // end of class ChessManager
