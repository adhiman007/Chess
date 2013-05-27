package com.hgakkurt.chess.activity;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.hgakkurt.chess.R;
import com.hgakkurt.chess.ui.ToastBuilder;
import com.hgakkurt.chess.ui.ChessViewFactory;

public class ChessActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(ChessViewFactory.generateBoardView(ChessActivity.this));
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		showWelcomeMessage();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
	}

	private void showWelcomeMessage(){
		ToastBuilder fact = new ToastBuilder(ChessActivity.this);
		fact.setToastText(getString(R.string.welcome_message));
		fact.show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chess, menu);
		return true;
	}

}
