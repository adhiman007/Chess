package com.hgakkurt.chess.ui;

import android.content.Context;

import com.hgakkurt.chess.view.ChessBoardView;

public class ChessViewFactory {
	
	public static ChessBoardView generateBoardView(Context context){
		return new ChessBoardView(context);		
	}
}
