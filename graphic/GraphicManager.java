package graphic;

/**
 * @author FrancescoCerruti&MarioCiacco
 */

import javafx.scene.control.Label;
import logic.ChessManager;
import logic.Position;

public class GraphicManager {

	public GraphicChessman CurrentObject;
	protected Label label = new Label("Chess Game");
	protected Label turnLabel = new Label("White turn");
	public static GraphicManager instance = null;

	// constructor
	private GraphicManager() {};

	public static GraphicManager getInstance() {
		if(instance == null)
			return instance = new GraphicManager();
		return instance;
	};

	/**
	 * sets CurrentObject according to Chessman color
	 */
	private void setCurrentObject(GraphicChessman gChessman) {
		CurrentObject = gChessman;	
	};

	/**
	 * resets opposite CurrentObject to null after capture
	 */
	public void resetCurrentObject() {
		CurrentObject = null;
	};

	/**
	 * draws Chessman on the board in position 
	 * given by row index i and column index j
	 * and adds it logic counterpart to list in ChessManager
	 * @param i
	 * @param j
	 */
	private void Draw(GraphicChessman gChessman,Chessboard board, int i, int j) {
		gChessman.getLogicChessman().getPosition().setRow(i);
		gChessman.getLogicChessman().getPosition().setColumn(j);
		ChessManager.getFactory().Add(gChessman.getLogicChessman());
		board.Board.add(gChessman.getChessmanPane(), j, i);
	};

	/** 
	 * removes Chessman from the board
	 * and from list in ChessManager
	 */
	public void Remove(GraphicChessman gChessman,Chessboard board) {
		ChessManager.getFactory().Remove(gChessman.getLogicChessman());
		board.Board.getChildren().remove(gChessman.getChessmanPane());
	};

	/**
	 * enables MouseEvents for Cell (in Chessboard)
	 */
	public void listenToMouse(Chessboard chessboard) {
		chessboard.cellList.forEach(node ->{
			// mouse entering cell
			node.getCellPane().setOnMouseEntered(
					e -> System.out.println("cell " + node.getRowIndex() + " " 
							+ node.getColIndex())
					);

			// mouse clicking cell	
			node.getCellPane().setOnMouseClicked(
					e -> {
						System.out.println("clicked cell " + node.getRowIndex() + " " 
								+ node.getColIndex());
						ChessManager.setCurrentSquare(
								new Position(node.getRowIndex(),
										node.getColIndex()));
						resetCurrentObject();
					}
					);	
		}
				);
	}; // end of method listenToMouse

	/**
	 * makes white GraphicChessman interactive for playing
	 */
	public void whitePlay(GraphicChessman gChessman,Chessboard board) {
		gChessman.getChessmanPane().setOnMouseClicked( e-> {
			if(ChessManager.isWhitePlayerMove() && (label.getText() != "The winner is Black")) {
				Move(gChessman,board);
				Capture(gChessman,board);
				ChessManager.getFactory().getList().forEach(c-> System.out.println(c.getName() + " at " 
						+ c.getPosition().getRow() + " " + c.getPosition().getColumn()));
			}
			else {
				setCurrentObject(gChessman);
				ChessManager.resetCurrentSquare();
			}
		}
				);
	};

	/**
	 * makes black GraphicChessman interactive for playing
	 */
	public void blackPlay(GraphicChessman gChessman,Chessboard board) {
		gChessman.getChessmanPane().setOnMouseClicked( e-> {
			if(!(ChessManager.isWhitePlayerMove()) && (label.getText() != "The winner is White")) {
				Move(gChessman,board);
				Capture(gChessman,board);
				ChessManager.getFactory().getList().forEach(c-> System.out.println(c.getName() + " at " 
						+ c.getPosition().getRow() + " " + c.getPosition().getColumn()));
			}
			else {
				setCurrentObject(gChessman);
				ChessManager.resetCurrentSquare();
			}
		}
				);
	};

	/**
	 * moves Chessman on the board
	 */
	private void Move(GraphicChessman graphicChessman,Chessboard board) {
		System.out.println("clicked " + graphicChessman.getLogicChessman().getName());	
		if(ChessManager.CheckMotion(graphicChessman.getLogicChessman(),ChessManager.getCurrentSquare()) && 
				CurrentObject == null ) {
			// removes StackPane from current position and draws it in target position
			Remove(graphicChessman, board);
			Draw(graphicChessman, board, ChessManager.getCurrentSquare().getRow(),ChessManager.getCurrentSquare().getColumn());

			// resetting Current Cell
			ChessManager.resetCurrentSquare();
			// prevents unwanted captures
			resetCurrentObject();
			
			// update turn and prints label according to Check			
			printLabelCheckTurn();
			
		}
	};

	/**
	 * moves Chessman on the board given position
	 * @param position
	 */
	private void Move(GraphicChessman graphicChessman, Position position,Chessboard board) {
		System.out.println("clicked " + graphicChessman.getLogicChessman().getName());	
		if(position != null) {
			// removes StackPane from current position and draws it in target position
			Remove(graphicChessman, board);
			Draw(graphicChessman, board, position.getRow(),position.getColumn());

			// resetting Current Cell
			ChessManager.resetCurrentSquare();
			// prevents unwanted captures
			resetCurrentObject();

			// updates turn and prints label according to Check		
			printLabelCheckTurn();
		}
	};

	/**
	 * captures Chessman identified by CurrentBlackObject
	 */
	private void Capture(GraphicChessman graphicChessman, Chessboard board) {
		if(CurrentObject != null && ChessManager.getCurrentSquare() == null) {
			System.out.println("clicked " + graphicChessman.getLogicChessman().getName());	
			if(ChessManager.CheckCapture(graphicChessman.getLogicChessman(), CurrentObject.getLogicChessman().getPosition())) {
				
				Remove(CurrentObject, board);
				// prints winner if King was captured
				printWinner();

				Move(graphicChessman, CurrentObject.getLogicChessman().getPosition(),board);
			}
		}
	};

	/**
	 * Prints labels and updates turn
	 */
	private void printLabelCheckTurn() {
		if(ChessManager.isWhitePlayerMove()) {
			if(label.getText() != "The winner is White") {
				turnLabel.setText("Black turn");
				if(ChessManager.BlackCheck())
					label.setText("Check for Black");
				else if(!ChessManager.BlackCheck()) {	
					if(ChessManager.WhiteCheck())
						label.setText("Check for White");
					else
						label.setText("Chess Game");	
				}
			}
			ChessManager.setWhitePlayerMove(false);}
		else {
			if(label.getText() != "The winner is Black") {
				turnLabel.setText("White turn");
				if(ChessManager.WhiteCheck())
					label.setText("Check for White");
				else if(!ChessManager.WhiteCheck()) {	
					if(ChessManager.BlackCheck())
						label.setText("Check for Black");
					else
						label.setText("Chess Game");	
				}
			}

			ChessManager.setWhitePlayerMove(true);
		}		
	};

	/**
	 * Prints winner if king was captured
	 */
	private void printWinner() {
		// prints label according to King capture
		if (CurrentObject.getLogicChessman().getName() == "BlackKing") {
			System.out.println("Game over for Player 2");
			label.setText("The winner is White");
			turnLabel.setText("");
		}
		else if (CurrentObject.getLogicChessman().getName() == "WhiteKing") {
			System.out.println("Game over for Player 1");
			label.setText("The winner is Black");
			turnLabel.setText("");
		}
	};
	
} // end of class GraphicManager
