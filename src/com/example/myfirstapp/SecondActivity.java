package com.example.myfirstapp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends Activity implements OnItemSelectedListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

		Intent intent = getIntent();
		String entered = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		String message = "you typed: " + entered;
		
		/* 
		 * edit the text in the CenterLabel textview 
		 */
		TextView centerLabel = (TextView)findViewById(R.id.CenterLabel);
		centerLabel.setText(message);
		
		/*
		 * populate the spinner with the words typed
		 */
		Spinner spin 			= (Spinner)findViewById(R.id.CenterSpinner);
		String enteredArray[] 	= entered.split(" ");
		List<String> words 		= new ArrayList<String>();

		/*
		 * put all of the words entered into the array for the spinner
		 * create the set of options for the spinner
		 */
		for (int i = 0; i < enteredArray.length; i++) words.add(enteredArray[i]);
		
		ArrayAdapter<String> options = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, words);
		options.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin.setAdapter(options);
		
		spin.setOnItemSelectedListener(this);
		
		//String chosen = String.valueOf(spin.getSelectedItem());
		//Toast.makeText(this, "You selected \"" + chosen + "\" from the Spinner", Toast.LENGTH_SHORT);
	}
	
	public void onItemSelected(AdapterView<?> parent, View view, 
            int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
		Toast.makeText(this, "You selected \"" + parent.getItemAtPosition(pos) + "\" from the Spinner", Toast.LENGTH_SHORT);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

}
