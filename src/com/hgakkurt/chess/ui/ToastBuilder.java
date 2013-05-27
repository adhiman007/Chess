package com.hgakkurt.chess.ui;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hgakkurt.chess.R;

public class ToastBuilder extends Toast{

	private TextView toastText;
	
	public ToastBuilder(Context context) {
		super(context);
		initializeLayout(context);
	}

	private void initializeLayout(Context context){
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.toast_layout,null);
		
		// initialize toast textview
		toastText=  (TextView) layout.findViewById(R.id.toast_text);
		
		// toast positioning
		this.setGravity(Gravity.TOP, 0, context.getResources().getDimensionPixelSize(R.dimen.toast_margin_top));
		// toast default duration
		this.setDuration(Toast.LENGTH_LONG);
		// set toast view 
		this.setView(layout);

	}

	public void setToastText(String toastText) {
		this.toastText.setText(toastText);
	}
}
