package graphic;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logic.ChessManager;

/**
 * @author FrancescoCerruti&MarioCiacco
 */


public class ChessGame extends Application {

	private Label instructions = new Label("Left click on square or opponent chessman"
			+ "\nand then on your chessman to move or capture");

	// main method launches application
	public static void main(String[] args) {
		launch("");

	};

	@Override
	public void start(Stage primaryStage) throws Exception {

		// creating GraphicManager
		GraphicManager manager = GraphicManager.getInstance();

		// BorderPane lays out children in top, left, right, bottom, and center positions.
		BorderPane border = new BorderPane();
		VBox vBox = new VBox();
		HBox hBox = new HBox();

		// creating chessboard and button
		Chessboard board = Chessboard.getInstance();
		// creating button box
		BorderPane buttonBox = ButtonBox(manager, board);

		board.fillGridWithPanes(Chessboard.SIZE+1, Chessboard.SIZE+1);
		
		// activating chessboard
		manager.listenToMouse(board);

		// begin game		
		placeGraphicChessman(manager, board);
		manager.resetCurrentObject();

		// setting label style and size
		manager.label.setPadding(new Insets(5,20,8,30));
		manager.label.setPrefSize(430, 50);
		manager.label.setFont(Font.font("Arial", FontWeight.BOLD, 25));

		manager.turnLabel.setPadding(new Insets(5,20,8,20));
		manager.turnLabel.setPrefSize(250, 50);
		manager.turnLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));

		hBox.getChildren().addAll(manager.label, manager.turnLabel);

		// setting style and size for instructions
		instructions.setPadding(new Insets(8,20,5,30));
		instructions.setPrefSize(430, 50);
		instructions.setFont(Font.font("Arial", FontWeight.BOLD, 15));

		vBox.getChildren().addAll(buttonBox,hBox);
		border.setBottom(vBox);
		border.setTop(instructions);
		border.setCenter(board.Board);

		Scene scene = new Scene(border, 445, 565);
		primaryStage.setTitle("Chess");
		primaryStage.getIcons().add(new Image("texture/BlackKnight.png"));
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

	};

	private BorderPane ButtonBox(GraphicManager manager, Chessboard board) {
		BorderPane Box = new BorderPane();

		Button Refresh = new Button("Refresh");
		Refresh.setPrefSize(100, 10);
		Refresh.setOnMouseClicked(e-> {
			Refresh(manager,board);
			placeGraphicChessman(manager, board);

		}
				);
		Box.setCenter(Refresh);

		return Box;		
	}	

	private void placeGraphicChessman(GraphicManager manager, Chessboard board){
		ChessManager.Begin();
		// instantiates GraphicChessman from list in ChessManager
		System.out.println(ChessManager.getFactory().getList());
		ChessManager.getFactory().getList().forEach(c -> {
			
			GraphicChessman chessman = new GraphicChessman(c, board);

			// makes chessman interactive for playing
			if(chessman.getLogicChessman().isWhite())
				manager.whitePlay(chessman, board);
			else
				manager.blackPlay(chessman, board);
		});
		
		ChessManager.setWhitePlayerMove(true);
		manager.turnLabel.setText("White turn");
		manager.label.setText("Chess Game");

	};

	private void Refresh(GraphicManager manager,Chessboard board){
		
		// removes graphic chessmen from board
		board.Board.getChildren().clear();
		board.fillGridWithPanes(Chessboard.SIZE+1, Chessboard.SIZE+1);
		
		// activating chessboard
		manager.listenToMouse(board);

	};

} // end of class ChessGame
