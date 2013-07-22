package com.sceuchin.birdandegg;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.sceuchin.birdandegg.BirdFly.OurView;

public class Sprite {

	int bird_x, bird_y;	//bird position
	int srcX = 0, srcY = 0;
	int xSpeed, ySpeed;
	int height, width;
	Bitmap b;
	OurView ov;
	int currentFrame = 0;
	int direction = 1;
	Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
	Rect dst = new Rect(bird_x-width, bird_y-height, bird_x+width, bird_y+height);

	public Sprite(OurView ourView, Bitmap birdie) {
		// TODO Auto-generated constructor stub
		b = birdie;
		ov = ourView;
		height = b.getHeight()/4;	//4 rows
		width = b.getWidth()/4;		//4 columns
		bird_x = bird_y = 0;
		xSpeed = 0;
		ySpeed = 0;
	}

	public void onDraw(Canvas canvas, float egg_x, float egg_y) {
		// TODO Auto-generated method stub
		update(egg_x, egg_y);
		srcX = currentFrame * width;
		srcY = direction * height;
		//position to cut out from spritesheet
		src.set(srcX, srcY, srcX + width, srcY + height);	
		//position to draw on canvas
		dst.set(bird_x-width, bird_y-height, bird_x+width, bird_y+height);	
		canvas.drawBitmap(b, src, dst, null);
	}

	private void update(float egg_x, float egg_y) {
		// TODO Auto-generated method stub
		
		// 0 = left
		// 1 = right
		// 2 = down
		// 3 = up
		
		//going left
		if (egg_x + width/2 < bird_x){
			xSpeed = -width/2;
			ySpeed = 0;
			direction = 0;
		}
		//going right
		else if (egg_x - width/2 > bird_x){
			xSpeed = width/2;
			ySpeed = 0;
			direction = 1;
		}
		//going down
		else if (egg_y - height/2 > bird_y){
			xSpeed = 0;
			ySpeed = height/2;
			direction = 2;
		}
		//going up
		else if (egg_y + height/2 < bird_y){
			xSpeed = 0;
			ySpeed = -height/2;
			direction = 3;
		}
		//found egg
		else {
			xSpeed = 0;
			ySpeed = 0;
		}
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		currentFrame = ++currentFrame % 4;
		bird_x += xSpeed;
		bird_y += ySpeed;
	}
	
}
