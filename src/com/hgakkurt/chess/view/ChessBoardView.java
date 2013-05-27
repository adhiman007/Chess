package com.hgakkurt.chess.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import com.hgakkurt.chess.R;
import com.hgakkurt.chess.model.ChessPiece;

public class ChessBoardView extends View{

	private Context context;
	private Integer boardWidth;
	private Integer boardHeight;
	private Integer squareSide;
	private Integer boardPadding;
	private Boolean isSquareSet = false;
	private Paint paint;
	private Resources resources;

	private ChessPiece piece;

	public ChessBoardView(Context context) {
		super(context);
		initializeBoardLayout(context);
	}

	private void initializeBoardLayout(Context context){
		this.context = context;
		this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		this.resources = context.getResources();

		LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

		this.setLayoutParams(layoutParams);
		this.setBackgroundDrawable(resources.getDrawable(R.drawable.square_selector_bg));

		this.setFocusable(true);
		this.setFocusableInTouchMode(true);
		this.requestFocus();		
	}

	public boolean validateTouch(float x, float y, float width, float height) {
		for (int i = 0; i < width; i+= width /8) {
			for (int j = boardPadding; j < height + boardPadding; j+= height/8) {
				if (((x > i) && (x < i + squareSide)) && ((y > j) && (y < j + squareSide))) {
					piece.setXPosition(i);
					piece.setYPosition(j);
					return true;
				}
			}
		}
		return false;
	}

	public boolean validateBoundaries(float x, float y) {
		if ((x >= 0 && x < this.getWidth()) && (y > boardPadding && y < this.getHeight())) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(validateTouch(event.getX(), event.getY(), getWidth(), getWidth())){
			invalidate();
		}
		return true;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_DPAD_UP: 
			if (validateBoundaries(piece.getXPosition(), piece.getYPosition() - squareSide)) 
				piece.setYPosition(piece.getYPosition() - squareSide);
			break;
		case KeyEvent.KEYCODE_DPAD_DOWN:
			if (validateBoundaries(piece.getXPosition(), piece.getYPosition() + squareSide)) 
				piece.setYPosition(piece.getYPosition() + squareSide); 
			break;
		case KeyEvent.KEYCODE_DPAD_LEFT:
			if (validateBoundaries(piece.getXPosition() - squareSide, piece.getYPosition())) 
				piece.setXPosition(piece.getXPosition() - squareSide); 
			break;
		case KeyEvent.KEYCODE_DPAD_RIGHT:
			if (validateBoundaries(piece.getXPosition() + squareSide, piece.getYPosition())) 
				piece.setXPosition(piece.getXPosition() + squareSide); 
			break;
		}
		invalidate();
		return true;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);


		boardWidth = canvas.getWidth();
		boardHeight = canvas.getHeight();
		squareSide = boardWidth / 8;
		// Determine padding value in order to draw the board center
		boardPadding = (boardHeight - (squareSide * 8)) / 2;

		drawBoard(canvas);

		if(piece == null)
			piece = new ChessPiece(0,boardPadding,resources,R.drawable.white_queen);

		drawPiece(canvas, piece);
	}

	private void drawBoard(Canvas canvas) {
		for (int i = 0; i < boardWidth; i += squareSide) {	
			for (int j = boardPadding; j < boardWidth + boardPadding; j += squareSide) {
				paint.setColor(getSquareColor());
				canvas.drawRect(new Rect(i,j, i + squareSide, j + squareSide),paint);
				setSquare();
			}
			setSquare();
		}
	}

	public void drawPiece(Canvas canvas, ChessPiece piece) {
		canvas.drawBitmap(
				piece.getPieceBitmap(),
				null,
				new Rect(piece.getXPosition(),piece.getYPosition(), piece.getXPosition()+squareSide, piece.getYPosition()+squareSide),paint);
	}

	private void setSquare(){
		if(isSquareSet)
			isSquareSet = false;
		else 
			isSquareSet = true;
	}

	private Integer getSquareColor(){
		if(isSquareSet)
			return context.getResources().getColor(R.color.dark_brown);
		else
			return context.getResources().getColor(R.color.light_brown);
	}
}
