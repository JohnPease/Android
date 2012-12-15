package com.example.myfirstapp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

		Intent intent = getIntent();
		String entered = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		String message = "you typed: " + entered;
		
		/*
		TextView textView = new TextView(this);
		textView.setTextSize(40);
		textView.setText(message);
		
		setContentView(textView);
		*/
		
		/* edit the text in the CenterLabel textview */
		TextView centerLabel = (TextView)findViewById(R.id.CenterLabel);
		centerLabel.setText(message);
		
		/*
		 * populate the spinner with the words typed
		 * the code below probably doesn't work
		 *
		 *
		
		Spinner spin = (Spinner)findViewById(R.id.CenterSpinner);
		
		String enteredArray[] = entered.split(" ");
		List<String> words = new ArrayList<String>();
		
		for (int i = 0; i < enteredArray.length; i++) words.add(enteredArray[i]);
		
		ArrayAdapter<String> options = new ArrayAdapter<String>(this, R.layout.activity_second, words);
		
		*/
	}

}
