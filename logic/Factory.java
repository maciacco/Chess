package logic;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author FrancescoCerruti&MarioCiacco
 *
 */

public class Factory {

	// data members	
	private static List<Chessman> list;

	/**
	 * adds newly instantiated chessmen to list
	 */
	public Factory(){
		// instantiates list of Chessman
		list = new ArrayList<Chessman>() ;
		
		// positioning WhitePawn
		for(int i = 0; i < Position.SIZE; i++)
			list.add(new Pawn(true,7,i+1));
		// positioning BlackPawn
		for(int i = 0; i < Position.SIZE; i++)
			list.add(new Pawn(false,2,i+1));
		
		// positioning WhiteRook
		list.add(new Rook(true,8,1));
		list.add(new Rook(true,8,8));
		// positioning BlackRook
		list.add(new Rook(false,1,1));
		list.add(new Rook(false,1,8));
		
		// positioning WhiteKnight
		list.add(new Knight(true,8,2));
		list.add(new Knight(true,8,7));
		// positioning BlackKnight
		list.add(new Knight(false,1,2));
		list.add(new Knight(false,1,7));
		
		// positioning WhiteBishop
		list.add(new Bishop(true,8,3));
		list.add(new Bishop(true,8,6));
		// positioning BlackBishop
		list.add(new Bishop(false,1,3));
		list.add(new Bishop(false,1,6));
		
		// positioning Queen
		list.add(new Queen(true,8,4));
		list.add(new Queen(false,1,4));
		
		// positioning King
		list.add(new King(true,8,5));
		list.add(new King(false,1,5));

	}; 
	
	// get list from outside
	public List<Chessman> getList(){
		return list;
	};

	// handle list from outside
	public void Remove(Chessman chessman){
		list.remove(chessman);
	};

	public void Add(Chessman chessman){
		list.add(chessman);
	};

}