/**
 * 
 */
package graphic;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import logic.Chessman;

/**
 * @author FrancescoCerruti&MarioCiacco
 */
public class GraphicChessman {

	// data members	
	private String filePath = "texture/";
	private final int IMAGE_SIZE = 50; 
	private Chessman logicChessman;
	private StackPane chessmanPane = new StackPane();
	
	// methods

	/**
	 * default constructor
	 */
	public GraphicChessman(){};

	/**
	 * constructor
	 * @param logicChessman
	 */
	public GraphicChessman(Chessman logicChessman, Chessboard board){
		
		// sets background image to GraphicChessman
		this.setPicture(new Image(this.filePath.concat(logicChessman.getName()+".png"), 
				IMAGE_SIZE, IMAGE_SIZE, false, false));
		this.logicChessman = logicChessman;
		
		// positions StackPane graphicChessman on the GridPane Board (static)
		board.Board.add(chessmanPane, logicChessman.getPosition().getColumn(), logicChessman.getPosition().getRow());	
	};

	// getter for chessmanPane
	public StackPane getChessmanPane() {
		return chessmanPane;
	};

	// getter for logicChessman
	public Chessman getLogicChessman() {
		return logicChessman;
	};
	
	/**
	 * sets background image to StackPane chessmanPane
	 * @param picture
	 */
	private void setPicture(Image picture) {
		this.chessmanPane.setBackground(new Background(new BackgroundImage(
				picture,
				BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT)));		
	};
		
} // end of class Chessman
