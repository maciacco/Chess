package logic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author FrancescoCerruti&MarioCiacco
 */
public class Position {
	
	// data members
	public static final int SIZE = 8;
	private int row;
	private int column;
	public List<Boolean> CheckMateTarget = new ArrayList<>();
	
	// default constructor
	public Position() {};
	// using-field constructor
	public Position(int row, int column){
		if((row > 0 && row <= SIZE) && (column > 0 && column <= SIZE)){
			this.row = row;
			this.column = column;
		}
	};
	
	// methods 
	public int getRow() {
		return row;
	};
	
	public void setRow(int row) {
		if(row > 0 && row <= SIZE)
			this.row = row;
	};
	
	public int getColumn() {
		return column;
	};
	
	public void setColumn(int column) {
		if(column > 0 && column <= SIZE)
			this.column = column;
	};
	
} // end of class Position
