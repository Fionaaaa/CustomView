package com.example.fiona.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by fiona on 15-12-10.
 */
public class BallView extends View {
    int x;
    int y;
    int w;
    int h;
    Paint p;

    public BallView(Context context) {
        this(context,null);
    }

    public BallView(Context context, AttributeSet attrs) {
        super(context, attrs);
        p=new Paint();
        p.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(w==0){
            w=getWidth();
            h=getHeight();
            x=w/2;
            y=h/2;
        }
        canvas.drawCircle(x, y, 50, p);
    }

    public void move(int dx,int dy){
        x-=dx*2;
        y+=dy*2;
        if(x<25){
            x=25;
        }
        if(y<25){
            y=25;
        }
        if(x>w-25){
            x=w-25;
        }
        if(y>h){
            y=h-25;
        }
        invalidate();       //  重绘
    }

    public void move() {
        x+=5;
        if(x>w+25){
            x=-25;
        }
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        y= (int) event.getY();
        x= (int) event.getX();
        invalidate();
        return true;
    }
}
