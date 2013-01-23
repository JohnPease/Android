package com.example.myfirstapp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
		 *
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
		
		*/
		
	}
	
	public void sendRequest(View view) {
		/*
		 * this method gets called when the HTTP Request button is pressed
		 */
		
		/*
		 * http post example
		 */
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost("http://www.example.com/login");
		
		// add the post parameters to the HttpPost object
		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		nameValuePair.add(new BasicNameValuePair("email", "user@gmail.com"));
		nameValuePair.add(new BasicNameValuePair("password", "abcdefgh"));
		
		// encode the URL post parameters
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
		} catch (UnsupportedEncodingException e) {
			//e.printStackTrace();
			Toast.makeText(this, e.getStackTrace().toString(), Toast.LENGTH_SHORT).show();
		}
		
		// get the http response
		try {
			HttpResponse response = httpClient.execute(httpPost);
			String responseString = response.toString();
			
		} catch (ClientProtocolException e) {
			//e.printStackTrace();
			Toast.makeText(this, e.getStackTrace().toString(), Toast.LENGTH_SHORT).show();
		} catch (IOException e) {
			//e.printStackTrace();
			Toast.makeText(this, e.getStackTrace().toString(), Toast.LENGTH_SHORT).show();
		}
	}
	
	/*
	 * this gets called when the save email address button is pressed
	 */
	public void SaveEmailAddress(View view) {
		EditText edit = (EditText)findViewById(R.id.EmailText);
		String email = edit.getText().toString();		
		Toast.makeText(this, email, Toast.LENGTH_LONG).show();
	}
	
	/*
	 * this gets called when the activity is paused, or left
	 * for the notification code
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
