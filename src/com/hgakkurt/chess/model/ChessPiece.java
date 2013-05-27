package com.hgakkurt.chess.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class ChessPiece{

	private int x;
	private int y;
	private Bitmap bitmap;
	private PieceType type;

	private enum PieceType {
		KING, QUEEN, ROOK, BISHOP, KNIGHT, PAWN
	}

	public ChessPiece() {
		// Default constructor
		this.setXPosition(0);
		this.setYPosition(0);
		this.setPieceBitmap(null);
		this.setPieceType(null);

	}

	public ChessPiece(int x, int y, Resources resoures, int drawableID){
		this.setXPosition(x);
		this.setYPosition(y);
		this.setPieceBitmap(BitmapFactory.decodeResource(resoures, drawableID));	
	}


	public int getXPosition() {
		return x;
	}

	public void setXPosition(int x) {
		this.x = x;
	}

	public int getYPosition() {
		return y;
	}

	public void setYPosition(int y) {
		this.y = y;
	}

	public Bitmap getPieceBitmap() {
		return bitmap;
	}

	public void setPieceBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}	

	public ChessPiece.PieceType getPieceType(){
		return type;
	}

	public void setPieceType(ChessPiece.PieceType type){
		this.type = type;
	}
}
