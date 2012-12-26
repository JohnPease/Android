package com.example.myfirstapp;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
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
		words.add("Select a word from this list");
		for (int i = 0; i < enteredArray.length; i++) words.add(enteredArray[i]);
		
		ArrayAdapter<String> options = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, words);
		options.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin.setAdapter(options);
		
		spin.setOnItemSelectedListener(this);
		
		//String chosen = String.valueOf(spin.getSelectedItem());
		//Toast.makeText(this, "You selected \"" + chosen + "\" from the Spinner", Toast.LENGTH_SHORT);
		
		/*
		 * show a notification of what the user chose
		 */
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
		mBuilder.setSmallIcon(android.R.drawable.star_big_on);
		mBuilder.setContentTitle("This is the content title");
		mBuilder.setContentText("This is the content text");
		
		Intent resultIntent = new Intent(this, SecondActivity.class);
		
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		stackBuilder.addParentStack(this);
		
		stackBuilder.addNextIntent(resultIntent);
		
		PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
		
		NotificationManager mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		
		mManager.notify(mId, mBuilder.build());
	}
	
	/*
	 * this gets called when the activity is paused, or left
	 */
	protected void onPause(Bundle savedInstanceState) {
		
	}
	
	public void onItemSelected(AdapterView<?> parent, View view, 
            int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
		String word = (String) parent.getItemAtPosition(pos);
		if (!word.equalsIgnoreCase("select a word from this list")) {
			Toast.makeText(this, "You selected \"" + parent.getItemAtPosition(pos) + "\" from the Spinner", Toast.LENGTH_LONG).show();
		}
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

}
