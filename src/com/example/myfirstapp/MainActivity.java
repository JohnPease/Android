package com.example.myfirstapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    protected void onPause(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_main);
    	EditText editText = (EditText) findViewById(R.id.edit_message);
    	
    	editText.setText("Enter text here");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    /* called when the user clicks the Send button */
    public void sendMessage(View view) {
    	/* 
    	 * an intent is an object that provides a runtime binding between separate components (such as two activities)
    	 * represents an app's "intent to do something"
    	 * usually used to start another activity
    	 * intents can carry a collection of data types as key-value pairs called extras
    	 * putExtra takes the key name in the first parameter and the value in the second
    	 * the key should somehow be related to your package name
    	 * every activity has to be invoked by an Intent
    	 */
    	
    	Intent intent;
    	EditText editText 	= (EditText) findViewById(R.id.edit_message);
    	String message 		= editText.getText().toString();
    	
    	if (message.toLowerCase().contains("second activity")) {
    		intent = new Intent(this, SecondActivity.class);
    	} else {
    		intent = new Intent(this, DisplayMessageActivity.class);
    	}
    	
    	/* add the message into the intent */
    	intent.putExtra(EXTRA_MESSAGE, message);
    	
    	/* start the activity */
    	startActivity(intent);
    	
    	
    }
    
}
