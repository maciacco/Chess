/**
 * 
 */
package graphic;

import java.util.ArrayList;

import javafx.geometry.Insets;

import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * @author FrancescoCerruti&MarioCiacco
 *
 */
public class Chessboard {

	// chess board size
	public static final int SIZE = 8;
	
	// chess board colors
	private final String LightColor = "#EEC280";
	private final String DarkColor = "#B76F3D";
	
	// list of Cell instantiated
	public ArrayList<Cell> cellList = 
			new ArrayList<Cell>();
	
	// chess board graphics
	public GridPane Board = new GridPane();	

	public static Chessboard instance = null;

	// constructor of class Chessboard
	private Chessboard() {
		Board.setPrefSize(450, 450);	
	};
	// using Singleton Pattern
	public static Chessboard getInstance() {
		if(instance == null)
			instance = new Chessboard();
		return instance;
	};

	/**
	 * fills GridPane with StackPanes
	 * each StackPane is able to detect a MouseEvent
	 * thus creating an interactive chess board
	 * @param args
	 */
	public void fillGridWithPanes(int rows, int cols) {
		for (int i = 1; i < rows; i++) {
			for (int j = 1; j < cols; j++) {
				Cell pane = new Cell(i,j);				
				Board.add(pane.cellPane, i, j);
				cellList.add(pane);
			} // end of j-for loop
		} // end of i-for loop

		//add letters to the grid
		LetterList lettersTop = new LetterList(SIZE);
		lettersTop.addLetterList(Board,0);
		
		LetterList lettersBottom = new LetterList(SIZE);
		lettersBottom.addLetterList(Board,9);
		
		//add numbers to the grid
		NumberList numbersLeft = new NumberList(SIZE);
		numbersLeft.addNumberList(Board,0);
		
		NumberList numbersRight = new NumberList(SIZE);
		numbersRight.addNumberList(Board,9);	

	}; // end of method fillGridWithPanes

	// inner class Cell
	public class Cell{
		// data members
		private final int CELL_SIZE = 50;
		private int RowIndex;
		private int ColIndex;
		private StackPane cellPane = new StackPane();	

		// default constructor	
		public Cell() {};

		// constructor
		public Cell(int i, int j) {
			this.RowIndex = j;
			this.ColIndex = i;
			setSizeAndStyle();
			colorCell(i, j);		
		};

		public int getRowIndex() {
			return RowIndex;
		};

		public int getColIndex() {
			return ColIndex;
		};

		public StackPane getCellPane() {
			return cellPane;
		};

		// methods
		/**
		 * sete size and style of cell
		 */
		private void setSizeAndStyle() {
			cellPane.setPrefSize(CELL_SIZE,CELL_SIZE);
			cellPane.setStyle("-fx-padding: 20;");			
		}; // end of method setSizeAndStyle

		/**
		 * colors cell according to its position on the board
		 * @param i 
		 * @param j
		 */
		private void colorCell(int i, int j) {
			if ((i+j) % 2 == 0)    
				cellPane.setBackground(
						new Background(new BackgroundFill(Color.web(Chessboard.this.LightColor), CornerRadii.EMPTY, Insets.EMPTY)));
			else
				cellPane.setBackground(
						new Background(new BackgroundFill(Color.web(Chessboard.this.DarkColor), CornerRadii.EMPTY, Insets.EMPTY)));		
		}; // end of method colorCell

	}; // end of inner class Cell


	// inner class LetterList
	private class LetterList{
		// data members
		private ArrayList<Label> LetterList = new ArrayList<>();

		// constructor
		public LetterList(int size) {
			fillLetterList(size);	
		}

		// methods 
		/**
		 * fills list with letters up to size
		 * @param size
		 */
		private void fillLetterList(int size) {
			char letter = 'a';
			for(int i = 0; i < size; i++) {		
				this.LetterList.add(new Label("      "+letter));
				letter += 1;
			}
			this.LetterList.forEach(c -> c.setFont(Font.font("Arial",FontWeight.BOLD, 13)));
		}; // end of method fillLetterList

		/**
		 * adds letters to GridPane root
		 * @param root
		 */
		private void addLetterList(GridPane root, int rowIndex) {
			for(int i = 0; i < LetterList.size(); i++)
				root.add(LetterList.get(i),i+1,rowIndex,1,1);			
		}; // end of method addLetterLIst

	}; // end of inner class NumberList


	// inner class NumberList
	private class NumberList{
		// data members
		private ArrayList<Label> NumberList = new ArrayList<>();

		// constructor
		public NumberList(int size) {
			fillNumberList(size);	
		}

		// methods
		/**
		 * fills list with numbers up to size
		 * @param size
		 */
		private void fillNumberList(int size) {
			for(int i = size; i > 0; i--) 
				this.NumberList.add(new Label( "  " + i + "  " ));
			this.NumberList.forEach(c -> c.setFont(Font.font("Arial",FontWeight.BOLD, 13)));
		}; // end of method fillNumberList

		/**
		 * adds numbers to GridPane root
		 * @param root
		 */
		private void addNumberList(GridPane root, int colIndex) {
			for(int i = 0; i < NumberList.size(); i++)
				root.add(NumberList.get(i),colIndex,i+1,1,1);			
		}; // end of method addNUmberList

	}; // end of inner class LetterList

} // end of class Chessboard