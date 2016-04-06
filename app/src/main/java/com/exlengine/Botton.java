package com.exlengine;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;


public class Botton {
	MainActivity activity;
	int center_x,center_y;
	Bitmap onBitmap;//按下狀態的圖片
	Bitmap offBitmap;//未按下狀態的圖片
	boolean isOn=false;//按下狀態為true
	int key;
	public Botton(MainActivity activity, Bitmap onBitmap, Bitmap offBitmap, int x, int y){
		this.activity=activity;
		//this.isOn_flag=activity.backgroundsoundFlag;
		this.onBitmap=onBitmap;
		this.offBitmap=offBitmap;
		this.center_x=x;
		this.center_y=y;
	}

	public void drawBtm(Canvas canvas,Paint paint){//繪製按鈕
		if(isOn)
			Graphic.drawPic(canvas,onBitmap,center_x,center_y,0,255,paint);
		else
			Graphic.drawPic(canvas, offBitmap, center_x, center_y, 0, 255, paint);
	}
	public void drawBtm(Canvas canvas,Paint paint,int alpha){//繪製按鈕
		paint.setAlpha(alpha);
        if(isOn)
            Graphic.drawPic(canvas,onBitmap,center_x,center_y,0,alpha,paint);
        else
            Graphic.drawPic(canvas, offBitmap, center_x, center_y, 0, alpha, paint);
		paint.reset();
	} 
	public void drawBtm(Canvas canvas,Paint paint,int x,int y){//繪製按鈕
		move(x,y);
		drawBtm(canvas,paint);
	}
	public void drawBtm(Canvas canvas,Paint paint,int x,int y,int alpha){//繪製按鈕
		paint.setAlpha(alpha);
		move(x,y);
		drawBtm(canvas,paint);
		paint.reset();
	}


	
	public void setBottom(){//切換按鈕狀態
		this.isOn=!this.isOn;
	}
	public boolean getBottom(){
		return this.isOn;
	}
	
	public void setBottomTo(Boolean i){
		this.isOn=i;
	}
	public void move(int x,int y){
		this.center_x=x;
		this.center_y=y;
	}
	public void setKey(int key){
		this.key=key;
	}
	public int getKey(){
		return key;
	}
	public Boolean isIn(float pointx,float pointy){//判斷觸控位置
		double width,height,x,y;
		if(isOn) {
			width = onBitmap.getWidth();
			height = onBitmap.getHeight();
		}else {
			width = offBitmap.getWidth();
			height = offBitmap.getHeight();
		}
		x=Coordinate.CoordinateX(center_x)-(width/2);
		y=Coordinate.CoordinateY(center_y)-(height/2);
		if(pointx>=x&&pointx<=x+width&&      	pointy>=y&&pointy<=y+height)
			return true;
		return false;
	}
    public Boolean isIn(double pointx,double pointy){//判斷觸控位置
        return isIn((float)pointx,(float)pointy);
    }
	public void recycle(){
		onBitmap.recycle();//按下狀態的圖片
		offBitmap.recycle();//未按下狀態的圖片
	}
}
