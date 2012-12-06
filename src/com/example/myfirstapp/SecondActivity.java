package com.example.myfirstapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

		Intent intent = getIntent();
		String message = "you typed: " + intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		
		/*
		TextView textView = new TextView(this);
		textView.setTextSize(40);
		textView.setText(message);
		
		setContentView(textView);
		*/
		
		/* edit the text in the CenterLabel textview */
		TextView centerLabel = (TextView) findViewById(R.id.CenterLabel);
		centerLabel.setText(message);
	}

}
