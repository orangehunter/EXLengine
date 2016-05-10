package com.exlengine.ui_script;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.exlengine.script.Graphic;

public class smallAnimax {
	
	Boolean animax_flag=false;
	Boolean pause_flag=false;
	
	double animax_count_flag=0;	
	double count_unit;
	int pic_number=0;
	int duration;
	int start_position;
	int x;
	int y;
	
	Bitmap[]pic;
	
	public smallAnimax(Bitmap pic[]){
		this.pic=new Bitmap[pic.length];
		this.pic=pic;
		this.pic_number=this.pic.length;
	}
	public void setDuration(int duration){//�]�w����
		this.duration=duration;
		this.count_unit=(pic_number*1.0)/(this.duration*1.0);
	}
	public void setPosition(int x,int y){//�]�w��m
		this.x=x;
		this.y=y;
	}
	public void start(int CurrentPosition){//�Ұ�(���]�w����)
		this.start_position=CurrentPosition;
		animax_flag=true;
	}
	public void start(){//�Ұ�(�L�]�w����)
		animax_flag=true;
		 animax_count_flag=0;	
	}
	public void pause(){//�Ȱ�
		pause_flag=true;
	}
	public void resume(){//�����Ȱ�
	pause_flag=false;		
	}
	public boolean getPause(){//���o�Ȱ����A
		return pause_flag;
	}
	public int getCount(){
		return (int)animax_count_flag;
	}
	public void drawEffect_time(int CurrentPosition,Canvas canvas,Paint paint){//ø��(���]�w����){
		if(animax_flag){
			animax_count_flag=count_unit*(CurrentPosition-start_position);
			if(((int)animax_count_flag)<pic_number){
				Graphic.drawPic(canvas, pic[((int)animax_count_flag)], x, y, 0, 255, paint);
			}else{
				animax_flag=false;
			}
		}
	}
	public void drawEffect(double speed,Canvas canvas,Paint paint){//ø��(�L�]�w����)
		if(animax_flag){
			if(!pause_flag){
			animax_count_flag+=speed;
			}
			if(((int)animax_count_flag)<pic_number){
				Graphic.drawPic(canvas, pic[((int)animax_count_flag)], x, y, 0, 255, paint);
			}else{
				animax_flag=false;
				animax_count_flag=0;
			}
		}
	}
	
	public Boolean getFlag(){
		return animax_flag;
	}
	
	//public int getCountFlag(){
		//return ((int)animax_count_flag);
	//}
	public void recycle(){
		for(int i=0;i<pic.length;i++){
			pic[i].recycle();
		}		
	}
}
