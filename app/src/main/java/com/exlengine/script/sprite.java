package com.exlengine.script;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.SparseArray;

/**
 * Created by Soldat on 2016/4/27.
 */
public class sprite{

    Bitmap sprite_base;
    int width,height;
    int width_singal_sprite,height_singal_sprite;
    SparseArray<Rect> rect_base;
    Rect rect_size;

    public sprite(Bitmap bit,int width_singal_sprite,int height_singal_sprite){
        sprite_base=bit;
        width=bit.getWidth();
        height=bit.getHeight();
        this.width_singal_sprite=width_singal_sprite;
        this.height_singal_sprite=height_singal_sprite;

    }
    public void setSize(int width,int height){

    }
    public void draw(){

    }
    public void recycle(){

    }
}