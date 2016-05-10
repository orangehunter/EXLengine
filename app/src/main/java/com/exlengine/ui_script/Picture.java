package com.exlengine.ui_script;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.exlengine.script.Graphic;
import com.exlengine.struct.Pic;

/**
 * Created by Soldat on 2016/5/1.
 */
public class Picture {
    Pic p;
    Bitmap bit;
    public Picture(Bitmap bit, int x, int y){
        this.bit=bit;
        p.x=x;
        p.y=y;
        p.width=bit.getWidth();
        p.hight=bit.getHeight();
        p.drawable=false;
    }
    public void addXY(int adx,int ady){
        p.x+=adx;
        p.y+=ady;
    }
    public void draw(Canvas canvas, Paint paint){
        if (p.drawable) {
            Graphic.drawPic(canvas, bit, p.x, p.y, p.rotation, p.alpha, paint);
        }
    }
}
