package com.sceuchin.birdandegg;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnTouchListener;

public class BirdFly extends Activity implements OnTouchListener {

	OurView v;
	Bitmap bird, egg;
	float x, y;
	MediaPlayer backgrndMusic;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		v = new OurView(this);
		v.setOnTouchListener(this);
		bird = BitmapFactory.decodeResource(getResources(), R.drawable.bird);
		egg = BitmapFactory.decodeResource(getResources(), R.drawable.egg);
		// Set initial egg position
		x = 100;
		y = 200;
		
		// Make it fullscreen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
				
		setContentView(v);
		// Play background music
		backgrndMusic = MediaPlayer.create(BirdFly.this, R.raw.birdsound);
		backgrndMusic.setLooping(true);
		backgrndMusic.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		backgrndMusic.pause();
		v.pause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		backgrndMusic.start();
		v.resume();
	}

	
	public class OurView extends SurfaceView implements Runnable {

		Thread t = null;
		SurfaceHolder holder;
		boolean notPause = false;
		Sprite birdSprite;
		
		public OurView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			holder = getHolder();
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			birdSprite = new Sprite(OurView.this, bird);
			while (notPause){
				//perform canvas drawing
				if (!holder.getSurface().isValid()){
					continue;
				}
				Canvas c = holder.lockCanvas();
				onDraw(c);
				holder.unlockCanvasAndPost(c);
			}
		}
		
		protected void onDraw(Canvas canvas) {
			canvas.drawARGB(255, 150, 200, 255);	//draw background
			canvas.drawBitmap(egg, x-(egg.getWidth()/2), y-(egg.getHeight()/2), null);
			birdSprite.onDraw(canvas, x, y);
		}
		
		public void pause(){
			notPause = false;
			while(true){
				try{
					t.join();
				}catch(InterruptedException e){
					e.printStackTrace();
				}
				break;
			}
			t = null;
		}
		public void resume(){
			notPause = true;
			t = new Thread(this);
			t.start();
		}
		
	}

	public boolean onTouch(View v, MotionEvent me) {
		// TODO Auto-generated method stub
		
		try {
			Thread.sleep(5);	
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		switch(me.getAction()){
		case MotionEvent.ACTION_DOWN:	//when touch
			x = me.getX();
			y = me.getY();
			break;
		case MotionEvent.ACTION_UP:		//when release
			x = me.getX();
			y = me.getY();
			break;
		case MotionEvent.ACTION_MOVE:	//when dragging
			x = me.getX();
			y = me.getY();
			break;
		}
		
		return true;
	}

}
