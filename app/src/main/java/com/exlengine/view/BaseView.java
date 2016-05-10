package com.exlengine.view;




import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.exlengine.Constant;
import com.exlengine.MainActivity;

@SuppressLint({ "ViewConstructor", "WrongCall", "ClickableViewAccessibility" })
abstract class BaseView extends SurfaceView implements SurfaceHolder.Callback{

    SparseArray<PointF> mActivePointers=new SparseArray<PointF>();
    Paint paint;			//畫筆
    MainActivity activity;
    boolean deTouchJump=true;
    int pointerCount=0;

    public BaseView(MainActivity mainActivity) {
        super(mainActivity);
        this.activity = mainActivity;
        this.getHolder().addCallback(this);//設定生命周期回調接口的實現者
    }

    public abstract void onLoadData();

    @Override
    public void surfaceCreated(final SurfaceHolder holder) {
        paint = new Paint();//建立畫筆
        Constant.Flag=true;
        //=============螢幕刷新=================================================
        new Thread(){
            @SuppressLint("WrongCall")
            public void run()
            {
                while(Constant.Flag){
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    SurfaceHolder myholder=holder;
                    Canvas canvas = myholder.lockCanvas();//取得畫布
                    onDraw(canvas);
                    if(canvas != null){
                        myholder.unlockCanvasAndPost(canvas);
                    }
                }

            }
        }.start();
        //===========================================================================
    }
    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {//重新定義的繪制方法
        if(canvas!=null){
            super.onDraw(canvas);
            canvas.clipRect(new Rect(0,0,Constant.SCREEN_WIDTH,Constant.SCREEN_HIGHT));//只在螢幕範圍內繪制圖片
            canvas.drawColor(Color.WHITE);//界面設定為白色
            paint.setAntiAlias(true);	//開啟抗鋸齒

        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){//觸控事件
        pointerCount = event.getPointerCount();

        // get pointer index from the event object
        int pointerIndex = event.getActionIndex();

        // get pointer ID
        int pointerId = event.getPointerId(pointerIndex);

        switch(event.getActionMasked())
        {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN://按下
                PointF pointer = new PointF();
                pointer.x = event.getX(pointerIndex);
                pointer.y = event.getY(pointerIndex);
                mActivePointers.put(pointerId, pointer);

                break;
            case MotionEvent.ACTION_MOVE:  // a pointer was moved
                PointF point = mActivePointers.get(pointerId);
                if (point != null) {
                    point.x = event.getX(pointerId);
                    point.y = event.getY(pointerId);
                }
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:
                mActivePointers.remove(pointerId);
                break;
        }

        return true;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {

    }

    public void surfaceDestroyed(SurfaceHolder arg0)	{//銷毀時被呼叫

        Constant.Flag=false;
    }
}