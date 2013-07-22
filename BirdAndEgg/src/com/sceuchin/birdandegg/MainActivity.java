package com.sceuchin.birdandegg;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Button Sound
		final MediaPlayer buttonSound = MediaPlayer.create(MainActivity.this, R.raw.button);
		
		//Setting up the button references
		Button play = (Button) findViewById(R.id.playButton);
		
		play.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				buttonSound.start();
				startActivity(new Intent("com.sceuchin.birdandegg.BIRDFLY"));
			}
		});
	}

	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}


	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()) {
		case R.id.help:
			startActivity(new Intent("com.sceuchin.birdandegg.HELP"));
			return true;
		case R.id.exit:
			Toast exitMsg = Toast.makeText(MainActivity.this, "Thanks for playing! =)", Toast.LENGTH_LONG);
			exitMsg.show();
			finish();
			return true;
		}
		
		return false;
	}

}
